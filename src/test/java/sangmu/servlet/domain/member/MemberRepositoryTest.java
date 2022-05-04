package sangmu.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        // 각 메소드가 수행 끝날 때마다 저장소를 비운다
        memberRepository.clearStore();
    }
    @Test
    @DisplayName("회원 저장")
    public void save() throws Exception {
        // given
        Member member = new Member("hello", 20);
        // when
        Member saveMember = memberRepository.save(member);
        // then
        Member findMember = memberRepository.findById(saveMember.getId());
        assertThat(findMember).isEqualTo(saveMember);
    }
    @Test
    @DisplayName("전체 회원 불러오기")
    public void findAll() throws Exception {
        // given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);
        memberRepository.save(member1);
        memberRepository.save(member2);
        // when
        List<Member> members = memberRepository.findAll();
        // then
        assertThat(members.size()).isEqualTo(2);
        assertThat(members).contains(member1, member2);
    }
}