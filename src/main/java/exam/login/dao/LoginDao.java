package exam.login.dao;


import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import exam.login.model.UserVO;

@Repository
public class LoginDao implements ILoginDao{
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate template;
	/**
	* Method : selectUserByIdAndPassword
	* 작성자 : pc20
	* 변경이력 :
	* @param userId 유저 아이디
	* @param password 유저 비밀번호
	* @return 유저객체 반환
	* Method 설명 : 아이디 체크
	*/
	@Override
	public UserVO selectUserByIdAndPassword(UserVO userVo) {
		
		UserVO user = template.selectOne("userSQL.selectUserByIdAndPassword", userVo);
		if(user != null){
			return user;
		}
		
		return null;
	}
	
}
