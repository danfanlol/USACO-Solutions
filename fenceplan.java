import java.io.*;
import java.util.*;

public class fenceplan {
    public static void main(String[] args) throws IOException {
        ArrayList<ArrayList<Integer>> cows = new ArrayList();
        BufferedReader br = new BufferedReader(new FileReader("fenceplan.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashMap<ArrayList<Integer>,ArrayList<ArrayList<Integer>>> graph = new HashMap();
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        for (int i =0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            ArrayList<Integer> location = new ArrayList();
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            location.add(x);
            location.add(y);
            cows.add(location);
        }
        for (int i =0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            if (graph.containsKey(cows.get(x))){
                graph.get(cows.get(x)).add(cows.get(y));
            }
            else{
                ArrayList<ArrayList<Integer>> connections = new ArrayList();
                connections.add(cows.get(y));
                graph.put(cows.get(x),connections);
            }
            if (graph.containsKey(cows.get(y))){
                graph.get(cows.get(y)).add(cows.get(x));
            }
            else{
                ArrayList<ArrayList<Integer>> connections = new ArrayList();
                connections.add(cows.get(x));
                graph.put(cows.get(y),connections);
            }
        }
        HashMap<ArrayList<Integer>, Boolean> visited = new HashMap();
        int ans = 1000000000;
        for (int i = 0; i < n; i++){
            if (!visited.containsKey(cows.get(i))){
                int res = dfs(graph,cows.get(i),visited);
                if (res < ans)
                    ans = res;
            }
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fenceplan.out")));
        pw.println(ans);
        pw.close();
    }
    public static int dfs( HashMap<ArrayList<Integer>,ArrayList<ArrayList<Integer>>> graph, ArrayList<Integer> node, HashMap<ArrayList<Integer>, Boolean> visited){
        int r=node.get(0);int l=node.get(0);int t = node.get(1); int b = node.get(1);
        Stack<ArrayList<Integer>> stack = new Stack();
        stack.add(node);
        visited.put(node,true);
        while (stack.size() != 0){
            ArrayList<Integer> location = stack.pop();
            r = Math.min(location.get(0),r);
            l = Math.max(location.get(0),l);
            b = Math.min(location.get(1),b);
            t = Math.max(location.get(1),t);
            for (int i =0 ; i < graph.get(location).size(); i++){
                if (!visited.containsKey(graph.get(location).get(i))){
                    visited.put(graph.get(location).get(i),true);
                    stack.add(graph.get(location).get(i));
                }
            }
        }
        return Math.abs(t-b)*2 + Math.abs(l-r)*2;
    }
}
