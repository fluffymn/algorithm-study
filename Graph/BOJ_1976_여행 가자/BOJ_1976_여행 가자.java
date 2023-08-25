import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String args[]) throws Exception
    {

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        final int INF = 1000000;

        StringTokenizer st = null;

        String result = "YES";

        int[][] city = new int [N+1][N+1];
        int[] route = new int[M+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int d = Integer.parseInt(st.nextToken());
                city[i][j] = INF;
                if(i == j) {
                    city[i][j] = 0;
                } else if(d == 1) {
                    city[i][j] = 1;
                }
            }
        }

        //플로이드-와샬
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= N; k++) {
                    city[j][k] = Math.min(city[j][k], city[j][i] + city[i][k]);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M ; i++) {
            route[i] = Integer.parseInt(st.nextToken());
        }
        
        for (int i = 1; i < M; i++) {
            if(city[route[i]][route[i+1]] >= INF) {
                result = "NO";
            }
        }

        bw.write(result);
        bw.close();
        br.close();

    }
}
