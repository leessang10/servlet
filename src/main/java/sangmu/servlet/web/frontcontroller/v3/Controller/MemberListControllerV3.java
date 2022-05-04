package sangmu.servlet.web.frontcontroller.v3.Controller;

import sangmu.servlet.domain.member.Member;
import sangmu.servlet.domain.member.MemberRepository;
import sangmu.servlet.web.frontcontroller.ModelView;
import sangmu.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {
    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();
        ModelView mv = new ModelView("members");
        mv.getModel().put("members", members);

        return mv;
    }
}
