package exam.board.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import exam.board.model.BoardVO;
import exam.board.service.IBoardService;
import exam.login.model.UserVO;
import exam.post.model.PageVO;
import exam.post.model.PostVO;
import exam.post.service.IPostService;


@Controller
public class BoardController {

	@Autowired
	private IBoardService boardService;
	
	@Autowired
	private IPostService postService;
	
	@RequestMapping("/boardMaker")
	public String boardMakerView(Model model) {
		
		List<BoardVO> boardListForBoardMaker =  boardService.selectBoardListForBoardMaker();
		model.addAttribute("boardListForBoardMaker",boardListForBoardMaker);
		
		return "board/boardMaker";
	}
	
	@RequestMapping(value="/boardUpdate", method=RequestMethod.POST)
	public String boardUpdate(BoardVO boardVo, Model model, HttpServletRequest request) {
		
		UserVO userVo = (UserVO)request.getSession().getAttribute("userVo");
		
		if(userVo == null){
			return "loginCheck";
		}
		
		//권한이 없는 아이디 일 경우
		if(userVo.getRight() != 3){
			return "loginRight";
		}
				
		int result = boardService.updateBoard(boardVo);
		
		if(result == -1) {
			return "dbError";
			
		}else {
			List<BoardVO> boardList = boardService.selectBoardList();
			request.getServletContext().setAttribute("boardList", boardList);
			
			List<BoardVO> boardListForBoardMaker =  boardService.selectBoardListForBoardMaker();
			model.addAttribute("boardListForBoardMaker",boardListForBoardMaker);
			return "board/boardMaker";
		}
		
	}
	
	@RequestMapping(value="/boardInsert", method=RequestMethod.POST)
	public String boardInsert(@RequestParam("boardName") String boardName ,HttpServletRequest request, Model model) {
		
		UserVO userVo = (UserVO)request.getSession().getAttribute("userVo");
		
		if(userVo == null){
			return "loginCheck";
		}
		
		//권한이 없는 아이디 일 경우
		if(userVo.getRight() != 3){
			return "loginRight";
		}
		
		int boardCode = boardService.nowBoardCode()+1;
		String userId = userVo.getUserId();
		//게시판 생성을 위한 게시판 객체 생성 
		BoardVO boardVo = new BoardVO();
		boardVo.setBoardCode(boardCode);
		boardVo.setBoardName(boardName);
		boardVo.setUserId(userId);
		
		int result = boardService.insertBoard(boardVo);
		

		if(result == -1){
			return "dbError";
		}else{
			
			List<BoardVO> boardList =  boardService.selectBoardList();
			
			request.getServletContext().setAttribute("boardList", boardList);
			
			List<BoardVO> boardListForBoardMaker =  boardService.selectBoardListForBoardMaker();
			model.addAttribute("boardListForBoardMaker",boardListForBoardMaker);
			
			return "board/boardMaker";
		}
		
	}
	
	@RequestMapping(value="/boardPageList", method=RequestMethod.GET)
	public String boardPageListView(PageVO pageVo, @RequestParam("boardCode") String boardCode,
			@RequestParam("pageNumber") String pageNumber, Model model) {
		
		List<PostVO> postList = postService.selectPostByPage(pageNumber, boardCode);
		
		//페이지 구하기
		Integer totalPage = postService.totalPageNumber(boardCode);
		model.addAttribute("totalPage", totalPage);
		
		//게시물 뽑아오기
		model.addAttribute("postList", postList);
		model.addAttribute("boardCode", boardCode);
		model.addAttribute("pageNumber", pageNumber);
		
		String boardName = boardService.getBoardName(Integer.parseInt(boardCode));
		
		//게시판 이름 받아오기
		model.addAttribute("boardName", boardName);
		//model.addAttribute("postRefer", postRefer);
		//글쓰기에 필요한 보더코드
		
		return "board/board";
	}
	
	@RequestMapping("/boardSearch")
	public String boardSearch(@RequestParam("postName") String postName, Model model) {
		List<PostVO> postList = postService.selectPostByPostName(postName);
		
		//게시물 뽑아오기
		model.addAttribute("postList", postList);
		
		String boardCode = null;
		
		if(postList == null || postList.size() == 0) {
			model.addAttribute("boardCode", "1");
			boardCode = "1";
		}else {
			model.addAttribute("boardCode", postList.get(0).getBoardCode());
			boardCode = postList.get(0).getBoardCode();
		}
		
		model.addAttribute("pageNumber", "1");

		String boardName = boardService.getBoardName(Integer.parseInt(boardCode));

		//게시판 이름 받아오기
		model.addAttribute("boardName", boardName);
		//model.addAttribute("postRefer", postRefer);
		//글쓰기에 필요한 보더코드
		
		return "board/board";
	}
	
}
