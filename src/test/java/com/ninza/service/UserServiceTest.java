package com.ninza.service;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.ninza.avro.User;
import com.ninza.service.UserServiceImpl;

public class UserServiceTest {

	@Before
	public void setUp() throws Exception {
		File file = new File("./userData");
//		System.out.println(file.getAbsolutePath());
		file.delete();
	}

	@Test
	public void testSaveUser() {
		User user = new User();
		user.setAge(18);
		user.setEmail("ninza@gmail.com");
		user.setName("Ninza");
		
		UserServiceImpl service = new UserServiceImpl();
		String filePath = service.saveUser(user);
//		System.out.println(filePath);
		File isExist = new File(filePath);
		assert(isExist.exists());
	}

}
