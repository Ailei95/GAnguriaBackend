package xyz.ganguria.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.ganguria.web.bean.SessionBean;

import java.util.Map;

@RestController
public class HelloController {

    private SessionBean sessionBean;

    @Autowired
    public HelloController(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    @RequestMapping("/api/hello")
    public String index(@RequestHeader Map<String, String> headers) {
        /*
        headers.forEach((key, value) -> {
            System.out.println("Header " + key + " Value " + value + " session " + sessionBean.getId());
        });
        */
        return "{ \"message\": \"Hello from Spring Boot!\" }";
    }

}
