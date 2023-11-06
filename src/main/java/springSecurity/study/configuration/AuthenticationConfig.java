package springSecurity.study.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import springSecurity.study.service.UserService;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class AuthenticationConfig {

    private final UserService userService;

    @Value("${jwt.secret}")
    private String secretKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .httpBasic().disable()//인증을 ui가 아닌 토큰으로 하기 때문에 비활성화
                .csrf().disable()
                .cors().and()
                .authorizeRequests()//요청에 접근권한을 확인하겠음.
                .antMatchers("/api/v1/users/join","/api/v1/users/login").permitAll()//로그인과 회원가입을 모두 접근 가능하게함.
                .antMatchers("/api/v1/**").authenticated()//로그인, 회원가입 빼고 모든 요청 막아놓음=>인증 필요로 만듬.
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtFilter(userService, secretKey), UsernamePasswordAuthenticationFilter.class)
                .build();

    }
}
