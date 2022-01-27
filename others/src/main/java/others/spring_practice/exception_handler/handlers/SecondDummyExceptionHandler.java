package others.spring_practice.exception_handler.handlers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import others.spring_practice.exception_handler.exceptions.SecondDummyException;

@ControllerAdvice
public class SecondDummyExceptionHandler {

    @ResponseBody
    @ExceptionHandler(SecondDummyException.class)
    public String handleException(Exception e) {
        return e + ", SecondDummyExceptionHandler";
    }
}
