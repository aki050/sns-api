package com.example.sns_api.controller;

import com.example.sns_api.model.Post;
import com.example.sns_api.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
// HTTPリクエストを受け取るコントローラークラス。JSONを返すRestAPIとして動作する
@RestController
// このクラス内の全エンドポイントのベースURLを /api/posts に設定
@RequestMapping("/api/posts")
public class PostController {

    // PostServiceをこのクラスで使う。finalで再代入を禁止し安全にする
    private final PostService postService;

    // SpringがPostServiceのインスタンスを自動で注入するコンストラクタ
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // GET /api/posts → DBにある全投稿をリストで返す
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    // POST /api/posts → リクエストのJSONを受け取り、新しい投稿をDBに保存して返す
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        // Serviceに保存処理を委譲し、保存済みのPostを受け取る
        Post saved = postService.createPost(post);
        // 保存成功を示す201 Createdと保存したデータをレスポンスとして返す
        return ResponseEntity.status(201).body(saved);
    }
}