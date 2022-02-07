package programmers.greedy;

import java.util.LinkedList;
import java.util.List;

/**
 * 프로그래머스 고득점 kit 그리디 - 조이스틱
 */

/*
 * 풀이 방법 : 'A'가 아닌 경우, 알파벳 변환의 최솟값을 정답에 더해준다. 그리고 정방향 이동시와 역방향 이동시 최솟값을 구하여
 * 정답을 구한다.
 */

public class JoyStick {

    public static void main(String[] args) {
        JoyStick joyStick = new JoyStick();
        System.out.println(joyStick.solution("ABABAAAAAAABA"));
    }

    public int solution(String name) {
        int answer = 0;
        List<Integer> aList = new LinkedList<>();

        for (int i = 0; i < name.length(); i++) {
            char alpha = name.charAt(i);

            answer += Math.min((alpha - 'A'), 26 - (alpha - 'A'));

            if (alpha != 'A') {
                aList.add(i);
            }
        }

        int minLeftRight;

        if (aList.isEmpty()) {
            minLeftRight = 0;
        } else {
            minLeftRight = aList.get(aList.size() - 1);
        }

        for (int i = 0; i < aList.size() - 1; i++) {
            int min = Math.min(aList.get(i) * 2 + name.length() - aList.get((i + 1) % aList.size())
                , (name.length() - aList.get((i + 1) % aList.size())) * 2 + aList.get(i));

            minLeftRight = Math.min(min, minLeftRight);
        }

        answer += minLeftRight;

        return answer;
    }

}
