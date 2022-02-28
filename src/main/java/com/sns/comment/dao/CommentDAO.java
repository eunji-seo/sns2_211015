package com.sns.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.comment.model.Comment;

@Repository
public interface CommentDAO {

	public List<Comment> selectCommentList(int postId);
	
	public int insertCommentList(
			@Param("postId") int postId,
			@Param("userId") int userId, 
			@Param("content") String content);
}
