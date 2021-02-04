package ryan.fighting.article;

import lombok.*;
import ryan.fighting.member.Member;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "TB_ARTICLE")
@SequenceGenerator(name = "SEQ_ARTICLE", sequenceName = "SEQ_ARTICLE", initialValue = 1000, allocationSize = 30)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "article_id")
    private Long id;

    private String subject;

    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
