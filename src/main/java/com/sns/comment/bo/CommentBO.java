package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.dao.CommentDAO;
import com.sns.comment.model.Comment;
import com.sns.comment.model.CommentView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class CommentBO {
	
	@Autowired
	private CommentDAO commentDAO; // BO는 남의 DAO 을 부르지 않고BO 을 부른다
	
	@Autowired
	private UserBO userBO;  // userBO 가 커먼트비오를 또 부르면 상호참조 에러 발생됨
	
	public int addCommentList(int postId, int userId, String content) {
		return commentDAO.insertCommentList(postId, userId, content);
	}
	
	public List<Comment> getCommentListByPostId(int postId){
		return commentDAO.selectCommentListByPostId(postId);
	}
	
	public List<CommentView> generateCommentViewByPostId(int postId){ // generate data 의 가공
		List<CommentView> resultList = new ArrayList<>();
		List<Comment> commentList = getCommentListByPostId(postId);
		
		for(Comment comment : commentList) { // Comment -> CommentView 
			CommentView commentView = new CommentView();
			// 댓글
			commentView.setComment(comment);
			
			// 댓글쓴이
			User user = userBO.getUserById(comment.getUserId()); // 댓글쓴이 객체를 통으로 넣어준다
			commentView.setUser(user); // 추가적으로 넣을 수있겝
			
			resultList.add(commentView); // 마지막으로 댓글을 넣어 준다.
			
		}
		
		return resultList;
		 
	}


}
