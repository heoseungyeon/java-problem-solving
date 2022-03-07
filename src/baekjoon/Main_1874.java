package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * solved.ac 자료구조(37분 > 18:52 - 19:29) 1874 스택 수열 풀이 방법: 스택 자료구조를 이용해 시뮬레이션 하였다. Stack을 사용하지 않고
 * int[] 사용하는게 더 연산속도를 높일 수 있을듯.
 */

public class Main_1874 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer tokens;
    public static int n;
    public static Stack<Integer> stack = new Stack<>();
    public static Stack<Character> answer = new Stack<>();
    public static boolean isPossible = true;

    public static void main(String[] args) throws Exception {
        // 1. input
        n = Integer.parseInt(br.readLine());

        // 2. simulation
        int[] sequence = new int[n];
        for (int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(br.readLine());
        }

        int idx = 0;
        int nowNum = 1;

        while (true) {
            if (idx == n) {
                break;
            }
            if (nowNum > n && stack.peek() != sequence[idx]) {
                isPossible = false;
                break;
            }
            if (stack.isEmpty() || stack.peek() != sequence[idx]) {
                answer.add('+');
                stack.add(nowNum);
                nowNum += 1;
                continue;
            }
            answer.add('-');
            stack.pop();
            idx++;
        }// while

        // 3. print
        if (isPossible) {
            for (char c : answer) {
                System.out.println(c);
            }
        } else {
            System.out.println("NO");
        }
    }
}
