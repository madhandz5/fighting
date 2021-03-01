package ryan.fighting.module.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ryan.fighting.module.repository.content.ContentJpaRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ContentService {

    private final ContentJpaRepository contentJpaRepository;


}
