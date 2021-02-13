package ryan.fighting.infra.config.springSecurity;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ryan.fighting.infra.config.springSecurity.certificationProvider.Token.TokenProvider;
import ryan.fighting.infra.config.springSecurity.filter.TokenAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        super.configure(webSecurity);
        webSecurity.ignoring().antMatchers("/css**", "/js/**", "/img/**", "/lib/**", "/**");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                // 1. CSRF 프로텍션 disable - 서비스시 able 검토
                .csrf().disable()
                // 2. HTTP 인증 비활성화
                .httpBasic().disable()
                // 폼 기반 인증 비활성화
                .formLogin().disable()
                // stateless한 세션 정책 설정
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .mvcMatcher("/**")
                .authorizeRequests()
                .anyRequest().permitAll();

        httpSecurity.addFilterBefore(TokenAuthenticationFilter() , UsernamePasswordAuthenticationFilter.class);
        httpSecurity.headers().cacheControl();
    }

    @Bean
    TokenAuthenticationFilter TokenAuthenticationFilter() throws Exception {
        return new TokenAuthenticationFilter(super.authenticationManagerBean() ,new TokenProvider());
    }

}
