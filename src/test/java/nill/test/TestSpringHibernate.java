package nill.test;

import java.util.Date;

import java.util.UUID;

import nill.model.Tuser;
import nill.service.UserServiceI;
import nill.utils.HqlFilter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-hibernate.xml", "classpath:spring-druid.xml" })
public class TestSpringHibernate {
	
	@Autowired
	private UserServiceI userService;
	
	@Test
	public void test(){
		
//		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"classpath:spring.xml","classpath:spring-hibernate.xml"});
//		UserServiceI userService = (UserServiceI) ac.getBean("userService");
		Tuser t = new Tuser();
		t.setId(UUID.randomUUID().toString());
		t.setName("nill");
		t.setLoginname("nill");
		t.setPwd("123456");
		t.setCreatedatetime(new Date());
		userService.save(t);
		
	}
	
	@Test
	@Transactional(readOnly = true)
	public void test2() {
		HqlFilter filter = new HqlFilter();
		userService.getByFilter(filter);
		userService.findByFilter(filter);
		userService.findByFilter(filter, 1, 10);
		userService.countByFilter(filter);
	}
}
