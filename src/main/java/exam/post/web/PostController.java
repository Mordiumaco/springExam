package exam.post.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import exam.comment.model.CommentVO;
import exam.comment.service.ICommentService;
import exam.file.model.FileVO;
import exam.file.service.IFileService;
import exam.login.model.UserVO;
import exam.post.model.PostVO;
import exam.post.service.IPostService;

@Controller
public class PostController {
	
	Logger log = LoggerFactory.getLogger(PostController.class);
	
	@Autowired
	IPostService postService;
	@Autowired
	ICommentService commentService;
	@Autowired
	IFileService fileService;
	
	
	@RequestMapping("/postDetail")
	public String postDetailView(@RequestParam("postCode") String postCode, Model model) {
		
		//포스트 코드로 게시물 객체를 받아온다.
		PostVO postVo = postService.selectPostByPostCode(postCode);
		
		//댓글에 대한 리스트
		List<CommentVO> commentList = commentService.selectPostCommentByPostCode(postCode);
		
		model.addAttribute("commentList", commentList);
		//조회 수 증가
		int result = postService.inquiryUp(postCode);
		
		//파일 리스트
		List<FileVO> fileList =  fileService.selectFilebyPostCode(postCode);
		model.addAttribute("fileList", fileList);
		
		//포스트 자체 정보에 대한 postVo
		model.addAttribute("postVo", postVo);
		
		return "post/postDetail";
	}
	
	@RequestMapping("/postSend")
	public String postWriteView(@RequestParam("boardCode") String boardCode, @RequestParam("postRefer") String postRefer, 
			Model model){ 
		
		model.addAttribute("boardCode", boardCode);
		model.addAttribute("postRefer", postRefer);
		
		return "post/postWrite";
	}
		
	@RequestMapping(value="/postMaker", method= {RequestMethod.POST})
	public String postWriteView(@RequestParam("file") MultipartFile[] files, MultipartHttpServletRequest request, Model model) throws IOException{ 


		UserVO userVo = (UserVO)request.getSession().getAttribute("userVo");

		if(userVo == null){
			return "LoginCheck";
		}

		StringJoiner sj = new StringJoiner(" , ");



		//--파일 업로드 부분 
		String directory = request.getServletContext().getRealPath("/upload/");
		String encoding ="UTF-8";
		int maxSize = 1024*1024*100;


		//-----------------------------------------------------------

		String postCon = request.getParameter("postCon");
		String postName = request.getParameter("postName");
		String boardCode = request.getParameter("boardCode");
		String postRefer = request.getParameter("postRefer");  

		PostVO postVo = new PostVO();
		postVo.setBoardCode(boardCode);
		postVo.setPostCon(postCon);
		postVo.setPostName(postName);
		postVo.setUserId(userVo.getUserId());
		int nextPostCode = postService.nowPostNumber()+1;
		postVo.setPostCode(nextPostCode);

		if(postRefer == null || "".equals(postRefer)){
			postVo.setPostRefer(null);
		}

		postVo.setPostRefer(postRefer);


		int result = postService.insertPost(postVo);

		if(result == -1){

			return "DBError";

		}else{

			for (MultipartFile file : files) {

				if (file.isEmpty()) {
					continue; //next pls
				}
				
				try {
					
					byte[] bytes = file.getBytes();
					file.transferTo(new File(directory + File.separator + file.getOriginalFilename()));
					/*Path path = Paths.get(directory + file.getOriginalFilename());
					Files.write(path, bytes);*/
					sj.add(file.getOriginalFilename());
					
					String fileName = file.getOriginalFilename();
					String fileRealName = file.getOriginalFilename();
					
					
					if(!fileName.endsWith(".jpg")&&!fileName.endsWith(".hwp")&&
							!fileName.endsWith(".png")&&!fileName.endsWith(".pdf")&&!fileName.endsWith(".xlsx")&&!fileName.endsWith(".xls")){
						File fileTest = new File(directory+fileRealName);
						fileTest.delete();
					}else{


						FileVO fileVo = new FileVO();
						fileVo.setFileCode(fileService.nowFileCodeNumber()+1);
						fileVo.setFileName(fileName);
						fileVo.setFilePosition(fileRealName);
						fileVo.setPostCode(nextPostCode);

						int fileResult = fileService.insertFile(fileVo);

						if(result == -1){

							return "DBError";
						}
					}

				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			//파일 업데이트 관련 내용

		}
		//--------------------------------------------------------------------

		return "main/main";
	}


	/**
	* Method : postDelete
	* 작성자 : pc20
	* 변경이력 :
	* @param postCode
	* @param boardCode
	* @return
	* Method 설명 : 포스트(게시물) 삭제
	*/
	@RequestMapping(value="/postDelete", method= {RequestMethod.GET})
	public String postDelete(@RequestParam("postCode") String postCode, @RequestParam("boardCode") String boardCode) {

		int result = postService.deletePost(postCode);

		if(result == -1){

			return "DBError";

		}else{

			return "board/boardPageList?boardCode="+boardCode+"&pageNumber=1";

		}
		
	}
	
	@RequestMapping(value="/postCommentInsert", method= {RequestMethod.POST})
	public String postCommentInsert(HttpServletRequest request, 
			@RequestParam("postCode") String postCode, @RequestParam("commentCon") String commentCon, Model model) {
		
		UserVO userVo = (UserVO)request.getSession().getAttribute("userVo");
		
		if(userVo == null) {
			return "loginCheck";
		}
		
		//댓글 객체에 정보 집어 넣어주기
		CommentVO commentVo = new CommentVO();
		commentVo.setCommentCon(commentCon);
		commentVo.setPostCode(Integer.parseInt(postCode));
		commentVo.setUserId(userVo.getUserId());

		int insertResult = commentService.insertPostComment(commentVo);

		//다시 게시물 조회 메서드를 통해 게시물로 돌아간다. 
		postDetailView(postCode, model);
		
		return "post/postDetail";
	}
	
	
	@RequestMapping("/postAnswerSend")
	public String postAnswerSend(@RequestParam("postRefer") String postRefer, Model model) {
		
		PostVO postVo =  postService.selectPostByPostCode(postRefer);
		
		model.addAttribute("boardCode", postVo.getBoardCode());
		model.addAttribute("postRefer", postRefer);
		
		return "post/postWrite";
	}
	
	@RequestMapping("/postUpdateSend")
	public String postUpdateSend(@RequestParam("postCode") String postCode, Model model) {
		
		PostVO postVo =  postService.selectPostByPostCode(postCode);
		
		model.addAttribute("postVo", postVo);
		model.addAttribute("postCode", postCode);
		
		return "post/postUpdateWrite";
	}
	
	@RequestMapping(value="/postUpdate", method=RequestMethod.POST)
	public String postUpdate(PostVO postVo, @RequestParam("boardCode") String boardCode) {
		
		int result = postService.updatePost(postVo);
		
		if(result == -1){

			return "DBError";

		}else{

			return "main/main";
		}

	}
	
	@RequestMapping(value="/postPageListAjax", method=RequestMethod.GET)
	public String postPageListAjax(Model model, @RequestParam("pageNumber") String pageNumber
			, @RequestParam("boardCode") String boardCode) {
		
		List<PostVO> postList = postService.selectPostByPage(pageNumber, boardCode);
		
		model.addAttribute("postList", postList);
		
		return "jsonView";
	}
}
