package com.kohan81.springMVC;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

   @GetMapping("/hello")
    public String helloPage() {
        return "hello";
    }

    @GetMapping("/goodbye")
    public String goodbyePage() {
       return "goodbye";
    }
}
