/*
 NULL 처리하기
 풀이 방법 : 음 case when then else 절을 사용하면 이상하게 조건을 반대로해야 답이 나오고,, ifnull 을 사용해서 답을 구했다.
 */
-- 코드를 입력하세요
-- 코드를 입력하세요
/*SELECT animal_type,
       CASE name
           WHEN name is NULL
               THEN  name
           ELSE "No name" END AS name
        , sex_upon_intake
FROM animal_ins;*/ // 왜 거꾸로해야 되지?
-- SELECT animal_type, IFNULL(name, "No name"), sex_upon_intake FROM animal_ins;