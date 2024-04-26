package com.kohan81.springMVC;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

   @GetMapping("/hello-world")
    public String sayHello() {
        return "Hello WOrld !!";
        //вообще в этом методе мы можем перенаправлять пользователя,
       //обращаться к модели,
       //доставать данные из БД и показывать их пользователю
    }
}
