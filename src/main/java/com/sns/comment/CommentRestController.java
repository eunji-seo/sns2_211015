package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.bo.CommentBO;
@RequestMapping("/comment")
@RestController
public class CommentRestController {
	
	@Autowired
	private CommentBO commentBO;
	
	
	@RequestMapping("/create")
	public Map<String, Object> create(			
			@RequestParam("postId") int postId,
			@RequestParam("content") String content
			
			){
		Map<String, Object> result = new HashMap<>();
		result.put("result", "success");
		
		int row = commentBO.addCommentList(postId, postId, content);
		
		if(row < 1) {
			result.put("error", "fail");
		}
		
		return result;	
		
	}
	
}
