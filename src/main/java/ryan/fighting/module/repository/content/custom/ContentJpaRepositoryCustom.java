package ryan.fighting.module.repository.content.custom;

import ryan.fighting.module.domain.content.Content;

import java.util.List;

public interface ContentJpaRepositoryCustom {
    // 이게 원래 버전 Custom queryDsl
    List<Content> findByAll();
}
