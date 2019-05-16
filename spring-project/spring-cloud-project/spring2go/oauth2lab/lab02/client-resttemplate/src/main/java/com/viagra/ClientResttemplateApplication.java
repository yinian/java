package com.viagra;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
public class ClientResttemplateApplication implements ServletContextInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        /*
        sessionCookie的名字改名，以防冲突
         */
        servletContext.getSessionCookieConfig().setName("client-session");
    }

    public static void main(String[] args) {
        SpringApplication.run(ClientResttemplateApplication.class, args);
    }

}
