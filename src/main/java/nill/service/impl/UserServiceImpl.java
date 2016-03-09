package nill.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import nill.dao.base.BaseDaoI;
import nill.model.Torganization;
import nill.model.Trole;
import nill.model.Tuser;
import nill.service.UserServiceI;
import nill.service.base.impl.BaseServiceImpl;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<Tuser> implements UserServiceI {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private BaseDaoI<Tuser> baseDao;

	public void setBaseDao(BaseDaoI<Tuser> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void test() {
		// TODO Auto-generated method stub
		logger.info(" this is spring test will into the Spring");
	}

	@Override
	public Serializable save(Tuser t) {
		return baseDao.save(t);
	}
	
	
	//////////////////////////////////////
	
	@Autowired
	private BaseDaoI<Trole> roleDao;

	@Autowired
	private BaseDaoI<Torganization> organizationDao;

	@Override
	public void grantRole(String id, String roleIds) {
		Tuser user = getById(id);
		if (user != null) {
			user.setTroles(new HashSet<Trole>());
			for (String roleId : roleIds.split(",")) {
				if (!StringUtils.isBlank(roleId)) {
					Trole role = roleDao.getById(Trole.class, roleId);
					if (role != null) {
						user.getTroles().add(role);
					}
				}
			}
		}
	}

	@Override
	public void grantOrganization(String id, String organizationIds) {
	Tuser user = getById(id);
		if (user != null) {
			user.setTorganizations(new HashSet<Torganization>());
			for (String organizationId : organizationIds.split(",")) {
				if (!StringUtils.isBlank(organizationId)) {
					Torganization organization = organizationDao.getById(Torganization.class, organizationId);
					if (organization != null) {
						user.getTorganizations().add(organization);
					}
				}
			}
		}
	}

	@Override
	public List<Long> userCreateDatetimeChart() {
		List<Long> l = new ArrayList<Long>();
		int k = 0;
		for (int i = 0; i < 12; i++) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("a", k);
			params.put("b", k + 2);
			k = k + 2;
			l.add(count("select count(*) from Tuser t where HOUR(t.createdatetime)>=:a and HOUR(t.createdatetime)<:b", params));
		}
		return l;
	}

	@Override
	public Long countUserByRoleId(String roleId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", roleId);
		String hql = "select count(*) from Tuser t join t.troles role where role.id = :roleId";
		return count(hql, params);
	}

	@Override
	public Long countUserByNotRoleId() {
		String hql = "select count(*) from Tuser t left join t.troles role where role.id is null";
		return count(hql);
	}

	
}
