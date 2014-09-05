package core.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class UppercaseHandler implements InvocationHandler {
    Object target;

    private UppercaseHandler(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        Object ret = method.invoke(target, args);
        if (ret instanceof String) {
            return ((String)ret).toUpperCase();
        }
        else {
            return ret;
        }
    }
}
