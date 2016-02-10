package nill.interceptor.base;

import java.util.Set;

import nill.model.Torganization;
import nill.model.Tresource;
import nill.model.Trole;
import nill.model.dataModel.SessionInfo;
import nill.utils.ConfigUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 权限拦截器
 * 
 * @author nill
 * 
 */
public class SecurityInterceptor extends MethodFilterInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(SecurityInterceptor.class);

	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		//ActionContext actionContext = actionInvocation.getInvocationContext();
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ConfigUtil.getSessionInfoName());
		String servletPath = ServletActionContext.getRequest().getServletPath();

		servletPath = StringUtils.substringBeforeLast(servletPath, ".");// 去掉后面的后缀 *.do *.action之类的

		logger.info("进入权限拦截器->访问的资源为：[" + servletPath + "]");

		Set<Trole> roles = sessionInfo.getUser().getTroles();
		for (Trole role : roles) {
			for (Tresource resource : role.getTresources()) {
				if (resource != null && StringUtils.equals(resource.getUrl(), servletPath)) {
					return actionInvocation.invoke();
				}
			}
		}
		Set<Torganization> organizations = sessionInfo.getUser().getTorganizations();
		for (Torganization organization : organizations) {
			for (Tresource resource : organization.getTresources()) {
				if (resource != null && StringUtils.equals(resource.getUrl(), servletPath)) {
					return actionInvocation.invoke();
				}
			}
		}

		String errMsg = "您没有访问此功能的权限！功能路径为[" + servletPath + "]请联系管理员给你赋予相应权限。";
		logger.info(errMsg);
		ServletActionContext.getRequest().setAttribute("msg", errMsg);
		return "noSecurity";
	}

}
