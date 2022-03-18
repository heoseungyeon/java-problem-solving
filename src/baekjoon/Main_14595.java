package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * solved.ac 시뮬레이션(15:11 - 16:30) 14595 동방 프로젝트(large)  풀이방법 : (Large)버전도 통과하게 문제 풀이 하였음. minX와 maxY
 * 값을 두고 두 값이 변경되면 count를 갱신하거나 추가하는 알고리즘으로 풀이함. -> large 버전 수정 : count 를 저장하는 List를 사용하지 않고, 바로바로
 * count를 더해주면서 시간복잡도 및 공간복잡도를 줄임.
 */

public class Main_14595 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N, M;
    public static List<Room> rooms = new LinkedList<>();
    public static int counts = 0;

    public static void main(String[] args) throws Exception {
        // 1. input
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(tokens.nextToken());
            int y = Integer.parseInt(tokens.nextToken());
            // x+1~y-1
            rooms.add(new Room(x, y));
        }

        // 2. sort
        Collections.sort(rooms);

        // 3. calculate count
        int count = 0;
        int minX = Integer.MAX_VALUE;
        int maxY = -1;
        for (int i = 0; i < M; i++) {
            if (i == 0) {
                minX = rooms.get(i).x;
                maxY = rooms.get(i).y;
                counts += count;
                count = maxY - minX;
            } else {
                if (rooms.get(i).x > maxY) {
                    minX = rooms.get(i).x;
                    maxY = rooms.get(i).y;
                    counts += count;
                    count = maxY - minX;
                } else if (rooms.get(i).y > maxY) {
                    maxY = rooms.get(i).y;
                    count = maxY - minX;
                }
            }
        }
        // 마지막에 더해지지 않은 count 더해줌.
        counts += count;

        // 4. print
        System.out.println(N - counts);


    }

    public static class Room implements Comparable<Room> {

        private int x;
        private int y;

        public Room(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Room o) {
            if (this.x > o.x) {
                return 1;
            } else {
                return -1;
            }
        }
    }

}
