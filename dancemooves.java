import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class dancemooves {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] name = br.readLine().split(" " );
        int N = Integer.parseInt(name[0]);
        int M = Integer.parseInt(name[1]);
        int[] pos = new int[N];
        for (int i= 0; i < N;i++){
            pos[i] = i+1;
        }
        HashMap<Integer,Set<Integer>> paths = new HashMap();
        for (int i= 0 ;i <N;i++){
            paths.put(i, new HashSet<>());
        }
        for (int i= 0; i < M;i++){
            name = br.readLine().split(" " );
            int start = Integer.parseInt(name[0]);
            int end = Integer.parseInt(name[1]);
            int val = pos[start-1];
            paths.get(pos[start-1]-1).add(end);
            paths.get(pos[end-1]-1).add(start);
            pos[start-1] = pos[end-1];
            pos[end-1] = val;
        }
        HashMap<Integer,Integer> lastpos = new HashMap();
        for (int i = 0; i  <N;i++){
            lastpos.put(pos[i],i+1);
        }
        int[] ans = new int[N];
        boolean[] visited = new boolean[N];
        for (int i= 0 ;i  < N;i++){
            if (!visited[i]){
                Set<Integer> set = new HashSet();
                ArrayList<Integer> starts = new ArrayList();
                set.addAll(paths.get(i));
                starts.add(i+1);
                int cur = lastpos.get(i+1);
                visited[cur-1] = true;
                while (cur != i+1){
                    starts.add(cur);
                    set.addAll(paths.get(cur-1));
                    cur = lastpos.get(cur);
                    visited[cur-1] = true;
                }
                int size = starts.size();
                int setsize = set.size();
                if (setsize == 0) setsize = 1;
                for (int j = 0; j < size;j++) ans[starts.get(j)-1] = setsize;
            }
        }
        StringBuilder out = new StringBuilder();
        for (int j = 0; j < N; j++) {
            out.append(ans[j]).append('\n');
        }
        System.out.print(out);
    }
}
