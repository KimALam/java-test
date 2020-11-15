package codegen.cglib.domain;

public interface MemberService {
    void register(Member member);

    Member getMember(String id);
}
