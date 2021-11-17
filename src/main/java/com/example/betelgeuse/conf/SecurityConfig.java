package com.example.betelgeuse.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.betelgeuse.auth.DatabaseUserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //ユーザーの認証方式を決定するためのメソッド(メモリとデータベースで情報を管理する)
    //passwordは必ずハッシュ化された物で管理する(攻撃を受けたとき平文だと流出するため)
	@Autowired
	private DatabaseUserDetailsService UserDetailsService;
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.inMemoryAuthentication()
            .withUser("user").password(PasswordEncoder().encode("password")).roles("USER");*/
    	auth.userDetailsService(UserDetailsService);
    }

    //Webアプリケーションが管理しているリソースへのアクセス制御するためのメソッド
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/css/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin().loginPage("/login").usernameParameter("useid").passwordParameter("password").permitAll();
    }

    //平文のパスワードをハッシュ法で変換する(平文→ハッシュ)
    @Bean//←あらゆる場所から呼び出せるようにしている(依存性注入をして共通化させるための物)
    public PasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
