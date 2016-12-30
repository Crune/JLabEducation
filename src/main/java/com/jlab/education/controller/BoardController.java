package com.jlab.education.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.jlab.education.dto.ArticleDto;
import com.jlab.education.dto.ReplyDto;
import com.jlab.education.service.BoardService;

@Controller //������ ������̼��� DI���  -- ��Ʈ�ѷ��� �ǹ��Ѵ� : gesipan_controller�� �ǹ��Ѵ�.
public class BoardController {
	@Autowired //������ ������̼��� DI��� -- ���踦 �ξ��־� SERVICE �� ��ü�� ��� : gesipan_SERVICE �� �����Ų��
	private BoardService GE_SERVICE; //DAO�� �����Ͽ� �����ϱ⿡ GE_SERVICE��� Ī��  : 
	// RequestMapping�� ���Ͽ�, /gesipan.jlab�� ��ġ�ϸ�, �޼ҵ� ���� : �ε��� ���� ������

	@RequestMapping(value = "/gesipan.jlab", method = RequestMethod.GET)
	public ModelAndView gesipan(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();// ������ ������� ��ü�� ����.
		List<?> glist = GE_SERVICE.gesi_list();// ������������ �Խ����� ��������� �Խù����� DB���� ��ȸ
		mav.addObject("glist", glist);// �Խù����� ���� List�� ������ ���� ��ü�� ����
		mav.setViewName("mainpage");// ������ �������� ����. tiles�� �̿��Ͽ� �������� �Ѹ�
		return mav;// ��ü�� ����
	}

	// �Խ��� �۾���
	@RequestMapping(value = "/gesipan_save.jlab", method = RequestMethod.POST)
	public ModelAndView gesipan_save(HttpServletRequest request, ArticleDto dto) {
		ModelAndView mav = new ModelAndView();
		GE_SERVICE.gesipan_insert(dto);// �Խ��� �۾��⸦ ���Ͽ� �Ѿ�� ������ DB�� ����.
		List<?> glist = GE_SERVICE.gesi_list();//�����ϰ� ������ ���ο� �Խù��� ��ȸ.
		mav.addObject("glist", glist);
		mav.setViewName("mainpage");
		return mav;
	}

	// �۾��� ��ư �̺�Ʈ
	@RequestMapping(value = "/gesipan_Write.jlab", method = RequestMethod.POST)
	public ModelAndView gesipan_write(HttpServletRequest request) {
		return new ModelAndView("gesipan_Write");// �۾��� �������� �̵�.
	}

	// �󼼺���
	@RequestMapping(value = "/gesipan_rview.jlab", method = RequestMethod.GET)
	public ModelAndView gesipan_review(@RequestParam(value="num", defaultValue = "0") int num) {
		ModelAndView mav = new ModelAndView();
		
		if (num == 0) {
			return new ModelAndView( "redirect:/gesipan.jlab");
		} else {
			ArticleDto member = GE_SERVICE.gesi_rview(num);// �Խù� ������ȣ�� �̿��Ͽ�, DB���� �������� ��ȸ.
			List<?> list = GE_SERVICE.gesi_rview_board(num);// �Խù� ������ȣ�� �̿��Ͽ�, �ش� �Խù��� ����� ��ȸ.
			
			mav.addObject("list", list);
			mav.setViewName("gesipan_review");
			mav.addObject("member", member);
			return mav;
		}
	}

	private ModelAndView goArticle(int num) {
		Map<String, Object> rttr = new HashMap<String, Object>();
		rttr.put("num", num);
		return new ModelAndView( "redirect:/gesipan_rview.jlab", rttr);
	}

	// ���
	@RequestMapping(value = "/gesipan_reple.jlab", method = RequestMethod.POST)
	public ModelAndView gesipan_reple(HttpServletRequest request, ReplyDto dto) {
		GE_SERVICE.gesi_rview_insert(dto);// �Խ��� �۾��⸦ ���Ͽ� �Ѿ�� ������ DB�� ����.
		return goArticle(dto.getGasinumber());
	}
	
	// ����
	@RequestMapping(value = "/gesipan_update.jlab", method = RequestMethod.POST)
	public ModelAndView gesipan_update(HttpServletRequest request, ArticleDto member) { // DTO�� ������ ������
		String nums = request.getParameter("num");// �Խù� ���� ��ȣ�� ������.
		int num = Integer.parseInt(nums);// StringŸ���� -> intŸ������ ����.
		GE_SERVICE.gesi_update(member);// DTO�� ������ �̿��Ͽ�, DB�� ������ ����		
		return goArticle(num);
	}

	// ����
	@RequestMapping(value = "/gesipan_delete.jlab", method = RequestMethod.GET)
	public ModelAndView gesipan_delete(HttpServletRequest request) {
		String nums = request.getParameter("num");// �Խù� ���� ��ȣ�� ������.
		int num = Integer.parseInt(nums);// StringŸ���� -> intŸ������ ����.
		GE_SERVICE.gesi_delete(num);// �Խù� ������ȣ�� ������ DB�� �����Ͽ� �Խù� ����
		return goArticle(0);
	}
}
