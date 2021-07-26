<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script  src="https://code.jquery.com/jquery-3.6.0.js"></script>
<title>Moidfy Page</title>

<style>
/* 제목 스타일 */
    #pageTitle{margin: auto; margin-top: 50px; width: 500px; text-align: center;}
    
/* 수정 내용 스타일 */    
    #modiBox{margin: auto; margin-top: 50px; border: 1px solid black; width: 500px; height: 500px;}
    #title{text-align: center;}
    #title>input{margin-top: 10px; width: 80%;}
    #contents{text-align: center; margin-top: 20px; height: 80%;}
    #contents>textarea{resize:none; width: 100%; height: 100%;}
    
 /* 버튼 박스 */   
    #btnBox{margin: auto; margin-top: 10px; width: 500px; text-align: center;}
</style>

<script>
	$(function(){
		$("#modify").on("click",function(){
			$("#listFrm").attr("action","modifyProc");
			$("#listFrm").submit;
		})
		
		$("#delete").on("click",function(){
			$("#listFrm").attr("action","delete");
			$("#listFrm").submit;
		})
		
		$("#back").on("click",function(){
			location.href="javascript:history.back()";
		})
	})
	
</script>

</head>
<body>
	<form action="" method="post" id="listFrm">
		<!-- page title -->
	    <div id="pageTitle">
	        <h2> 글 수정 </h2>
	    </div>
	    
	    <!--  modify box -->
	    <div id="modiBox">
	    	<input type="text" name="board_seq" value="1">
	        <div id="title">제목: <input type="text" name="title"> </div>
	    <hr>
	        <div id="contents"><textarea name="contents"></textarea></div>
	    </div>
	    
	    <!-- button -->
	    <div id="btnBox">
	        <button class="btn btn-success" id="modify">수정</button>   
	        <button class="btn btn-danger" id="delete">삭제</button>
	        <button class="btn btn-outline-danger" id="back">뒤로</button>
	    </div>
	</form>

</body>
</html>