package codegen.cglib.domain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberServiceImpl implements MemberService {
    public MemberServiceImpl() {
        log.info("create MemberServiceImpl");
    }

    @Override
    public void register(Member member) {
        log.info("MemberServiceImpl.register");
    }

    @Override
    public Member getMember(String id) {
        log.info("MemberServiceImpl.getMember : {}", id);
        return new Member();
    }
}
