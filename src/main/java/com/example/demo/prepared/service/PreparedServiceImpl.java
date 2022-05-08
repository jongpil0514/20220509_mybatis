package com.example.demo.prepared.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class PreparedServiceImpl implements PreparedServiceIF {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Value("${spring.datasource.url}")
	private String DB_URL;
	
	@Value("${spring.datasource.username}")
	private String DB_USERNAME;
	
	@Value("${spring.datasource.password}")
	private String DB_PASSWORD;
	
	// 책 목록 조회
	@Override
	public Map<String, Object> getBookList() {
		Map<String, Object> result = new HashMap<String, Object>();
		PreparedStatement pstmt = null;
		
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);) {
			
			System.out.println("[PREPARED STATEMENT]");
			
			String sql = "SELECT BOOK_SEQ, BOOK_NAME, BOOK_NUM, REG_DT, MOD_DT FROM TB_BOOK";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			ArrayList<Map<String, Object>> book_list = convertResultSetToArrayList(rs);
			result.put("bookList", book_list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// 책 상세정보 조회
	@Override
	public Map<String, Object> getBookDetail(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		PreparedStatement pstmt = null;
		
		// parameter setting
		Map<String, Object> param = getRequestParam(request);
		
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);) {
			if (param.get("bookSeq") != null) {
				String sql = "SELECT BOOK_SEQ, BOOK_NAME, BOOK_NUM, REG_DT, MOD_DT FROM TB_BOOK WHERE BOOK_SEQ = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, (String) param.get("bookSeq"));
				ResultSet rs = pstmt.executeQuery();
				ArrayList<Map<String, Object>> list = convertResultSetToArrayList(rs);
				
				result.put("bookDetail", list.get(0));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// 책등록
	@Override
	public Map<String, Object> addBook(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		PreparedStatement pstmt = null;
		
		// parameter setting
		Map<String, Object> param = getRequestParam(request);
		
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);) {
			
			String sql = "INSERT INTO TB_BOOK ( BOOK_NAME, BOOK_NUM, REG_DT, MOD_DT ) VALUES ( ?, ?, NOW(), NOW() )";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String) param.get("bookName"));
			pstmt.setString(2, (String) param.get("bookNum"));
			pstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// 책 수정
	@Override
	public Map<String, Object> modifyBook(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		PreparedStatement pstmt = null;
		
		// parameter setting
		Map<String, Object> param = getRequestParam(request);
		
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);) {
			
			String sql = "UPDATE TB_BOOK SET BOOK_NAME = ?, BOOK_NUM = ?, MOD_DT = NOW() WHERE BOOK_SEQ = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String) param.get("bookName"));
			pstmt.setString(2, (String) param.get("bookNum"));
			pstmt.setString(3, (String) param.get("bookSeq"));
			pstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// 책 삭제
	@Override
	public Map<String, Object> removeBook(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		PreparedStatement pstmt = null;
		
		// parameter setting
		Map<String, Object> param = getRequestParam(request);
		
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);) {
			
			String sql = "DELETE FROM TB_BOOK WHERE BOOK_SEQ = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String) param.get("bookSeq"));
			pstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
	
	// ResultSet -> ArrayList
	public ArrayList<Map<String,Object>> convertResultSetToArrayList(ResultSet rs) throws SQLException {
	    ResultSetMetaData md = rs.getMetaData();
	    int columns = md.getColumnCount();
	    ArrayList<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	 
	    while(rs.next()) {
	        HashMap<String,Object> row = new HashMap<String, Object>(columns);
	        for(int i=1; i<=columns; ++i) {
	            row.put(md.getColumnName(i), rs.getObject(i));
	        }
	        list.add(row);
	    }
	 
	    return list;
	}

}
