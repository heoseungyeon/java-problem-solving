/*
 동명 동물 수 찾기
 풀이 방법 : group by를 통해 조회 및 조건에 맞게 where 과 having, order by 절 사용
 */
-- 코드를 입력하세요
-- SELECT name, count(*) AS count FROM animal_ins WHERE name IS NOT NULL GROUP BY name HAVING count > 1 ORDER BY name;