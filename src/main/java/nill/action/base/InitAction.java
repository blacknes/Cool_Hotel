package nill.action.base;

import nill.model.dataModel.Json;
import nill.service.base.InitServiceI;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 初始化数据库使用
 *  不需要在 拷贝什么数据库
 *  
 * @author nill
 * 
 */
@Namespace("/")
@Action
public class InitAction extends BaseAction{

	@Autowired
	private InitServiceI service;

	synchronized public void doNotNeedSessionAndSecurity_initDb() {
		Json j = new Json();
		service.initDb();
		j.setSuccess(true);
		writeJson(j);

		if (getSession() != null) {
			getSession().invalidate();
		}
	}
}
