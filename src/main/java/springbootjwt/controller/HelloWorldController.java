package springbootjwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {

    @GetMapping("/greeting")
    public String getGreeting() {
        return "Hello from the other side";
    }

    @GetMapping("/api/test")
    public String test() {
        return "If you see this, JWT authentication is working!";
    }

}
