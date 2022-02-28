<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center align-items-center">
	<div class="login-box text-center">
		<h1 class="mb-4">로그인</h1>
		<form id="loginForm" action="/timeline/timeline_list_view" method="post">
		<div class="d-flex justify-content-center mr-2 mb-3">
			<span class="">아이디</span>
			<input type="text" id="loginId" name="loginId" class="form-control input-form">
		</div>
		<div class="d-flex justify-content-center mr-3 mb-3">
			<span class="">비밀번호</span>
			<input type="password" id="password" name="password" class="form-control input-form">
		</div>
		<div class="mr-3 mb-3">
			<input type="submit" class="joinBtn btn btn-primary btn-form mb-2" value="로그인">
			<a href="/user/sign_up_view" type="button" class="signUpBtn btn btn-dark btn-form">회원가입</a>
		</div>
		</form>
	</div>
	
</div>
<script>
$(document).ready(function(){
	 $('#loginForm').on('submit',function(e){
		// e.preventDefault(e);
		 //validation
		 let loginId = $('#loginId').val().trim();
		 if(loginId ==''){
			 alert("아이디를 입력해주세요.");
			 return;
		 }
		 let password = $('#password').val().trim();
		 if(password ==''){
			 alert("비밀번호를 입력해주세요.");
			 return;
		 }
		 
		 let url= $(this).attr('action');
		 let params = $(this).serialize();
		 
		 $.post(url, params)
		 .done(function(data){
			 if(data.result == "success"){
				 alert(data.success);
				 location.href="/timeline/timeline_list_view";
			 } else {
				 alert(data.errorMassage);
			 }
			 
		 });
		 
	 });
});
</script>