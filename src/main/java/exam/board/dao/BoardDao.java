package exam.board.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import exam.board.model.BoardVO;

@Repository
public class BoardDao implements IBoardDao{
	
	@Autowired
	private SqlSessionTemplate template;
	
	/**
	* Method : selectBoardList
	* 작성자 : pc20
	* 변경이력 :
	* @return List<BoardVO> list 반환
	* Method 설명 : 현재 가지고 있는 게시판들을 불러온다
	*/
	@Override
	public List<BoardVO> selectBoardList() {
		
		List<BoardVO> boardList = template.selectList("boardSQL.selectBoardList");
		
		
		if(boardList != null){
			return boardList;
		}
		
		return null;
	}
	
	/**
	* Method : selectBoardListForBoardMaker
	* 작성자 : pc20
	* 변경이력 :
	* @return List<BoardVO> list 반환
	* Method 설명 : 현재 가지고 있는 게시판들을 불러온다
	*/
	@Override
	public List<BoardVO> selectBoardListForBoardMaker(){
		List<BoardVO> boardList = template.selectList("boardSQL.selectBoardListForBoardMaker");
		
		if(boardList != null){
			return boardList;
		}
		
		return null;
	}
	
	/**
	* Method : getBoardName
	* 작성자 : pc20
	* 변경이력 :
	* @param boardCode
	* @return
	* Method 설명 : 게시판 이름 가져오기
	*/
	@Override
	public String getBoardName(Integer boardCode){
		
		String boardName = template.selectOne("boardSQL.getBoardName", boardCode);
		
		if(boardName != null){
			return boardName;
		}
		
		return null;
	}
	
	
	/**
	* Method : nowBoardCode
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 :현재 최근 boardcode를 얻어온다.
	*/
	@Override
	public Integer nowBoardCode(){
		
		try {
			Integer nowBoardCode = template.selectOne("boardSQL.nowBoardCode");
			return nowBoardCode;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	/**
	* Method : insertBoard
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 :게시판을 만든다.
	*/
	@Override
	public int insertBoard(BoardVO boardVo){
		try {
			
			int result = template.insert("boardSQL.insertBoard", boardVo);
			
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	/**
	* Method : updateBoard
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 :게시판을 수정한다.
	*/
	@Override
	public int updateBoard(BoardVO boardVo){
		
		try {

			int result = template.update("boardSQL.updateBoard", boardVo);
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
}
