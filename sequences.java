import java.io.*;
import java.util.StringTokenizer;

public class sequences {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("div7.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long[] sums = new long[N];
        long psum = 0;
        for (int i= 0; i <N;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            psum += s;
            sums[i] = psum;
        }
        int ans = 0;
        for (int i= 0; i < N;i++){
            if (sums[i] % 7 == 0){
                ans = i+1;
                continue;
            }
            for (int j =0; j < i - ans; j++){
                if ((sums[i] - sums[j]) % 7 == 0){
                    ans = i-j;
                    break;
                }
            }
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("div7.out")));
        pw.println(ans);
        pw.close();
    }
}
