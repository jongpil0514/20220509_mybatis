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

	// å ��� ��ȸ
	public List<Map<String, Object>> selectBookList(){
		return sqlSession.selectList(NAMESPACE + "selectBookList");
	}
	
	// å ������ ��ȸ
	public Map<String, Object> selectBookDetail(Map<String, Object> param){
		return sqlSession.selectOne(NAMESPACE + "selectBookDetail", param);
	}
	
	// å ���
	public int insertBook(Map<String, Object> param) {
		return sqlSession.insert(NAMESPACE + "insertBook", param);
	}
	
	// å ����
	public int updateBook(Map<String, Object> param) {
		return sqlSession.update(NAMESPACE + "updateBook", param);
	}

	// å ����
	public int deleteBook(Map<String, Object> param) {
		return sqlSession.delete(NAMESPACE + "deleteBook", param);
	}
}
