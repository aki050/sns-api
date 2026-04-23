# ===== Stage 1: ビルド =====
FROM maven:3.9-eclipse-temurin-21 AS builder
# ↑ Maven + JDK入りの重いイメージ

WORKDIR /app
# 依存関係を先にキャッシュ（変更なければ再ダウンロードしない）
COPY pom.xml .
RUN mvn dependency:go-offline -B
# ↑ ソースコードをJARファイルにビルド

# ソースをコピーしてビルド（テストはJenkinsfileで実行するのでスキップ）
COPY src ./src
RUN mvn package -B

# ===== Stage 2: 実行環境（軽量） =====
FROM eclipse-temurin:21-jre-alpine
# ↑ JREだけの軽いイメージ（MavenもJDKも入ってない）
WORKDIR /app

# ビルドステージからFat JARだけコピー
COPY --from=builder /app/target/sns-api-0.0.1-SNAPSHOT.jar app.jar
# ↑ Stage 1で作ったJARファイルだけをコピー
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]