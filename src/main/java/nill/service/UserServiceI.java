package nill.service;

import java.io.Serializable;

import nill.model.Tuser;
import nill.service.base.BaseServiceI;

/**
 * 用户业务
 * 
 * @author nill
 * 
 */

public interface UserServiceI extends BaseServiceI<Tuser> {

	public void test();
	public Serializable save(Tuser t);
	
}
