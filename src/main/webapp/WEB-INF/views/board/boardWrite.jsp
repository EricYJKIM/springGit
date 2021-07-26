<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
	
<style>
	body{padding-top:20px;}
	.container{width:1500px;}
</style>

<script>
  $(function() {
      $("#toList").on("click", function() {
            location.href = "javascript:history.back()";
      })
</script>

</head>
<body>
	<div class="container">
		<form action="/board/writeProc" method="post" enctype="multipart/form-data" id="frm">
			<div class="row">
				<div class="card-header bg-transparent border-secondary text-secondary col-12">
					<h4 class="card-title" id="title2">글 쓰기</h4>
				</div>
				<div>
					<input type="file" name="file" multiple>
				</div>
				<div class="col-12">
					<input type=text class="form-control mb-2" placeholder="제목을 입력하세요." name="title" id="title">
				</div>
				<div class="col-12" style="height: 600px"><br>
					<textarea id="contents" name="contents" style="width:100%;height:80%;"></textarea>
				</div>
				<div class="col-12 btns" align="right">
					<button type="submit" class="btn btn-outline-secondary" id="toWrite">글 쓰기</button>
					<button type="button" class="btn btn-outline-secondary" id="toList">목록으로</button>
				</div>
			</div>
		</form>
	</div>

</body>
</html>