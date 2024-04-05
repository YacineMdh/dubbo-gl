package org.apache.dubbo.common.threadpool;

import java.lang.instrument.Instrumentation;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MemoryLimiter {

    private final Instrumentation inst;
    private long memoryLimit;
    private final LongAdder memory = new LongAdder();
    private final ReentrantLock acquireLock = new ReentrantLock();
    private final Condition notLimited = acquireLock.newCondition();
    private final ReentrantLock releaseLock = new ReentrantLock();
    private final Condition notEmpty = releaseLock.newCondition();

    public MemoryLimiter(Instrumentation inst) {
        this(Integer.MAX_VALUE, inst);
    }

    public MemoryLimiter(long memoryLimit, Instrumentation inst) {
        if (memoryLimit <= 0) {
            throw new IllegalArgumentException();
        }
        this.memoryLimit = memoryLimit;
        this.inst = inst;
    }

    public void setMemoryLimit(long memoryLimit) {
        if (memoryLimit <= 0) {
            throw new IllegalArgumentException();
        }
        this.memoryLimit = memoryLimit;
    }

    public long getMemoryLimit() {
        return memoryLimit;
    }

    public long getCurrentMemory() {
        return memory.sum();
    }

    public long getCurrentRemainMemory() {
        return getMemoryLimit() - getCurrentMemory();
    }

    public boolean acquire(Object e) {
        if (e == null) {
            throw new NullPointerException();
        }
        if (memory.sum() >= memoryLimit) {
            return false;
        }
        acquireLock.lock();
        try {
            return doAcquire(e);
        } finally {
            acquireLock.unlock();
        }
    }

    public void acquireInterruptibly(Object e) throws InterruptedException {
        if (e == null) {
            throw new NullPointerException();
        }
        acquireLock.lockInterruptibly();
        try {
            doAcquireInterruptibly(e);
        } finally {
            acquireLock.unlock();
        }
    }

    public boolean acquire(Object e, long timeout, TimeUnit unit) throws InterruptedException {
        if (e == null) {
            throw new NullPointerException();
        }
        long nanos = unit.toNanos(timeout);
        acquireLock.lockInterruptibly();
        try {
            return doAcquire(e, nanos);
        } finally {
            acquireLock.unlock();
        }
    }

    public
