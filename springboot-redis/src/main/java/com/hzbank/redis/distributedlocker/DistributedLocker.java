package com.hzbank.redis.distributedlocker;

/**
 * 获取锁管理类
 */
public interface DistributedLocker {

    <T> T lock(String keyLocker,AquiredLockWork<T> work) throws  UnableToAquiredLockException,Exception;

    <T> T lock(String keyLocker,AquiredLockWork<T> work,int lockTime) throws  UnableToAquiredLockException,Exception;
}
