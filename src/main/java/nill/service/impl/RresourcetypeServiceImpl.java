package nill.service.impl;

import java.util.List;

import nill.model.Tresourcetype;
import nill.service.ResourcetypeServiceI;
import nill.service.base.impl.BaseServiceImpl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class RresourcetypeServiceImpl extends BaseServiceImpl<Tresourcetype> implements ResourcetypeServiceI {

	/**
	 * 为列表添加了缓存，查询一次过后，只要不重启服务，缓存一直存在，不需要再查询数据库了，节省了一些资源
	 * 
	 * 在ehcache.xml里面需要有对应的value
	 * 
	 * <cache name="TresourcetypeServiceCache"
	 * 
	 * key是自己设定的一个ID，用来标识缓存
	 */
	@Override
	@Cacheable(value = "TresourcetypeServiceCache", key = "'TresourcetypeList'")
	public List<Tresourcetype> findResourcetype() {
		return find();
	}

}
