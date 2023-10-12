-- where 절에 집계함수는 사용할 수 없으므로 having 절을 쓴다.

SELECT U.USER_ID, U.NICKNAME, sum(B.PRICE) AS TOTAL_SALES
from USED_GOODS_USER U, USED_GOODS_BOARD B
where U.USER_ID = B.WRITER_ID AND b.status = "DONE"
GROUP BY U.USER_ID
HAVING TOTAL_SALES >= 700000
order by total_sales;