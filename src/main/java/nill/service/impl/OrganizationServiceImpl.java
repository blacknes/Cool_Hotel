package nill.service.impl;

import java.util.HashSet;
import java.util.List;

import nill.dao.base.BaseDaoI;
import nill.model.Torganization;
import nill.model.Tresource;
import nill.model.Tuser;
import nill.service.OrganizationServiceI;
import nill.service.base.impl.BaseServiceImpl;
import nill.utils.BeanUtils;
import nill.utils.HqlFilter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 机构业务
 * 
 * @author nill
 * 
 */
@Service
public class OrganizationServiceImpl extends BaseServiceImpl<Torganization> implements OrganizationServiceI {

	@Autowired
	private BaseDaoI<Tresource> resourceDao;

	@Autowired
	private BaseDaoI<Tuser> userDao;

	@Override
	public void updateOrganization(Torganization torganization) {
		if (!StringUtils.isBlank(torganization.getId())) {
			Torganization t = getById(torganization.getId());
			Torganization oldParent = t.getTorganization();
			BeanUtils.copyNotNullProperties(torganization, t, new String[] { "createdatetime" });
			if (torganization.getTorganization() != null) {// 说明要修改的节点选中了上级节点
				Torganization pt = getById(torganization.getTorganization().getId());// 上级节点
				isParentToChild(t, pt, oldParent);// 说明要将当前节点修改到当前节点的子或者孙子下
				t.setTorganization(pt);
			} else {
				t.setTorganization(null);
			}
		}
	}

	/**
	 * 判断是否是将当前节点修改到当前节点的子节点下
	 * 
	 * @param t
	 *            当前节点
	 * @param pt
	 *            要修改到的节点
	 * 
	 * @param oldParent
	 *            原上级节点
	 * @return
	 */
	private boolean isParentToChild(Torganization t, Torganization pt, Torganization oldParent) {
		if (pt != null && pt.getTorganization() != null) {
			if (StringUtils.equals(pt.getTorganization().getId(), t.getId())) {
				pt.setTorganization(oldParent);
				return true;
			} else {
				return isParentToChild(t, pt.getTorganization(), oldParent);
			}
		}
		return false;
	}

	@Override
	public void grant(String id, String resourceIds) {
		Torganization organization = getById(id);
		if (organization != null) {
			organization.setTresources(new HashSet<Tresource>());
			for (String resourceId : resourceIds.split(",")) {
				if (!StringUtils.isBlank(resourceId)) {
					Tresource resource = resourceDao.getById(Tresource.class, resourceId);
					if (resource != null) {
						organization.getTresources().add(resource);
					}
				}
			}
		}
	}

	@Override
	public List<Torganization> findOrganizationByFilter(HqlFilter hqlFilter) {
		String hql = "select distinct t from Torganization t join t.tusers user";
		return find(hql + hqlFilter.getWhereAndOrderHql(), hqlFilter.getParams());
	}

	@Override
	public void saveOrganization(Torganization torganization, String userId) {
		save(torganization);

		Tuser user = userDao.getById(Tuser.class, userId);
		user.getTorganizations().add(torganization);
	}

	
}
