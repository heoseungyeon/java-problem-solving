package programmers.dp;

/**
 * 프로그래머스 고득점 kit 정수 삼각형 DP
 */

/*
 * 풀이 방법 : 메모이제이션(BOTTOM-UP) 풀이, 맨 밑 인덱스의 값을 초기화 해두고, max 값 비교하며 풀이함.
 */

public class IntegerTriangle {

    public static int[][] dp;

    public static void main(String[] args) {
        IntegerTriangle integerTriangle = new IntegerTriangle();
        System.out.println(integerTriangle.solution(
            new int[][]{{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}}));
    }

    public int solution(int[][] triangle) {
        int answer = 0;

        // 1. init
        dp = new int[triangle.length][triangle.length];

        for (int j = 0; j < triangle.length; j++) {
            dp[triangle.length - 1][j] = triangle[triangle.length - 1][j];
        }

        // 2. solve
        for (int i = triangle.length - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = triangle[i][j] + Math.max(dp[i + 1][j], dp[i + 1][j + 1]);
            }
        }

        answer = dp[0][0];
        return answer;
    }

}
