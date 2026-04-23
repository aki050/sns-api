package com.example.sns_api.repository;

import com.example.sns_api.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// 【Repositoryクラス】
// DBとのやり取り（CRUD操作）を担当する層。
// JpaRepositoryを継承するだけで、findAll()・save()・deleteById()などが
// 自動で使えるようになる。自分でSQLを書く必要がない！
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // JpaRepositoryが以下を自動提供してくれる：
    // - findAll()    → 全件取得
    // - save(post)   → 保存・更新
    // - findById(id) → ID指定で1件取得
    // - deleteById() → ID指定で削除
    // 追加のメソッドが必要になったらここに書く
}