import java.io.*;
import java.util.*;
public class cowsatlarge {
    public static boolean bfs2(int start, int[] dist,HashMap<Integer, ArrayList<Integer>> graph,int N,HashMap<Integer,Boolean> occupied,int[]dist2){
        LinkedList<Integer> queue = new LinkedList();
        queue.add(start);
        boolean[] visited = new boolean[N];
        visited[start-1] = true;
        dist[start-1] = 0;
        occupied.put(start,true);
        while (!queue.isEmpty()) {
            int node = queue.removeFirst();
            for (int i= 0 ; i < graph.get(node).size();i++){
                if (occupied.containsKey(graph.get(node).get(i)) && graph.get(node).get(i) != start && !visited[graph.get(node).get(i)-1]) if (occupied.get(graph.get(node).get(i))) return false;
                if (!visited[graph.get(node).get(i)-1] && dist[graph.get(node).get(i)-1] > dist[node-1] + 1){
                    dist[graph.get(node).get(i)-1] = dist[node-1] + 1;
                    if (dist[graph.get(node).get(i)-1] <= dist2[graph.get(node).get(i)-1]) occupied.put(graph.get(node).get(i),true);
                    else occupied.put(graph.get(node).get(i),false);
                    queue.add(graph.get(node).get(i));
                    visited[graph.get(node).get(i)-1] = true;
                }
                else if (!visited[graph.get(node).get(i)-1] && dist[graph.get(node).get(i)-1] == dist[node-1] + 1){
                    if (dist[graph.get(node).get(i)-1] <= dist2[graph.get(node).get(i)-1]) occupied.put(graph.get(node).get(i),true);
                    else occupied.put(graph.get(node).get(i),false);
                    break;
                }
            }
        }
        return true;
    }
    public static boolean valid(int start, int[] dist2, HashMap<Integer,ArrayList<Integer>> graph,int N,int[]dist,HashMap<Integer,Boolean> occupied){
        LinkedList<Integer> queue = new LinkedList();
        queue.add(start);
        boolean[] visited = new boolean[N];
        visited[start-1] = true;
        while (!queue.isEmpty()) {
            int node = queue.removeFirst();
            if (graph.get(node).size() == 1) return true;
            for (int i= 0 ; i < graph.get(node).size();i++){
                if (!visited[graph.get(node).get(i)-1] && !occupied.containsKey(graph.get(node).get(i))){
                    queue.add(graph.get(node).get(i));
                    visited[graph.get(node).get(i)-1] = true;
                }
            }
        }
        return false;
    }
    public static void bfs(ArrayList<leaf> leafs, int[] dist, int start,HashMap<Integer, ArrayList<Integer>> graph){
        LinkedList<Integer> queue = new LinkedList();
        queue.add(start);
        while (!queue.isEmpty()){
            int node = queue.removeFirst();
            if (graph.get(node).size() == 1){
                leafs.add(new leaf(node,dist[node-1]));
                if (dist[graph.get(node).get(0)-1] == 0 && graph.get(node).get(0) != start){
                    queue.add(graph.get(node).get(0));
                    dist[graph.get(node).get(0)-1] = dist[node-1] + 1;
                }
            }
            else{
                for (int i= 0 ; i < graph.get(node).size();i++){
                    if (dist[graph.get(node).get(i)-1] == 0 && graph.get(node).get(i) != start){
                        dist[graph.get(node).get(i)-1] = dist[node-1] + 1;
                        queue.add(graph.get(node).get(i));
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("atlarge.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap();
        for (int i= 0; i <N;i++) graph.put(i+1,new ArrayList());
        for (int i= 0; i < N-1;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph.get(s).add(e);
            graph.get(e).add(s);
        }
        HashMap<Integer,Boolean> occupied = new HashMap();
        ArrayList<leaf> leafs = new ArrayList();
        int[] dist = new int[N];
        bfs(leafs,dist,start,graph);
        leaf[] leaves = new leaf[leafs.size()];
        for (int i =0 ; i < leafs.size();i++) leaves[i] = leafs.get(i);
        int[] dist2 = dist.clone();
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("atlarge.out")));
        int cnt = 0;
        for (int i= 0 ;i < leaves.length;i++){
            if (bfs2(leaves[i].node,dist,graph,N,occupied,dist2)) cnt += 1;
            if (!valid(start,dist2,graph,N,dist,occupied)){
                pw.println(cnt);
                break;
            }
        }
        pw.close();
    }
}
class leaf implements Comparable<leaf>{
    public int node,dist;
    public leaf(int n, int d){
        node = n;
        dist = d;
    }
    public int compareTo(leaf other){
        return this.dist-other.dist;
    }
}
