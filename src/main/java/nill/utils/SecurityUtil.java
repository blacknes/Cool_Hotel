package nill.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import nill.model.Torganization;
import nill.model.Tresource;
import nill.model.Trole;
import nill.model.dataModel.SessionInfo;

import org.apache.commons.lang3.StringUtils;

/**
 * 用于前台页面判断是否有权限的工具类
 * 
 * @author nill
 * 
 */
public class SecurityUtil {
	private HttpSession session;

	public SecurityUtil(HttpSession session) {
		this.session = session;
	}

	/**
	 * 判断当前用户是否可以访问某资源
	 * 
	 * @param url
	 *            资源地址
	 * @return
	 */
	public boolean havePermission(String url) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		List<Tresource> resources = new ArrayList<Tresource>();
		for (Trole role : sessionInfo.getUser().getTroles()) {
			resources.addAll(role.getTresources());
		}
		for (Torganization organization : sessionInfo.getUser().getTorganizations()) {
			resources.addAll(organization.getTresources());
		}
		resources = new ArrayList<Tresource>(new HashSet<Tresource>(resources));// 去重(这里包含了当前用户可访问的所有资源)
		for (Tresource resource : resources) {
			if (StringUtils.equals(resource.getUrl(), url)) {// 如果有相同的，则代表当前用户可以访问这个资源
				return true;
			}
		}
		return false;
	}
}
