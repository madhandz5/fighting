package ryan.fighting.module.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "TB_MEMBER")
@SequenceGenerator(name = "SEQ_MEMBER", sequenceName = "SEQ_MEMBER", allocationSize = 30, initialValue = 1001)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MEMBER")
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String nickname;

    private String email;

    private String password;
}
