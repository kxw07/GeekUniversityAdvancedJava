package others.spring_practice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/prest")
public class PracRestController {

//    not works
    @RequestMapping("/a")
    public String a() {
        String result = "controller a...";
        System.out.println(result);
        return result;
    }

    @RequestMapping("/b")
    public ResponseEntity<String> b() {
        String result = "controller b...";
        System.out.println(result);
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }
}
