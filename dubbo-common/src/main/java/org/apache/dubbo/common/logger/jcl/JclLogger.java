package org.apache.dubbo.common.logger.jcl;

import org.apache.commons.logging.Log;
import org.apache.dubbo.common.logger.AbstractLogger;
import org.apache.dubbo.common.logger.Level;

public class JclLogger extends AbstractLogger {

    private final Log logger;

    public JclLogger(Log logger) {
        this.logger = logger;
    }

    @Override
    protected void log(Level level, String msg, Throwable e) {
        switch (level) {
            case TRACE:
                if (e != null) {
                    logger.trace(msg, e);
                } else {
                    logger.trace(msg);
                }
                break;
            case DEBUG:
                if (e != null) {
                    logger.debug(msg, e);
                } else {
                    logger.debug(msg);
                }
                break;
            case INFO:
                if (e != null) {
                    logger.info(msg, e);
                } else {
                    logger.info(msg);
                }
                break;
            case WARN:
                if (e != null) {
                    logger.warn(msg, e);
                } else {
                    logger.warn(msg);
                }
                break;
            case ERROR:
                if (e != null) {
                    logger.error(msg, e);
                } else {
                    logger.error(msg);
                }
                break;
        }
    }

    @Override
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    @Override
    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    @Override
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }
}
