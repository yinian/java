package com.viagra.repository;

import com.viagra.model.Tweet;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 11:35 2019/5/13
 * @Modified By:
 */
@Repository
public interface TweetRepository extends ReactiveMongoRepository<Tweet,String> {
}
