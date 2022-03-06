package programmers.binarySearch;

/**
 * 프로그래머스 고득점 kit 입국 심사 이분탐색
 */

/*
 * 풀이 방법 : 진 짜 아이디어를 떠올리는게 중요하구나 느낀 문제.. 구글 서칭해서 힌트를 보고 풀었는데 진짜 이리 간단한 문젠데
 * 왜 아이디어가 생각나지 않았을까..
 * 풀이 방법은 mid 값을 입국 심사 받는데 걸리는 시간으로 설정하고 mid 값 기준 심사 가능한 인원 수를 n과 비교하여 answer를 갱신한다.
 */

public class Immigration {

    public static void main(String[] args) {
        Immigration immigration = new Immigration();
        System.out.println(immigration.solution(6, new int[]{7, 10}));
    }

    public long solution(int n, int[] times) {
        long answer = binarySearch(n, times);

        return answer;
    }

    private long binarySearch(int n, int[] times) {
        long answer;
        long start = 0;
        long end = -1;
        for (int i = 0; i < times.length; i++) {
            end = Math.max(end, times[i]);
        }
        end = (long) n * end;
        answer = end + 1;

        while (start <= end) {
            long mid = (start + end) / 2;
            long totalSum = 0;
            for (int i = 0; i < times.length; i++) {
                totalSum += mid / times[i];
            }
            if (totalSum < n) {
                start = mid + 1;
            } else {
                answer = Math.min(answer, mid);
                end = mid - 1;
            }
        }

        return answer;
    }

}
