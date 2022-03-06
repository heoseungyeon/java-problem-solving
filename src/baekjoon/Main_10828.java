package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * solved.ac 자료구조(9분> 13:58 - 14:07) 10828 스택 풀이 방법 : stack 자료구조 사용하여 if문으로 cmd 분기하여 풀이
 */

public class Main_10828 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N;
    public static StringBuilder sb = new StringBuilder();
    public static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws Exception {
        // 1. input
        N = Integer.parseInt(br.readLine());

        int number = 0;
        while (N > 0) {
            tokens = new StringTokenizer(br.readLine());
            String cmd = tokens.nextToken();
            if (cmd.equals("push")) {
                number = Integer.parseInt(tokens.nextToken());
                stack.add(number);
            }
            if (cmd.equals("top")) {
                if (stack.isEmpty()) {
                    sb.append(-1 + "\n");
                } else {
                    sb.append(stack.peek() + "\n");
                }
            }
            if (cmd.equals("pop")) {
                if (stack.isEmpty()) {
                    sb.append(-1 + "\n");
                } else {
                    int popValue = stack.pop();
                    sb.append(popValue + "\n");
                }
            }
            if (cmd.equals("empty")) {
                int emptyValue = stack.isEmpty() ? 1 : 0;
                sb.append(emptyValue + "\n");
            }
            if (cmd.equals("size")) {
                sb.append(stack.size() + "\n");
            }
            N--;
        }

        // print
        System.out.println(sb);
    }
}
