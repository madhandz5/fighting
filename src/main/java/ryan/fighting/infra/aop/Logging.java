package ryan.fighting.infra.aop;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logging {
    Logger logger = LoggerFactory.getLogger(Logging.class);
}
