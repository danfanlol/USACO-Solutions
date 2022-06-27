import java.io.*;
import java.util.*;
public class moobuzz {
    public static int get_num(int N, double max){
        if (N == 0)
            return (int)max-1;
        if (N == 1)
            return (int)max-2;
        if (N==2)
            return (int)max-4;
        if (N==3)
            return (int)max-7;
        if (N==4)
            return (int)max-8;
        if (N==5)
            return (int)max - 11;
        if (N==6)
            return (int)max - 13;
        if (N==7)
            return (int)max-14;
        return 0;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("moobuzz.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moobuzz.out")));
        int N = Integer.parseInt(st.nextToken());
        double max = (Math.ceil((double)N/8) * 15);
        if (N > 8){
            if (N % 8 == 0)
                N = 8;
            else
                N %= 8;
        }
        int ans = get_num(8-N,max);
        pw.println(ans);
        pw.close();
    }
}
