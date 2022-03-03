package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.model.CommentView;
import com.sns.like.model.Like;
import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.timeline.model.ContentView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class ContentViewBO {
	
	@Autowired
	private UserBO	userBO;
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private CommentBO commentBO;

	public List<ContentView> generateContentViewList(Integer userId){
		List<ContentView> contentViewList = new ArrayList<>();
		
		
		// post 목록
		List<Post> postList = postBO.getPostList();
		for(Post post: postList) {
			ContentView content = new ContentView();
			// 글 정보
			content.setPost(post);
			
			// 글쓴이 정보
			User user = userBO.getUserById(post.getUserId());
			content.setUser(user);
			// 댓글 정보
			
			List<CommentView> commentList =  commentBO.generateCommentViewByPostId(post.getId());
			content.setCommentList(commentList);
			
			// 좋아요 갯수 세팅
			
		
			
			// 로그인됨 사용자의 좋아요 여부 세팅
			
			
			
			contentViewList.add(content);
		}
		
		return contentViewList;
	}
}
