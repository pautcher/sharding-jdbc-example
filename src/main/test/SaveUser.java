import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bz.open.sharding.ucenter.model.User;
import com.bz.open.sharding.ucenter.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:context-service.xml" })
public class SaveUser {

	@Autowired
	UserService userService;

	public void saveUser() {

		User u = new User();
		u.setAvatar("");
		u.setCreateTime(new Date());
		u.setId(101);
		u.setMobile("");
		u.setNickname("");
		u.setStatus(1);
		u.setUid(DigestUtils.md5Hex("" + System.currentTimeMillis() + gen()));
		userService.save(u);
	}

	@Test
	public void testA() {
		saveUser();
	}

	private String gen() {
		return String.valueOf(Math.random() * 3).substring(2, 6);
	}

}
