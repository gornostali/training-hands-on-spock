package me.igornostali.web;

import me.igornostali.web.config.ServerContextConfig;
import me.igornostali.web.rest.UserRestService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DemoApp extends ResourceConfig {

    public DemoApp() {
        final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.register(ServerContextConfig.class);
        ctx.refresh();

        register(ctx.getBean(UserRestService.class));
    }
}