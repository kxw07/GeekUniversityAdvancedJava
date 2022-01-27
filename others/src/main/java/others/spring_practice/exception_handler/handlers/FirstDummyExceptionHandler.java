package others.spring_practice.exception_handler.handlers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import others.spring_practice.exception_handler.exceptions.FirstDummyException;

@ControllerAdvice
public class FirstDummyExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {FirstDummyException.class})
    public String handleException(Exception e) {
        return e + ", FirstDummyExceptionHandler";
    }
}
