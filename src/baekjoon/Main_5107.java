package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * solved.ac 해시( 59분 > 11:52 - 12:51) 5107 마니또 풀이 방법: 무방향 그래프에서 사이클 탐색을 하기 위해선 서로소 알고리즘을 사용하지만 방향
 * 그래프에서 사이클 탐색을 하기 위해선 dfs 알고리즘을 사용하면 된다. 시간 복잡도 및 클린코드를 위해서 이름에 대해 숫자로 인덱싱하는 자료구조를 사용하면 더 좋았을 듯.
 */

public class Main_5107 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static StringBuilder sb = new StringBuilder();
    public static int N;
    public static int testCase = 1;
    public static Map<String, Boolean> visited;
    public static Map<String, String> info;

    public static void main(String[] args) throws Exception {
        // 1. input
        while (true) {
            N = Integer.parseInt(br.readLine());

            if (N == 0) {
                break;
            }
            int answer = 0;

            visited = new HashMap<>();
            info = new HashMap<>();

            for (int i = 0; i < N; i++) {
                tokens = new StringTokenizer(br.readLine());
                String name1 = tokens.nextToken();
                String name2 = tokens.nextToken();
                visited.put(name1, false);
                info.put(name1, name2);
            }

            for (String start : info.keySet()) {
                if (!visited.get(start)) {
                    visited.replace(start, true);
                    if (dfs(start, info.get(start))) {
                        answer += 1;
                    }
                }
            }

            sb.append(testCase + " " + answer + "\n");
            testCase++;
        }

        // 2. print
        System.out.println(sb);
    }

    private static boolean dfs(String start, String end) {
        boolean rtn = false;
        if (start.equals(end)) {
            return true;
        }

        if (!visited.get(end)) {
            visited.replace(end, true);
            rtn = dfs(start, info.get(end));
        }

        return rtn;
    }


}
