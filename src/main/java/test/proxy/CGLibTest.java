package test.proxy;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Method;

public class CGLibTest {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        // proxy 할 class 지정
        enhancer.setSuperclass(MemberServiceImpl.class);
//        enhancer.setCallback(NoOp.INSTANCE);
//        enhancer.setCallback(new MemberCallInterceptor());
        enhancer.setCallbacks(new Callback[] {
                new RegisterCallbackInterceptor(), // index 0
                new GetMemberCallbackInterceptor() // index 1
        });
        enhancer.setCallbackFilter(new MemberServiceCallbackFilter());

        // proxy 생성 및 접근
        MemberService obj = (MemberServiceImpl) enhancer.create();
        obj.register(new Member());
        obj.getMember("abcd");
    }

    static private class MemberCallInterceptor implements MethodInterceptor {
        @Override
        public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            System.out.println("MemberCallInterceptor | intercept start.");
            Object ret = methodProxy.invokeSuper(o, args);
            System.out.println("MemberCallInterceptor | intercept end.");
            return ret;
        }
    }

    static private class MemberServiceCallbackFilter implements CallbackFilter {
        @Override
        public int accept(Method method) {
            if (method.getName().equals("register")) {
                return 0; // call RegisterCallbackInterceptor.intercept()
            } else {
                return 1; // call GetMemberCallbackInterceptor.intercept()
            }
        }
    }

    static private class RegisterCallbackInterceptor implements MethodInterceptor {
        @Override
        public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            System.out.println("RegisterCallbackInterceptor | start.");
            Object ret = methodProxy.invokeSuper(o, args);
            System.out.println("RegisterCallbackInterceptor | end.");
            return ret;
        }
    }

    static private class GetMemberCallbackInterceptor implements MethodInterceptor {
        @Override
        public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            System.out.println("GetMemberCallbackInterceptor | start.");
            Object ret = methodProxy.invokeSuper(o, args);
            System.out.println("GetMemberCallbackInterceptor | end.");
            return ret;
        }
    }
}
