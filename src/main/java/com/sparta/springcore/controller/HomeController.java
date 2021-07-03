package com.sparta.springcore.controller;

import com.sparta.springcore.security.UserDetailsImpl;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")            // 로그인한 사용자의 정보를 넘긴다
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
                                // index 가 뜨면 회원 아이디를 넘겨라, 타임리프
        model.addAttribute("username", userDetails.getUsername());
        return "index";
    }

    @Secured("ROLE_ADMIN") // 관리자만 이 권한을 사용할 수 있다고 표기
    // 관리자일때 보이는 홈페이지
    @GetMapping("/admin")
    public String admin(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("admin", true);
        return "index";
    }
}