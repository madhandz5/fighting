package ryan.fighting.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ryan.fighting.member.form.SignUpForm;

@Controller
@RequestMapping(value = "/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "/sign-up")
    public String signUpForm(Model model) {
        model.addAttribute("signUpForm",new SignUpForm());
        return "member/sign-up";
    }


}
