package qnmc;

import java.io.Serial;

public class ExceptionQuine extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public ExceptionQuine(String message) {
        super(message);
    }

    public ExceptionQuine(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionQuine(Throwable cause) {
        super(cause);
    }

}
