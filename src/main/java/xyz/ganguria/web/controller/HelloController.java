package xyz.ganguria.web.controller;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RestController
public class HelloController {

    @RequestMapping("/api/hello")
    public String index(@RequestHeader Map<String, String> headers) {
        headers.forEach((key, value) -> {
            System.out.println("Header " + key + " Value " + value);
        });

        return "{ \"message\": \"Hello from Spring Boot!\" }";
    }

}
