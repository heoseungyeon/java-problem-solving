package baekjoon;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac 완전탐색( > 14:40 - 16:31) 1007 벡터 매칭 풀이 방법: 벡터.... 어렵다.. 시작점일 경우엔 방문 처리를 하고 도착점일 땐 하지 않은
 * 뒤, 벡터 길이의 합을 계산하여 최솟값을 answer에 갱신하였음..
 */

public class Main_1007 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int T;
    public static int N;
    public static double answer;
    public static Point[] points;
    public static boolean[] visited;

    public static void main(String[] args) throws Exception {
        // 1. input 
        T = Integer.parseInt(br.readLine());

        while (T > 0) {
            N = Integer.parseInt(br.readLine());
            points = new Point[N];

            for (int i = 0; i < N; i++) {
                tokens = new StringTokenizer(br.readLine());
                points[i] = new Point(Integer.parseInt(tokens.nextToken()),
                    Integer.parseInt(tokens.nextToken()));
            }

            visited = new boolean[N];

            answer = Integer.MAX_VALUE;

            dfs(0, 0);

            System.out.println(String.format("%.12f", answer));
            T--;
        }
    }

    public static void dfs(int now, int count) {
        if (count == N / 2) {
            answer = Math.min(answer, calculateDistance());
        }
        for (int i = now; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i, count + 1);
                visited[i] = false;
            }
        }
    }

    public static double calculateDistance() {
        int aValue = 0;
        int bValue = 0;
        for (int i = 0; i < N; i++) {
            // 시작점일 때,
            if (visited[i]) {
                aValue -= points[i].x;
                bValue -= points[i].y;
                // 도착점일 때
            } else {
                aValue += points[i].x;
                bValue += points[i].y;
            }
        }

        return Math.sqrt(Math.pow(aValue, 2) + Math.pow(bValue, 2));
    }

}
