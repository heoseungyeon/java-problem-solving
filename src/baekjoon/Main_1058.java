package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * solved.ac 플로이드 와샬 1058 친구 풀이 방법: 이 문제로 플로이드 와샬의 필요성을 살짝 느낌. 내 풀이 방식은 3중 for문을 돌며 i-j 의 2-친구의 갯수를
 * 구함. 그 조건은 isFriends[i][j] == 1 이거나 k 건너 친구일 경우. 다른 풀이 방식으론 플로이드 와샬을 통해 거리가 2인 친구들을 모조리 구함. -> 거리가
 * 2다? -> 건너친구까지 ㅇㅈ
 */


public class Main_1058 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N;
    public static int[][] isFriends;
    public static int[][] twoFriendsCount;

    public static void main(String[] args) throws Exception {

        // 1. input
        N = Integer.parseInt(br.readLine());

        isFriends = new int[N][N];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                char a = input.charAt(j);
                if (a == 'Y') {
                    isFriends[i][j] = 1;
                }
            }
        }

        twoFriendsCount = new int[N][N];

        // 2. floyd - warshall
        int answer = 0;
        // 경유 노드
        for (int k = 0; k < N; k++) {
            // 출발 노드
            for (int i = 0; i < N; i++) {
                // 도착 노드
                for (int j = 0; j < N; j++) {
                    if (i == j) {
                        continue;
                    }
                    // i, j 가 친구거나 k 건너 친구인 경우 i-j는 친구이다.
                    if (isFriends[i][j] == 1 || (isFriends[i][k] == 1 && isFriends[k][j] == 1)) {
                        twoFriendsCount[i][j] = 1;
                    }
                }
            }
        }
        // i의 2-friends 수를 구하고 max 값을 구함.
        for (int i = 0; i < N; i++) {
            answer = Math.max(answer, Arrays.stream(twoFriendsCount[i]).sum());
        }

        // 3. print
        System.out.println(answer);


    }

}
