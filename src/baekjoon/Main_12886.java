package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * solved.ac bfs( 36분 > 12:04 - 12:40 )  12886 돌 그룹 풀이 방법: bfs로 풀이하였는데 처음엔 visited 처리를 Group 클래스에
 * equals&hashcode 메서드를 두고 Set자료형을 통해 하여 속도가 느리게 나왔다. 그래서 돌의 총양은 변하지 않는 점을 활용해 2차원 boolean 배열을 통해
 * 방문처리를 하여 속도를 향상 시켰다.
 */

public class Main_12886 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;

    public static int[] stones = new int[3];

    public static boolean[][] visited = new boolean[1500][1500];

    public static void main(String[] args) throws Exception {

        // 1. input
        tokens = new StringTokenizer(br.readLine());

        for (int i = 0; i < 3; i++) {
            stones[i] = Integer.parseInt(tokens.nextToken());
        }

        // 2. bfs
        int answer = bfs();

        // 3. print
        System.out.println(answer);
    }

    private static int bfs() {
        Queue<Group> queue = new LinkedList<>();

        Group group = new Group(stones[0], stones[1], stones[2]);

        if (!group.allEqual()) {
            queue.add(group);
            visited[stones[0]][stones[1]] = true;
        } else {
            return 1;
        }

        while (!queue.isEmpty()) {
            Group nowGroup = queue.poll();

            if (!nowGroup.aEqualToB()) {
                int a = Math.min(nowGroup.a, nowGroup.b) + Math.min(nowGroup.a, nowGroup.b);
                int b = Math.max(nowGroup.a, nowGroup.b) - Math.min(nowGroup.a, nowGroup.b);

                if (isAnswer(a, b, nowGroup.c)) {
                    return 1;
                } else {
                    Group newGroup = new Group(a, b, nowGroup.c);
                    if (!visited[a][b]) {
                        queue.add(newGroup);
                        visited[a][b] = true;
                    }
                }
            }
            if (!nowGroup.bEqualToC()) {
                int b = Math.min(nowGroup.c, nowGroup.b) + Math.min(nowGroup.c, nowGroup.b);
                int c = Math.max(nowGroup.c, nowGroup.b) - Math.min(nowGroup.c, nowGroup.b);

                if (isAnswer(nowGroup.a, b, c)) {
                    return 1;
                } else {
                    Group newGroup = new Group(nowGroup.a, b, c);
                    if (!visited[b][c]) {
                        queue.add(newGroup);
                        visited[b][c] = true;
                    }
                }
            }

            if (!nowGroup.aEqualToC()) {
                int a = Math.min(nowGroup.a, nowGroup.c) + Math.min(nowGroup.a, nowGroup.c);
                int c = Math.max(nowGroup.a, nowGroup.c) - Math.min(nowGroup.a, nowGroup.c);

                if (isAnswer(a, nowGroup.b, c)) {
                    return 1;
                } else {
                    Group newGroup = new Group(a, nowGroup.b, c);
                    if (!visited[a][c]) {
                        queue.add(newGroup);
                        visited[a][c] = true;
                    }
                }
            }
        }
        return 0;
    }

    public static boolean isAnswer(int a, int b, int c) {
        return a == b && b == c;
    }

    public static class Group {

        private int a;
        private int b;
        private int c;

        public Group(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public boolean allEqual() {
            return this.a == this.b && this.b == this.c;
        }

        public boolean aEqualToB() {
            return this.a == this.b;
        }

        public boolean bEqualToC() {
            return this.b == this.c;
        }

        public boolean aEqualToC() {
            return this.a == this.c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Group group = (Group) o;
            return a == group.a && b == group.b && c == group.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b, c);
        }
    }

}
