package nill.model.dataModel;

import nill.model.Tuser;

/**
 * sessionInfo模型，只要登录成功，就需要设置到session里面，便于系统使用
 * 
 * @author nill
 * 
 */
public class SessionInfo implements java.io.Serializable {

	private Tuser user;

	public Tuser getUser() {
		return user;
	}

	public void setUser(Tuser user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return user.getLoginname();
	}

}
