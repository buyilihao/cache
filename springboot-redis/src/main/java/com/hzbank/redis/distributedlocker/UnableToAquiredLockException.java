package com.hzbank.redis.distributedlocker;

/**
 * 异常类
 */
public class UnableToAquiredLockException extends RuntimeException {

    public UnableToAquiredLockException() {
    }

    public UnableToAquiredLockException(String message) {
        super(message);
    }

    public UnableToAquiredLockException(String message, Throwable cause) {
        super(message, cause);
    }
}
