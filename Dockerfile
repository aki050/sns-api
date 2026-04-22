# ===== Stage 1: ビルド =====
FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app

# 依存関係を先にキャッシュ（変更なければ再ダウンロードしない）
COPY pom.xml .
RUN mvn dependency:go-offline -B

# ソースをコピーしてビルド（テストはJenkinsfileで実行するのでスキップ）
COPY src ./src
RUN mvn package -DskipTests -B

# ===== Stage 2: 実行環境（軽量） =====
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# ビルドステージからFat JARだけコピー
COPY --from=builder /app/target/sns-api-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]