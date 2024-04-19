# 문법 정리

- CONCAT : 문자열 이어붙이기
  - CONCAT("안녕","하세요") = 안녕하세요, SELECT CONCAT("문자열") 식으로 사용

## 순위 함수
- NTILE : PARTITION을 지정된 수 만큼의 등급으로 나누어 각 등급 번호를 출력
  - NTILE(4) : 4로 나누어 분할
  - 참고 문제 :