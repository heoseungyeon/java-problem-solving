package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * solved.ac 시뮬레이션 (8분 > 23:30 - 23:38) 14382 숫자 세는 양 (Large) 풀이 방법: 숫자 세는 양 (Small) 과 동일하게 풀이
 */

public class Main_14382 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int T;

    public static void main(String[] args) throws Exception {
        // 1. input

        T = Integer.parseInt(br.readLine());
        int caseNum = 1;
        while (caseNum <= T) {
            N = Integer.parseInt(br.readLine());

            boolean[] visited = new boolean[10];
            int cnt = 0;

            String num = String.valueOf(N);
            while (true) {
                if (N == 0) {
                    System.out.println("Case #" + caseNum + ": " + "INSOMNIA");
                    break;
                }
                for (int i = 0; i < num.length(); i++) {
                    if (!visited[num.charAt(i) - '0']) {
                        visited[num.charAt(i) - '0'] = true;
                        cnt += 1;
                    }
                }
                if (cnt == 10) {
                    System.out.println("Case #" + caseNum + ": " + Integer.parseInt(num));
                    break;
                }
                num = String.valueOf(Integer.parseInt(num) + N);
            }

            caseNum++;
        }
    }
}
