package ryan.fighting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ryan.fighting.member.CurrentMember;
import ryan.fighting.member.Member;
import ryan.fighting.member.MemberRepository;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MemberRepository memberRepository;

    @GetMapping(value = "/")
    public String index(@CurrentMember Member member, Model model) {
        if (member != null) {
            model.addAttribute(member);
            return "index-after-login";
        }
        return "index";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }
}
