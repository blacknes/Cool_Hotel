package nill.service.base.impl;

import java.util.HashSet;
import java.util.List;

import nill.dao.base.BaseDaoI;
import nill.model.Tintake;
import nill.model.Torganization;
import nill.model.Tresource;
import nill.model.Tresourcetype;
import nill.model.Trole;
import nill.model.Troom;
import nill.model.Tuser;
import nill.service.base.InitServiceI;
import nill.utils.MD5Util;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class InitServiceImpl implements InitServiceI {
	
	private static final Logger logger = Logger.getLogger(InitServiceImpl.class);
	private static final String FILEPATH = "initDataBase.xml";

	@Autowired
	private BaseDaoI baseDao;
	
	@Override
	synchronized public void initDb() {
		// TODO Auto-generated method stub
		try {
			Document document = new SAXReader().read(Thread.currentThread().getContextClassLoader().getResourceAsStream(FILEPATH));
			
			initResourcetype(document);// 初始化资源类型

			initResource(document);// 初始化资源

			initRole(document);// 初始化角色

			initRoleResource(document);// 初始化角色和资源的关系

			initOrganization(document);// 初始化机构

			initOrganizationResource(document);// 初始化机构和资源的关系

			initUser(document);// 初始化用户

			initUserRole(document);// 初始化用户和角色的关系

			initUserOrganization(document);// 初始化用户和机构的关系
			
			initRoom(document);
			
			initIntake(document); // 初始化入住信息
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	private void initResourcetype(Document document) {
		List childNodes = document.selectNodes("//resourcetypes/resourcetype");
		for (Object obj : childNodes) {
			Node node = (Node) obj;
			Tresourcetype type = (Tresourcetype) baseDao.getById(Tresourcetype.class, node.valueOf("@id"));
			if (type == null) {
				type = new Tresourcetype();
			}
			type.setId(node.valueOf("@id"));
			type.setName(node.valueOf("@name"));
			type.setDescription(node.valueOf("@description"));
			logger.info(JSON.toJSONStringWithDateFormat(type, "yyyy-MM-dd HH:mm:ss"));
			baseDao.saveOrUpdate(type);
		}
	}

	private void initResource(Document document) {
		List childNodes = document.selectNodes("//resources/resource");
		for (Object obj : childNodes) {
			Node node = (Node) obj;
			Tresource resource = (Tresource) baseDao.getById(Tresource.class, node.valueOf("@id"));
			if (resource == null) {
				resource = new Tresource();
			}
			resource.setId(node.valueOf("@id"));
			resource.setName(node.valueOf("@name"));
			resource.setUrl(node.valueOf("@url"));
			resource.setDescription(node.valueOf("@description"));
			resource.setIconCls(node.valueOf("@iconCls"));
			resource.setSeq(Integer.parseInt(node.valueOf("@seq")));

			if (!StringUtils.isBlank(node.valueOf("@target"))) {
				resource.setTarget(node.valueOf("@target"));
			} else {
				resource.setTarget("");
			}

			if (!StringUtils.isBlank(node.valueOf("@pid"))) {
				resource.setTresource((Tresource) baseDao.getById(Tresource.class, node.valueOf("@pid")));
			} else {
				resource.setTresource(null);
			}

			Node n = node.selectSingleNode("resourcetype");
			Tresourcetype type = (Tresourcetype) baseDao.getById(Tresourcetype.class, n.valueOf("@id"));
			if (type != null) {
				resource.setTresourcetype(type);
			}

			logger.info(JSON.toJSONStringWithDateFormat(resource, "yyyy-MM-dd HH:mm:ss"));
			baseDao.saveOrUpdate(resource);
		}
	}

	private void initRole(Document document) {
		List childNodes = document.selectNodes("//roles/role");
		for (Object obj : childNodes) {
			Node node = (Node) obj;
			Trole role = (Trole) baseDao.getById(Trole.class, node.valueOf("@id"));
			if (role == null) {
				role = new Trole();
			}
			role.setId(node.valueOf("@id"));
			role.setName(node.valueOf("@name"));
			role.setDescription(node.valueOf("@description"));
			role.setSeq(Integer.parseInt(node.valueOf("@seq")));
			logger.info(JSON.toJSONStringWithDateFormat(role, "yyyy-MM-dd HH:mm:ss"));
			baseDao.saveOrUpdate(role);
		}
	}

	private void initRoleResource(Document document) {
		List<Node> childNodes = document.selectNodes("//roles_resources/role");
		for (Node node : childNodes) {
			Trole role = (Trole) baseDao.getById(Trole.class, node.valueOf("@id"));
			if (role != null) {
				role.setTresources(new HashSet());
				List<Node> cNodes = node.selectNodes("resource");
				for (Node n : cNodes) {
					Tresource resource = (Tresource) baseDao.getById(Tresource.class, n.valueOf("@id"));
					if (resource != null) {
						role.getTresources().add(resource);
					}
				}
				logger.info(JSON.toJSONStringWithDateFormat(role, "yyyy-MM-dd HH:mm:ss"));
				baseDao.saveOrUpdate(role);
			}
		}

		Trole role = (Trole) baseDao.getById(Trole.class, "0");// 将角色为0的超级管理员角色，赋予所有权限
		if (role != null) {
			role.getTresources().addAll(baseDao.find("from Tresource t"));
			logger.info(JSON.toJSONStringWithDateFormat(role, "yyyy-MM-dd HH:mm:ss"));
			baseDao.saveOrUpdate(role);
		}
	}

	private void initOrganization(Document document) {
		List childNodes = document.selectNodes("//organizations/organization");
		for (Object obj : childNodes) {
			Node node = (Node) obj;
			Torganization organization = (Torganization) baseDao.getById(Torganization.class, node.valueOf("@id"));
			if (organization == null) {
				organization = new Torganization();
			}
			organization.setId(node.valueOf("@id"));
			organization.setName(node.valueOf("@name"));
			organization.setAddress(node.valueOf("@address"));
			organization.setSeq(Integer.parseInt(node.valueOf("@seq")));
			organization.setIconCls(node.valueOf("@iconCls"));

			if (!StringUtils.isBlank(node.valueOf("@pid"))) {
				organization.setTorganization((Torganization) baseDao.getById(Torganization.class, node.valueOf("@pid")));
			} else {
				organization.setTorganization(null);
			}

			logger.info(JSON.toJSONStringWithDateFormat(organization, "yyyy-MM-dd HH:mm:ss"));
			baseDao.saveOrUpdate(organization);
		}
	}

	private void initOrganizationResource(Document document) {
		List<Node> childNodes = document.selectNodes("//organizations_resources/organization");
		for (Node node : childNodes) {
			Torganization organization = (Torganization) baseDao.getById(Torganization.class, node.valueOf("@id"));
			if (organization != null) {
				organization.setTresources(new HashSet());
				List<Node> cNodes = node.selectNodes("resource");
				for (Node n : cNodes) {
					Tresource resource = (Tresource) baseDao.getById(Tresource.class, n.valueOf("@id"));
					if (resource != null) {
						organization.getTresources().add(resource);
					}
				}
				logger.info(JSON.toJSONStringWithDateFormat(organization, "yyyy-MM-dd HH:mm:ss"));
				baseDao.saveOrUpdate(organization);
			}
		}
	}

	private void initUser(Document document) {
		List<Node> childNodes = document.selectNodes("//users/user");
		for (Node node : childNodes) {
			Tuser user = (Tuser) baseDao.getById(Tuser.class, node.valueOf("@id"));
			if (user == null) {
				user = new Tuser();
			}
			user.setId(node.valueOf("@id"));
			user.setName(node.valueOf("@name"));
			user.setLoginname(node.valueOf("@loginname"));
			user.setPwd(MD5Util.md5(node.valueOf("@pwd")));
			user.setSex(node.valueOf("@sex"));
			user.setAge(Integer.valueOf(node.valueOf("@age")));
			logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
			List<Tuser> ul = baseDao.find("from Tuser u where u.loginname = '" + user.getLoginname() + "' and u.id != '" + user.getId() + "'");
			for (Tuser u : ul) {
				baseDao.delete(u);
			}
			baseDao.saveOrUpdate(user);
		}
	}

	private void initUserRole(Document document) {
		List<Node> childNodes = document.selectNodes("//users_roles/user");
		for (Node node : childNodes) {
			Tuser user = (Tuser) baseDao.getById(Tuser.class, node.valueOf("@id"));
			if (user != null) {
				user.setTroles(new HashSet());
				List<Node> cNodes = node.selectNodes("role");
				for (Node n : cNodes) {
					Trole role = (Trole) baseDao.getById(Trole.class, n.valueOf("@id"));
					if (role != null) {
						user.getTroles().add(role);
					}
				}
				logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
				baseDao.saveOrUpdate(user);
			}
		}

		Tuser user = (Tuser) baseDao.getById(Tuser.class, "0");// 用户为0的超级管理员，赋予所有角色
		if (user != null) {
			user.getTroles().addAll(baseDao.find("from Trole"));
			logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
			baseDao.saveOrUpdate(user);
		}
	}

	private void initUserOrganization(Document document) {
		List<Node> childNodes = document.selectNodes("//users_organizations/user");
		for (Node node : childNodes) {
			Tuser user = (Tuser) baseDao.getById(Tuser.class, node.valueOf("@id"));
			if (user != null) {
				user.setTorganizations(new HashSet());
				List<Node> cNodes = node.selectNodes("organization");
				for (Node n : cNodes) {
					Torganization organization = (Torganization) baseDao.getById(Torganization.class, n.valueOf("@id"));
					if (organization != null) {
						user.getTorganizations().add(organization);
					}
				}
				logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
				baseDao.saveOrUpdate(user);
			}
		}

		Tuser user = (Tuser) baseDao.getById(Tuser.class, "0");// 用户为0的超级管理员，赋予所有机构
		if (user != null) {
			user.getTorganizations().addAll(baseDao.find("from Torganization"));
			logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
			baseDao.saveOrUpdate(user);
		}
	}
	
	private void initRoom(Document document){
		List<Node> childNodes = document.selectNodes("//rooms/room");
		for (Node node : childNodes) {
			Troom room = (Troom) baseDao.getById(Troom.class, node.valueOf("@id"));
			if (room == null) {
				room = new Troom();
			}
			room.setId(node.valueOf("@id"));
			room.setRoomName(node.valueOf("@roomName"));
			room.setRoomProperty(node.valueOf("@roomProperty"));
			room.setRoomStatus(node.valueOf("@roomStatus"));
			room.setCanCancel(node.valueOf("@canCancel"));
			room.setRoomPrice(node.valueOf("@roomPrice"));

			logger.info(JSON.toJSONStringWithDateFormat(room, "yyyy-MM-dd HH:mm:ss"));
			List<Troom> ul = baseDao.find("from Troom r where r.roomName = '" + room.getRoomName() + "' and r.id != '" + room.getId() + "'");
			for (Troom r : ul) {
				baseDao.delete(r);
			}
			baseDao.saveOrUpdate(room);
		}
	}
	
	private void initIntake(Document document){
		List<Node> childNodes = document.selectNodes("//intakes/intake");
		for (Node node : childNodes) {
			Tintake intake = (Tintake) baseDao.getById(Tintake.class, node.valueOf("@id"));
			if (intake == null) {
				intake = new Tintake();
			}
			intake.setId(node.valueOf("@id"));
			intake.setName(node.valueOf("@name"));
			intake.setPhone(node.valueOf("@phone"));
			intake.setEmail(node.valueOf("@email"));
			intake.setInDate(node.valueOf("@inDate"));
			intake.setOutDate(node.valueOf("@outDate"));
			intake.setRoomCount(Integer.parseInt(node.valueOf("@roomCount")));

			logger.info(JSON.toJSONStringWithDateFormat(intake, "yyyy-MM-dd HH:mm:ss"));
			List<Tintake> ul = baseDao.find("from Tintake r where r.name = '" + intake.getName() + "' and r.id != '" + intake.getId() + "'");
			for (Tintake r : ul) {
				baseDao.delete(r);
			}
			baseDao.saveOrUpdate(intake);
		}
	}


}
