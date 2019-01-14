package exam.board.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import exam.board.model.BoardVO;
import exam.test.ServiceDaoTestConfig;

public class boardServiceTest extends ServiceDaoTestConfig{

	@Autowired
	IBoardService boardService;
	
	/**
	* Method : selectBoardListTest
	* 작성자 : pc20
	* 변경이력 :
	* Method 설명 : 현재 가지고 있는 게시판 리스트를 불러온다. (이용가능한 게시판만)
	*/
	@Test
	public void selectBoardListTest() {
		List<BoardVO> boardList = boardService.selectBoardList();
		
		assertEquals(3, boardList.size());
	}
	
	/**
	* Method : selectBoardListForBoardMaker
	* 작성자 : pc20
	* 변경이력 :
	* Method 설명 : 현재 가지고 있는 게시판들을 불러온다 (이용불가능한 게시판도 같이 불러옴) 
	*/
	@Test
	public void selectBoardListForBoardMaker() {
		List<BoardVO> boardList = boardService.selectBoardListForBoardMaker();
		
		assertEquals(3, boardList.size());
	}

	/**
	* Method : nowBoardCode
	* 작성자 : pc20
	* 변경이력 :
	* Method 설명 : 최근 boardCode를 받아온다.
	*/
	@Test
	public void nowBoardCode() {
		int boardCode =  boardService.nowBoardCode();
		
		assertEquals(3, boardCode);
	}
	
	@Test
	public void insertBoard(){
		
	}
	
	@Test
	public void updateBoard(){
		
	}
}	
