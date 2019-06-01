package com.hzbank.redis.seckill;

import com.hzbank.redis.entity.Product;
import com.hzbank.redis.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SecKillService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ProductService productService;

    /**
     * 秒杀前一分钟读取库存到redis的zset集合中
     *
     */
    public void loadCount(Integer productId){
        Product product = productService.findOne(productId);
        redisTemplate.opsForZSet().add("seckill", productId, product.getCount());
        Integer userId=null;
        while (null != (userId= (Integer) redisTemplate.boundListOps(productId).rightPop())) {
            log.info("提前清空队列 " + userId);
        }
        log.info(product.getName()+" 库存加载完毕，数量-->"+product.getCount());
        popMQ(productId);
    }

    /**
     * 用户请求入队列
     * @param userId
     * @return
     */
    public void sendMsg(Integer productId,Integer userId) {

        //接受用户请求，从redis查询库存，判断是否放入redis的list队列中
        Double count = redisTemplate.opsForZSet().score("seckill", productId);
        if (count <= 0) {
            log.info("Sorry，活动太火爆了，被抢完了，稍等有用户未付款，您还有机会。"+userId);
            return;
        }
        //如果用户请求数大于商品数量，返回抢购失败
        Long  size= redisTemplate.opsForList().size(productId);
        if (size > count) {
            log.info("Sorry，活动太火爆了，被抢完了，稍等有用户未付款，您还有机会。"+userId);
            return;
        }
        //队列用rabbitMQ更好
        redisTemplate.opsForList().leftPush(productId, userId);
        log.info(userId+" 进入队列");

    }

    /**
     * 用户请求出队列，锁定库存
     * @return
     */
    public void popMQ(Integer productId) {

        while (true){
            //从队列中取出用户请求，从数据库查询库存，判断是否返回“秒杀失败”
            Long  size= redisTemplate.opsForList().size(productId);
            if (size > 0) {
                Integer userId1 = (Integer) redisTemplate.opsForList().rightPop(productId);
                Product product = productService.findOne(productId);
                if (product.getCount() <= 0) {
                    log.info( "Sorry，活动太火爆了，被抢完了，稍等有用户未付款，您还有机会。"+userId1);
                    return;
                }

                //锁定库存，同时修改redis的库存数量，返回抢购成功
                productService.decreCount(productId);//数据库库存递减
                redisTemplate.opsForZSet().incrementScore("seckill", productId, -1);
                log.info("抢购成功...... "+userId1);
            }
        }


    }
}
