package ryan.fighting.infra.config.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // .allowedOrigins("http://localhost:3000" , "http://localhost:3001")
                .allowedOrigins("*")
                .allowedMethods("GET" , "POST" , "FETCH")
                // .allowCredentials(true)
                .maxAge(3600);
    }
}
