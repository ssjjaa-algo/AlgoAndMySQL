
SELECT U.USER_ID, U.NICKNAME,
       concat(U.CITY, ' ', U.STREET_ADDRESS1, ' ', U.STREET_ADDRESS2) as '전체주소',
        concat(substring(U.TLNO, 1, 3), '-', substring(U.TLNO, 4, 4), '-',
            substring(U.TLNO, 8, 4)) as '전화번호'
FROM USED_GOODS_USER U
         join USED_GOODS_BOARD B on U.USER_ID = B.WRITER_ID
GROUP BY U.USER_ID
HAVING count(B.BOARD_ID) >= 3
ORDER BY U.USER_ID DESC
