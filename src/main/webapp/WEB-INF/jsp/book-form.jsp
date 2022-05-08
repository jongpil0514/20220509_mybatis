<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="isMybatis" value='${fn:indexOf(requestScope["javax.servlet.forward.request_uri"], "mybatis") ne -1}' />
<c:set var="subUrl" value='${isMybatis ? "mybatis" : "prepared"}' />
<c:set var="submitUrl" value='${not empty result.bookDetail ? "modifyBook" : "addBook"}' />

<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Spring Boot Application with JSP</title>
		<style>
		.title { text-align: center; padding: 20px; font-size: 1.5rem; font-weight: bold; }
		table { margin: auto; }
		table tr th,
		table tr td { border: 1px solid black; padding: 10px; }
		.btn-wrap { margin-top: 20px; text-align: center; }
		a { text-decoration: none; color: black; border: 1px solid black; padding: 3px 8px; border-color: rgb(118, 118, 118); border-radius: 2px; background: #efefef; font-size: 13px; }
		</style>
	</head>
	<body>
		<div class="title">책 정보</div>
		<form action="${pageContext.request.contextPath}/${subUrl}/${submitUrl}">
			<input type="hidden" name="bookSeq" value="${result.bookDetail.BOOK_SEQ}">
			<table>
				<tbody>
					<tr>
						<th>이름</th>
						<td><input type="text" name="bookName" value="${result.bookDetail.BOOK_NAME}"></td>
					</tr>
					<tr>
						<th>책번호</th>
						<td><input type="text" name="bookNum" value="${result.bookDetail.BOOK_NUM}"></td>
					</tr>
					<c:if test="${not empty result.bookDetail}">
						<tr>
							<th>등록일시</th>
							<td>${result.bookDetail.REG_DT}</td>
						</tr>
						<tr>
							<th>수정일시</th>
							<td>${result.bookDetail.MOD_DT}</td>
						</tr>
					</c:if>
				</tbody>
			</table>
			<div class="btn-wrap">
				<c:choose>
					<c:when test='${isMybatis}'>
						<a href="${pageContext.request.contextPath}/${subUrl}/getBookList">목록</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/${subUrl}/getBookList">목록</a>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${not empty result.bookDetail}">
						<button type="submit">수정</button>
					</c:when>
					<c:otherwise>
						<button type="submit">등록</button>
					</c:otherwise>
				</c:choose>
			</div>
		</form>
	</body>
</html>