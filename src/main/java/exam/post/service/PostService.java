package exam.post.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exam.post.dao.IPostDao;
import exam.post.model.PageVO;
import exam.post.model.PostVO;

@Service
public class PostService implements IPostService{
	
	@Autowired
	private IPostDao dao;

	/**
	* Method : nowPostNumber
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 : 현재 게시물 번호를 알수 있다.
	*/
	@Override
	public int nowPostNumber() {
		return dao.nowPostNumber();
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
		return dao.inquiryUp(postCode);
	}

	
	/**
	* Method : selectPostByPage
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 : 게시물 불러오기
	*/
	@Override
	public List<PostVO> selectPostByPage(String pageNumber, String boardCode) {
		
		PageVO pageVo = new PageVO();
		pageVo.setBoardCode(Integer.parseInt(boardCode));
		pageVo.setPageNumber(Integer.parseInt(pageNumber));
		pageVo.setPageSize(10);
		
		return dao.selectPostByPage(pageVo);
	}
	
	/**
	* Method : selectPostByPage
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 : 포스트 코드를 가지고 게시물 불러오기
	*/
	@Override
	public PostVO selectPostByPostCode(String postCode){
		return dao.selectPostByPostCode(postCode);
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
	public int insertPost(PostVO postVo){
		return dao.insertPost(postVo);
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
		return dao.updatePost(postVo);
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
		return dao.deletePost(postCode);
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
		return dao.countPost(boardCode);
	}
	
	
	/**
	* Method : totalPageNumber
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 : 총 현재 페이지의 토탈 페이지를 구한다. 
	*/
	@Override
	public int totalPageNumber(String boardCode){
		Integer count = countPost(boardCode);
		
		int totalPage = count%10==0 ? count/10 : count/10+1;
				
		return totalPage;
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
	public List<PostVO> selectPostByPostName(String postName){
		return dao.selectPostByPostName(postName);
	}
	
}
