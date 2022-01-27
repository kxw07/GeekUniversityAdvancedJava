package others.spring_practice.exception_handler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import others.spring_practice.exception_handler.exceptions.FirstDummyException;
import others.spring_practice.exception_handler.exceptions.SecondDummyException;

@RestController
@RequestMapping("/error")
public class NormalController {

    @GetMapping("/first")
    public String getFirstDummy() throws FirstDummyException {
        System.out.println("getFirstDummy");
        throw new FirstDummyException("getFirstDummy");
    }

    @GetMapping("/second")
    public String getSecondDummy() throws SecondDummyException {
        System.out.println("getSecondDummy");
        throw new SecondDummyException("getSecondDummy");
    }
}
