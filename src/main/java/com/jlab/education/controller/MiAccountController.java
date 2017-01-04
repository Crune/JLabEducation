package com.jlab.education.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.jlab.education.dto.MemberDto;
import com.jlab.education.mi.MiVR;
import com.jlab.education.mi.MiDTO;
import com.jlab.education.service.AccountService;

@Controller
@RequestMapping("mi/account")
public class MiAccountController {

	@Autowired
	private AccountService serv;
	
	@Resource(name="miView")
	View miView;
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MiAccountController.class);

	@RequestMapping(value = "/login")
	public ModelAndView login(MiDTO in) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setView(miView);

		try {
			System.out.println(in.toString());
			MemberDto dto = new MemberDto();


			Map<String,String> vars = in.getVList();
			dto.setMember_id(vars.get("id"));
			dto.setMember_pw(vars.get("pw"));

			String name = serv.login_check(dto); //�Է��� ���̵𰪿� �ش��ϴ� �̸��� ����

			if (name != null) { //���̵� �ش��ϴ� ���� �������
				dto.setMember_name(name);
				dto.setRegdate(new Date(System.currentTimeMillis()).toString());
			} else {
				throwMiError(mv, "ID Ȥ�� PW�� �߸��Ǿ����ϴ�.");
			}
			//dto.setMember_id("testing!");
			
			mv.addObject("MiDTO", dto);
			mv.addObject("OutDsName", "out_ds");
			
			mv.addObject("MiResultCode", "0");
			mv.addObject("MiResultMsg", "success");

		} catch (Exception e) {
			e.printStackTrace();
			throwMiError(mv, "��������: "+e.toString());
		}
		return mv;
	}
	
	public void throwMiError(ModelAndView mav, String msg) {
		mav.addObject("MiResultCode", "-1");
		mav.addObject("MiResultMsg", msg);
	}
}
