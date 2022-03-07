# Kakaopay Workspace
Kakaopay Workspace 과제

# 프로젝트 구성
## 백엔드
### 기술 스택

- 스프링부트 (2.4.3)
- 하이버네이트
- 자바 8
- 그래들
- H2 DB

## 프론트엔드
### 기술 스택
- Nodejs (v12.22.9)
- VueJS

# 실행 방법
## 프론트 파일 생성
프론트 최상위 경로로 이동 후 번들링
```
cd src/vue
npm install
npm run build
```
### jar 생성
root 경로로 이동 후 jar 생성
```
cd ../../
./gradlew bootjar
```
### jar 실행
"Application start successfully" 로그 확인이 되어야함
```
java -jar ./build/libs/inquiry-0.0.1-SNAPSHOT.jar
```

### 애플리케이션 접속
브라우저 url http://localhost:8080 으로 진입

# 문제 해결 전략
- 소스만으로 바로 사용가능하도록 H2 DB 사용
- 소스만으로 바로 사용가능하도록 번들링된 파일 함께 빌드
- 관리자, 일반유저로 구분된 페이지를 구성하여 로그인 후 각각 다른 유저 경험을 할 수 있도록 권한 구성