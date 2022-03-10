package com.sns.post.bo;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.common.FileMangerService;
import com.sns.like.bo.LikeBO;
import com.sns.post.dao.PostDAO;
import com.sns.post.model.Post;

@Service
public class PostBO {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private FileMangerService fileManager;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	public List<Post> getPostList(){
		return postDAO.selectPostList();
	}
	
	public Post getPostByPostIdAndUserId(int postId, int userId) {
		return postDAO.selectPostByPostIdAndUserId(postId, userId);
	}
	
	// userId, subject, content, file => BO에 insert 요청함
	public void addPost(int userId, String userLoginId, String content, MultipartFile file) { // MultipartFile file 주소로 들어가는 형태로 가공을 한다 BO에서
		String imagePath = null;
		if(file != null) {
			imagePath = fileManager.saveFile(userLoginId, file);
		}
	// DAO insert
		postDAO.insertPost(userId, content, imagePath);
	}
	public void deletePostByPostIdANDUserId(int postId, int userId) {
		// postId로 select post //로깅에 남겨두는것이 좋다 if문으로 logger 처리
		Post post = getPostByPostIdAndUserId(postId, userId);  
	
		if(post == null) {
			logger.warn("[post delete] 삭제할 게시물이 없습니다. ");
			return;
		}
		
		// 이미지가 있으면 이미지 삭제 
		if(post.getImagePath() != null) {
			try {
				fileManager.deleteFile(post.getImagePath());
			} catch (IOException e) {
				logger.error("[delete post] 이미지 삭제 실패, postId{}, path:{} ",postId, post.getImagePath());
			}
		}
		// 글 삭제 byPostIdUserId 종속(좋아요, 댓글)
		postDAO.deletePost(postId, userId);
		// 댓글들 삭제 byPostId 다른 테이블에 postId를 넣어 삭제된 글 표시함 (Batch System(spring Batch) 일정시간에 수행(job) 1000행씩 지운다)
		commentBO.deleteCommentsByPostId(postId);
		// 좋아요들 삭제 byPostId 
		likeBO.deleteLikeByPostId(postId);
		
	}
}