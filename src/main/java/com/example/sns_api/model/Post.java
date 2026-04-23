package com.example.sns_api.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime; 

// 【Entityクラス】
// DBのテーブル1行分をJavaオブジェクトとして表現するクラス。
// このクラスの定義をもとにSpringがDBのテーブルを自動生成する。
@Entity
// DBのテーブル名を "posts" に指定
@Table(name = "posts")
// Lombokが自動でgetter/setter/toString/equalsを生成してくれる
@Data
public class Post {

    // 主キー（レコードを一意に識別するID）
    @Id
    // IDを1, 2, 3... と自動採番する
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 投稿の本文テキスト
    private String content;

    // 投稿が作成された日時
    private LocalDateTime createdAt;

    // 本来はここに「誰の投稿か」という紐付けが必要だが、
    // まずはシンプルに「内容だけ」を保存できるようにする（Phase3で追加）

    // DBに保存される直前に自動で呼ばれるメソッド
    // createdAtに現在日時をセットする
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}