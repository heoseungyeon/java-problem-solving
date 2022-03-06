package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * solved.ac 자료구조(14 분> 14:10 -  14:24) 10866 스택 풀이 방법 : linkedList 사용하여 deque 명령 구현
 */

public class Main_10866 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static StringBuilder sb = new StringBuilder();
    public static List<Integer> deque = new LinkedList<>();
    public static int N;

    public static void main(String[] args) throws Exception {
        // 1. input
        N = Integer.parseInt(br.readLine());

        // 2. deque algo
        while (N > 0) {
            tokens = new StringTokenizer(br.readLine());
            String cmd = tokens.nextToken();
            if (cmd.equals("push_back")) {
                int push_value = Integer.parseInt(tokens.nextToken());
                deque.add(push_value);
            }
            if (cmd.equals("push_front")) {
                int push_value = Integer.parseInt(tokens.nextToken());
                deque.add(0, push_value);
            }
            if (cmd.equals("pop_back")) {
                if (deque.isEmpty()) {
                    sb.append("-1" + "\n");
                } else {
                    sb.append(deque.remove(deque.size() - 1) + "\n");
                }
            }
            if (cmd.equals("pop_front")) {
                if (deque.isEmpty()) {
                    sb.append("-1" + "\n");
                } else {
                    sb.append(deque.remove(0) + "\n");
                }
            }
            if (cmd.equals("size")) {
                sb.append(deque.size() + "\n");

            }
            if (cmd.equals("empty")) {
                int emptyValue = deque.isEmpty() ? 1 : 0;
                sb.append(emptyValue + "\n");
            }
            if (cmd.equals("front")) {
                if (deque.isEmpty()) {
                    sb.append("-1" + "\n");
                } else {
                    sb.append(deque.get(0) + "\n");
                }
            }
            if (cmd.equals("back")) {
                if (deque.isEmpty()) {
                    sb.append("-1" + "\n");
                } else {
                    sb.append(deque.get(deque.size() - 1) + "\n");
                }
            }
            N--;
        }

        // 3. print

        System.out.println(sb);
    }

}
