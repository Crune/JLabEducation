package com.jlab.education.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jlab.education.dto.Articles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

	@RequestMapping(value = "/xml/list", produces = "application/xml")
	public @ResponseBody Articles gesipanXml(HttpServletRequest request) {
		List<ArticleDto> glist = GE_SERVICE.gesi_list();// ������������ �Խ����� ��������� �Խù����� DB���� ��ȸ
        Articles atcls = new Articles();
        atcls.setArticles(glist);
        return atcls;// ��ü�� ����
	}

	// �Խ��� �۾���
	@RequestMapping(value = "/gesipan_save.jlab", method = RequestMethod.POST)
	public ModelAndView gesipan_save(HttpSession session, ArticleDto dto) {
		boolean isName = dto.getName() != null && !dto.getName().isEmpty();
		boolean isTitle = dto.getTitle() != null && !dto.getTitle().isEmpty();
		boolean isCont = dto.getContent() != null && !dto.getContent().isEmpty();
		if (isName && isTitle && isCont) {
			
			String uid = (String) session.getAttribute("sesion_id");
			boolean isLogined = uid != null && !uid.isEmpty();
			if (isLogined) {
				dto.setId(uid);
				String name = (String) session.getAttribute("name");
				dto.setName(name);
			}
			
			GE_SERVICE.gesipan_insert(dto);// �Խ��� �۾��⸦ ���Ͽ� �Ѿ�� ������ DB�� ����.
		}
		
		return goArticle(0);
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
	
	private boolean isAAuth(int a_num, HttpSession session) {
		String uid = (String) session.getAttribute("sesion_id");
		boolean isLogined = uid != null && !uid.isEmpty();
		if (isLogined) {
			ArticleDto atc = GE_SERVICE.gesi_rview(a_num);
			boolean isAuth = uid.equals(atc.getId());
			if (isAuth) {
				return true;
			}
		}
		return false;
	}

	// ����
	@RequestMapping(value = "/gesipan_update.jlab", method = RequestMethod.POST)
	public ModelAndView gesipan_update(HttpSession session, ArticleDto dto,
			@RequestParam(value="num", defaultValue = "0") int num) { // DTO�� ������ ������
		
		if (isAAuth(num, session)) {
			GE_SERVICE.gesi_update(dto);// DTO�� ������ �̿��Ͽ�, DB�� ������ ����
		}
		
		return goArticle(num);
	}

	// ����
	@RequestMapping(value = "/gesipan_delete.jlab", method = RequestMethod.GET)
	public ModelAndView gesipan_delete(HttpSession session,
			@RequestParam(value="num", defaultValue = "0") int num) {
		
		if (isAAuth(num, session)) {
			GE_SERVICE.gesi_delete(num);// �Խù� ������ȣ�� ������ DB�� �����Ͽ� �Խù� ����
		}
		
		return goArticle(0);
	}

	private ReplyDto injectAccount(ReplyDto dto, HttpSession session) {
		boolean isName = dto.getName() != null && !dto.getName().isEmpty();
		boolean isCont = dto.getContent() != null && !dto.getContent().isEmpty();
		if (isName && isCont) {
			
			String uid = (String) session.getAttribute("sesion_id");
			boolean isLogined = uid != null && !uid.isEmpty();
			if (isLogined) {
				dto.setId(uid);
				String name = (String) session.getAttribute("name");
				dto.setName(name);
			}
			return dto;
		}
		return null;
	}

	private boolean isRAuth(int r_num, HttpSession session) {
		String uid = (String) session.getAttribute("sesion_id");
		boolean isLogined = uid != null && !uid.isEmpty();
		if (isLogined) {
			ReplyDto reply = GE_SERVICE.pick_review(r_num);
			boolean isAuth = uid.equals(reply.getId());
			if (isAuth) {
				return true;
			}
		}
		return false;
	}
	// ���
	@RequestMapping(value = "/gesipan_reple.jlab", method = RequestMethod.POST)
	public ModelAndView gesipan_reple(HttpSession session, ReplyDto dto) {
		dto = injectAccount(dto, session);
		if (dto != null) {
			GE_SERVICE.gesi_rview_insert(dto);// �Խ��� �۾��⸦ ���Ͽ� �Ѿ�� ������ DB�� ����.
		}
		return goArticle(dto.getGasinumber());
	}

	// ��� ����
	@RequestMapping(value = "/gesipan_reple_delete.jlab", method = RequestMethod.GET)
	public ModelAndView gesipan_reple_delete(HttpSession session,
			@RequestParam(value="anum", defaultValue = "0") int anum,
			@RequestParam(value="rnum", defaultValue = "0") int rnum) {
		if (anum > 0 && rnum > 0 && isRAuth(rnum, session)) {
			GE_SERVICE.gesi_rview_delete(rnum);// �Խù� ������ȣ�� ������ DB�� �����Ͽ� �Խù� ����
			return goArticle(anum);
		} else {
			return goArticle(0);
		}
	}
}
