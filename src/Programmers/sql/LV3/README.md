# 문법 정리

- CONCAT : 문자열 이어붙이기
  - CONCAT("안녕","하세요") = 안녕하세요, SELECT CONCAT("문자열") 식으로 사용

## 순위 함수
- NTILE : PARTITION을 지정된 수 만큼의 등급으로 나누어 각 등급 번호를 출력
  - NTILE(4) : 4로 나누어 분할
  - 참고 문제 : [대장균의 크기에 따라 분류하기 2](https://github.com/ssjjaa-algo/AlgoAndMySQL/blob/master/src/Programmers/sql/LV3/%EB%8C%80%EC%9E%A5%EA%B7%A0%EC%9D%98%20%ED%81%AC%EA%B8%B0%EC%97%90%20%EB%94%B0%EB%9D%BC%20%EB%B6%84%EB%A5%98%ED%95%98%EA%B8%B0%202.sql)