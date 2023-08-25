import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String args[]) throws Exception
    {
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int[][] city = new int[n][n];
        final int INF = 100000*n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                city[i][j] = INF;

                if(i == j)
                    city[i][j] = 0;
            }
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            city[a][b] = Math.min(city[a][b], c);
        }

        // 플로이드-와샬
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    city[j][k] = Math.min(city[j][k], city[j][i] + city[i][k]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(city[i][j] >= INF) {
                    sb.append(0);
                } else {
                    sb.append(city[i][j]);
                }
                sb.append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();

    }
}
