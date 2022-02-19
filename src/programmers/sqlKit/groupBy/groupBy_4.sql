/*
 입양 시각 구하기 (2)
 풀이 방법 : recursive 를 사용해서 해야된다는데,, 처음 보는 거라 너무 어렬웠음.
 */
-- 코드를 입력하세요
-- with recursive time as
-- (select 0 as hour union all select hour + 1 from time where hour < 23)
--
-- select hour, count(animal_id) count
-- from time
-- left join animal_outs on (hour = date_format(datetime, '%H'))
-- group by hour;