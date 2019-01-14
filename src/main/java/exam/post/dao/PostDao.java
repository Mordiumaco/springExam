package exam.post.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import exam.post.model.PageVO;
import exam.post.model.PostVO;

@Repository
public class PostDao implements IPostDao{
	
	@Resource(name="sqlSessionTemplate")
	SqlSessionTemplate template;
	
	/**
	* Method : nowPostNumber
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 : 현재 게시물 번호를 알수 있다.
	*/
	@Override
	public int nowPostNumber() {
		
		Integer nowPostCode = template.selectOne("postSQL.nowPostNumber");
		if(nowPostCode != null){
			return nowPostCode;
		}
		
		return -1;
	}

	/**
	* Method : inquiryUp
	* 작성자 : pc20
	* 변경이력 :
	* @param boardCode
	* @return
	* Method 설명 : 조회수 증가
	*/
	@Override
	public int inquiryUp(String postCode) {
		
		int result = template.update("postSQL.inquiryUp", postCode);
		if(result != -1){
			return result;
		}
		
		return -1;
	}

	/**
	* Method : selectPostByPage
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 : 게시물 불러오기
	*/
	@Override
	public List<PostVO> selectPostByPage(PageVO pageVo) {
		
		List<PostVO> postList = template.selectList("postSQL.selectPostByPage", pageVo);
		if(postList != null){
			return postList;
		}
		
		return null;
	}
	
	
	/**
	* Method : selectPostByPage
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 : 포스트 코드를 가지고 게시물 불러오기
	*/
	@Override
	public PostVO selectPostByPostCode(String postCode) {
		try{
			PostVO postVo= template.selectOne("postSQL.selectPostByPostCode", postCode);
			return postVo;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	* 
	* Method : insertPost
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 : 포스트 객체로 정보 넣어주기
	* 
	*/
	
	@Override
	public int insertPost(PostVO postVo){
		
		try{
			System.out.println(postVo);
			int result = template.insert("postSQL.insertPost", postVo);
			
			return result;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return -1;
	}
	
	/**
	* Method : updatePost
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 : 포스트 객체로 수정하기
	* 
	*/
	@Override
	public int updatePost(PostVO postVo){
		
		try{
			System.out.println(postVo);
			int result = template.insert("postSQL.updatePost", postVo);
			
			return result;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return -1;
	}
	
	/**
	* Method : deletePost
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 : 포스트 코드를 이용해서 postavailable = 2 로 바꾸기
	* 
	*/
	@Override
	public int deletePost(String postCode){
		
		try{
			System.out.println(postCode);
			int result = template.insert("postSQL.deletePost", postCode);
			
			return result;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return -1;
	}
	
	/**
	* Method : countPost
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 : 총 현재 게시물 수를 구한다. 
	*/
	@Override
	public Integer countPost(String boardCode){

		
		try{
			
			Integer count = template.selectOne("postSQL.countPost", boardCode);
			return count;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return -1;
	}

	
	/**
	* Method : selectPostByPostName
	* 작성자 : pc20
	* 변경이력 :
	* @param postName
	* @return
	* Method 설명 : 검색한 이름에 해당하는 게시물을 구한다. 
	*/
	@Override
	public List<PostVO> selectPostByPostName(String postName) {
		List<PostVO> postList = template.selectList("postSQL.selectPostByPostName", postName);
		
		if(postList != null){
			return postList;
		}
		
		return null;
	}
}
