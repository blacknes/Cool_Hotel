package nill.service;

import java.util.List;

import nill.model.Tresourcetype;
import nill.service.base.BaseServiceI;

public interface ResourcetypeServiceI extends BaseServiceI<Tresourcetype> {

	/**
	 * 获取所有资源类型
	 */
	public List<Tresourcetype> findResourcetype();
}
