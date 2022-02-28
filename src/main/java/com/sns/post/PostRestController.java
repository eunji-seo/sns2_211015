package com.sns.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private PostBO postBO;

	@RequestMapping("/posts")
	public List<Post> post(){	
		List<Post> result = postBO.getPostList();
		return result;
	}
	
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
	
}
