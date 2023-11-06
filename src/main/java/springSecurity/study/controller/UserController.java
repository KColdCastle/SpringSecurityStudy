package springSecurity.study.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springSecurity.study.domain.dto.LoginRequest;
import springSecurity.study.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor//서비스에 맞게 의존성 주입해줌
public class UserController {


    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest dto){

        return ResponseEntity.ok().body(userService.login(dto.getUserName(),dto.getPassword()));//여기에 아이디 비밀번호 받아야 함.
    }
}
