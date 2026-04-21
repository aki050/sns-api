package com.example.sns_api.repository;

import com.example.sns_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * データベース（MySQLなど）へのアクセスを自動化するインターフェース
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 自分でメソッドを書かなくても、これだけで以下の機能が使えます：
    // - save(user): 保存・更新
    // - findById(id): IDで検索
    // - findAll(): 全員取得
    // - deleteById(id): 削除
}
