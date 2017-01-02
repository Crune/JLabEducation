package com.jlab.education.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jlab.education.dao.MiDao;

@Controller
public class MiController {

	MiDao dao = MiDao.getInstance();

	@RequestMapping(value = "/mi/base_sawon_sel")
	public ModelAndView base_sawon_sel(HttpServletRequest request) {
		return new ModelAndView("mi/base_sawon_sel");
	}

	@RequestMapping(value = "/mi/base_sawon_sel2")
	public void base_sawon_sel2(HttpServletResponse response, String name) {
		dao.test(name, response);
	}

	@RequestMapping(value = "/mi/base_sawon_tr")
	public ModelAndView base_sawon_tr(HttpServletRequest request) {
		return new ModelAndView("mi/base_sawon_tr");
	}

	@RequestMapping(value = "/mi/base_code_sel")
	public ModelAndView base_code_sel(HttpServletRequest request) {
		return new ModelAndView("mi/base_code_sel");
	}
}
