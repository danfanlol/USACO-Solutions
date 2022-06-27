import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class cowntagion {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] neighbors = new int[N];
        for (int i= 0 ;i < N-1;i++){
            String[] name = br.readLine().split(" " );
            int a  = Integer.parseInt(name[0]);
            int b = Integer.parseInt(name[1]);
            neighbors[a-1] += 1;
            neighbors[b-1] += 1;
        }
        int ans = 0;
        for (int i= 0; i < N;i++){
            int n = 0;
            if (i != 0) n = neighbors[i] - 1;
            else n = neighbors[i];
            int dayspassed = 0;
            int val = 1;
            while (val <= n){
                dayspassed += 1;
                val *= 2;
            }
            ans += dayspassed;
            ans += n;
        }
        System.out.println(ans);
    }
}