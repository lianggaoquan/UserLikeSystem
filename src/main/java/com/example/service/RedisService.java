package com.example.service;

import com.example.entity.LikedCountDTO;
import com.example.entity.UserLike;

import java.util.List;

public interface RedisService {
    // likedUserId: 被点赞人的id
    // likedPostId: 点赞人的id

    //点赞，状态为 1
    void saveLiked2Redis(String likedUserId, String likedPostId);

    //取消点赞，状态为 0
    void unlikeFromRedis(String likedUserId, String likedPostId);

    //从Redis中删除一条数据
    void deleteLikedFromRedis(String likedUserId, String likedPostId);

    //点赞数加一
    void incrementLikedCount(String likedUserId);

    //点赞数减一
    void decrementLikedCount(String likedUserId);

    //获取Redis中所有的点赞数据
    List<UserLike> getLikedDataFromRedis();

    //获取Redis中存储的点赞数量
    List<LikedCountDTO> getLikedCountFromRedis();
}
