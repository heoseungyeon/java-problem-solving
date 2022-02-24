package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac 이분탐색(참고)( 53분 > 13:29 - 15:22) 2343 기타 레슨 풀이 방법: 이분탐색에서 적절한 start, end 값을 설정해야 된다는 것을
 * 해당 문제를 풀면서 깨달았다. left 값을 전체 강의 중 가장 긴 값으로 설정한 이유는 하나의 블루레이는 강의 하나가 온전히 담겨야 되기 때문이고 right 값을 전체 강의
 * 합으로 설정한 이유는 하나의 블루레이가 전체 강의보다 많은 시간을 담을 수 없기 때문이다. 또 어려웠던 부분은 mid 값을 '하나의 블루레이에 담길 수 있는 상한선'으로
 * 설정하였는데 반복문을 돌며 무조건 mid 값보다 작은 강의 집단이 블루레이에 담는 것이었고, 또 다른 하나는 cnt 값이 m(블루레이의 수)보다 작거나 같을 경우 answer
 * = Math.min(answer, mid); 을 작성하였는데 '작을 경우는 정답이 되지 않자나?" 했지만, 해당 조건문에선 right 에 mid -1 을 넣어주므로 cnt는
 * 증가할 것이고, 그러므로 cnt == m (정답 조건)이 나왔을 때 그 이후에 answer = Math.min(answer, mid);을 거칠일이 없다.
 */

public class Main_2343 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N, M;
    public static int[] lectures;
    public static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {

        // 1. input
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        int lowValue = -1, highValue = 0;
        lectures = new int[N];
        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {

            lectures[i] = Integer.parseInt(tokens.nextToken());
            lowValue = Math.max(lowValue, lectures[i]);
            highValue += lectures[i];
        }

        // 2. binarySearch
        binarySearch(N, M, lowValue, highValue);

        // 3. print asnwer
        System.out.println(answer);
    }

    private static void binarySearch(int n, int m, int leftValue, int rightValue) {
        int left = leftValue;
        int right = rightValue;

        while (left <= right) {
            int mid = (left + right) / 2;

            int sum = 0;
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (sum + lectures[i] > mid) {
                    sum = 0;
                    cnt += 1;
                }
                sum += lectures[i];
            }
            if (sum <= mid) {
                cnt += 1;
            }

            if (cnt > m) {
                left = mid + 1;
            } else {
                right = mid - 1;
                answer = Math.min(answer, mid);
            }
        }

    }
}
