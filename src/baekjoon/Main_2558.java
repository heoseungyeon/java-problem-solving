package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2558 {

    public static StringTokenizer tokens;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        // 1. input
        int A, B;

        A = Integer.parseInt(br.readLine());
        B = Integer.parseInt(br.readLine());

        System.out.println(A + B);

    }
}
