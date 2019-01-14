package exam.login.web;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import exam.test.ControllerTestConfig;

public class LoginAndMainTest extends ControllerTestConfig{

	/**
	* Method : coverViewTest
	* 작성자 : pc20
	* 변경이력 :
	* @throws Exception
	* Method 설명 : cover View 출력 테스트
	*/
	@Test
	public void coverViewTest() throws Exception {
		
		MvcResult mvc = mockMvc.perform(get("/cover")).andReturn();
		
		ModelAndView mav = mvc.getModelAndView();
		
		String viewName = mav.getViewName();
		
		assertEquals("cover", viewName); 
	}
	
	
	
	/**
	* Method : loginViewTest
	* 작성자 : pc20
	* 변경이력 :
	* @throws Exception
	* Method 설명 : login View 출력테스트
	*/
	@Test
	public void loginViewTest() throws Exception {
		
		MvcResult mvc = mockMvc.perform(get("/login")).andReturn();
		
		ModelAndView mav = mvc.getModelAndView();
		
		String viewName = mav.getViewName();
		
		assertEquals("login/login", viewName); 
	}

	
	/**
	* Method : loginViewToMainWhenSuccessTest
	* 작성자 : pc20
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 로그인 성공 시 가는 뷰 테스트(main View 로 리턴) 
	*/
	@Test
	public void loginViewToMainWhenSuccessTest() throws Exception {
		
		MvcResult mvc = mockMvc.perform(post("/login")).andReturn();
		
		ModelAndView mav = mvc.getModelAndView();
		
		String viewName = mav.getViewName();
		
		assertEquals("cover", viewName); 
	}
	
	/**
	* Method : loginViewToMainWhenFailTest
	* 작성자 : pc20
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 로그인 실패시 가는 뷰 테스트 (login view로 return)
	*/
	@Test
	public void loginViewToMainWhenFailTest() throws Exception {
		
		MvcResult mvc = mockMvc.perform(post("/login")).andReturn();
		
		ModelAndView mav = mvc.getModelAndView();
		
		String viewName = mav.getViewName();
		
		assertEquals("cover", viewName); 
	}
}
