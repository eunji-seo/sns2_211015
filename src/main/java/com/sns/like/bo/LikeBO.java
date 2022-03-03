package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.dao.LikeDAO;

@Service
public class LikeBO {

	@Autowired
	private LikeDAO likeDAO;
	
	public void addLike (int postId , int userId){
		
		likeDAO.insertLike(postId, userId);		
		
	}
	public boolean exsistLike(int postId, Integer userId) {
		if(userId == null) {
			return false;
		}
		int count= likeDAO.selectExsistLikePostIdUserId(postId, userId);
		return count < 0? true : false;
	}

}
