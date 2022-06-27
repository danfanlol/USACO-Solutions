import java.io.*;
import java.util.*;
public class mootube {
    static class Edge {
        public int d, w;
        public Edge(int a, int b) {
            d=a;
            w=b;
        }
    }
    public static int dfs(LinkedList<Edge>[] graph, int k, int node, int n){
        int ans = 0;
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(node);
        boolean[] visited = new boolean[n+1];
        visited[node] = true;
        while (!queue.isEmpty()){
            ans += 1;
            int cur = queue.removeFirst();
            for (int i= 0;i < graph[cur].size();i++){
                if (!visited[graph[cur].get(i).d] && graph[cur].get(i).w >= k){
                    visited[graph[cur].get(i).d] = true;
                    queue.add(graph[cur].get(i).d);
                }
            }
        }
        return ans-1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("mootube.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        LinkedList<Edge>[] graph = new LinkedList[n+1];
        for (int i = 1;i <n+1;i++){
            graph[i] = new LinkedList<Edge>();
        }
        for (int i =0;i<n-1;i++){
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            graph[q].add(new Edge(p,r));
            graph[p].add(new Edge(q,r));
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
        for (int i = 0; i < m;i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int node = Integer.parseInt(st.nextToken());
            pw.println(dfs(graph,k,node,n));
        }
        pw.close();
    }
}
