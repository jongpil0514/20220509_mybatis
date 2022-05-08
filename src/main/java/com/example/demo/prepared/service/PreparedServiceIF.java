package com.example.demo.prepared.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface PreparedServiceIF {
	
	public Map<String, Object> getBookList();
	public Map<String, Object> getBookDetail(HttpServletRequest request);
	public Map<String, Object> addBook(HttpServletRequest request);
	public Map<String, Object> modifyBook(HttpServletRequest request);
	public Map<String, Object> removeBook(HttpServletRequest request);
	
}
