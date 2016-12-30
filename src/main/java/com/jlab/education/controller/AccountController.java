package com.jlab.education.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jlab.education.dto.MemberDto;
import com.jlab.education.service.AccountService;

/**
 * Handles requests for the application home page.
 */

@Controller
public class AccountController {
	
	@Autowired		//스프링 어노테이션의 DI방식 -- 관계를 맺어주어 SERVICE 를 빈객체로 등록 : jlabService 를 연결시킨다
	private	AccountService SERVICE;  //DAO를 포함하여 서비스하기에 SERVICE라고 칭함  :
	
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = {"/","index.jlab" }, method = RequestMethod.GET)
	public ModelAndView index_GET(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		HttpSession sesion = request.getSession();
		
		String name = (String) sesion.getAttribute("sesion_id");

		if(name != null && name != ""){
			mv.addObject("name",name);
			mv.setViewName("main_session_page");
		}else{
			mv.setViewName("main_login_page");
		}
		return mv;
	}

	@RequestMapping(value="/login.jlab",method=RequestMethod.GET)	
	public ModelAndView login_GET(HttpServletRequest request){
		
		HttpSession sesion = request.getSession();

		ModelAndView mv = new ModelAndView();
		
		if(sesion.getAttribute("sesion_id") == null ){
			mv.setViewName("login");
			
		}else{
			
			mv.addObject("id" , sesion.getAttribute("sesion_id"));
			mv.setViewName("login_sucess");
		}
		return mv;			
	}
	
	@RequestMapping(value="/login.jlab",method=RequestMethod.POST)		// 아이디와 비번입력후 로그인버튼(submit버튼) 누를시 호출
	public ModelAndView login_POST(HttpServletRequest request,MemberDto dto){
		ModelAndView mv = new ModelAndView();
		
		HttpSession sesion = request.getSession();		//세션 호출
			
		String name = SERVICE.login_check(dto);		//입력한 아이디값에 해당하는 이름을 뽑음

		if(name != null){		//아이디에 해당하는 값이 있을경우
			sesion.setAttribute("sesion_id", dto.getMember_id());		//아이디를 세션에 저장
			sesion.setAttribute("name", name);
			mv.addObject("name",name);		//리턴할 데이터에 이름을 추가
			mv.setViewName("main_session_page");
		}else{
			mv.setViewName(new String("login_erorr"));
		}		
		return mv;		
	}
	
	@RequestMapping(value="/logout.jlab",method=RequestMethod.GET)		//로그아웃 버튼 누를시 호출
	public ModelAndView logout_GET(HttpServletRequest request){
		HttpSession sesion = request.getSession();		//세션 호출
		sesion.invalidate();		//세션 연결중단
		return new ModelAndView("main_login_page");	  //jsp 페이지이름을 리턴		
	}
	
	@RequestMapping(value="/join.jlab",method=RequestMethod.GET)			//회원가입 페이지 호출
	public ModelAndView join_GET(HttpServletRequest req,MemberDto dto){
		
		return new ModelAndView("join");	//jsp 페이지이름을 리턴	
	}
	
	@RequestMapping(value="/join.jlab",method=RequestMethod.POST)		//정보입력후 회원가입 submit버튼 누를시 호출
	public ModelAndView join_POST(HttpServletRequest req, MemberDto dto){
		ModelAndView mv = new ModelAndView();
		
		if(SERVICE.id_check(dto.getMember_id())){		//입력된 아이디값에 해당하는 정보의 유무를 판단하여 조건문을 작성

			SERVICE.create(dto);				//아이디에 해당하는 값이 없으면 회원가입을 진행
			mv.setViewName("main_login_page");
		}else{
			mv.setViewName("join_faile");		//아이디에 해당하는 값이 있으면 중복창을 띄움
		}
		return mv;		//조건문에 해당하는 jsp 이름을 리턴
	}
	
	@RequestMapping(value="/member_info.jlab",method=RequestMethod.GET)		//로그인한 정보를 리스트로 출력
	public ModelAndView member_info_GET(HttpServletRequest req){
		ModelAndView mv = new ModelAndView();
		
		HttpSession sesion = req.getSession();		//세션 호출
		String name = (String) sesion.getAttribute("sesion_id");	   //세션에 저장된 아이디값을 뽑아옴
		
		List<?> member_info = SERVICE.member_info( name );			//세션의 값에 해당하는 데이터를 뽑아 리스트에 담음
		Iterator<?> iter = member_info.iterator();				//Iterator 로 리스트의 값을 변환
			
		mv.addObject("list",iter);		//리턴할 데이터를 추가함
		mv.addObject("name",name);
		mv.setViewName("member_info");		//jsp 페이지이름을 추가
		return mv;		//데이터와 jsp 페이지를 리턴
	}
	
	@RequestMapping(value="/member_info.jlab",method=RequestMethod.POST)
	public ModelAndView member_info_POST(HttpServletRequest req,MemberDto dto){
		HttpSession sesion = req.getSession();

		ModelAndView mv = new ModelAndView();

		Map<String, Object> map = new HashMap<String, Object>();
		String name = SERVICE.get_name((String)sesion.getAttribute("sesion_id"));		//세션 아이디값에 해당하는 이름을 뽑아옴
		map.put("member_id", sesion.getAttribute("sesion_id"));		//수정할 정보들을 Map 에 담음
		map.put("member_pw", dto.getMember_pw());
		map.put("member_name", name);

		SERVICE.member_update(map);			//데이터를 수정하기위해 Map 에 데이터를 담아 보냄
		mv.addObject("name",sesion.getAttribute("sesion_id"));
		mv.setViewName("main_session_page");
		return mv;		
	}
	
	@RequestMapping(value="/member_delete.jlab",method=RequestMethod.GET)		//회원탈퇴시 호출
	public ModelAndView member_delete_GET(HttpServletRequest req){
		HttpSession sesion = req.getSession();		//세션호출
		
		ModelAndView mv = new ModelAndView("main_login_page");		//리턴할 jsp 페이지이름을 미리 등록

		SERVICE.delete((String)sesion.getAttribute("sesion_id"));		//세션에 저장된 아이디값에 해당하는 데이터를 모두 지움
		sesion.invalidate();		//세션연결 해제

		return mv;		//jsp 페이지이름을 리턴
	}
	
	
}
