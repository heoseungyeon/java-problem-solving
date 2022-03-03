package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_10998 {

    public static StringTokenizer tokens;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        // 1. input
        int A, B;
        tokens = new StringTokenizer(br.readLine());

        A = Integer.parseInt(tokens.nextToken());
        B = Integer.parseInt(tokens.nextToken());

        System.out.println(A * B);

    }
}
