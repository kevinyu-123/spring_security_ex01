package aloha.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration            // 이 클래스를 스프링 설정 빈으로 등록
@EnableWebSecurity         // 이 클래스에 스프링 시큐리티 기능 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	   return new BCryptPasswordEncoder();
	  }
	
   @Override
   protected void configure(HttpSecurity http) throws Exception {
      // 인가 
      // authorizeRequests()          : URL 요청에 대한  접근권한 설정
      // permitAll()               : 모두에게 접근 허용
      // hasRole(), hasAnyRoes()      : 특정권한을 가진 사용자만 허용
      http.authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/css/**", "/js/**", "/img/**").permitAll()
            .antMatchers("/guest/**").permitAll()
            .antMatchers("/member/**").hasAnyRole("USER", "ADMIN")
            .antMatchers("/admin/**").hasRole("ADMIN")
         .anyRequest().authenticated();
      
      // 로그인 설정
      // - default 로그인 화면 URL    	 : /login
      // - custom  로그인 화면 URl	 	 : /login/loginForm
      // - custom  로그인 에러 화면 URL    : /login/loginError
      http.formLogin()
          .permitAll();
      
      // 로그아웃 설정
      // - default 로그아웃 화면 URL : /logout
      http.logout()
         .permitAll();
      
   }
   
   @Autowired
   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
      // inMemory 방식으로 인증 사용자 등록
      // ID : user   /  pw : 1234   / 권한 : USER
      // ID :   admin  /  pw : 1234   / 권한 : ADMIN
      auth.inMemoryAuthentication()
         .withUser("user").password(passwordEncoder().encode("1234")).roles("USER")
         .and()
         .withUser("admin").password(passwordEncoder().encode("1234")).roles("ADMIN");
      
   }
   

   

}


