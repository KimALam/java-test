package codegen.cglib;

import codegen.cglib.domain.Member;
import codegen.cglib.domain.MemberServiceImpl;
import codegen.cglib.filter.MemberServiceCallbackFilter;
import codegen.cglib.interceptor.GetMemberInterceptor;
import codegen.cglib.interceptor.RegisterMemberInterceptor;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

public class ProxyMultiCallbacksTest {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MemberServiceImpl.class);
        Callback[] callbacks = new Callback[]{
                new RegisterMemberInterceptor(),
                new GetMemberInterceptor()
        };
        enhancer.setCallbacks(callbacks);
        enhancer.setCallbackFilter(new MemberServiceCallbackFilter());

        // proxy 생성
        Object proxy = enhancer.create();

        // proxy를 통해 접근
        MemberServiceImpl memberService = (MemberServiceImpl) proxy;
        memberService.register(new Member());
        memberService.getMember("madvirus");
    }
}
