package com.example.service.impl;

import com.example.entity.UserLike;
import com.example.mapper.UserLikeMapper;
import com.example.service.LikedService;
import com.example.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class LikedServiceImpl implements LikedService {

    @Autowired
    private UserLikeMapper mapper;

    @Autowired
    private RedisService redisService;

    @Override
    public void save(UserLike userLike) {
        mapper.insertUserLike(userLike.getLikedUserId(), userLike.getLikedPostId(), userLike.getStatus());
    }

//    @Override
//    public List<UserLike> saveAll(List<UserLike> list) {
//        return null;
//    }

//    @Override
//    public Page<UserLike> getLikedListByLikedUserId(String likedUserId, Pageable pageable) {
//        return null;
//    }

//    @Override
//    public Page<UserLike> getLikedListByLikedPostId(String likedPostId, Pageable pageable) {
//        return null;
//    }
//
    @Override
    @Transactional
    public UserLike getByLikedUserIdAndLikedPostId(String likedUserId, String likedPostId) {
        return mapper.getByLikedUserIdAndLikedPostId(likedUserId, likedPostId);
    }

    @Override
    public void transLikedFromRedis2DB() {
        List<UserLike> list = redisService.getLikedDataFromRedis();
        for (UserLike like: list){
            UserLike ul = getByLikedUserIdAndLikedPostId(like.getLikedUserId(), like.getLikedPostId());
            if(ul==null){
                save(like);
            } else {
                ul.setStatus(like.getStatus());
                if(ul.getLikedUserId() != null && ul.getLikedPostId() != null){
                    save(ul);
                }
            }
        }
    }

    @Override
    public void transLikedCountFromRedis2DB() {

    }
}
