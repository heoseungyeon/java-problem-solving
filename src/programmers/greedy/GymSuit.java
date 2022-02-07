package programmers.greedy;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 프로그래머스 고득점 kit 그리디 - 운동복
 */

/*
 * 풀이 방법 : lost 와 reserve를 Set 자료형으로 변경한 뒤, reserve를 오름차순으로 정렬한다.
 * 그 후, reserve 원소들을 for문을 돌면서 앞 뒤로 대여 가능 여부를 따진다. 만약 reserve 원소가 lost Set에 있을 경우,
 * answer에 1을 더해준다.
 */

public class GymSuit {

    public static void main(String[] args) {
        GymSuit gymSuit = new GymSuit();
        System.out.println(gymSuit.solution(5, new int[]{3, 4, 6}, new int[]{1, 3, 5}));
    }

    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length;

        Set<Integer> lostSet = Arrays.stream(lost)
            .boxed()
            .collect(Collectors.toSet());

        Set<Integer> reserveSet = Arrays.stream(reserve)
            .boxed()
            .collect(Collectors.toSet());

        Arrays.sort(reserve);

        for (int i = 0; i < reserve.length; i++) {
            if (!lostSet.contains(reserve[i])) {
                if (!reserveSet.contains(reserve[i] - 1) && lostSet.contains(reserve[i] - 1)) {
                    answer += 1;
                    lostSet.remove(reserve[i] - 1);
                } else if (!reserveSet.contains(reserve[i] + 1) && lostSet.contains(
                    reserve[i] + 1)) {
                    answer += 1;
                    lostSet.remove(reserve[i] + 1);
                }
            } else {
                answer += 1;
            }

        }

        return answer;
    }

}
