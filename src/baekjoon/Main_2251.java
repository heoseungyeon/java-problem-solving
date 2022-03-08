package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * solved.ac bfs/dfs(1시간 반 정도?) 2251 물통 풀이 방법: 이거 bfs인데 진짜 완전 시뮬레이션.. ㅡ,ㅡ
 */

public class Main_2251 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int[] fullWaters = new int[3];
    public static boolean[][][] visited = new boolean[201][201][201];
    public static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) throws Exception {
        // 1. input
        tokens = new StringTokenizer(br.readLine());

        fullWaters[0] = Integer.parseInt(tokens.nextToken());
        fullWaters[1] = Integer.parseInt(tokens.nextToken());
        fullWaters[2] = Integer.parseInt(tokens.nextToken());

        Queue<Integer[]> queue = new LinkedList<>();
        queue.add(new Integer[]{0, 0, fullWaters[2]});
        visited[0][0][fullWaters[2]] = true;
        set.add(fullWaters[2]);
        while (!queue.isEmpty()) { // 3 7
            Integer[] waters = queue.poll();

            int a, b, c;
            if (waters[0] > 0) {
                if (waters[0] > fullWaters[1] - waters[1]) {
                    a = waters[0] - (fullWaters[1] - waters[1]);
                    b = fullWaters[1];
                    c = waters[2];
                } else {
                    a = 0;
                    b = waters[1] + waters[0];
                    c = waters[2];
                }
                if (!visited[a][b][c]) {
                    visited[a][b][c] = true;
                    queue.add(new Integer[]{a, b, c});
                    if (a == 0) {
                        set.add(c);
                    }
                }
                if (waters[0] > fullWaters[2] - waters[2]) {
                    a = waters[0] - (fullWaters[2] - waters[2]);
                    b = waters[1];
                    c = fullWaters[2];
                } else {
                    a = 0;
                    b = waters[1];
                    c = waters[2] + waters[0];
                }
                if (!visited[a][b][c]) {
                    visited[a][b][c] = true;
                    queue.add(new Integer[]{a, b, c});
                    if (a == 0) {
                        set.add(c);
                    }
                }
            }
            if (waters[1] > 0) {
                if (waters[1] > fullWaters[0] - waters[0]) {
                    a = fullWaters[0];
                    b = waters[1] - (fullWaters[0] - waters[0]);
                    c = waters[2];
                } else {
                    a = waters[1] + waters[0];
                    b = 0;
                    c = waters[2];
                }
                if (!visited[a][b][c]) {
                    visited[a][b][c] = true;
                    queue.add(new Integer[]{a, b, c});
                    if (a == 0) {
                        set.add(c);
                    }
                }
                if (waters[1] > fullWaters[2] - waters[2]) {
                    a = waters[0];
                    b = waters[1] - (fullWaters[2] - waters[2]);
                    c = fullWaters[2];
                } else {
                    a = waters[0];
                    b = 0;
                    c = waters[1] + waters[2];
                }
                if (!visited[a][b][c]) {
                    visited[a][b][c] = true;
                    queue.add(new Integer[]{a, b, c});
                    if (a == 0) {
                        set.add(c);
                    }
                }
            }
            if (waters[2] > 0) {
                if (waters[2] > fullWaters[0] - waters[0]) {
                    a = fullWaters[0];
                    b = waters[1];
                    c = waters[2] - (fullWaters[0] - waters[0]);
                } else {
                    a = waters[0] + waters[2];
                    b = waters[1];
                    c = 0;
                }
                if (!visited[a][b][c]) {
                    visited[a][b][c] = true;
                    queue.add(new Integer[]{a, b, c});
                    if (a == 0) {
                        set.add(c);
                    }
                }
                if (waters[2] > fullWaters[1] - waters[1]) {
                    a = waters[0];
                    b = fullWaters[1];
                    c = waters[2] - (fullWaters[1] - waters[1]);
                } else {
                    a = waters[0];
                    b = waters[1] + waters[2];
                    c = 0;
                }
                if (!visited[a][b][c]) {
                    visited[a][b][c] = true;
                    queue.add(new Integer[]{a, b, c});
                    if (a == 0) {
                        set.add(c);
                    }
                }
            }

        }// while

        // sort and print
        List<Integer> list = new LinkedList<>(set);
        Collections.sort(list);

        list.stream().map(integer -> integer + " ").forEach(System.out::print);
    }


}
