package com.sns.like;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeRestController {

	// /like/{postId} // 와일드카드
	@RequestMapping("/like/{postId}") //pathVariable
	public Map<String, Object> like(
			@PathVariable int postId ){
		
		
		
		Map<String, Object> result = new HashMap<>();
		result.put("result", "success");
		
		
		
		
		return result;
		
	}
	
}
