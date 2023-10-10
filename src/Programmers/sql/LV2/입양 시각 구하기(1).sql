-- 코드를 입력하세요
SELECT HOUR(DATETIME) AS "HOUR", count(DATETIME) as "COUNT"
from animal_outs
group by hour
HAVING HOUR >= 9 AND HOUR <= 19
order by hour