<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

</head>
<style>
	body {
		text-align: center;
	}
	table {
		width: 40%;		
	}
	#header{
		background-color:#FFFFD2;
		font-weight: bold;
	}
	#contentslist{
		background-color:#FCFCFC;
	}
	a {
		text-decoration: none;
	}
	
</style>
<body>
	   <table align="center">
	      <tr>
	    	  <th colspan=5><h3>List</h3>
	     </tr>
	      <tr id=header>
	         <td> No.
	         <td> Title
	         <td> Writer
	         <td> Write_Date
	         <td> View
	      </tr>
		<c:forEach items="${viewAll }" var="list">
			<tr id=contentslist>
				<td>${list.board_seq }</td>
				<td><a href='detail?seq=${list.board_seq }'>${list.title }</a></td>
				<td>${list.id }
				<td>${list.write_date}
				<td>${list.view_count}
			</tr>
		</c:forEach>
	   </table>
	   <br>
	   
	   <!-- 페이징 -->
	   	<div style="display: block; text-align: center;">		
		<c:if test="${paging.startPage != 1 }">
			<a href="list?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}">&lt;</a>
		</c:if>
		<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
			<c:choose>
				<c:when test="${p == paging.nowPage }">
					<b>${p }</b>
				</c:when>
				<c:when test="${p != paging.nowPage }">
					<a href="list?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
			<a href="list?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}">&gt;</a>
		</c:if>
	</div>
</body>
</html>