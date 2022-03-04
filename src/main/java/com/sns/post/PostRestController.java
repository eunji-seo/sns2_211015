package com.sns.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;

@RequestMapping("/post")
@RestController
public class PostRestController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass()); // import slf4j 확인 
	@Autowired
	private PostBO postBO;
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/posts")
	public List<Post> post(){	
		List<Post> result = postBO.getPostList();
		return result;
	}
	/**
	 * 
	 * @param content
	 * @param file
	 * @param request
	 * @return
	 */
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("content") String content,
			@RequestParam(value="file" , required = false) MultipartFile file,
			HttpServletRequest request){
		
		Map<String, Object> result = new HashMap<>(); // 디버깅 request 잘되는 검증
		result.put("result", "success");
		
		// 글쓴이 정보를 가져온다.(세션에서)
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		String userLoginId = (String) session.getAttribute("userLoginId");
		
		if(userId == null) { 
			result.put("result", "error");
			result.put("errorMassage", "로그인 후 사용 가능합니다.");
			return result;
		}
			
		// userId, userLoginId,subject, content, file => BO에 insert 요청함
		postBO.addPost(userId, userLoginId, content, file);
		
		return result;
	}
	
	@DeleteMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("postId") int postId,
			HttpServletRequest request){
		
		Map<String, Object> result = new HashMap<>();
		
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId"); // 직접 권한 검사함
		if(userId == null) {
			result.put("result", "error");
			result.put("errorMessage", "로그인후 사용가능합니다.");
			logger.error("[post delete] 로그인 세션이 없습니다. userId:{}, postId{}", userId , postId); // 데이터 베이스 검증 > 로거에서 요청 주소 찾고 , 로거가 요청했던 단서를 남겨 놓는다.
			return result;
		}
		//postBO;
		result.put("result", "success");
		return result;
	}
}
