package com.ruslan.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DaoException extends RuntimeException {
    private final Logger logger = LogManager.getLogger();

    public DaoException(Throwable throwable) {
        super(throwable);
        logger.error("Something went wrong" + throwable);
    }
}
