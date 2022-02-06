package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * solved.ac dp or 위상정렬 14567 선수과목 풀이방법 : 위상정렬 알고리즘을 활용하여 선수과목이 없어 큐에서 나올 때 순서를 저장하여 출력하였다. 다른 풀이를
 * 보니 이중 반복문을 돌려 dp 방식으로 문제 풀이도 가능했었음. 세상 대단
 */


public class Main_14567 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N, M;

    public static List<Integer>[] lectures;
    public static int[] counts;
    public static int[] answers;

    public static void main(String[] args) throws Exception {

        // 1. input
        tokens = new StringTokenizer(br.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        lectures = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            lectures[i] = new ArrayList<>();
        }

        counts = new int[N + 1];

        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            lectures[a].add(b);
            counts[b] += 1;
        }

        // 2. tonopoly_sort
        answers = new int[N + 1];
        tonopolySort();

        // 3. print answer
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(answers[i] + " ");
        }
        System.out.println(sb.toString());

    }

    private static void tonopolySort() {
        Queue<Info> queue = new LinkedList<>();
        // 선수과목이 0인 강의들
        for (int i = 1; i <= N; i++) {
            if (counts[i] == 0) {
                queue.add(new Info(i, 1));
                answers[i] = 1;
            }
        }

        while (!queue.isEmpty()) {
            Info lecture = queue.poll();

            //선수 과목 빼주기
            for (int i = 0; i < lectures[lecture.num].size(); i++) {
                counts[lectures[lecture.num].get(i)] -= 1;
                // 선수과목이 0인 강의들
                if (counts[lectures[lecture.num].get(i)] == 0) {
                    queue.add(new Info(lectures[lecture.num].get(i), lecture.order + 1));
                    answers[lectures[lecture.num].get(i)] = lecture.order + 1;
                }
            }
        }


    }

    private static class Info {

        private int num;
        private int order;

        public Info(int num, int order) {
            this.num = num;
            this.order = order;
        }
    }

}
