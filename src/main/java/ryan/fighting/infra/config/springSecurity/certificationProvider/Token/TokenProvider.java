package ryan.fighting.infra.config.springSecurity.certificationProvider.Token;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.*;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ryan.fighting.module.domain.Account;
import ryan.fighting.module.service.member.MemberService;

import javax.servlet.http.HttpServletRequest;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@NoArgsConstructor
@ToString
public class TokenProvider {

    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";
    private static final String ISSUER = "SuggestyProd";

    @Value("${UserSecretKey}")
    private String secretKey;

    @Autowired
    private MemberService memberService;

//    @Autowired
//    private VisitorService visitorService;

    private JWTVerifier JwtDecoder;

    @Autowired
    public TokenProvider(MemberService memberService) {
        Algorithm algorithm = Algorithm.HMAC256(this.secretKey);
        this.JwtDecoder = JWT.require(algorithm).withIssuer(ISSUER).build();
    }

    public Authentication getAuthentication(String email , String authToken) {
        // 로그인 인증처리
        Account account = (Account) memberService.loadUserByUsername(email);

        SimpleGrantedAuthority grant = new SimpleGrantedAuthority(account.getNickname());
        List<GrantedAuthority> result = new ArrayList<>();
        result.add(grant);

        // Authention token을 보냄.
        return new UsernamePasswordAuthenticationToken(account,authToken,result);
    }


    public String validateToken(HttpServletRequest request){

        String authHeaderToken = request.getHeader(HEADER_AUTHORIZATION);

        // 헤더에 인증태그가 붙어있는 경우 반환
        // 모형 Bearer sgmkasdmgrigjirajglid
        if(authHeaderToken != null && authHeaderToken.contains(BEARER)){
            // token 반환 - 반환값은 jwt
            String resultToken = authHeaderToken.replace(BEARER , "").trim();
            return resultToken;
        }

        return null;
    }

    // Jwt token 해제
    public Map<String,Object> DecodeAccessJwt(String authToken){
        Map<String , Object> command = new HashMap<>();
        try {
            DecodedJWT decodedJWT = JwtDecoder.verify(authToken);

            command.put("mode" , 1);
            command.put("value" , decodedJWT);
        }
        catch (TokenExpiredException te){
            command.put("mode" , 2);
            command.put("value" , "ERR_TOKEN_EXPIRED");
        }
        catch (JWTVerificationException ve) {
            //Invalid signature/claims
            command.put("mode" , 3);
            command.put("value" , "ERR_INVALID_SIGNATURE_OR_CLAIM");
        } catch (Exception e){
            command.put("mode" , 4);
            command.put("value" , "FAILED_DECODE");
        }
        return command;
    }

    public Object MakeAuthToken(String email) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        String token = JWT.create()
                .withIssuer(ISSUER)
                .withClaim("email", email)
                // 현재 plusDay 1 -> 하루 기한.
                .withExpiresAt(Date.from(ZonedDateTime.now(ZoneId.systemDefault()).plusDays(1).toInstant()))
                .sign(algorithm);
        return token;
    }
}
