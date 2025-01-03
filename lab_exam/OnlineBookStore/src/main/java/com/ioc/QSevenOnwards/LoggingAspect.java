package com.ioc.QSevenOnwards;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
  @After("execution(* com.itsc.OnlineBookStore.BookRegistrationServlet.*(..))")
    public void logAfterBookRegistration() {
        System.out.println("BookRegistrationServlet method called.");
    }
}
