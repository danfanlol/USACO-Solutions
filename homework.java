import java.io.*;
import java.util.StringTokenizer;

public class homework {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("homework.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] sums = new int[N];
        float[] minimums = new float[N];
        float minimum = Integer.MAX_VALUE;
        int sum = 0;
        int[] vals = new int[N];
        for (int i= 0; i < N;i++){
            int num = Integer.parseInt(st.nextToken());
            sum += num;
            vals[N-1-i] = num;
            sums[i] = sum;
        }
        for (int i = 0 ; i <N;i++){
            if (vals[i] < minimum) minimum = vals[i];
            minimums[N-i-1] = minimum;
        }
        float max = 0;
        float s= 0;
        float[] scores = new float[N];
        for (int i = 0;i < N-2;i++){
            s = sums[N-1] - sums[i];
            s -= minimums[i+1];
            s = s/(N-2-i);
            if (s > max) max = s;
            scores[i] = s;
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("homework.out")));
        for (int i= 0 ;i < N;i++) if (scores[i] == max) pw.println(i+1);
        pw.close();
    }
}
