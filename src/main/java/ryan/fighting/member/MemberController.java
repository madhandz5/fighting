package ryan.fighting.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ryan.fighting.member.form.SignUpForm;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "/sign-up")
    public String signUpForm(Model model) {
        model.addAttribute("signUpForm", new SignUpForm());
        return "member/sign-up";
    }

    @PostMapping(value = "/sign-up")
    public String signUp(@Valid SignUpForm signUpForm, ModelAndView model) {
        Member newMember = memberService.signUp(signUpForm);
        model.setViewName("member/after-sign-up");
        model.addObject("newMember", newMember);
        return "member/after-sign-up";
    }
}
