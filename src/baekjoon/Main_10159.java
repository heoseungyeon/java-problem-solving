package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac 플로이드 와샬(14:06 - 15:50) 10159 저울 풀이 방법 : 플로이드 와샬 알고리즘을 활용하여 문제를 풀이. 최단거리를 구할 수 있지만 경유하는
 * 경우 특정 조건을 만족할 경우 사용할 수도 있다.
 */

public class Main_10159 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N, M;
    public static int[][] routes;

    public static void main(String[] args) throws Exception {

        // 1. input
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        routes = new int[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            int heavy = Integer.parseInt(tokens.nextToken());
            int light = Integer.parseInt(tokens.nextToken());
            routes[heavy][light] = 1;
        }

        // 2. floyd-warshall
        // 공유 노드
        for (int k = 0; k < N + 1; k++) {
            // 출발 노드
            for (int i = 0; i < N + 1; i++) {
                // 도착 노드
                for (int j = 0; j < N + 1; j++) {
                    // i보다 작은 k 와 j 보단 큰 k(j<k<i)를 만족하는 k가 있으면 i와 j 의 저울을 비교할 수 있다.
                    if (routes[i][k] == 1 && routes[k][j] == 1) {
                        routes[i][j] = 1;
                    }
                }
            }
        }

        // 3. print
        for (int i = 1; i < N + 1; i++) {
            int cnt = 0;
            for (int j = 1; j < N + 1; j++) {
                if (i == j) {
                    continue;
                }
                // i, j 간의 관계를 정의할 수 없는 경우를 카운트
                if (routes[i][j] == 0 && routes[j][i] == 0) {
                    cnt++;
                }
            }
            System.out.println(cnt);
        }
    }

}

// 1 -> 2 -> 3-> 4
//6
//5
//    1 2
//    2 3
//    3 4
//    5 4
//    6 5
