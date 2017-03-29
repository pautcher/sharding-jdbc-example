import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bz.open.sharding.ucenter.model.UserApplication;
import com.bz.open.sharding.ucenter.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:context-service.xml" })
public class GetUserApplication {

	@Autowired
	UserService userService;

	public void a() {

	}

	@Test
	public void testA() {
		UserApplication ua = userService.getUserApplication(1);
		System.out.println(ua);
	}

	private String gen() {
		return String.valueOf(Math.random() * 3).substring(2, 6);
	}

}
