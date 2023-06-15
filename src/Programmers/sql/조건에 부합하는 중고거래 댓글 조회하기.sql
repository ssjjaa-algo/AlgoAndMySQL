SELECT
    b.TITLE,
    b.BOARD_ID,
    r.REPLY_ID,
    r.WRITER_ID,
    r.CONTENTS,
    date_format(r.CREATED_DATE,"%Y-%m-%d") AS CREATED_DATE
FROM USED_GOODS_BOARD b, USED_GOODS_REPLY r
WHERE b.BOARD_ID = r.BOARD_ID
  AND
        b.CREATED_DATE LIKE "2022-10-%"
order by r.CREATED_DATE, b.TITLE;

-- date_format 형식 익히기
-- order by 조건은 순서대로 쓰면 다음 조건 갱신된다.