package exam.login.user;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import exam.login.model.UserVO;
import exam.login.service.ILoginService;
import exam.test.ServiceDaoTestConfig;

public class UserVoTest extends ServiceDaoTestConfig{

	@Autowired
	ILoginService loginService;
	
	/**
	* Method : userVotest
	* 작성자 : pc20
	* 변경이력 :
	* Method 설명 : 비밀번호 및 아이디 입력시 데이터에 있는 아이디 반환
	*/
	@Test
	public void userVotest() {
		
		UserVO userVo = loginService.selectUserByIdAndPassword("choi", "choi");
		
		assertEquals("choi", userVo.getUserId());
	}
	
}
