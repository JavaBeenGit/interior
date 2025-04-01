package com.interior.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("")
public class HomeController {

	@RequestMapping("/index")
	@ResponseBody
	public String index() {
		return "heell";

	}

//	@Override
//	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//		ModelAndView mv = new ModelAndView();
//		// mv.addObject("data", "test jibin");
//		mv.setViewName("root.index");
//
//		return mv;
//	}

}
