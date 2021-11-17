import org.apache.ibatis.type.MappedTypes;
import org.springframework.boot.SpringApplication;

import com.example.betelgeuse.auth.User;

@MappedTypes(User.class)
@MapperScan("com.example.betelgeuse.auth")
@SpringBootApplication

public class SpringBootApplication {

	public static void main(String [] aregs) {
		SpringApplication.run(SpringApplication.class, aregs)
	}
}
