package nill.cxf.demo;

import javax.jws.WebParam;
import javax.jws.WebService;

import nill.model.Tuser;

/**
 * WebService接口定义
 * 
 * @author nill
 * 
 */
@WebService
public interface WebServiceDemoI {

	public String helloWs(@WebParam(name = "userName") String name);

	public Tuser getUser(@WebParam(name = "userId") String id);

}
