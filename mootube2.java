import java.io.*;
import java.util.*;
class Edge{
    public int dest;
    public int weight;
    public Edge(int node, int w){
        dest = node;
        weight = w;
    }
}
public class mootube2 {
    public static int bfs(int video, int minrel,int n){
        int res = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(video);
        boolean[] visited = new boolean[n];
        visited[video-1] = true;
        while (queue.size() != 0){
            int node = queue.poll();
            for(int i= 0;i<graph[node-1].size();i++){
                Edge e = graph[node-1].get(i);
                if (!visited[e.dest-1] && e.weight >= minrel){
                    queue.offer(e.dest);
                    visited[e.dest-1] = true;
                    res += 1;
                }
            }
        }
        return res;
    }
    public static ArrayList<Edge>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("mootube.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n];
        for (int i = 0; i < graph.length;i++) graph[i] = new ArrayList<Edge>();
        for (int i= 0 ; i < graph.length-1;i++){
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            graph[p-1].add(new Edge(q,r));
            graph[q-1].add(new Edge(p,r));
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
        for (int i= 0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int minimumrelevance = Integer.parseInt(st.nextToken());
            int video = Integer.parseInt(st.nextToken());
            pw.println(bfs(video,minimumrelevance, n));
        }
        pw.close();
    }
}
