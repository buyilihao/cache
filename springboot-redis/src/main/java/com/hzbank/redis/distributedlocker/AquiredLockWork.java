package com.hzbank.redis.distributedlocker;

/**
 * 获取锁后要处理的逻辑
 */
public interface AquiredLockWork<T> {

    T invokeAfterLockAquired() throws Exception;
}
