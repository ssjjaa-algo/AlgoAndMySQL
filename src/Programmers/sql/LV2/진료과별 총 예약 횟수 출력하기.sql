SELECT MCDP_CD as "진료과코드", count(APNT_NO) as "5월예약건수"
from appointment
where APNT_YMD LIKE "2022-05%"
GROUP BY MCDP_CD
ORDER BY count(MCDP_CD) ASC, MCDP_CD ASC;


