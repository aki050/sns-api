package com.example.sns_api.service;

import com.example.sns_api.model.Post;
import com.example.sns_api.repository.PostRepository;
import org.springframework.stereotype.Service;
import java.util.List;

// 【Serviceクラス】
// ビジネスロジック（処理の中身）を担当する層。
// ControllerとRepositoryの橋渡し役。
// DBへの直接操作はせず、Repositoryに委譲する。
@Service
public class PostService {

    // PostRepositoryをこのクラスで使う。finalで再代入を禁止し安全にする
    private final PostRepository postRepository;

    // SpringがPostRepositoryのインスタンスを自動で注入するコンストラクタ
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 【全投稿取得】
    // DBにある全てのPostレコードをリストで返す
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // 【投稿作成】
    // 受け取ったPostオブジェクトをDBに保存し、保存済みのオブジェクトを返す
    public Post createPost(Post post) {
        return postRepository.save(post);
    }
}