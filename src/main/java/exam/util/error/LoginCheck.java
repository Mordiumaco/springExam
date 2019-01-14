package exam.util.error;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;

//구구단 custom view 
//spring customview 를 만들기 위해서는 View interface를 구현할 필요가 있음 
public class LoginCheck implements View{

	@Override
	public String getContentType() {
		return null;
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter script = response.getWriter();
		
		script.println("<script>");
		script.println("alert('먼저 로그인을 해주세요.');");
		script.println("location.href = '/login';");
		script.println("</script>");
		script.flush();
		script.close();
	}
	
}
