package com.jp.betelgeuse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jp.betelgeuse.auth.User;
import com.jp.betelgeuse.auth.UserRegistrationService;

/**
 * home画面用コントローラー
 *
 * @since 2022/04/16
 * @author betelgeuse kudouyuu
 */
@Controller
public class HomeController {

	@Autowired
	private UserRegistrationService userRegistrationService;

	@Autowired
	private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String home(){
        return "index";
    }
    @GetMapping("/index")
    public String index(){
        return "index";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/regist")
    public String regist(){
        return "regist";
    }
    @GetMapping("/complete")
    public String complete(){
        return "complete";
    }
    @PostMapping("/loginfail")
    public String loginfail(){
        return "loginfail";
    }
	@PostMapping("/regist")
	public String registration(@RequestParam("name_sei") String name_sei,
								@RequestParam("name_mei") String name_mei,
								@RequestParam("kana_sei") String kana_sei,
								@RequestParam("kana_mei") String kana_mei,
								@RequestParam("sex") String sex,
								@RequestParam("age") String age,
								@RequestParam("birthday_year") String birthday_year,
								@RequestParam("birthday_month") String birthday_month,
								@RequestParam("birthday_day") String birthday_day,
								@RequestParam("tel_1") String tel_1,
								@RequestParam("tel_2") String tel_2,
								@RequestParam("tel_3") String tel_3,
								@RequestParam("mail") String mail,
								@RequestParam("address") String address,
								@RequestParam("password") String password) {

		// 生年月日の編集
		String birthday = birthday_year + "/" + birthday_month + "/" + birthday_day;

		// 電話番号の編集
		String tel = tel_1 + "-" + tel_2 + "-" + tel_3;

		//＠todo：コントローラでセットするのはどうなのか？
		User user = new User();
		user.setName_sei(name_sei);
		user.setName_mei(name_mei);
		user.setKana_sei(kana_sei);
		user.setKana_mei(kana_mei);
		user.setSex(sex);
		user.setAge(age);
		user.setBirthday(birthday);
		user.setTel(tel);
		user.setMail(mail);
		user.setAddress(address);
		user.setPassword(passwordEncoder.encode(password));
		userRegistrationService.registUser(user);

		return "index";
	}
}