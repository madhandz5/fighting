package ryan.fighting.module.repository.content;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ryan.fighting.module.domain.content.Content;
import ryan.fighting.module.repository.content.custom.ContentJpaRepositoryCustom;

import java.util.List;

public interface ContentJpaRepository extends JpaRepository<Content , Long> , ContentJpaRepositoryCustom , QuerydslPredicateExecutor<Content> {

    // JPA 는 자동으로 매핑
    List<Content> findByTitle(String title);
    
}
