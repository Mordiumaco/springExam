package exam.comment.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import exam.comment.model.CommentVO;

@Repository
public class CommentDao implements ICommentDao{
	
	@Resource(name="sqlSessionTemplate")
	SqlSessionTemplate template;
	
	/**
	* Method : selectPostCommentByPostCode
	* 작성자 : pc20
	* 변경이력 :
	* @return List<CommentVO> list 반환
	* Method 설명 : 해당 PostCode에 대한 댓글들을 불러온다.
	*/
	@Override
	public List<CommentVO> selectPostCommentByPostCode(String postCode){
		try {
			
			List<CommentVO> commentList = template.selectList("commentSQL.selectPostCommentByPostCode", postCode);
			if(commentList != null){
				return commentList;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	* Method : insertPostComment
	* 작성자 : pc20
	* 변경이력 :
	* @return int 반환
	* Method 설명 : 성공여부를 반환한다. sql 문제시 -1 , 성공할시 1
	*/
	@Override
	public int insertPostComment(CommentVO commentVo){
		try {

			int result = template.insert("commentSQL.insertPostComment", commentVo);
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}
	
	/**
	* Method : nowPostCommentNumber
	* 작성자 : pc20
	* 변경이력 :
	* @return Integer 반환
	* Method 설명 : 현재 CommentCode의 최신 게시물 번호를 반환한다. 
	*/
	@Override
	public Integer nowPostCommentNumber(){
		try {

			int result = template.selectOne("commentSQL.nowPostCommentNumber");
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	/**
	* Method : deletePostComment
	* 작성자 : pc20
	* 변경이력 :
	* @return int 반환
	* Method 설명 : 현재 CommentCode의 available을 2로 바꿔준다. 
	*/
	public int deletePostComment(String CommentCode){
		
		try {

			int result = template.update("commentSQL.deletePostComment", CommentCode);
			
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
}
