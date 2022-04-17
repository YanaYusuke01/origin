package com.jp.betelgeuse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jp.betelgeuse.auth.DatabaseUserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //ユーザーの認証方式を決定するためのメソッド(メモリとデータベースで情報を管理する)
    //passwordは必ずハッシュ化された物で管理する(攻撃を受けたとき平文だと流出するため)
	@Autowired
	private DatabaseUserDetailsService UserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(UserDetailsService).passwordEncoder(passwordEncoder());
    }

    //Webアプリケーションが管理しているリソースへのアクセス制御するためのメソッド
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        	.antMatchers("/css/**").permitAll()
			.antMatchers("/fonts/**").permitAll()
			.antMatchers("/vendor/**").permitAll()
			.antMatchers("/images/**").permitAll()
			.antMatchers("/js/**").permitAll()
			.antMatchers("/").permitAll()
			.antMatchers("/index").permitAll()
			.antMatchers("/regist").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin().loginPage("/login").usernameParameter("mail").permitAll()
            .defaultSuccessUrl("/complete").failureForwardUrl("/loginfail")
            .and()
            .logout()
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .permitAll();
    }

    //平文のパスワードをハッシュ法で変換する(平文→ハッシュ)
    @Bean//←あらゆる場所から呼び出せるようにしている(依存性注入をして共通化させるための物)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
