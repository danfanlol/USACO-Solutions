import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class swapityswapityswap {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("swap.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        for (int i= 0 ; i < N;i++){
            arr[i] = i+1;
        }
        for (int i= 0 ; i < M;i++){
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            while (first <= second) {
                int s = arr[first - 1];
                arr[first - 1] = arr[second - 1];
                arr[second - 1] = s;
                first += 1;
                second -= 1;
            }
        }
        HashMap<Integer,ArrayList<ArrayList<Integer>>> info = new HashMap();
        for (int i= 0 ; i < N;i++){
            ArrayList<ArrayList<Integer>> s = new ArrayList();
            info.put(i,s);
        }
        for (int i =0; i < arr.length;i++){
            ArrayList<Integer> pos = new ArrayList();
            pos.add(i);
            info.get(arr[i]-1).add(pos);
        }
        boolean[] visited = new boolean[N];
        for (int i= 0 ; i <N;i++){
            if (!visited[i]){
                int cnt = 1;
                int cur = info.get(i).get(0).get(0);
                ArrayList<Integer> path = new ArrayList();
                path.add(i);
                visited[i] = true;
                if (cur != i){
                    visited[i] = true;
                    path.add(cur);
                    cnt += 1;
                }
                while (true){
                    cur = info.get(cur).get(0).get(0);
                    if (cur == i){
                        break;
                    }
                    path.add(cur);
                    visited[i] = true;
                    cnt += 1;
                }
                for (int j = 0 ; j < cnt;j++){
                    int node = path.get(j);
                    visited[node] = true;
                    int pos = j;
                    int mod = K % cnt;
                    pos = (pos + mod) % cnt; //% cnt to avoid index overflow
                    arr[path.get(pos)] = node + 1;
                }
            }
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("swap.out")));
        for (int i = 0 ; i < arr.length;i++){
            pw.println(arr[i]);
        }
        pw.close();
    }
}
