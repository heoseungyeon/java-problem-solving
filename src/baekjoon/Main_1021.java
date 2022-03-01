package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * solved.ac 자료구조 1021 회전하는 큐 풀이 방법: LinkedList를 이용해서 deque 연산을 수행하였고, 왼쪽, 오른쪽 연산은 중간 인덱스와 타겟 인덱스의
 * 위치값을 비교하여 연산함.
 */

public class Main_1021 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N, M;
    public static int[] indexes;

    public static void main(String[] args) throws Exception {

        // 1. input
        tokens = new StringTokenizer(br.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        List<Integer> deque = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            deque.add(i);
        }

        // 2. count
        indexes = new int[M];
        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            indexes[i] = Integer.parseInt(tokens.nextToken());
        }
        int cnt = 0;
        for (int i = 0; i < indexes.length; i++) {
            int target = indexes[i];

            if (deque.get(0) == target) {
                deque.remove(0);
            } else {
                int halfIdx = (deque.size() - 1) / 2;
                int targetIdx = deque.indexOf(target);

                if (targetIdx <= halfIdx) {
                    while (deque.get(0) != target) {
                        int popNum = deque.remove(0);
                        deque.add(popNum);
                        cnt += 1;
                    }
                } else {
                    while (deque.get(0) != target) {
                        int popNum = deque.remove(deque.size() - 1);
                        deque.add(0, popNum);
                        cnt += 1;
                    }
                }
                deque.remove(0);
            }
        }

        // 3. print
        System.out.println(cnt);

    }

}
