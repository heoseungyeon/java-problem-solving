package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * solved.ac 완전탐색(86분 > 16:34 - 18:00) 1062 가르침 풀이 방법: 조합 + 백트래킹으로 문제를 풀이했는데 비트마스킹으로 풀면 시간복잡도를 많이 줄일
 * 수 있을듯..
 */

public class Main_1062 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static Set<Character>[] compositionSet;
    public static Set<Character> alphas = new HashSet<>();
    public static Set<Character> candidates = new HashSet<>();
    public static List<Character> alphaLists;
    public static Set<Character> defaults = new HashSet<>(Arrays.asList('a', 'n', 't', 'i', 'c'));
    public static int N, K;
    public static int answer = 0;


    public static void main(String[] args) throws Exception {
        // 1. input
        tokens = new StringTokenizer(br.readLine());

        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        compositionSet = new HashSet[N];

        for (int i = 0; i < N; i++) {
            compositionSet[i] = new HashSet<>();
        }

        for (int i = 0; i < N; i++) {
            String input = br.readLine();

            for (int j = 4; j < input.length() - 4; j++) {
                if (!defaults.contains(input.charAt(j))) {
                    compositionSet[i].add(input.charAt(j));
                    alphas.add(input.charAt(j));
                }
            }
        }

        // print
        if (K < 5) {
            System.out.println(answer);
        } else {
            // 2. bruteForce
            alphaLists = new ArrayList<>(alphas);
            dfs(0, 5);
            System.out.println(answer);
        }
    }

    private static void dfs(int now, int cnt) {
        if (cnt == K || now == alphaLists.size()) {
            answer = Math.max(answer, possibleCounts());
            return;
        }
        for (int i = now; i < alphaLists.size(); i++) {
            candidates.add(alphaLists.get(i));
            dfs(i + 1, cnt + 1);
            candidates.remove(alphaLists.get(i));
        }
    }

    private static int possibleCounts() {
        int rtn = 0;
        for (int i = 0; i < N; i++) {
            boolean possible = true;
            for (Character ch : compositionSet[i]) {
                if (!candidates.contains(ch)) {
                    possible = false;
                    break;
                }
            }
            if (possible) {
                rtn += 1;
            }
        }

        return rtn;
    }
}
