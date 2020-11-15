package codegen.cglib;

import codegen.cglib.domain.Member;
import codegen.cglib.domain.MemberServiceImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

public class ProxyNoOpCallbackTest {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MemberServiceImpl.class);
        enhancer.setCallback(NoOp.INSTANCE);

        // proxy 생성
        Object proxy = enhancer.create();

        // proxy를 통해 접근
        MemberServiceImpl memberService = (MemberServiceImpl) proxy;
        memberService.register(new Member());
        memberService.getMember("madvirus");
    }
}
