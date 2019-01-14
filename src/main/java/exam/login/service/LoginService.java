package exam.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exam.login.dao.ILoginDao;
import exam.login.model.UserVO;
import exam.util.encrypt.KISA_SHA256;

@Service
public class LoginService implements ILoginService{
	
	@Autowired
	private ILoginDao dao;
	
	@Override
	public UserVO selectUserByIdAndPassword(String userId, String password) {
		UserVO userVo = new UserVO();
		userVo.setUserId(userId);
		
		//암호 적용
		password =  KISA_SHA256.encrypt(password);
		userVo.setPassword(password);
		
		return dao.selectUserByIdAndPassword(userVo);
	}
	
	
	
}
