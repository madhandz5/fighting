package ryan.fighting.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ryan.fighting.member.form.SignUpForm;
import ryan.fighting.member.validator.SignUpFormValidator;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/member")
@RequiredArgsConstructor
public class MemberController {

    private final SignUpFormValidator signUpFormValidator;
    private final MemberService memberService;

    @InitBinder("signUpForm")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(signUpFormValidator);
    }


    @PostMapping(value = "/sign-up")
    public String signUp(@Valid SignUpForm signUpForm, Model model) {
        Member newMember = memberService.signUp(signUpForm);
        model.addAttribute("newMember", newMember);
        System.out.println("newMember.getUsername() = " + newMember.getUsername());
        return "member/after-sign-up";
    }

    @GetMapping(value = "/sign-up")
    public String signUpForm(Model model) {
        model.addAttribute("signUpForm", new SignUpForm());
        return "member/sign-up-form";
    }


}
