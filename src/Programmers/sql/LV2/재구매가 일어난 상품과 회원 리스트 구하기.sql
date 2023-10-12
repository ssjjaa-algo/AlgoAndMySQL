-- group을 2개로 묶었다는 점
SELECT USER_ID, PRODUCT_ID
from online_sale
group by user_id, product_id
having count(product_id) >= 2
order by user_id asc, product_id desc;g