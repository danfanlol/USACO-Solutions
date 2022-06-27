import java.io.*;
import java.util.StringTokenizer;
// The most important observation that should be made here is that you don't need to simulate painting each rectangle
// naively through a nested loop. If we break the 2d grid down into 1000 1d grids, each rectangle now becomes an interval.
// Mark when an interval starts and ends. Create a sum counter that then sweeps through each 1d grid and stores the number of paint coats.
// Doing this will be better than naively simulating each rectangle.

// However, we can optimize it even further. There is no need for us to have a bunch of 1d grids. We can store 2d sum arrays
// which details the number of coats of paint for every (i,j) index. The following formula can be used to get the sum
// for any (i,j) index: sum(i,j) = sum(i,j-1) + sum(i-1,j) - sum(i-1,j-1) + barn(i,j).
public class paintingbarn {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] barn = new int[1002][1002];
        for (int i= 0; i < N;i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            barn[y1][x1] += 1;
            barn[y2][x2] += 1;
            barn[y1][x2] -= 1;
            barn[y2][x1] -= 1;
        }
        int ans =0 ;
        int[][] sums = new int[1002][1002];
        for (int i= 0; i < 1002;i++){
            for (int j= 0; j < 1002;j++){
                int sum = barn[i][j];
                if (j > 0) sum += sums[i][j-1];
                if (i > 0) sum += sums[i-1][j];
                if (j > 0 && i > 0) sum -= sums[i-1][j-1];
                sums[i][j] = sum;
                if (sum == M) ans += 1;
            }
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("paintbarn.out")));
        pw.println(ans);
        pw.close();
    }
}
