package com.buk.elasticsearch.repository;

import com.buk.elasticsearch.pojo.document.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * TODO: Elasticsearch存储库
 *
 * @author BuK
 * @since 2020/09/04
 */
public interface UserRepository extends ElasticsearchRepository<User, Long> {

}
