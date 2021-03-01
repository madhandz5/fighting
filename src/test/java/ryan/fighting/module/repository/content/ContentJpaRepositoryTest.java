package ryan.fighting.module.repository.content;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import ryan.fighting.module.domain.content.Content;
import ryan.fighting.module.domain.content.QContent;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ContentJpaRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    ContentJpaRepository contentJpaRepository;

    QContent qContent = QContent.content;

    @BeforeEach
    public void before() {
        Content content = new Content();
        content.setTitle("hello world");

        Content content2 = new Content();
        content2.setTitle("hello world2");

        em.persist(content);
        em.persist(content2);
        em.flush();
        System.out.println("\n === === === === === === === === === === === === === === \n");
    }

    @DisplayName("Content Test")
    @Test
    @Commit
    void findByTitle() {
        List<Content> contentList = contentJpaRepository.findByAll();

        for (Content content:contentList) {
            System.out.println(content);
        }
    }

    @Test
    public void querydslPredicateExec() throws Exception {
        Iterable<Content> result = contentJpaRepository.findAll(qContent.title.contains("hello"));

        for (Content content:result) {
            System.out.println(content);
        }
    }

    @AfterEach
    public void after() {
        System.out.println("\n === === === === === === === === === === === === === === \n");
    }
}