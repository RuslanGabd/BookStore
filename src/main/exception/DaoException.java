package exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DaoException extends RuntimeException {
    private static final Logger logger = LogManager.getLogger();

    public DaoException(Throwable throwable) {
        super(throwable);
    }
}
