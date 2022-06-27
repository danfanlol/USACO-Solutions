import java.io.*;
import java.util.*;

public class lemonideline {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lemonade.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] numbers = new int[N];
        for (int i = 0;i < N;i++){
            int num = Integer.parseInt(st.nextToken());
            numbers[i] = num;
        }
        int cur = 0;
        Arrays.sort(numbers);
        for (int i = numbers.length-1; i >= 0;i--){
            if (cur <= numbers[i]){
                cur += 1;
            }
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lemonade.out")));
        pw.println(cur);
        pw.close();
    }
}
