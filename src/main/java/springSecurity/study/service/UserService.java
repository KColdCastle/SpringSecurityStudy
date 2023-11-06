package springSecurity.study.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import springSecurity.study.utils.JwtUtil;

@Service
public class UserService {
    @Value("${jwt.secret}")
    private String secretKey;

    private Long expriedMs = 1000*60*60L;//1시간
    public String login(String userName, String password){
        //인증 과정 생략
        return JwtUtil.createJwt(userName, secretKey, expriedMs);
    }
}
