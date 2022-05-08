package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao {
	protected static final String NAMESPACE = "com.example.demo.book.";

	@Autowired
	private SqlSession sqlSession;

	// 책 목록 조회
	public List<Map<String, Object>> selectBookList(){
		return sqlSession.selectList(NAMESPACE + "selectBookList");
	}
	
	// 책 상세정보 조회
	public Map<String, Object> selectBookDetail(Map<String, Object> param){
		return sqlSession.selectOne(NAMESPACE + "selectBookDetail", param);
	}
	
	// 책 등록
	public int insertBook(Map<String, Object> param) {
		return sqlSession.insert(NAMESPACE + "insertBook", param);
	}
	
	// 책 수정
	public int updateBook(Map<String, Object> param) {
		return sqlSession.update(NAMESPACE + "updateBook", param);
	}

	// 책 삭제
	public int deleteBook(Map<String, Object> param) {
		return sqlSession.delete(NAMESPACE + "deleteBook", param);
	}
}
