package exam.comment.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import exam.comment.service.ICommentService;

@Controller
public class CommentController {
	
	@Autowired
	ICommentService commentService;
	
	@RequestMapping("/commentS")
	public String commentDelete(@RequestParam("commentCode") String commentCode) {
		
		int result = commentService.deletePostComment(commentCode);
		
		if(result == -1){
			return "DBError";
		}else{
			return "main/main";
		}
		
	}
	
	
}
