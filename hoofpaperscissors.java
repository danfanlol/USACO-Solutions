import java.io.*;
import java.util.StringTokenizer;
public class hoofpaperscissors {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("hps.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] pc = new int[N];
        int[] hc = new int[N];
        int[] sc = new int[N];
        int psum = 0;
        int hsum = 0;
        int ssum = 0;
        for (int i =0 ; i <N;i++){
            st = new StringTokenizer(br.readLine());
            String s= st.nextToken();
            if (s.equals("H")) hsum += 1;
            if (s.equals("P")) psum += 1;
            if (s.equals("S")) ssum += 1;
            pc[i] = psum;
            sc[i] = ssum;
            hc[i] = hsum;
        }
        int max = 0;
        for (int i= 0; i <N;i++) max = Math.max(max,Math.max(sc[i],Math.max(pc[i],hc[i])) + Math.max(sc[N-1] - sc[i], Math.max(pc[N-1]-pc[i],hc[N-1]-hc[i])));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
        pw.println(max);
        pw.close();
    }
}
