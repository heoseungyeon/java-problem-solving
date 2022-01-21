package baekjoon;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * solved.ac bfs 17092 색칠 공부(1h30m) 풀이 방법: H와 W으로 2차원 배열을 만들면 Out of Memory 에러가 나기 때문에 배열의 크기를 줄이는
 * 아이디어가 중요하다. 검정색 부분을 리스트에 저장하고 9가지 경우에 대해 순회를 돌면서 hashMap의 key(String 으로 행과 열을 표현) 에 해당하는 값을 증가시켜
 * 이를 출력. 검정색의 갯수가 0인 경우는 전체 경우에서 카운트된 경우의 총합을 뺴서 계산 리팩토링 : String으로 행과 열을 표현하는 것을 객체로 변경, 기존 Data
 * 클래스에 equals & hashCode 를 Override 하여 구현
 */


public class Main_17092 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int H, W, N;
    public static List<Data> points = new ArrayList<>();
    public static Map<Data, Integer> hashMap = new HashMap<>();
    public static int[] answer = new int[10];

    public static void main(String[] args) throws Exception {
        // 1. input
        tokens = new StringTokenizer(br.readLine());

        H = parseInt(tokens.nextToken());
        W = parseInt(tokens.nextToken());
        N = parseInt(tokens.nextToken());

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(tokens.nextToken()) - 1;
            int col = Integer.parseInt(tokens.nextToken()) - 1;
            points.add(new Data(row, col));
        }

        // 2. sort points and solve
        Collections.sort(points);

        for (int i = 0; i < points.size(); i++) {
            int pRow = points.get(i).row;
            int pCol = points.get(i).col;
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (pRow + j >= 2 && pRow + j < H && pCol + k >= 2 && pCol + k < W) {
                        hashMap.put(new Data(pRow + j, pCol + k),
                            hashMap.getOrDefault(
                                new Data(pRow + j, pCol + k), 0)
                                + 1);
                    }
                }
            }
        }

        int cnt = 0;
        for (Data data : hashMap.keySet()) {
            answer[hashMap.get(data)] += 1;
            cnt += 1;
        }

        // 3. print
        for (int i = 0; i <= 9; i++) {
            if (i == 0) {
                System.out.println((long) (H - 2) * (W - 2) - cnt);
            } else {
                System.out.println(answer[i]);
            }
        }

    }

    public static class Data implements Comparable<Data> {

        private int row;
        private int col;

        public Data(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Data o) {
            if (this.row > o.row) {
                if (this.col > o.col) {
                    return 1;
                } else {
                    return 0;
                }
            } else if (this.row == o.row) {
                if (this.col > o.col) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        }

        @Override
        public String toString() {
            return "Data{" +
                "row=" + row +
                ", col=" + col +
                '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Data data = (Data) o;
            return row == data.row && col == data.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }
}
