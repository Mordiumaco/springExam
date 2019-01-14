package exam.util.error;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;

public class LoginRight implements View{

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
		script.println("alert('해당 권한이 없는 아이디 입니다..');");
		script.println("location.href = '/main';");
		script.println("</script>");
		script.flush();
		script.close();
	}
	
}