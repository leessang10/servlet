package sangmu.servlet.web.frontcontroller.v3.Controller;

import sangmu.servlet.domain.member.Member;
import sangmu.servlet.domain.member.MemberRepository;
import sangmu.servlet.web.frontcontroller.ModelView;
import sangmu.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {
    private final MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member);
        return mv;

    }
}