package com.ruslan.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DaoException extends RuntimeException {

    public DaoException(Throwable throwable) {
        super(throwable);
        Logger logger = LogManager.getLogger(DaoException.class);
        logger.error(throwable);
    }
}
