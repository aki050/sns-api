package com.example.sns_api.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content; // 投稿内容

    private LocalDateTime createdAt; // 投稿日時

    // 本来はここに「誰の投稿か」という紐付けが必要ですが、
    // まずはシンプルに「内容だけ」を保存できるようにします！

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}