/*
 입양 시각 구하기 (1)
 풀이 방법 : HOUR 함수를 통해 시간을 추출하고 WHERE BETWEEN 절을 사용하여 검색
 */
-- 코드를 입력하세요
-- SELECT HOUR(datetime) AS HOUR, COUNT(*) AS COUNT
-- FROM animal_outs
-- WHERE HOUR(datetime) BETWEEN 9 AND 20
-- GROUP BY HOUR ORDER BY HOUR;