
## 3월에 태어난 여성 목록 출력하기
```sql
SELECT MEMBER_ID, MEMBER_NAME, GENDER, DATE_FORMAT(DATE_OF_BIRTH, '%Y-%m-%d') AS DATE_OF_BIRTH
FROM MEMBER_PROFILE
WHERE GENDER = 'W' 
    AND TLNO IS NOT NULL 
    AND DATE_OF_BIRTH 
    LIKE '%-03-%'
ORDER BY MEMBER_ID ASC
```

## 과일로 만든 아이스크림 고르기
```sql
SELECT FIRST_HALF.FLAVOR
FROM FIRST_HALF
INNER JOIN ICECREAM_INFO ON FIRST_HALF.FLAVOR = ICECREAM_INFO.FLAVOR
WHERE FIRST_HALF.TOTAL_ORDER > 3000
AND ICECREAM_INFO.INGREDIENT_TYPE = 'fruit_based'
ORDER BY FIRST_HALF.TOTAL_ORDER DESC
```

## 모든 레코드 조회하기
```sql
SELECT *
FROM ANIMAL_INS
ORDER BY ANIMAL_ID ASC
```
