import java.io.*;
import java.util.*;

public class clocktree {
    public static boolean isleaf(boolean[] visited,int node,ArrayList<ArrayList<Integer>> adjacencies){
        int cnt= 0;
        for (int i= 0 ; i < adjacencies.get(node-1).size();i++)
            if (!visited[adjacencies.get(node-1).get(i)-1]) cnt += 1;
        return cnt == 1;
    }
    public static boolean dfs(int room, ArrayList<ArrayList<Integer>> adjacencies, int N,int[] values1){
        int[] values = values1.clone();
        Stack<Integer> stack = new Stack();
        stack.push(adjacencies.get(room-1).get(0));
        HashMap<Integer,Boolean> visited = new HashMap();
        boolean[] full = new boolean[N];
        while (stack.size() != 0){
            int node = stack.pop();
            values[node-1] += 1;
            if (values[node-1] == 13) values[node-1] = 1;
            visited.put(node-1,true);
            if (isleaf(full,node,adjacencies)){
                full[node-1] = true;
                for (int i= 0; i < adjacencies.get(node-1).size(); i++){
                    if (!full[adjacencies.get(node-1).get(i)-1]){
                        values[adjacencies.get(node-1).get(i)-1] += 12-values[node-1];
                        if (values[adjacencies.get(node-1).get(i)-1] >= 13) values[adjacencies.get(node-1).get(i)-1] = values[adjacencies.get(node-1).get(i)-1]-12;
                        stack.clear();
                        stack.push(adjacencies.get(node-1).get(i));
                        visited.clear();
                        break;
                    }
                }
                values[node-1] = 12;
            }
            else{
                boolean has = false;
                for(int i =0 ; i <adjacencies.get(node-1).size();i++){
                    if (!visited.containsKey(adjacencies.get(node-1).get(i)-1) && !full[adjacencies.get(node-1).get(i)-1]){
                        stack.push(adjacencies.get(node-1).get(i));
                        has = true;
                        break;
                    }
                }
                if (!has) if (values[node-1] == 1) values[node-1] = 12;
            }
        }
        for (int i =0 ; i < N;i++) if (values[i] != 12) return false;
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("clocktree.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] values = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i= 0 ; i <N;i++) values[i] = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Integer>> adjacencies = new ArrayList();
        for (int i= 0 ; i < N;i++) adjacencies.add(new ArrayList<Integer>());
        for (int i= 0 ; i < N-1;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjacencies.get(a-1).add(b);
            adjacencies.get(b-1).add(a);
        }
        int cnt =0;
        for (int i= 1 ; i < N+1;i++) if (dfs(i,adjacencies,N,values)) cnt += 1;
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("clocktree.out")));
        pw.println(cnt);
        pw.close();
    }
}
