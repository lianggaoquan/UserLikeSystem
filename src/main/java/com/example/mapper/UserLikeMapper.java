package com.example.mapper;

import com.example.entity.UserLike;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLikeMapper {
    void insertUserLike(String likedUserId, String likedPostId, Integer status);
    UserLike getByLikedUserIdAndLikedPostId(String likedUserId, String likedPostId);
}
