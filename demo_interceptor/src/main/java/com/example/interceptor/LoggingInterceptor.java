package com.example.interceptor;

import com.example.annotation.Loggable;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.jboss.logging.Logger;

@Loggable
@Interceptor
public class LoggingInterceptor {
    private static final Logger LOGGER = Logger.getLogger(LoggingInterceptor.class);

    @AroundInvoke
    public Object loggingMethodCall(InvocationContext context) throws Exception {
        LOGGER.info("Start logging method "+ context.getMethod().getName());
        LOGGER.info("argument "+ context.getParameters());
        Object result = context.proceed();
        LOGGER.info("End logging method ");
        return  result;
    }
}
