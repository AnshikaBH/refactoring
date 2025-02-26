package qnmc;

import java.io.Serial;

public class ExceptionQuine extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    ExceptionQuine(String str) {

        super(str);

    }

}
