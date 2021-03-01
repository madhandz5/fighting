package ryan.fighting.module.repository.content.custom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import ryan.fighting.module.domain.content.Content;
import ryan.fighting.module.domain.content.QContent;

import javax.persistence.EntityManager;
import java.util.List;

public class ContentJpaRepositoryImpl implements ContentJpaRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final QContent qContent;

    public ContentJpaRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
        this.qContent = QContent.content;
    }

    @Override
    public List<Content> findByAll() {
        return queryFactory.selectFrom(qContent).fetch();
    }
}
