package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.model.Comment;
@RequestMapping("/comment")
@RestController
public class CommentRestController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CommentBO commentBO;
	
	
	
	@RequestMapping("/create")
	public Map<String, Object> create(
			
			@RequestParam("postId") int postId,
			@RequestParam("content") String content,
			
			HttpServletRequest request){
		
		HttpSession session = request.getSession();
	Integer userId = (Integer) session.getAttribute("userId");
		
		Map<String, Object> result = new HashMap<>();
		result.put("result", "success");
		
		int row = commentBO.addCommentList(postId, userId, content);
		
		if(row < 1) {
			result.put("error", "fail");
		}
		
		return result;	
		
	}
	
	@DeleteMapping("/delete")
	public Map<String, Object> deleteComment(
			@RequestParam("commentId") int commentId,
			HttpServletRequest request){
		
		Map<String, Object> result = new HashMap<>();
		
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		if(userId == null ) {
			result.put("result", "error");
			result.put("errorMessage", "로그인을 다시 시도해 주세요.");
			logger.error("[comment error]  로그인 세션이 없습니다. userId:{}, commentId:{} ",userId, commentId );
			return result;
		}
		commentBO.deleteCommentByCommentIdAndUserId(commentId, userId);
	
		result.put("result", "success");
		return result;
	}
}
