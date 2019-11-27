package com.example.service.impl;

import com.example.entity.LikedCountDTO;
import com.example.entity.LikedStatusEnum;
import com.example.entity.UserLike;
import com.example.service.LikedService;
import com.example.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private LikedService likedService;

    @Override
    public void saveLiked2Redis(String likedUserId, String likedPostId) {
        String key = likedUserId + "::" + likedPostId;
        redisTemplate.opsForValue().set(key, LikedStatusEnum.LIKE.getCode());
    }

    @Override
    public void unlikeFromRedis(String likedUserId, String likedPostId) {

    }

    @Override
    public void deleteLikedFromRedis(String likedUserId, String likedPostId) {

    }

    @Override
    public void incrementLikedCount(String likedUserId) {

    }

    @Override
    public void decrementLikedCount(String likedUserId) {

    }

    @Override
    public List<UserLike> getLikedDataFromRedis() {
        Set<Object> set = redisTemplate.keys("*");
        List<UserLike> list = new ArrayList<>();

        for (Object k:set){
            String key = (String) k;
            String[] temp = key.split("::");
            String likedUserId = temp[0];
            String likedPostId = temp[1];
            Integer value = Integer.parseInt((String) redisTemplate.opsForValue().get(key));
            UserLike userLike = new UserLike(likedUserId,likedPostId,value);
            list.add(userLike);
        }
        return list;
    }

    @Override
    public List<LikedCountDTO> getLikedCountFromRedis() {
        return null;
    }
}
