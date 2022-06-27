import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

public class closingfarm {
    public static boolean bfs(HashMap<Integer, ArrayList<Integer>> graph,HashMap<Integer,Integer> invalids,int source, int N){
        boolean[] visited= new boolean[N];
        int ans = 1;
        visited[source-1] = true;
        Stack<Integer> stack = new Stack();
        stack.add(source);
        while (stack.size() != 0){
            int node = stack.pop();
            if (graph.containsKey(node)){
                for (int neighbor : graph.get(node)){
                    if (!visited[neighbor - 1] && !invalids.containsKey(neighbor)){
                        visited[neighbor-1] = true;
                        stack.add(neighbor);
                        ans += 1;
                    }
                }
            }
        }
        if (ans == N-invalids.size())
            return true;
        return false;
    }
    public static void main(String[] args) throws IOException {
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap();
        HashMap<Integer, Integer> invalids = new HashMap();
        BufferedReader br = new BufferedReader(new FileReader("closing.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int a = 0;
        int b = 0;
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if (!graph.containsKey(a)){
                ArrayList<Integer> starting = new ArrayList();
                starting.add(b);
                graph.put(a, starting);
            }
            else{
                graph.get(a).add(b);
            }
            if (!graph.containsKey(b)){
                ArrayList<Integer> starting = new ArrayList();
                starting.add(a);
                graph.put(b, starting);
            }
            else{
                graph.get(b).add(a);
            }
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("closing.out")));
        if (bfs(graph,invalids,1,N))
            pw.println("YES");
        else
            pw.println("NO");
        int source= 1;
        for (int i = 0; i < N-1; i++){
            st = new StringTokenizer(br.readLine());
            int remove = Integer.parseInt(st.nextToken());
            invalids.put(remove,0);
            if (remove== source)
                for (int j= 1; j < N+1;j++){
                    if (!invalids.containsKey(j)){
                        source = j;
                        break;
                    }
                }
            if (bfs(graph,invalids,source,N))
                pw.println("YES");
            else
                pw.println("NO");
        }
        pw.close();
    }
}
