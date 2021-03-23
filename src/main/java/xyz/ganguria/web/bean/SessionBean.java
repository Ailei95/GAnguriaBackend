package xyz.ganguria.web.bean;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@SessionScope
public class SessionBean {

    private static final AtomicInteger COUNTER = new AtomicInteger();
    private int id;

    public SessionBean() {
        this.id = COUNTER.addAndGet(1);
    }

    public int getId() {
        return this.id;
    }
}
