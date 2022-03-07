package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * solved.ac 자료구조 1406 에디터 풀이 방법: subString으로 했을 때 시간 초과. O(N)으로 문제를 풀이해야되는데, Charater 스택 두개를 두고 조건에
 * 맞게 스택에 삽입/삭제 연산을 수행하였다. -> 알고 있으면 좋은 문제
 */

public class Main_1406 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static String str;
    public static Stack<Character> leftStack = new Stack<>();
    public static Stack<Character> rightStack = new Stack<>();
    public static int N;

    public static void main(String[] args) throws Exception {

        // 1. input
        str = br.readLine();
        for (int i = 0; i < str.length(); i++) {
            leftStack.add(str.charAt(i));
        }

        N = Integer.parseInt(br.readLine());

        while (N > 0) {
            String cmd = br.readLine();
            if (cmd.startsWith("P")) {
                char alpha = cmd.substring(2, 3).charAt(0);
                leftStack.add(alpha);
            } else if (cmd.equals("L")) {
                if (!leftStack.isEmpty()) {
                    rightStack.add(leftStack.pop());
                }
            } else if (cmd.equals("D")) {
                if (!rightStack.isEmpty()) {
                    leftStack.add(rightStack.pop());
                }
            } else if (cmd.equals("B")) {
                if (!leftStack.isEmpty()) {
                    leftStack.pop();
                }
            }

            N--;
        }

        // 2. print
        StringBuilder sb = new StringBuilder();
        for (char c : leftStack) {
            sb.append(c);
        }
        while (!rightStack.isEmpty()) {
            sb.append(rightStack.pop());
        }

        System.out.println(sb.toString());


    }


}

