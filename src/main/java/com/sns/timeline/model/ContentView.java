package com.sns.timeline.model;

import java.util.List;

import com.sns.comment.model.CommentView;
import com.sns.post.model.Post;
import com.sns.user.model.User;

// 타임라인 카드 하나 
public class ContentView { // contentView.post.id

	private Post post; // 글
	private User user; // 글쓴이
	private List<CommentView> commentList; // 글의 댓글  // ${content.commentList.user.name} 댓글쓴이
	//private List<Like> likeList;
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<CommentView> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<CommentView> commentList) {
		this.commentList = commentList;
	}
	
	
	
	
}
