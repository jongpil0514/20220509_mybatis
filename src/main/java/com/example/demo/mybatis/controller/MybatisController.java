package com.example.demo.mybatis.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.mybatis.service.MybatisServiceIF;

@Controller
public class MybatisController {
	
	@Autowired
	private MybatisServiceIF mybatisService;
	
	// 책 목록 조회
	@RequestMapping("/mybatis/getBookList")
	public ModelAndView getBookList() {
		ModelAndView mav = new ModelAndView("book-list");
		mav.addObject("result", mybatisService.getBookList());
		return mav;
	}
	
	@RequestMapping("/mybatis/addBookForm")
	public ModelAndView addBookPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("book-form");
		mav.addObject("result", mybatisService.getBookDetail(request));
		return mav;
	}
	
	@RequestMapping("/mybatis/addBook")
	public ModelAndView addBook(HttpServletRequest request) {
		mybatisService.addBook(request);
		
		// 목록 페이지로 redirect
        RedirectView rv = new RedirectView(request.getContextPath() + "/mybatis/getBookList");
        rv.setExposeModelAttributes(false); // QueryString이 없도록 함.
        return new ModelAndView(rv);
	}
	
	@RequestMapping("/mybatis/modifyBook")
	public ModelAndView modifyBook(HttpServletRequest request) {
		mybatisService.modifyBook(request);
		
		// 목록 페이지로 redirect
        RedirectView rv = new RedirectView(request.getContextPath() + "/mybatis/getBookList");
        rv.setExposeModelAttributes(false); // QueryString이 없도록 함.
        return new ModelAndView(rv);
	}
	
	@RequestMapping("/mybatis/removeBook")
	public ModelAndView removeBook(HttpServletRequest request) {
		mybatisService.removeBook(request);
		
		// 목록 페이지로 redirect
        RedirectView rv = new RedirectView(request.getContextPath() + "/mybatis/getBookList");
        rv.setExposeModelAttributes(false); // QueryString이 없도록 함.
        return new ModelAndView(rv);
	}
}
