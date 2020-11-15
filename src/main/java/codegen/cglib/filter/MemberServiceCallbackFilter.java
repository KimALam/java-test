package codegen.cglib.filter;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

public class MemberServiceCallbackFilter implements CallbackFilter {
    @Override
    public int accept(Method method) {
        if (method.getName().equals("register")) {
            // callback 등록 순서
            return 0;
        } else {
            return 1;
        }
    }
}
