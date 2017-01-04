package com.jlab.education.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.jlab.education.dto.SawonDto;
import com.jlab.education.mi.Mi;
import com.jlab.education.mi.MiDTO;
import com.jlab.education.service.MiService;

@Controller
@RequestMapping(value = "mi")
public class MiController {

	@Autowired
	private MiService serv;

	@Resource(name = "miView")
	private View miView;
	
	@RequestMapping(value = "/selSawon.do")
	public ModelAndView base_sawon_sel(MiDTO in, String name) {
		ModelAndView mav = new ModelAndView(miView);
		try {
			List<SawonDto> rst = serv.findSawon(name);
			Mi.setResult(mav, "ds_sawon", rst, "0", "SUCC");
		} catch (Exception e) {
			e.printStackTrace();
			Mi.setResult(mav, "-1", e.getMessage());
		}
		
		return mav;
		//return new ModelAndView("mi/base_sawon_sel");
	}

	@RequestMapping(value = "/trSawon.do")
	public ModelAndView base_sawon_tr(MiDTO in) {
		ModelAndView mav = new ModelAndView(miView);
		try {
			// 입력된 수정사항 DB 반영 
			serv.modifySawon(in.getDSList().get("input"));
			
			// 수정된 결과물 반환
			List<SawonDto> rst = serv.findSawon("");
			
			Mi.setResult(mav, "ds_sawon", rst, "0", "SUCC");
		} catch (Exception e) {
			e.printStackTrace();
			Mi.setResult(mav, "-1", e.getMessage());
		}
		
		return mav;
		//return new ModelAndView("mi/base_sawon_tr");
	}

	@RequestMapping(value = "/selCode.do")
	public ModelAndView base_code_sel(MiDTO in) {
		ModelAndView mav = new ModelAndView(miView);
		try {
			Map<String, List<?>> rst = serv.getInitCode();
			Mi.setResult(mav, rst, "0", "SUCC");
		} catch (Exception e) {
			e.printStackTrace();
			Mi.setResult(mav, "-1", e.getMessage());
		}
		
		return mav;
		//return new ModelAndView("mi/base_code_sel");
	}
}
