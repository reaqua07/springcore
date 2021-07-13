package com.sparta.springcore.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // 비밀번호를 암호화하기 위한 해쉬함수
    // 다른 클래스에서도 사용할 수 있도록 빈으로 등록
    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();

        http.authorizeRequests()

                // image 폴더를 login 없이 허용
                .antMatchers("/images/**").permitAll()
                // css 폴더를 login 없이 허용
                .antMatchers("/css/**").permitAll()
                // 회원 가입 화면 접근허용
                .antMatchers("/user/**").permitAll()
                // h2 페이지 접근 허용
                .antMatchers("/h2-console/**").permitAll()

                // 그 외 모든 요청은 인증과정 필요
                .anyRequest().authenticated() // 어떤 요청이 있든, 로그인이 안 되어 있으면 로그인 요청을 하겠다 // css 도 여기서 걸림
                .and()// 하지만
                .formLogin()// 로그인 페이지에 대해서는
                .loginPage("/user/login")   // 로그인 페이지 위치 지정
                .failureUrl("/user/login/error")    //  로그인이 실패할때의 페이지 위치 지정
                .defaultSuccessUrl("/") // 로그인이 완료되었을 때에는 홈으로 이동된다
                .permitAll()    // 접근을 허용한다
                .and()
                .logout()   //로그아웃 기능을
                .logoutUrl("/user/logout")  // user/logout 요청이 들어오면 controller 작업 필요없이 자동 로그아웃
                .permitAll()   // 허용한다
                .and()
                .exceptionHandling()    // 인가가 되지 않았을 때
                .accessDeniedPage("/user/forbidden");   // 포비든 페이지로 이동동
        }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}