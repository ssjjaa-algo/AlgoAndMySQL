-- 코드를 입력하세요
SELECT USER_ID, PRODUCT_ID
from online_sale
group by user_id, product_id
having count(product_id) >= 2
order by user_id asc, product_id desc;g