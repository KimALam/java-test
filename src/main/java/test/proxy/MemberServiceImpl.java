package test.proxy;

public class MemberServiceImpl implements MemberService {
    public MemberServiceImpl() {
        System.out.println("new MemberServiceImpl");
    }

    @Override
    public void register(Member member) {
        System.out.println("MemberServiceImpl | register.");
    }

    @Override
    public Member getMember(String id) {
        System.out.println("MemberServiceImpl | getMember.");
        return null;
    }
}
