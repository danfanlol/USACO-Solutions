import java.io.*;
import java.util.*;
class pair{
    int x,y;
    public pair(int s, int d){
        x = s;
        y = d;
    }
}
public class mooyo {
    public static boolean findcomponent(int[][] board, int i, int j,int N, boolean[][] visited, int K){
        ArrayList<pair> points = new ArrayList();
        Stack<pair> curs = new Stack();
        curs.add(new pair(i,j));
        while (curs.size() != 0){
            pair cur = curs.pop();
            if (cur.x < 0 || cur.x >= N || cur.y < 0 || cur.y >= 10 || visited[cur.x][cur.y] || board[cur.x][cur.y] != board[i][j]) continue;
            points.add(cur);
            visited[cur.x][cur.y] = true;
            curs.add(new pair(cur.x+1,cur.y));
            curs.add(new pair(cur.x-1,cur.y));
            curs.add(new pair(cur.x,cur.y+1));
            curs.add(new pair(cur.x,cur.y-1));
        }
        if (points.size() >= K) for (pair pair : points) board[pair.x][pair.y] = 0;
        if (points.size() >= K) return true;
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("mooyomooyo.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] board = new int[N][10];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String row = st.nextToken();
            for (int j = 0; j < 10; j++) board[i][j] = row.charAt(j)-48;
        }
        while (true) {
            boolean[][] visited = new boolean[N][10];
            int cnt = 0;
            for (int i = 0; i < N; i++) for (int j = 0; j < 10; j++) if (!visited[i][j] && board[i][j] != 0) if(findcomponent(board, i, j,N,visited,K)) cnt += 1;
            if (cnt == 0) break;
            for (int i = 9; i >= 0;i--){
                int j =N-1;
                while (j >= 0){
                    if (board[j][i] != 0) break;
                    j -= 1;
                    if (j < 0) break;
                }
                cnt = 0;
                while (j >= 0){
                    if (board[j][i] != 0){
                        int val = board[j][i];
                        board[j][i] = 0;
                        board[N-1-cnt][i] = val;
                        cnt += 1;
                    }
                    j -= 1;
                }
            }
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mooyomooyo.out")));
        for (int i= 0; i < N;i++){
            StringBuilder line = new StringBuilder();
            for (int j =0 ;j<10;j++) line.append(board[i][j]);
            pw.println(line);
        }
        pw.close();
    }
}
