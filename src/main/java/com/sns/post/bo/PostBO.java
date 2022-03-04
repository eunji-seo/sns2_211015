package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.FileMangerService;
import com.sns.post.dao.PostDAO;
import com.sns.post.model.Post;

@Service
public class PostBO {

	private Logger logger = logger
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private FileMangerService fileManger;
	
	public List<Post> getPostList(){
		return postDAO.selectPostList();
	}
	
	// userId, subject, content, file => BO에 insert 요청함
	public void addPost(int userId, String userLoginId, String content, MultipartFile file) { // MultipartFile file 주소로 들어가는 형태로 가공을 한다 BO에서
		String imagePath = null;
		if(file != null) {
			imagePath = fileManger.saveFile(userLoginId, file);
		}
	// DAO insert
		postDAO.insertPost(userId, content, imagePath);
	}
	public void deletePostByPostIdANDUserId(int postId, int userId) {
		// postId로 select post //로깅에 남겨두는것이 좋다 if문으로 logger 처리
		List<Post> post = getPostList();  
	
		if(post == null) {
			logger.wran("[post delete] 삭제할 게시물이 없습니다. ");
			
		}
		
		// 이미지가 있으면 이미지 삭제 
		
		// 글 삭제 byPostIdUserId 종속(좋아요, 댓글)
		
		// 댓글들 삭제 byPostId 다른 테이블에 postId를 넣어 삭제된 글 표시함 (Batch System(spring Batch) 일정시간에 수행(job) 1000행씩 지운다)
			
		// 좋아요들 삭제 byPostId 
		
	}
}