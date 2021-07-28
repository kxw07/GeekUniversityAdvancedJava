package others.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping("/prac")
public class PracController {

//    not works
    @RequestMapping("/a")
    public String a() {
        String result = "controller a...";
        System.out.println(result);
        return result;
    }

    @RequestMapping("/b")
    public @ResponseBody String b() {
        String result = "controller b...";
        System.out.println(result);
        return result;
    }

    @RequestMapping("/c/{cparam}")
    public @ResponseBody String c(@PathVariable("cparam") String cparam) {
        String result = "controller c... " + cparam;
        System.out.println(result);
        return result;
    }

    @RequestMapping("/d/*")
    public @ResponseBody String d() {
        String result = "controller d...";
        System.out.println(result);
        return result;
    }

//    http://localhost:8080/prac/e?e1=1
    @RequestMapping("/e")
    public @ResponseBody String e(@RequestParam(required = true) String e1, @RequestParam(required = false) String e2) {
        String result = "controller e... " + e1 + e2;
        System.out.println(result);
        return result;
    }
}
