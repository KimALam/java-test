package test.proxy;

public interface MemberService {
    void register(Member member);

    Member getMember(String id);
}
