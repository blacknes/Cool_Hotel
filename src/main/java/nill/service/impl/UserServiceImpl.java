package nill.service.impl;

import java.io.Serializable;

import nill.dao.base.BaseDaoI;
import nill.model.Tuser;
import nill.service.UserServiceI;
import nill.service.base.impl.BaseServiceImpl;

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
		logger.info(" this is spring test while into the Spring");
	}

	@Override
	public Serializable save(Tuser t) {
		return baseDao.save(t);
	}

	
}
