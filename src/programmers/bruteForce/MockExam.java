package programmers.bruteForce;

import java.util.ArrayList;
import java.util.List;

/**
 * 프로그래머스 고득점 kit 모의고사 완전탐색
 */

/*
 * 풀이 방법 : 3명의 학생이 찍는 패턴을 저장해 두고 맞은 정답 수 저장, 출력 시 max 값과 비교하여 고득점자 리스트에 저장 후 배열로 변환.
 */

public class MockExam {

    public static int[][] patterns = new int[][]{{0}, {1, 2, 3, 4, 5}, {2, 1, 2, 3, 2, 4, 2, 5},
        {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}};

    public static int[] solution(int[] answers) {
        int[] result;
        int[] correctAnswerCnt = new int[4];
        int cnt = answers.length;

        // 1. calculate correct Answer Count
        for (int i = 1; i <= 3; i++) {
            for (int j = 0; j < cnt; j++) {
                if (answers[j] == patterns[i][j % patterns[i].length]) {
                    correctAnswerCnt[i]++;
                }
            }
        }

        // 2. print
        int maxCnt = correctAnswerCnt[0];
        for (int i = 1; i < 4; i++) {
            if (maxCnt < correctAnswerCnt[i]) {
                maxCnt = correctAnswerCnt[i];
            }
        }

        List<Integer> results = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            if (maxCnt == correctAnswerCnt[i]) {
                results.add(i);
            }
        }

        return results.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        MockExam mockExam = new MockExam();
        int[] results = mockExam.solution(new int[]{1, 2, 3, 4, 5});
        for (int i = 0; i < results.length; i++) {
            System.out.print(results[i] + " ");
        }
    }
}
