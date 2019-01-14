package exam.login.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import exam.board.model.BoardVO;
import exam.board.service.IBoardService;
import exam.login.model.UserVO;
import exam.login.service.ILoginService;

@Controller
public class LogInController {
	
	@Autowired
	ILoginService loginService;
	
	@Autowired
	IBoardService boardService;
	
	@RequestMapping(value= {"/", "cover"})
	public String coverView() {
		return "cover";
	}
	
	@RequestMapping(value= {"/login"}, method= {RequestMethod.GET})
	public String loginView() {
		return "login/login";
	}
	
	@RequestMapping(value= {"/login"}, method= {RequestMethod.POST})
	public String loginProcess(UserVO userVo, HttpServletRequest request) {
		
		String userId = userVo.getUserId();
		String password = userVo.getPassword();
		
		UserVO ResultUserVo = loginService.selectUserByIdAndPassword(userId, password);
		
		if(ResultUserVo != null) {
			
			request.getSession().setAttribute("userVo", ResultUserVo);
			
			List<BoardVO> boardList = boardService.selectBoardList();
			
			request.getServletContext().setAttribute("boardList", boardList);
			
			return "main/main"; //성공시 메인으로
		}
		
		return "login/login"; //실패시 로그인창으로 다시
	}
	
	@RequestMapping("/main")
	public String mainView() {
		return "main/main";
	}
}
