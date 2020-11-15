package codegen.cglib.interceptor;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MethodCallLogInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("Logging before MemberService call.");

        // 1. reflection으로 호출
//        Object returnVal = method.invoke(obj, args);

        // 2. 또는 cglib의 MethodProxy로 호출
        Object returnVal = methodProxy.invokeSuper(obj, args);

        System.out.println("Logging after MemberService call.");
        return returnVal;
    }
}
