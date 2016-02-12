package nill.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import nill.action.base.BaseAction;
import nill.model.Torganization;
import nill.model.Tuser;
import nill.model.dataModel.Json;
import nill.model.dataModel.SessionInfo;
import nill.service.OrganizationServiceI;
import nill.service.UserServiceI;
import nill.utils.ConfigUtil;
import nill.utils.HqlFilter;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 机构
 * 
 * 访问地址：/base/organization.action
 * 
 * @author nill
 * 
 */
@Namespace("/base")
@Action
public class OrganizationAction extends BaseAction<Torganization> {

	@Autowired
	private UserServiceI userService;

	/**
	 * 注入业务逻辑，使当前action调用service.xxx的时候，直接是调用基础业务逻辑
	 * 
	 * 如果想调用自己特有的服务方法时，请使用((TServiceI) service).methodName()这种形式强转类型调用
	 * 
	 * @param service
	 */
	@Autowired
	public void setService(OrganizationServiceI service) {
		this.service = service;
	}

	/**
	 * 保存一个机构
	 */
	public void save() {
		Json json = new Json();
		if (data != null) {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
			((OrganizationServiceI) service).saveOrganization(data, sessionInfo.getUser().getId());
			json.setSuccess(true);
		}
		writeJson(json);
	}

	/**
	 * 更新机构
	 */
	public void update() {
		Json json = new Json();
		if (!StringUtils.isBlank(data.getId())) {
			if (data.getTorganization() != null && StringUtils.equals(data.getId(), data.getTorganization().getId())) {
				json.setMsg("父机构不可以是自己！");
			} else {
				((OrganizationServiceI) service).updateOrganization(data);
				json.setSuccess(true);
			}
		}
		writeJson(json);
	}

	/**
	 * 获得机构下拉树
	 */
	public void doNotNeedSecurity_comboTree() {
		HqlFilter hqlFilter = new HqlFilter();
		writeJson(service.findByFilter(hqlFilter));
	}

	/**
	 * 机构授权
	 */
	public void grant() {
		Json json = new Json();
		((OrganizationServiceI) service).grant(id, ids);
		json.setSuccess(true);
		writeJson(json);
	}

	/**
	 * 获得当前用户能看到的所有机构树
	 */
	public void doNotNeedSecurity_getSyorganizationsTree() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		Tuser user = userService.getById(sessionInfo.getUser().getId());
		Set<Torganization> organizations = user.getTorganizations();
		List<Torganization> l = new ArrayList<Torganization>(organizations);
		Collections.sort(l, new Comparator<Torganization>() {// 排序
					@Override
					public int compare(Torganization o1, Torganization o2) {
						if (o1.getSeq() == null) {
							o1.setSeq(1000);
						}
						if (o2.getSeq() == null) {
							o2.setSeq(1000);
						}
						return o1.getSeq().compareTo(o2.getSeq());
					}
				});
		writeJson(l);
	}

	/**
	 * 获得当前用户的机构
	 */
	public void doNotNeedSecurity_getSyorganizationByUserId() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_user#id_S_EQ", id);
		List<Torganization> organizations = ((OrganizationServiceI) service).findOrganizationByFilter(hqlFilter);
		writeJson(organizations);
	}
}
