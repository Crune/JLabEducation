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
	
	@Autowired		//������ ������̼��� DI��� -- ���踦 �ξ��־� SERVICE �� ��ü�� ��� : jlabService �� �����Ų��
	private	AccountService SERVICE;  //DAO�� �����Ͽ� �����ϱ⿡ SERVICE��� Ī��  :
	
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
	
	@RequestMapping(value="/login.jlab",method=RequestMethod.POST)		// ���̵�� ����Է��� �α��ι�ư(submit��ư) ������ ȣ��
	public ModelAndView login_POST(HttpServletRequest request,MemberDto dto){
		ModelAndView mv = new ModelAndView();
		
		HttpSession sesion = request.getSession();		//���� ȣ��
			
		String name = SERVICE.login_check(dto);		//�Է��� ���̵𰪿� �ش��ϴ� �̸��� ����

		if(name != null){		//���̵� �ش��ϴ� ���� �������
			sesion.setAttribute("sesion_id", dto.getMember_id());		//���̵� ���ǿ� ����
			sesion.setAttribute("name", name);
			mv.addObject("name",name);		//������ �����Ϳ� �̸��� �߰�
			mv.setViewName("main_session_page");
		}else{
			mv.setViewName(new String("login_erorr"));
		}		
		return mv;		
	}
	
	@RequestMapping(value="/logout.jlab",method=RequestMethod.GET)		//�α׾ƿ� ��ư ������ ȣ��
	public ModelAndView logout_GET(HttpServletRequest request){
		HttpSession sesion = request.getSession();		//���� ȣ��
		sesion.invalidate();		//���� �����ߴ�
		return new ModelAndView("main_login_page");	  //jsp �������̸��� ����		
	}
	
	@RequestMapping(value="/join.jlab",method=RequestMethod.GET)			//ȸ������ ������ ȣ��
	public ModelAndView join_GET(HttpServletRequest req,MemberDto dto){
		
		return new ModelAndView("join");	//jsp �������̸��� ����	
	}
	
	@RequestMapping(value="/join.jlab",method=RequestMethod.POST)		//�����Է��� ȸ������ submit��ư ������ ȣ��
	public ModelAndView join_POST(HttpServletRequest req, MemberDto dto){
		ModelAndView mv = new ModelAndView();
		
		if(SERVICE.id_check(dto.getMember_id())){		//�Էµ� ���̵𰪿� �ش��ϴ� ������ ������ �Ǵ��Ͽ� ���ǹ��� �ۼ�

			SERVICE.create(dto);				//���̵� �ش��ϴ� ���� ������ ȸ�������� ����
			mv.setViewName("main_login_page");
		}else{
			mv.setViewName("join_faile");		//���̵� �ش��ϴ� ���� ������ �ߺ�â�� ���
		}
		return mv;		//���ǹ��� �ش��ϴ� jsp �̸��� ����
	}
	
	@RequestMapping(value="/member_info.jlab",method=RequestMethod.GET)		//�α����� ������ ����Ʈ�� ���
	public ModelAndView member_info_GET(HttpServletRequest req){
		ModelAndView mv = new ModelAndView();
		
		HttpSession sesion = req.getSession();		//���� ȣ��
		String name = (String) sesion.getAttribute("sesion_id");	   //���ǿ� ����� ���̵��� �̾ƿ�
		
		List<?> member_info = SERVICE.member_info( name );			//������ ���� �ش��ϴ� �����͸� �̾� ����Ʈ�� ����
		Iterator<?> iter = member_info.iterator();				//Iterator �� ����Ʈ�� ���� ��ȯ
			
		mv.addObject("list",iter);		//������ �����͸� �߰���
		mv.addObject("name",name);
		mv.setViewName("member_info");		//jsp �������̸��� �߰�
		return mv;		//�����Ϳ� jsp �������� ����
	}
	
	@RequestMapping(value="/member_info.jlab",method=RequestMethod.POST)
	public ModelAndView member_info_POST(HttpServletRequest req,MemberDto dto){
		HttpSession sesion = req.getSession();

		ModelAndView mv = new ModelAndView();

		Map<String, Object> map = new HashMap<String, Object>();
		String name = SERVICE.get_name((String)sesion.getAttribute("sesion_id"));		//���� ���̵𰪿� �ش��ϴ� �̸��� �̾ƿ�
		map.put("member_id", sesion.getAttribute("sesion_id"));		//������ �������� Map �� ����
		map.put("member_pw", dto.getMember_pw());
		map.put("member_name", name);

		SERVICE.member_update(map);			//�����͸� �����ϱ����� Map �� �����͸� ��� ����
		mv.addObject("name",sesion.getAttribute("sesion_id"));
		mv.setViewName("main_session_page");
		return mv;		
	}
	
	@RequestMapping(value="/member_delete.jlab",method=RequestMethod.GET)		//ȸ��Ż��� ȣ��
	public ModelAndView member_delete_GET(HttpServletRequest req){
		HttpSession sesion = req.getSession();		//����ȣ��
		
		ModelAndView mv = new ModelAndView("main_login_page");		//������ jsp �������̸��� �̸� ���

		SERVICE.delete((String)sesion.getAttribute("sesion_id"));		//���ǿ� ����� ���̵𰪿� �ش��ϴ� �����͸� ��� ����
		sesion.invalidate();		//���ǿ��� ����

		return mv;		//jsp �������̸��� ����
	}
	
	
}
