<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Detail</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>

<style>
.table-title {
   padding-bottom: 15px;
   background: #435d7d;
   color: #fff;
   padding: 16px 30px;
   min-width: 100%;
   margin: -20px -25px 10px;
   border-radius: 3px 3px 0 0;
}
.table-title h2 {margin: 5px 0 0;font-size: 24px;}

/* Content */
#comments li a.edit{color: #FFC107; align:rigth;} #comments li a.delete{color: #F44336;}
.comcont_btn {text-align: right;}
.btn_wrap {padding-top: 50px; border-top: 1px solid #ddd;padding-button:20px;}
.contents {padding: 20px; min-height: 300px; background-color: #FBFBFB;}
.title {overflow: hidden; padding-bottom: 10px; border-bottom: 1px solid #ddd;}
.title ul {overflow:hidden; float:right; padding: 0; margin: 0;}
.title ul li {list-style: none; font-size: 13px; float: left; position: relative; margin-right: 11px; color: #666;}
.title ul li:last-child {margin-right: 0;}
.title ul li:after {display: block; width: 1px; height: 12px; background-color: #ddd; content: ''; position: absolute; top: 50%; margin-top: -6px; margin-left: -5px;}
.files{border-bottom: 1px solid #ddd;}
.btn_wrap {margin-top: 10px; padding-top: 30px;}

</style>
</head>
<body>

	<div class="container p-3 col-12"
		style="margin-bottom: 200px; width: 40%; height: 30%; margin-top: 10%; border: 1px solid rgb(141, 137, 137); border-radius: 10px;">
						<div class="col-sm-6">
							<h2>${list.title}</h2>
						</div>
				<div class="title">
					<ul>
						<li class="view_cnt">${list.id}</li>
						<li class="write_date">${list.write_date}</li>
						<li class="view_cnt">${list.view_count}</li>
					</ul>
				</div>
				<div class="files">
					<h4>Files</h4>
					<c:forEach var="f" items="${flist}">
						<a
							href="${pageContext.request.contextPath}/download.file?file_seq=${f.file_seq}&oriName=${f.oriName}&sysName=${f.sysName}">${f.oriName}</a>
						<br>
					</c:forEach>
				</div>
				<h4>Contents</h4>
				<div></div>
				<div class="contents">${list.contents}</div>
				<div class="btn_wrap" align="right">
					<c:choose>
						<c:when test="${loginID eq list.id}">
							<a href="/board/modifyForm?board_seq=${list.board_seq}" class="btn btn-warning" style="background-color: #ffc107; color: white">수정</a>
							<a href="#boardDeleteForm" class="btn btn-danger" data-toggle="modal">삭제</a>
						</c:when>
					</c:choose>
					<a href="/board/list" id="backBtn" class="btn btn-default pull-left"
						style="background-color: #00285b; color: white">목록</a>
				</div>
			</div>
			
			
			   <!-- Board Delete Modal HTML -->
   <div id="boardDeleteForm" class="modal fade">
      <div class="modal-dialog">
         <div class="modal-content">
            <form
               action="/board/delete?board_seq=${list.board_seq}"
               method="post">
               <div class="modal-header">
                  <h4 class="modal-title">게시글을 삭제하시겠습니까?</h4>
                  <button type="button" class="close" data-dismiss="modal"
                     aria-hidden="true">&times;</button>
               </div>
               <div class="modal-body">
                  <p>삭제된 게시글은 복구할 수 없습니다.</p>
                  <p class="text-warning">
                     <small>해당 게시글의 댓글 또한 삭제됩니다.</small>
                  </p>
               </div>
               <div class="modal-footer">
                  <input type="button" class="btn btn-default" data-dismiss="modal"
                     value="취소">
                     <input type="submit" class="btn btn-danger" value="삭제" id="delete">
               </div>
            </form>
         </div>
      </div>
   </div>

</body>
</html>