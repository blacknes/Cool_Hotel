package nill.cxf.demo.impl;

import javax.jws.WebService;

import nill.cxf.demo.WebServiceDemoI;
import nill.model.Tuser;
import nill.service.UserServiceI;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * WebService接口实现类
 * 
 * @author nill
 * 
 */
@WebService(endpointInterface = "nill.cxf.demo.WebServiceDemoI", serviceName = "webServiceDemo")
public class WebServiceDemoImpl implements WebServiceDemoI {

	@Autowired
	private UserServiceI userService;

	@Override
	public String helloWs(String name) {
		if (StringUtils.isBlank(name)) {
			name = "nill";
		}
		String str = "hello[" + name.trim() + "]WebService test ok!";
		System.out.println(str);
		return str;
	}

	@Override
	public Tuser getUser(String id) {
		if (StringUtils.isBlank(id)) {
			id = "0";
		}
		Tuser user = userService.getById(id.trim());
		Tuser u = new Tuser();
		u.setName(user.getName());
		u.setAge(user.getAge());
		u.setCreatedatetime(user.getCreatedatetime());
		u.setUpdatedatetime(user.getUpdatedatetime());
		u.setId(user.getId());
		u.setLoginname(user.getLoginname());
		u.setSex(user.getSex());
		u.setPhoto(user.getPhoto());
		return u;
	}

}
