package nill.service;

import java.util.List;

import nill.model.Torganization;
import nill.service.base.BaseServiceI;
import nill.utils.HqlFilter;

public interface OrganizationServiceI extends BaseServiceI<Torganization> {

	/**
	 * 更新机构
	 */
	public void updateOrganization(Torganization torganization);

	/**
	 * 机构授权
	 * 
	 * @param id
	 *            机构ID
	 * @param resourceIds
	 *            资源IDS
	 */
	public void grant(String id, String resourceIds);

	/**
	 * 查找机构
	 */
	public List<Torganization> findOrganizationByFilter(HqlFilter hqlFilter);

	/**
	 * 保存机构
	 * 
	 * @param data
	 *            机构信息
	 * @param id
	 *            用户ID
	 */
	public void saveOrganization(Torganization torganization, String userId);
}
