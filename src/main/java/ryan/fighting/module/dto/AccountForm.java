package ryan.fighting.module.dto;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Getter
@ToString
public class AccountForm {

    @NotBlank
    @Length(min = 2, max = 8)
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣A-Za-z0-9_-]{2,8}$")
    private String name;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    @Column(unique = true)
    @Length(min = 2, max = 8)
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣A-Za-z0-9_-]{2,8}$")
    private String nickname;

    @NotBlank
    @Length(min = 8, max = 30)
    private String password;

}
