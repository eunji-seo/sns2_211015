<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="signUpBox container mt-5">
	<div class="p-3">
		<h1>회원 가입</h1>
		<form id="signUpForm" action="/user/sign_up" method="post">
		<div class="form-group">
			<label>loginId</label>
			<div class="d-flex justify-content-between">
				<input type="text" class="form-control col-5" id="loginId" name="loginId">
				<button id="loginIdChackBtn" type="button"class="btn btn-success">중복 체크</button>
			</div>
			<div id="ischackLength" class="small text-danger d-none">4자 이상 입력해주세요.</div>
			<div id="ischackDupuilcated" class="small text-danger d-none">중복된 아이디가 있습니다.</div>
			<div id="ischackOk" class="small text-success d-none">사용 가능한 아이디입니다.</div>
		</div>
		<div>
			<label>password</label>
			<input type="password" class="form-control col-5" id="password" name="password">	
		</div>
		<div>
			<label>password confim</label>
			<input type="password" class="form-control col-5" id="passwordConfim" name="passwordConfim">	
		</div>
		<div>
			<label>이름</label>
			<input type="text" class="form-control col-5" id="name" name="name">	
		</div>
		<div>
			<label>이메일</label>
			<input type="text" class="form-control col-7" id="email" name="email">	
		</div>		
		<div>
			<label>프로필 이미지 URL</label>
			<div class="d-flex justify-content-between mb-3">
				<input type="text" class="form-control col-7" id="profileImageUrl" name="profileImageUrl">
				<button type="button"class="btn btn-secondary ">업로드</button>
			</div>	
		</div>	
		<div class="d-flex justify-content-center">		
			<button type="submit" class="joinBtn btn btn-primary">회원가입</button>
		</div>
		</form>
	</div>
</div>
<script>
	$(document).ready(function(){
		$('#loginIdChackBtn').on('click',function(){
			//alert("클릭");
			let loginId = $('#loginId').val().trim(); 	   
			
			//초기화
			$('#ischackLength').addClass('d-none');
			$('#ischackDupuilcated').addClass('d-none');
			$('#ischackOk').addClass('d-none');
			
			if(loginId.length < 4){
				$('#ischackLength').removeClass('d-none');
				return;
			}
			
			$.ajax({
				url:'/user/is_duplicated_id'
				,data:{"loginId" : loginId}
				,success: function(data){
					if(data.result){
						$('#ischackDupuilcated').removeClass('d-none');
					} else if(data.result == false){
						$('#ischackOk').removeClass('d-none');
					}
					
				}
				,error: function(e){
					alert("아이디 중복확인에 실패했습니다. 관리자에게 문의해주세요.");
				}
				
			});
			
			
			
		});
		
		$('#signUpForm').on('submit',function(e){
			e.preventDefault();
			// alert("click");
			let loginId = $('#loginId').val().trim(); 	
			if(loginId.length < 1){
				alert("아이디를 입력해주세요.");
				return false;
			}
			let password = $('#password').val(); 	
			let passwordConfim = $('#passwordConfim').val(); 	
			if(password == '' || passwordConfim == ''){
				alert("비밀번호를 입력해주세요.");
				return false;
			}
			if(password != passwordConfim ){
				alert("비밀번호가 일치 하지 않습니다.");
				$('#password').val('');
				$('#passwordConfim').val('');
				return false;
			}
			let name = $('#name').val().trim(); 	
			if(name == ''){
				alert("이름을 입력해주세요.");
				return false;
			}
			let email = $('#email').val().trim(); 	
			if(email == ''){
				alert("이메일을 입력해주세요.");
				return false;
			}
			
			if($('#ischackOk').hasClass('d-none')){
				alert("아이디 중복 체크를 해주세요.")
				return false;
			}
			
			let url = $(this).attr('action');
			let params = $(this).serialize();
			
			$.post(url, params)
			.done (function(data){
				if(data.result == 'success'){
					alert("환영합니다. 회원가입이 완료 되었습니다.")
					location.href= "/user/sign_in_view";
				}else {
					alert("회원가입이 실패하였습니다. 관리자에 연락부탁드립니다.")
				}
				
			});
			
		});
		
		
		
		// validation check
		
	});
</script>