<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="d-flex justify-content-center m-5">
	<div class="w-40">
	
		<c:if test="${not empty userId}">
		<div class="cleateTimeline-Group write-box border rounded m-3">		
			<textarea id="content" name="content" class="cleateTimeline border-0 w-100" rows="5" ></textarea>
			<div class="cleateTimelineUpload d-flex justify-content-between">
				<div class="file-upload d-flex">
			       <input type="file" id="file" class="d-none" accept=".gif, .jpg, .png, .jpeg">
						<a href="#" id="fileUploadBtn"><img width="35" src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png"></a>
						<div id="fileName" class="ml-2">
						</div>
				</div>				
				<button type="button" id="uploadBtn" class="btn btn-primary">업로드</button>
			</div>	
		</div>
		</c:if>
		<div class="timelinelist-group mt-3">
			<div class="nickname-group mt-3">
			<c:forEach var="item" items="${postList}">
				<div class="bg-secondary h-10 border rounded d-flex justify-content-between pr-2">
					<span class="display-5 ml-2 text-white"><b>nickname</b></span>
						<%-- 클릭할 수 있는 ... 버튼 이미지 --%>
						<a href="#" class="more-btn">
							<img src="https://www.iconninja.com/files/860/824/939/more-icon.png" width="30">
						</a>
					
				</div>
				</ul class="list m-2">
					<li><img width="400" src="${item.imagePath}" alt="이미지" class="m-2"/></li>
					<li>${item.content}</li>
				<ul>
			
				<div class="d-flex justify-content-start mt-2">
					<a href="#" class="mr-2"><img width="18" src="https://www.iconninja.com/files/527/809/128/heart-icon.png"/></a>
					<span class=""><b>좋아요 개</b></span>
				</div>
			</div>
			<div class="comment-group m-2">
				<div class="bg-secondary h-10 border rounded ">
					<span class="ml-2 text-white"><b>댓글</b></span>
				</div>
				</div class="comment-list ">
					<span class="ml-2"><b>d</b></span>
					<span>d</span>
					<a href="#" class="commentDelBtn"><img src="https://www.iconninja.com/files/603/22/506/x-icon.png" width="10px" height="10px"></a>
			<div>
				<c:if test="${not empty userId}">
					<div class="cleate-comment-group d-flex justify-content-start m-2">
						<input type="text" id="commentText${post.id}" name="commentText" class="form-control" placeholder="댓글을 입력해주세요.">
						<button href="" type="button" class="commentBtn btn btn-none" data-post-id="${post.id}">게시</button>
					</div>	
				</c:if>
				</c:forEach>		
			</div>
			<div>
			</div>
		</div>	
	</div>
</div>
<script>
$(document).ready(function(){
	
	// 파일 업로드 이미지 버튼 클릭 - 사용자가 업로드를 할 수 있게 해줌
	$('#fileUploadBtn').on('click', function(e) {
		e.preventDefault(); // 기본 작동 중지
		$('#file').click(); // input file을 클릭한 것과 같은 동작
	});
	
	// 사용자가 파일 업로드를 했을 때 유효성 확인 및 업로드 된 파일 이름을 노출
	$('#file').on('change', function(e) {
		var name = e.target.files[0].name;
		
		// 확장자 유효성 확인
		var extension = name.split('.');
		if (extension.length < 2 || 
		 	(extension[extension.length - 1] != 'gif' 
		 	&& extension[extension.length - 1] != 'png' 
		 	&& extension[extension.length - 1] != 'jpg'
		 	&& extension[extension.length - 1] != 'jpeg')) {
		 	
		 	alert("이미지 파일만 업로드 할 수 있습니다.");
		 	$(this).val(""); // 이미 올라간 것을 확인한 것이기 때문에 비워주어야 한다.
		 	return;
		 }
		 
		 $("#fileName").text(name);
	});
	
	
	$('#uploadBtn').on('click', function(e){
		//alert("click");
		let content = $('#content').val();
		//console.log(content);
		
		// 파일이 업로드 된 경우 확장자 체크
		let file = $('#file').val(); // 파일 경로만 가져온다
		// console.log(file); //C:\fakepath\partlyCloudy.jpg
		//validation
		if(file != ''){
			let ext = file.split('.').pop().toLowerCase(); // 파일 경로를 .으로 나누고 확장자가 있는 마지막 문자열을 가져온 후 모두 소문자로 변경 
			if($.inArray(ext, ['gif','png','jpg','jpeg']) == -1){ // -1 확장자가 포함되지 않음
				alert("gif,png,jpg,jpeg 파일만 업로드 할 수 있습니다.");
				 $('#file').val(''); // 파일을 비운다.
				return;
			}
			
		}
		
		// 폼태그를 자바스크립트에서 만든다.
		let formData = new FormData();
		formData.append("content", content);
		formData.append("file", $('#file')[0].files[0]); // $('#file')[0] 첫번째 input file 태그를 의미 , flies[0] 업로드된 첫번째 파일을 의미 
		
		//AJAX from 데이터 전송 $.ajax({ ㅌ
		$.ajax({
			type: "POST"
			, url: "/post/create"
			, data: formData
			, enctype: "multipart/form-data" // 파일업로드를 위한 필수 설정
			, processData: false // 파일업로드를 위한 필수 설정
			, contentType: false // 파일업로드를 위한 필수 설정
			//---request
			, success: function(data){ //response
				if(data.result == 'success'){
					alert("게시글이 저장되었습니다.");
					location.href="/timeline/timeline_list_view";
				}else{
					alert("로그인 후 사용가능 합니다.")
					location.href="/user/sign_in_view";
				}
				
			}
			, error: function(e) {
				alert("게시글이 저장에 실패했습니다. 관리자에게 문의해주세요.");
			}
		});
	});
		
	// 댓글쓰기 - 게시 버튼 클릭
	$('.commentBtn').on('click',function(){
	let postId= $(this).data('post-id'); //data-post-id 
		alert(postId);
	
		let commetText = $('#commentText' + postId).val();
		alert(commetText);
		
		$.ajax({
			type:"post"
			,url:"/comment/create"
			,data: {"postId":postId, "content":commetText}
			,success: function(data){
				if(data.result == 'success'){
					alert("댓글이 입력되었습니다.")
					location.reload();
				}
				
			}
			,error: function(e){
				alert("댓글입력에 실패하였습니다. 관리자에 연락해주세요.")	
			}
			
			
			
		});
		
	});
	
	
});
</script>