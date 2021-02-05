package ryan.fighting.member;

import lombok.*;
import ryan.fighting.article.Article;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Table(name = "TB_MEMBER")
@SequenceGenerator(name = "SEQ_MEMBER", sequenceName = "SEQ_MEMBER", initialValue = 1000, allocationSize = 30)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "member_id")
    private Long id;

    private String username;
    @Column(unique = true)
    private String nickname;
    @Column(unique = true)
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    private LocalDateTime joinedAt;

    private String generatedEmailToken;
    private LocalDateTime emailTokenGeneratedAt;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    private String profileImage;

    private boolean isExit = false;

    @OneToMany(mappedBy = "member")
    private List<Article> articleList = new ArrayList<>();

}
