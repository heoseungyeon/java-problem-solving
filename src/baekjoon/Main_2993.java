package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac 완탐(12:48 - 13:18) 2993 세 부분 풀이 방법: 완탐으로 문제 풀이, StringBuilder의 reverse 메서드를 활용 그리고
 * compareTo 메서드를 통해 사전 순 가장 빠른 단어를 찾음.
 */


public class Main_2993 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static String word;
    public static String answer;

    public static void main(String[] args) throws Exception {
        // 1. input
        word = br.readLine();
        answer = word;

        // 2. bruteForce
        int length = word.length();
        // 8 기준
        for (int i = 1; i < length - 2; i++) { // 1~6
            for (int j = 1; j <= (length - i) - 1; j++) { // 1~1~1
                findAnswer(i, j);
            }
        }

        // 3. print
        System.out.println(answer);
    }

    private static void findAnswer(int idx1, int idx2) {
        StringBuilder sb1 = new StringBuilder(word.substring(0, idx1));
        StringBuilder sb2 = new StringBuilder(word.substring(idx1, idx1 + idx2));
        StringBuilder sb3 = new StringBuilder(word.substring(idx1 + idx2));

        StringBuilder result = new StringBuilder();
        result.append(sb1.reverse());
        result.append(sb2.reverse());
        result.append(sb3.reverse());

        if (answer.compareTo(result.toString()) > 0) {
            answer = result.toString();
        }
    }

}
