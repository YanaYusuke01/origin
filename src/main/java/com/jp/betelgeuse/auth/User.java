package com.jp.betelgeuse.auth;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
@Data
public class User implements UserDetails {

	// 会員ID
	private String member_id;
	// 権限ID
	private List<String> auth_id;
	// 権限名
	private List<String> auth_name;
	// 会員名_姓
	private String name_sei;
	// 会員名_名
	private String name_mei;
	// 会員名_姓_かな
	private String kana_sei;
	// 会員名_名_かな
	private String kana_mei;
	// 会員名(姓＋名)
	private String username;
	// 性別
	private String sex;
	// 年齢
	private String age;
	// 生年月日
	private String birthday;
	// 電話番号
	private String tel;
	// メールアドレス
	private String mail;
	// 住所
	private String address;
	// 有効期限日
	private String limit_day;
	// 秘密の質問CD
	private String secret_cd;
	// 会員情報凍結フラグ
	private String cold_flg;
	// 有効期限フラグ
	private String limit_flg;
	// 削除フラグ
	private String delete_flg;
	// パスワード
	private String password;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return auth_id.stream()
					.map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());
	}
	public Collection<? extends GrantedAuthority>getAuthName() {
		return auth_name.stream()
					.map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());

	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		username = getMail();
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
