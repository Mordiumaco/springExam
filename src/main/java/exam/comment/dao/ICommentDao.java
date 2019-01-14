package exam.comment.dao;

import java.util.List;

import exam.comment.model.CommentVO;

public interface ICommentDao {
	
	/**
	* Method : selectPostCommentByPostCode
	* 작성자 : pc20
	* 변경이력 :
	* @return List<CommentVO> list 반환
	* Method 설명 : 해당 PostCode에 대한 댓글들을 불러온다.
	*/
	public List<CommentVO> selectPostCommentByPostCode(String postCode);
	
	/**
	* Method : insertPostComment
	* 작성자 : pc20
	* 변경이력 :
	* @return int 반환
	* Method 설명 : 성공여부를 반환한다. sql 문제시 -1 , 성공할시 1
	*/
	public int insertPostComment(CommentVO commentVo);
	
	/**
	* Method : nowPostCommentNumber
	* 작성자 : pc20
	* 변경이력 :
	* @return Integer 반환
	* Method 설명 : 현재 CommentCode의 최신 게시물 번호를 반환한다. 
	*/
	public Integer nowPostCommentNumber();
	
	/**
	* Method : deletePostComment
	* 작성자 : pc20
	* 변경이력 :
	* @return int 반환
	* Method 설명 : 현재 CommentCode의 available을 2로 바꿔준다. 
	*/
	public int deletePostComment(String CommentCode);
}
