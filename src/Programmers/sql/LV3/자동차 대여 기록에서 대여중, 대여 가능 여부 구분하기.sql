SELECT distinct CAR_ID,
                CASE
                    WHEN CAR_ID IN (
                        select CAR_ID FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
                        WHERE '2022-10-16' between START_DATE AND END_DATE
                    ) then '대여중'
                    ELSE '대여 가능'
                    END AS 'AVAILABILITY'
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
ORDER BY CAR_ID DESC
