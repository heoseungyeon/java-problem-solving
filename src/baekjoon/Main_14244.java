package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac 트리 14244 트리만들기 풀이 방법: 첫 반복문을 통해 m-1 개의 리프를 만들어주고 두번째 반복문을 통해 1개의 리프를 만들어 출력
 */

public class Main_14244 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int n, m;

    public static void main(String[] args) throws Exception {
        // 1. input
        tokens = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokens.nextToken());
        m = Integer.parseInt(tokens.nextToken());

        for (int i = 0; i < m - 1; i++) {
            System.out.println(i + " " + (m - 1));
        }

        for (int i = m - 1; i < n - 1; i++) {
            System.out.println(i + " " + (i + 1));
        }
    }

}

