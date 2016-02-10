package nill.service;

import java.util.List;

import nill.model.Tresource;
import nill.service.base.BaseServiceI;
import nill.utils.HqlFilter;

public interface ResourceServiceI extends BaseServiceI<Tresource> {

	/**
	 * 获得资源树，或者combotree(只查找菜单类型的节点)
	 * 
	 * @return
	 */
	public List<Tresource> getMainMenu(HqlFilter hqlFilter);

	/**
	 * 获得资源treeGrid
	 * 
	 * @return
	 */
	public List<Tresource> resourceTreeGrid(HqlFilter hqlFilter);

	/**
	 * 更新资源
	 */
	public void updateResource(Tresource syresource);

	/**
	 * 保存一个资源
	 * 
	 * @param syresource
	 * @param userId
	 * @return
	 */
	public void saveResource(Tresource syresource, String userId);

	/**
	 * 查找符合条件的资源
	 */
	public List<Tresource> findResourceByFilter(HqlFilter hqlFilter);

}

