package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * solved.ac 자료구조 23253 자료구조 정말 최고야 풀이 방법: 와 나는 그냥 시뮬레이션 돌렸을 땐 시간초과나서 책 번호의 스택 위치를 저장하는 Map을 활용해서
 * 풀이했는데, 애초에 입력받을 때 책번호가 낮은게 책번호 높은 것보다 밑에 있으면 안되는 것을 검사하면 된다.. 킹정!
 */

public class Main_23253 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N, M;
    public static Stack<Integer>[] stacks;
    public static Map<Integer, Integer> indexMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        // 1. input
        tokens = new StringTokenizer(br.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        stacks = new Stack[M];

        for (int i = 0; i < M; i++) {
            stacks[i] = new Stack<>();
            int size = Integer.parseInt(br.readLine());
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                int num = Integer.parseInt(tokens.nextToken());
                stacks[i].add(num);
                indexMap.put(num, i);
            }
        }
        // 2. simulation
        boolean possible = true;
        for (int i = 1; i <= N; i++) {
            int index = indexMap.get(i);
            if (!stacks[index].isEmpty() && stacks[index].peek() == i) {
                stacks[index].pop();
            } else {
                possible = false;
            }
            if (!possible) {
                break;
            }
        }

        // 3. print result
        if (possible) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

    }

}
