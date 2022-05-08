package com.example.demo.prepared.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.prepared.service.PreparedServiceIF;

@Controller
public class PreparedController {
	
	@Autowired
	private PreparedServiceIF preparedService;
	
	@RequestMapping("/prepared/getBookList")
	public ModelAndView getBookList() {
		ModelAndView mav = new ModelAndView("book-list");
		mav.addObject("result", preparedService.getBookList());
		return mav;
	}
	
	@RequestMapping("/prepared/addBookForm")
	public ModelAndView addBookPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("book-form");
		mav.addObject("result", preparedService.getBookDetail(request));
		return mav;
	}
	
	@RequestMapping("/prepared/addBook")
	public ModelAndView addBook(HttpServletRequest request) {
		preparedService.addBook(request);
		
		// 목록 페이지로 redirect
        RedirectView rv = new RedirectView(request.getContextPath() + "/prepared/getBookList");
        rv.setExposeModelAttributes(false); // QueryString이 없도록 함.
        return new ModelAndView(rv);
	}
	
	@RequestMapping("/prepared/modifyBook")
	public ModelAndView modifyBook(HttpServletRequest request) {
		preparedService.modifyBook(request);
		
		// 목록 페이지로 redirect
        RedirectView rv = new RedirectView(request.getContextPath() + "/prepared/getBookList");
        rv.setExposeModelAttributes(false); // QueryString이 없도록 함.
        return new ModelAndView(rv);
	}
	
	@RequestMapping("/prepared/removeBook")
	public ModelAndView removeBook(HttpServletRequest request) {
		preparedService.removeBook(request);
		
		// 목록 페이지로 redirect
        RedirectView rv = new RedirectView(request.getContextPath() + "/prepared/getBookList");
        rv.setExposeModelAttributes(false); // QueryString이 없도록 함.
        return new ModelAndView(rv);
	}
}
