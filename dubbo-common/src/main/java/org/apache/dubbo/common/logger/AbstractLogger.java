package org.apache.dubbo.common.logger;

public abstract class AbstractLogger implements Logger {

    @Override
    public void trace(String msg) {
        log(Level.TRACE, msg, null);
    }

    @Override
    public void trace(Throwable e) {
        log(Level.TRACE, e.getMessage(), e);
    }

    @Override
    public void trace(String msg, Throwable e) {
        log(Level.TRACE, msg, e);
    }

    @Override
    public void debug(String msg) {
        log(Level.DEBUG, msg, null);
    }

    @Override
    public void debug(Throwable e) {
        log(Level.DEBUG, e.getMessage(), e);
    }

    @Override
    public void debug(String msg, Throwable e) {
        log(Level.DEBUG, msg, e);
    }

    @Override
    public void info(String msg) {
        log(Level.INFO, msg, null);
    }

    @Override
    public void info(Throwable e) {
        log(Level.INFO, e.getMessage(), e);
    }

    @Override
    public void info(String msg, Throwable e) {
        log(Level.INFO, msg, e);
    }

    @Override
    public void warn(String msg) {
        log(Level.WARN, msg, null);
    }

    @Override
    public void warn(Throwable e) {
        log(Level.WARN, e.getMessage(), e);
    }

    @Override
    public void warn(String msg, Throwable e) {
        log(Level.WARN, msg, e);
    }

    @Override
    public void error(String msg) {
        log(Level.ERROR, msg, null);
    }

    @Override
    public void error(Throwable e) {
        log(Level.ERROR, e.getMessage(), e);
    }

    @Override
    public void error(String msg, Throwable e) {
        log(Level.ERROR, msg, e);
    }

    @Override
    public abstract boolean isTraceEnabled();

    @Override
    public abstract boolean isDebugEnabled();

    @Override
    public abstract boolean isInfoEnabled();

    @Override
    public abstract boolean isWarnEnabled();

    @Override
    public abstract boolean isErrorEnabled();

    protected abstract void log(Level level, String msg, Throwable e);
}
