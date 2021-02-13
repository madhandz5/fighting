package ryan.fighting.infra.config.springSecurity.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ryan.fighting.infra.config.springSecurity.certificationProvider.Token.TokenProvider;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class TokenAuthenticationFilter extends BasicAuthenticationFilter {

    private final TokenProvider tokenProvider;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
        super(authenticationManager);
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authToken = tokenProvider.validateToken(request);
        Map<String,Object> DecodeResult = tokenProvider.DecodeAccessJwt(authToken);
        Authentication authentication;

        int mode = Integer.parseInt(String.valueOf(DecodeResult.get("mode")));
        String email = String.valueOf(DecodeResult.get("email"));

        switch (mode){
            case 1:
                authentication = tokenProvider.getAuthentication(email , authToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                break;
            default:
                // 차후 비로그인 진입
                // if (urlpath = /cart/)
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(null,null));
                break;
        }

        chain.doFilter(request , response);
    }
}
