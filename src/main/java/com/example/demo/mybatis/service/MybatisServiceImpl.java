package com.example.demo.mybatis.service;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BookDao;

@Service
public class MybatisServiceImpl implements MybatisServiceIF {
	
	@Autowired
	private BookDao bookDao;
	
	// 책 목록 조회
	@Override
	public Map<String, Object> getBookList() {
		Map<String, Object> result = new HashMap<String, Object>();
		
		System.out.println("[MYBATIS]");
		
		List<Map<String, Object>> book_list = bookDao.selectBookList();
		result.put("bookList", book_list);
		
		return result;
	}
	
	// 책 상세정보 조회
	@Override
	public Map<String, Object> getBookDetail(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		// parameter setting
		Map<String, Object> param = getRequestParam(request);
		
		if (param.get("bookSeq") != null) {
			result.put("bookDetail", bookDao.selectBookDetail(param));
		}
		
		return result;
	}
	
	// 책 등록
	@Override
	public Map<String, Object> addBook(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		// parameter setting
		Map<String, Object> param = getRequestParam(request);
		bookDao.insertBook(param);
		
		return result;
	}
	
	// 책 수정
	@Override
	public Map<String, Object> modifyBook(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		// parameter setting
		Map<String, Object> param = getRequestParam(request);
		bookDao.updateBook(param);
		
		return result;
	}
	
	// 책 삭제
	@Override
	public Map<String, Object> removeBook(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		// parameter setting
		Map<String, Object> param = getRequestParam(request);
		bookDao.deleteBook(param);
		
		return result;
	}
	
	// parameter setting
	public Map<String, Object> getRequestParam(HttpServletRequest request) {
		Map<String, Object> param = new HashMap<String, Object>();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String key = names.nextElement();
			param.put(key, request.getParameter(key));
		}
		return param;
	}
	
}
