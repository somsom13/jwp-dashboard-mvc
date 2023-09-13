package webmvc.org.springframework.web.servlet.mvc.tobe;

import core.org.springframework.util.ReflectionUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import webmvc.org.springframework.web.servlet.ModelAndView;

public class HandlerExecution {

    private final Method method;

    public HandlerExecution(final Method method) {
        this.method = method;
    }

    public ModelAndView handle(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final Object targetObject = getObjectToRunMethod();

        return (ModelAndView) method.invoke(targetObject, request, response);
    }

    private Object getObjectToRunMethod() throws Exception {
        final Class<?> clazz = method.getDeclaringClass();
        return ReflectionUtils.accessibleConstructor(clazz).newInstance();
    }
}
