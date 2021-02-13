package ryan.fighting.module.controller.member;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ryan.fighting.module.service.member.MemberService;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
}
