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
}