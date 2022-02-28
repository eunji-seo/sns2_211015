package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.model.Comment;
import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.timeline.model.ContentView;

@Service
public class ContentViewBO {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private CommentBO commentBO;

	public List<ContentView> getContentList(){
		List<ContentView> contentList = new ArrayList<>();
		
		// post 목록
		List<Post> postList = postBO.getPostList();
		for(Post post: postList) {
			ContentView contentView = new ContentView();
			contentView.setPost(post);
			
			List<Comment> commentList = commentBO.getCommentList(post.getId());
			contentView.setCommentList(commentList);
			
			contentList.add(contentView);
			
		}
		return contentList;
	}
}
