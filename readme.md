## 프로젝트 개요
* 주제
  * 특정 시간동안 특가로 상품을 판매하는 타임딜 서비스의 백엔드 API 구축
* 기능
  * 회원 가입/탈퇴/조회
  * 상품 등록/수정/삭제/목록/상세
  * 상품 구매
  * 회원 로그인/로그아웃
* 목적
  * 안정적인 아키텍처를 설계한다
    * 기능의 정상 작동
    * 정규화를 따른 테이블 설계
    * 단위 및 통합 테스트 생성
  * API 성능을 개선한다
* 일정 
  * 23년 3월 4째주까지 1차 배포를 목표로 하며, 그 이후는 개선 작업을 한다
  * [TODO] 노션 에자일 보드 공유 예정 
  


## 설계
1. ERD 설계는 다음과 같다.<br>
   ![erd.png](docs/erd.png)<br>

2. API 는 rest 규칙에 맞춰서 다음 그림과 같이 설계하였다.<br>
   ![rest-api.png](docs/rest-api.png)

## 로컬 개발환경 설정

#### java 설치
* sdkman 설치
   ```bash
   $ curl -s "https://get.sdkman.io" | bash
   $ source "$HOME/.sdkman/bin/sdkman-init.sh"
   $ sdk version
   ```
* sdkman 에서 java corretto 17 설치
    ```bash
    $ sdk list java
    $ sdk install java ${java-version}
    ```
<br>

#### mysql 설치
* [docker 설치](https://docs.docker.com/engine/install/)

* docker-compose 설치
  ```bash
  $ sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
  $ sudo chmod +x /usr/local/bin/docker-compose
  $ docker-compose --version
  ```
* mysql 띄우기
    ```bash
    $ cd docker; docker-compose up -d
    ```
<br>

#### swagger 로 API 문서 확인
* 앱 실행 후, `http://localhost:8080/swagger` 로 접속
   