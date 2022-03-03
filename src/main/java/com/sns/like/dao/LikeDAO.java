package com.sns.like.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface LikeDAO {

	public int likeCount(int postId);
}
