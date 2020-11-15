package codegen.cglib;

import codegen.cglib.domain.Member;
import codegen.cglib.domain.MemberServiceImpl;
import codegen.cglib.interceptor.MethodCallLogInterceptor;
import net.sf.cglib.proxy.Enhancer;

public class ProxyCallbackTest {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MemberServiceImpl.class);
        enhancer.setCallback(new MethodCallLogInterceptor());

        // proxy 생성
        Object proxy = enhancer.create();

        // proxy를 통해 접근
        MemberServiceImpl memberService = (MemberServiceImpl) proxy;
        memberService.register(new Member());
        memberService.getMember("madvirus");
    }
}
