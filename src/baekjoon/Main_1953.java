package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * solved.ac bfs 1953 팀 배분 풀이 방법: bfs로 풀어본 것 중 제일 기발했던 문제인 것 같다. visited 배열을 -1 (홍), 0 (방문x), 1
 * (청)으로 나누고 모든 사람에 대해선 순회 하여 풀이 dfs 풀이도 해봐야겠다.
 */

public class Main_1953 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int n;
    public static boolean[][] haters;

    public static void main(String[] args) throws Exception {
        // 1. input
        n = Integer.parseInt(br.readLine());
        haters = new boolean[n + 1][n + 1];

        for (int i = 0; i < n; i++) {
            tokens = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(tokens.nextToken());
            for (int j = 0; j < cnt; j++) {
                int person = Integer.parseInt(tokens.nextToken());
                haters[i + 1][person] = true;
                haters[person][i + 1] = true;
            }
        }
        // 2. bfs
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            if (visited[i] != 0) {
                continue;
            }
            queue.add(i);
            visited[i] = 1; // 청팀 선택
            while (!queue.isEmpty()) {
                int person = queue.poll();
                for (int j = 1; j <= n; j++) {
                    if (visited[j] != 0) { // 팀 선정이 된 경우
                        continue;
                    }
                    if (haters[j][person]) { // person을 싫어하는 사람
                        queue.add(j);
                        visited[j] = visited[person] * (-1); // person과 반대팀으로
                    }
                }

            }
        }
        // 3. print
        List<Integer> blues = new ArrayList<>();
        List<Integer> reds = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (visited[i] == 1) {
                blues.add(i);
            } else {
                reds.add(i);
            }
        }

        System.out.println(blues.size());
        blues.stream().sorted().forEach((person) -> System.out.print(person + " "));
        System.out.println();
        System.out.println(reds.size());
        reds.stream().sorted().forEach((person) -> System.out.print(person + " "));
    }


}
