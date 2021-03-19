package xyz.ganguria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class GAnguriaApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(GAnguriaApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder configure) {
        return configure.sources(GAnguriaApplication.class);
    }
}
