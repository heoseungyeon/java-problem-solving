package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_10757 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;

    public static void main(String[] args) throws Exception {
        String A, B;
        String answer = "";
        // 1. input
        tokens = new StringTokenizer(br.readLine());

        A = tokens.nextToken();
        B = tokens.nextToken();

        if (A.length() < B.length()) {
            String tmp = A;
            A = B;
            B = A;
        }

        // 2. solution
        int tmp = 0;
        for (int i = 0; i <= A.length(); i++) {
            int aValue =
                i < A.length() ? Integer.parseInt(String.valueOf(A.charAt(A.length() - (i + 1))))
                    : 0;
            int bValue =
                i < B.length() ? Integer.parseInt(String.valueOf(B.charAt(B.length() - (i + 1))))
                    : 0;
            int sum = aValue + bValue + tmp;
            if (sum >= 10) {
                tmp = 1;
                sum -= 10;
            } else {
                tmp = 0;
            }
            answer = sum + answer;
        }

        System.out.println(answer);
    }
}
