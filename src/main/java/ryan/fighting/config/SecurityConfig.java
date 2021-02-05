package ryan.fighting.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .mvcMatchers("/").permitAll()
                .antMatchers(HttpMethod.GET, "/member/sign-up").permitAll()
                .antMatchers(HttpMethod.POST, "/member/sign-up").permitAll()
                .antMatchers("/login").permitAll()

                .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated();

        httpSecurity.formLogin()
                .loginPage("/login");

        httpSecurity.logout()
                .logoutSuccessUrl("/");
    }

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring()
                .antMatchers("/css/**", "/js/**", "/images/**");
    }


}
