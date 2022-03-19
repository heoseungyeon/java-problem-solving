package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * solved.ac 백트래킹(17:33 - 17:45) 15663 N과 M (9) 풀이 방법: nums 배열 sorting 하고 hashSet으로 중복 검사 후 백트래킹하여
 * 문제풀이
 */

public class Main_15663 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N, M;
    public static int[] nums;
    public static boolean[] visited;
    public static HashSet<String> sets = new HashSet<>();
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {

        // 1. input
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        nums = new int[N];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(tokens.nextToken());
        }

        Arrays.sort(nums);

        visited = new boolean[N];

        // 2. get permutation
        permutation("", 0);

        // 3. print
        System.out.println(sb.toString());
    }

    private static void permutation(String s, int cnt) {
        if (cnt == M) {
            if (!sets.contains(s)) {
                sb.append(s + "\n");
                sets.add(s);
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                permutation(s + nums[i] + " ", cnt + 1);
                visited[i] = false;
            }
        }
    }
}