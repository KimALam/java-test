package codegen.cglib.interceptor;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class RegisterMemberInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("[RegisterMemberInterceptor] before calling register...");
        Object returnVal = methodProxy.invokeSuper(obj, args);
        System.out.println("[RegisterMemberInterceptor] after calling register...");
        return returnVal;
    }
}
