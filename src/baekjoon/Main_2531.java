package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * solved.ac 투포인터(&슬라이딩 윈도우) 2531 회전 초밥 풀이 방법: 연속된 k개 속 특정 초밥의 갯수를 저장하는 countSush를 통해 dishSet(중복제거)에
 * 초밥을 뺄지 안뺄지 결정하여 정답을 구함 굳이 dishSet을 쓰지 않더라도 count 변수를 조건에 따라 증감하는게 더 시간복잡도가 좋을 듯!
 */

public class Main_2531 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;

    // 회전 초밥 벨트에 놓인 접시의 수 N, 초밥의 가짓수 d, 연속해서 먹는 접시의 수 k, 쿠폰 번호 c
    public static int N, d, k, c;
    public static int[] dishes;
    public static int maxSize = 0;
    public static int answer;
    public static int[] countSush;
    public static Set<Integer> dishSet = new HashSet<>();

    public static void main(String[] args) throws Exception {
        // 1. input
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        d = Integer.parseInt(tokens.nextToken());
        k = Integer.parseInt(tokens.nextToken());
        c = Integer.parseInt(tokens.nextToken());

        dishes = new int[N];
        countSush = new int[d + 1];

        for (int i = 0; i < N; i++) {
            dishes[i] = Integer.parseInt(br.readLine());
        }

        // 2. init first K dishes
        for (int i = 0; i < k; i++) {
            countSush[dishes[i % N]]++;
            dishSet.add(dishes[i % N]);
        }
        // 3. calculate all cases
        for (int i = k; i < N + k; i++) {
            if (countSush[dishes[(i - k) % N]] > 0) {
                countSush[dishes[(i - k) % N]]--;
            }
            if (countSush[dishes[(i - k) % N]] == 0) {
                dishSet.remove(dishes[(i - k) % N]);
            }
            dishSet.add(dishes[i % N]);
            countSush[dishes[i % N]]++;
            if (countSush[c] != 0) {
                maxSize = Math.max(maxSize, dishSet.size());
            } else {
                maxSize = Math.max(maxSize, dishSet.size() + 1);
            }
        }

        // 4. print answer
        answer = maxSize;
        System.out.println(answer);
    }

}
