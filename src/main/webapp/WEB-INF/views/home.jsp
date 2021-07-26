<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>

<script>
	$(function() {
		$("#logout").on("click", function() {
			location.href = "/member/logout";
		})
		$("#memberOut").on("click", function() {
         let result=confirm("정말 탈퇴하시겠습니까?");
         if(result){
            location.href = "/member/memberOut";
         }
      })
		$("#signup").on("click", function() {
			location.href = "/member/signup";
		})
		
	})
</script>
<style>
.wrapper {
	margin: 40px 120px 0px 120px;
	text-align: center;
}

.img>img {
	width: 100% height:100%
}

.title {
	margin: 30px 0px 60px 0px;
}

.menu {
	width: 800px;
	height: 150px;
	overflow: hidden;
	display: flex;
	margin: auto;
}

.menu>div {
	height: 70px;
	width: 200px;
	border: 1px solid #ddd;
	margin: auto;
	line-height: 60px;
}

.menu>div:hover {
	background-color: #ddd;
	text-decoration: none; !important
	
}


a {
	text-decoration: none;
	color: black;
	display: block;
	width: 100%;
	height: 100%;
}
</style>
</head>
<body>

	<c:choose>
		<c:when test="${loginID==null }">
			<div class="container p-3 col-12"
				style="width: 30%; height: 30%; margin-top: 10%; border: 1px solid rgb(141, 137, 137); border-radius: 10px;">
				<form action="/member/loginProc" method="post">
					<h3>로그인</h3>
					<div class="form-group">
						<label for="exampleInputEmail1">ID</label> <input type="text"
							class="form-control" id="id" name="id" placeholder="아이디를 입력해 주세요."
							aria-describedby="emailHelp"> <small id="emailHelp"
							class="form-text text-muted">저희는 아이디를 다른 사람과 공유하지 않습니다.</small>
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">Password</label> <input
							type="password" class="form-control" id="pw" name="pw"
							placeholder="비밀번호를 입력해 주세요.">
					</div>

					<button type="submit" class="btn btn-secondary"
						style="margin-left: 30%;">로그인</button>
					<button type="button" class="btn btn-secondary" id="signup">회원가입</button>
				</form>

			</div>
		</c:when>
		<c:otherwise>
			<div class=wrapper>
				<div class=img>
					<img src="/img/user.png">
				</div>
				<div class="title">${loginID }님</div>
				<div class="menu">
					<div>
						<a href="#">게시판</a>
					</div>
					<div>
						<a href="/member/logout">로그아웃</a>
					</div>
					<div>
						<a href="/member/memberOut" id="memberOut">회원탈퇴</a>
					</div>
				</div>

			</div>
		</c:otherwise>

	</c:choose>

</body>
</html>