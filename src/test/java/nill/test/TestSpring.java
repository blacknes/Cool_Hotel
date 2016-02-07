package nill.test;

import nill.service.UserServiceI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-hibernate.xml"})
public class TestSpring {
	
	@Autowired
	private UserServiceI userService;
	
	@Test
	public void test(){
//		Do Not Use This,
//		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[] { "classpath:spring.xml" });
//		UserServiceI userService = (UserServiceI) ac.getBean("userService");
		
		userService.test();
		
	}

}
