package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac 트리 9934 완전이진트리 풀이 방법 : 주어진 입력 값은 중위 순회를 했을 때 결과이므로 중위 순회로 재귀문을 구성한다. 재귀는 부모 노드 idx 기준
 * 왼쪽 자식 노드는 2*idx, 오른쪽 자식 노드는 2*idx+1 인 점을 이용하고, 탈출 조건으로 높이(height)가 K 보다 작을 경우를 설정하였다.
 */

public class Main_9934 {

    public static int K;
    public static int[] numbers;
    public static int[] tree;
    public static int[] answer;
    public static int cnt = 0;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;

    public static void main(String[] args) throws Exception {

        // 1. input
        K = Integer.parseInt(br.readLine());

        numbers = new int[(int) (Math.pow(2, K) - 1)];
        tree = new int[(int) (Math.pow(2, K) - 1)];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < Math.pow(2, K) - 1; i++) {
            numbers[i] = Integer.parseInt(tokens.nextToken());
        }

        // 2. calculate tree and print
        inOrderTree(1, 1);

        // 3. print
        StringBuilder sb = new StringBuilder();
        cnt = 0;
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < Math.pow(2, i); j++) {
                sb.append(tree[cnt] + " ");
                cnt++;
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());

    }

    private static void inOrderTree(int idx, int height) {
        // 재귀 탈출 조건
        if (height > K) {
            return;
        }
        inOrderTree(idx * 2, height + 1);
        tree[idx - 1] = numbers[cnt];
        cnt++;
        inOrderTree(idx * 2 + 1, height + 1);
    }

}
