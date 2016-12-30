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

@Controller //스프링 어노테이션의 DI방식  -- 컨트롤러를 의미한다 : gesipan_controller를 의미한다.
public class BoardController {
	@Autowired //스프링 어노테이션의 DI방식 -- 관계를 맺어주어 SERVICE 를 빈객체로 등록 : gesipan_SERVICE 를 연결시킨다
	private BoardService GE_SERVICE; //DAO를 포함하여 서비스하기에 GE_SERVICE라고 칭함  : 
	// RequestMapping을 통하여, /gesipan.jlab와 일치하면, 메소드 실행 : 로딩시 시작 페이지

	@RequestMapping(value = "/gesipan.jlab", method = RequestMethod.GET)
	public ModelAndView gesipan(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();// 정보를 담기위해 객체를 만듬.
		List<?> glist = GE_SERVICE.gesi_list();// 시작페이지에 게시판을 만들기위해 게시물들을 DB에서 조회
		mav.addObject("glist", glist);// 게시물들을 담은 List를 보내기 위해 객체에 담음
		mav.setViewName("mainpage");// 보여줄 페이지를 지정. tiles를 이용하여 페이지를 뿌림
		return mav;// 객체를 리턴
	}

	// 게시판 글쓰기
	@RequestMapping(value = "/gesipan_save.jlab", method = RequestMethod.POST)
	public ModelAndView gesipan_save(HttpServletRequest request, ArticleDto dto) {
		ModelAndView mav = new ModelAndView();
		GE_SERVICE.gesipan_insert(dto);// 게시판 글쓰기를 통하여 넘어온 값들을 DB에 저장.
		List<?> glist = GE_SERVICE.gesi_list();//저장하고 난뒤의 새로운 게시물을 조회.
		mav.addObject("glist", glist);
		mav.setViewName("mainpage");
		return mav;
	}

	// 글쓰기 버튼 이벤트
	@RequestMapping(value = "/gesipan_Write.jlab", method = RequestMethod.POST)
	public ModelAndView gesipan_write(HttpServletRequest request) {
		return new ModelAndView("gesipan_Write");// 글쓰기 페이지로 이동.
	}

	// 상세보기
	@RequestMapping(value = "/gesipan_rview.jlab", method = RequestMethod.GET)
	public ModelAndView gesipan_review(@RequestParam(value="num", defaultValue = "0") int num) {
		ModelAndView mav = new ModelAndView();
		
		if (num == 0) {
			return new ModelAndView( "redirect:/gesipan.jlab");
		} else {
			ArticleDto member = GE_SERVICE.gesi_rview(num);// 게시물 고유번호를 이용하여, DB에서 상세정보를 조회.
			List<?> list = GE_SERVICE.gesi_rview_board(num);// 게시물 고유번호를 이용하여, 해당 게시물의 댓글을 조회.
			
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

	// 답글
	@RequestMapping(value = "/gesipan_reple.jlab", method = RequestMethod.POST)
	public ModelAndView gesipan_reple(HttpServletRequest request, ReplyDto dto) {
		GE_SERVICE.gesi_rview_insert(dto);// 게시판 글쓰기를 통하여 넘어온 값들을 DB에 저장.
		return goArticle(dto.getGasinumber());
	}
	
	// 수정
	@RequestMapping(value = "/gesipan_update.jlab", method = RequestMethod.POST)
	public ModelAndView gesipan_update(HttpServletRequest request, ArticleDto member) { // DTO에 정보를 가져옴
		String nums = request.getParameter("num");// 게시물 고유 번호를 가져옴.
		int num = Integer.parseInt(nums);// String타입을 -> int타입으로 변형.
		GE_SERVICE.gesi_update(member);// DTO오 정보를 이용하여, DB에 수정을 한후		
		return goArticle(num);
	}

	// 삭제
	@RequestMapping(value = "/gesipan_delete.jlab", method = RequestMethod.GET)
	public ModelAndView gesipan_delete(HttpServletRequest request) {
		String nums = request.getParameter("num");// 게시물 고유 번호를 가져옴.
		int num = Integer.parseInt(nums);// String타입을 -> int타입으로 변형.
		GE_SERVICE.gesi_delete(num);// 게시물 고유번호를 가지고 DB에 접근하여 게시물 삭제
		return goArticle(0);
	}
}
