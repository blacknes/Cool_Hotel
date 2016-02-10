package nill.service;

import java.util.List;

import nill.model.Trole;
import nill.service.base.BaseServiceI;
import nill.utils.HqlFilter;

public interface RoleServiceI extends BaseServiceI<Trole> {

	/**
	 * 查找角色
	 * 
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Trole> findRoleByFilter(HqlFilter hqlFilter, int page, int rows);

	/**
	 * 查找角色
	 */
	public List<Trole> findRoleByFilter(HqlFilter hqlFilter);

	/**
	 * 统计角色
	 * 
	 * @param hqlFilter
	 * @return
	 */
	public Long countRoleByFilter(HqlFilter hqlFilter);

	/**
	 * 添加一个角色
	 * 
	 * @param data
	 * @param userId
	 */
	public void saveRole(Trole syrole, String userId);

	/**
	 * 为角色授权
	 * 
	 * @param id
	 *            角色ID
	 * @param resourceIds
	 *            资源IDS
	 */
	public void grant(String id, String resourceIds);
}
