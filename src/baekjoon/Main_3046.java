package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_3046 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;

    public static void main(String[] args) throws Exception {
        int r1, s;
        tokens = new StringTokenizer(br.readLine());
        r1 = Integer.parseInt(tokens.nextToken());
        s = Integer.parseInt(tokens.nextToken());

        System.out.println(2 * s - r1);

    }

}
