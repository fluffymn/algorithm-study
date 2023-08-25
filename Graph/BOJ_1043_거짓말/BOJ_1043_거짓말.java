import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String args[]) throws Exception
    {

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Queue<Integer> truth = new ArrayDeque<>();  // 진실을 아는 사람들 번호 목록
        Queue<Integer> spread = new ArrayDeque<>(); // 진실이 퍼지게 될 파티 번호 목록
        boolean[] check = new boolean[N];   // i번째 사람이 진실을 알고있는지 체크
        boolean[][] party = new boolean[M][N];  // i번째 파티에 j번째 사람이 참석하는지 여부
        int result = M;

        // 진실을 알고있는 사람들을 truth에 넣어주고 check의 상태도 변경해준다
        st = new StringTokenizer(br.readLine());
        int truthN = Integer.parseInt(st.nextToken());
        for (int i = 0; i < truthN; i++) {
            int p = Integer.parseInt(st.nextToken()) - 1;
            truth.offer(p);
            check[p] = true;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            for (int j = 0; j < n; j++) {
                int p = Integer.parseInt(st.nextToken());
                party[i][p - 1] = true;
            }
        }

        while (!truth.isEmpty()) {
            int person = truth.poll();  // 진실을 아는 사람

            spread.clear();
            for (int i = 0; i < M; i++) {
                if(party[i][person]) {
                    spread.offer(i);    // 진실을 아는 사람이 참석하는 파티 목록 생성
                }
            }

            while (!spread.isEmpty()) {
                int p = spread.poll();  // 진실을 아는 사람이 참석하는 파티
                for (int i = 0; i < N; i++) {
                    if(party[p][i] && !check[i]) {  // 파티에 참석하면서 아직 진실을 모를 경우
                        truth.offer(i); // 진실을 아는 사람 목록에 추가
                        check[i] = true;    // 진실을 알고있다고 check
                    }
                }
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if(party[i][j] && check[j]) {   // 파티에 참석하면서 진실을 알고있는 경우
                    result--;
                    break;
                }
            }
        }

        bw.write(Integer.toString(result));
        bw.close();
        br.close();

    }
}
