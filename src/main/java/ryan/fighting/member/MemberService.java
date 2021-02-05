package ryan.fighting.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ryan.fighting.member.form.SignUpForm;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member signUp(@Valid SignUpForm signUpForm) {
        Member member = Member.builder()
                .username(signUpForm.getUsername())
                .nickname(signUpForm.getNickname())
                .email(signUpForm.getEmail())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
                .build();
        return memberRepository.save(member);
    }
}
