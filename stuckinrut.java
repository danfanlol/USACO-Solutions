import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
public class stuckinrut {
    public static void dfs(HashMap<Integer,Integer> blocks, int[] ans, int i){
        int cnt = 0;
        while (true){
            if (ans[i] != 0){
                ans[i] += cnt;
            }
            else{
                ans[i] += cnt;
                cnt += 1;
            }
            i = blocks.get(i);
            if (i == -1)
                break;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] xs = new int[N];
        int[] ys = new int[N];
        ArrayList<Integer> north = new ArrayList();
        ArrayList<Integer> east = new ArrayList();
        for (int i= 0 ;i < N;i++){
            String[] name = br.readLine().split(" " );
            String d = name[0];
            if (name[0].equals("N")) north.add(i);
            else east.add(i);
            xs[i] = Integer.parseInt(name[1]);
            ys[i] = Integer.parseInt(name[2]);
        }
        north.sort(Comparator.comparingInt(j -> xs[j]));
        east.sort(Comparator.comparingInt(j -> ys[j]));
        HashMap<Integer,Integer> blocks = new HashMap();
        for (int i= 0; i < N;i++)
            blocks.put(i,-1);
        int[] incoming = new int[N];
        for (int b= 0 ;b < east.size();b++){
            for (int s =0; s < north.size();s++){
                int i = east.get(b);
                int j = north.get(s);
                if (xs[j] < xs[i] || ys[j] > ys[i] || blocks.get(j) != -1) continue;
                if (xs[j] - xs[i] > ys[i] - ys[j]){ //j blocks i so put block at i
                    blocks.put(i,j);
                    incoming[j] += 1;
                    break;
                }
                else if (xs[j] - xs[i] < ys[i] - ys[j]){ //i blocks j so put block at j
                    blocks.put(j,i);
                    incoming[i] += 1;
                }
            }
        }
        int[] ans = new int[N];
        for (int i= 0; i <N;i++){
            if (incoming[i] == 0 && blocks.get(i) != -1){
                dfs(blocks,ans,i);
            }
        }
        for (int i=0 ;i < N;i++){
            System.out.println(ans[i]);
        }
    }
}
