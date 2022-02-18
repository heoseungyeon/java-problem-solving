package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac 완전 탐색(18분> 15:56 - 16 : 14) 10819 차이를 최대로 풀이 방법 : 모든 경우의 수를 백트래킹을 통해 구하여 풀었음.
 */

public class Main_10819 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N;
    public static int[] nums;
    public static int[] tmpNums;
    public static boolean[] visited;
    public static int answer;

    public static void main(String[] args) throws Exception {
        // 1. input 
        N = Integer.parseInt(br.readLine());

        nums = new int[N];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(tokens.nextToken());
        }

        // 2. bruteForce
        tmpNums = new int[N];
        visited = new boolean[N];
        permutation(0);

        // 3. print
        System.out.println(answer);
    }

    private static void permutation(int cnt) {
        if (cnt == N) {
            answer = Math.max(answer, makeSum());
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            tmpNums[cnt] = nums[i];
            permutation(cnt + 1);
            visited[i] = false;
        }

    }

    private static int makeSum() {
        int sum = 0;

        for (int i = 0; i < N - 1; i++) {
            sum += Math.abs(tmpNums[i] - tmpNums[i + 1]);
        }

        return sum;
    }

}
