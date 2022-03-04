package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_8393 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int n;

    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());

        System.out.println(n * (n + 1) / 2);
    }
}
