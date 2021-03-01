package ryan.fighting.module.repository.content.custom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import ryan.fighting.module.domain.content.Content;
import ryan.fighting.module.domain.content.QContent;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ContentQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final QContent qContent;

    public ContentQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
        this.qContent = QContent.content;
    }

    public List<Content> findByAll() {
        return queryFactory.selectFrom(qContent).fetch();
    }

}
