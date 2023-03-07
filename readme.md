## 설계 
1. ERD 설계는 다음과 같다.<br>
   ![erd.png](docs/erd.png)<br>

2. API 는 rest 규칙에 맞춰서 다음 그림과 같이 설계하였다.<br>
   ![rest-api.png](docs/rest-api.png)


## 개발 환경 세팅
1. java 설치한다
   * 버전 : corretto 17
   
2. mysql 같은 외부 앱 설치를 위해 docker 를 띄운다.
   * 명령어
     ```bash
     cd docker; docker-compose up -d
     ```