package ryan.fighting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@EnableAspectJAutoProxy
@SpringBootApplication
@PropertySources({
        @PropertySource("classpath:/config/data.properties")
})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
