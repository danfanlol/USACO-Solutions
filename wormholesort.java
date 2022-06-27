import java.io.*;
import java.util.*;

class edge{
    public int dest, weight;
    public edge(int d, int w){
        dest = d;
        weight = w;
    }
}
public class wormholesort {
    public static boolean dfs(int minweight, int[] curpos, ArrayList<edge>[] graph, int N){
        int[] visited = new int[N];
        ArrayList<Integer> a = new ArrayList();
        ArrayList<Integer> b = new ArrayList();
        a.add(1);
        b.add(curpos[0]);
        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[0] = 1;
        while (queue.size() != 0){
            int node = queue.poll();
            for (int i = 0; i < graph[node-1].size();i++){
                edge e = graph[node-1].get(i);
                if (visited[e.dest-1] == 0){
                    if (e.weight >= minweight){
                        a.add(e.dest);
                        b.add(curpos[e.dest-1]);
                        queue.offer(e.dest);
                        visited[e.dest-1] = 1;
                    }
                }
            }
        }
        for (int i= 0 ; i < N;i++){
            if (visited[i] == 0){
                if (curpos[i] != i+1)
                    return false;
                }
            }
        Collections.sort(a);
        Collections.sort(b);
        return a.equals(b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("wormsort.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] curpos = new int[N];
        int[] weights = new int[M];
        for (int i = 0;i<N;i++){
            int pos = Integer.parseInt(st.nextToken());
            curpos[pos-1] = i+1;
        }
        ArrayList<edge>[] connections = new ArrayList[N];
        for (int i = 0; i < N;i++){
            connections[i] = new ArrayList<edge>();
        }
        for (int i= 0 ; i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
            connections[a-1].add(new edge(b,c));
            connections[b-1].add(new edge(a,c));
            weights[i] = c;
        }
        boolean sorted = true;
        for (int i = 0 ; i < N-1;i++){
            if (curpos[i] > curpos[i+1]){
                sorted = false;
                break;
            }
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("wormsort.out")));
        Arrays.sort(weights);
        //binary search time!
        if (sorted) pw.println(-1);
        else{
            int high = M-1;
            int low = 0;
            int mid;
            while (high >= low){
                mid = (high + low) / 2;
                if (dfs((weights[mid]),curpos,connections,N)) low = mid + 1;
                else high = mid -1;
            }
            pw.println(weights[low-1]);
        }
        pw.close();
    }
}
