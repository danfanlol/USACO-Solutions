import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

// binary search on the minimum power. As part of our strategy, sort the landing spots. Our goal is to find the largest intervals
// we can get by filling them up with a maximum difference of 2 * power. If it is possible to get at most K intervals with this strategy
// and all the intervals together cover all of the positions, the power used to create those intervals is a working power.
public class angrycowsa {
    public static boolean simulate(int[] pos, int power, int N, int M){
        int cur = -1; //index
        for (int i= 0 ; i < M;i++){
            cur += 1;
            int curpos = pos[cur];
            while (true){
                cur += 1;
                if (cur > pos.length-1) break;
                if (pos[cur] - curpos > 2 * power){
                    cur --;
                    break;
                }
            }
            if (cur > pos.length-1){
                cur -= 1;
                break;
            }
        }
        if (cur != pos.length-1) return false;
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("angry.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] pos = new int[N];
        for (int i= 0; i < N;i++){
            st = new StringTokenizer(br.readLine());
            pos[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(pos);
        int low = 0;
        int high = pos[pos.length-1];
        while (low <= high){
            int power = (low + high) / 2;
            if (simulate(pos,power,N,M)) high = power - 1;
            else low = power + 1;
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
        pw.println(low);
        pw.close();
    }
}
