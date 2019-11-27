package com.example.service;

import com.example.entity.UserLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LikedService {
    void save(UserLike userLike);

    //List<UserLike> saveAll(List<UserLike> list);

    //谁给这个人点赞过
    //Page<UserLike> getLikedListByLikedUserId(String likedUserId, Pageable pageable);

    //这个人点赞过谁
    //Page<UserLike> getLikedListByLikedPostId(String likedPostId, Pageable pageable);

    //根据被点赞人和点赞人的id查询是否存在点赞记录
    UserLike getByLikedUserIdAndLikedPostId(String likedUserId, String likedPostId);

    // 将Redis的点赞数据存入数据库
    void transLikedFromRedis2DB();

    // 将Redis的点赞数量存入数据库
    void transLikedCountFromRedis2DB();
}
