<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="isMybatis" value='${fn:indexOf(requestScope["javax.servlet.forward.request_uri"], "mybatis") ne -1}' />
<c:set var="subUrl" value='${isMybatis ? "mybatis" : "prepared"}' />

<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Spring Boot Application with JSP</title>
		<style>
		.title { text-align: center; padding: 20px; font-size: 1.5rem; font-weight: bold; }
		table { margin: auto; }
		table tr th,
		table tr td { border: 1px solid black; padding: 10px; }
		.btn-wrap { margin: 10px 0; text-align: center; }
		a { text-decoration: none; color: black; border: 1px solid black; padding: 3px 8px; border-color: rgb(118, 118, 118); border-radius: 2px; background: #efefef; font-size: 13px; }
		</style>
	</head>
	<body>
		<div class="title">책 목록</div>
		<div class="btn-wrap">
			<a href="${pageContext.request.contextPath}/${subUrl}/addBookForm">등록</a>
		</div>
		<table>
			<thead>
				<tr>
					<th>순번</th>
					<th>이름</th>
					<th>책번호</th>
					<th>등록일시</th>
					<th>수정일시</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${result.bookList}" var="book">
					<tr>
						<td>${book.BOOK_SEQ}</td>
						<td>${book.BOOK_NAME}</td>
						<td>${book.BOOK_NUM}</td>
						<td>${book.REG_DT}</td>
						<td>${book.MOD_DT}</td>
						<td>
							<a href="${pageContext.request.contextPath}/${subUrl}/addBookForm?bookSeq=${book.BOOK_SEQ}">수정</a>
							<a href="${pageContext.request.contextPath}/${subUrl}/removeBook?bookSeq=${book.BOOK_SEQ}">삭제</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>