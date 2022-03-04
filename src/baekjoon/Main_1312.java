package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1312 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;

    public static void main(String[] args) throws Exception {
        // 1. input
        int A, B, N;

        tokens = new StringTokenizer(br.readLine());

        A = Integer.parseInt(tokens.nextToken());
        B = Integer.parseInt(tokens.nextToken());
        N = Integer.parseInt(tokens.nextToken());

        // 2. simulation
        int lastValue = 0;
        int cnt = -1;
        while (cnt != N) {
            if (A < B) {
                lastValue = 0;
            } else {
                lastValue = A / B;
                A = A % B;
            }

            A = Integer.parseInt(String.valueOf(A) + "0");
            cnt += 1;
        }

        // 3. print
        System.out.println(lastValue);
    }

}
