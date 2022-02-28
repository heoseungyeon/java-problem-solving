package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * solved.ac 백트래킹((3분) 11:40 - 11:43) 15655 N과 M (6) 풀이 방법: nums 배열 sorting 하고, visited 처리하면서 백트래킹
 * 추가로 오름차순이어야 하므로 permutation에 현재 바라보는 idx를 주고, 반복문의 시작을 idx+1로 함.
 */

public class Main_15655 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N, M;
    public static int[] nums;
    public static boolean[] visited;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {

        // 1. input
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        nums = new int[N];
        visited = new boolean[N];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(tokens.nextToken());
        }

        Arrays.sort(nums);

        // 2. get permutation
        permutation("", 0, -1);

        // 3. print
        System.out.println(sb.toString());
    }

    private static void permutation(String s, int cnt, int nowIdx) {
        if (cnt == M) {
            sb.append(s + "\n");
            return;
        }

        for (int i = nowIdx + 1; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                permutation(s + nums[i] + " ", cnt + 1, i);
                visited[i] = false;
            }
        }
    }
}
