import java.io.*;
import java.util.*;

public class milkvisits {
    public static void dfs(HashMap<Integer, ArrayList<Integer>> tree, int start, int[] visited,int comp, String cows){
        Stack<Integer> stack = new Stack();
        stack.add(start);
        visited[start-1] = comp;
        while (stack.size() != 0){
            int node = stack.pop();
            for (int x : tree.get(node)){
                if (visited[x-1] == 0 && cows.charAt(x-1) == cows.charAt(start-1)){
                    visited[x-1] = comp;
                    stack.add(x);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("milkvisits.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        String cows = st.nextToken();
        HashMap<Integer, ArrayList<Integer>> tree = new HashMap();
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (!tree.containsKey(a)) {
                ArrayList<Integer> array = new ArrayList();
                array.add(b);
                tree.put(a, array);
            } else {
                tree.get(a).add(b);
            }
            if (!tree.containsKey(b)) {
                ArrayList<Integer> array = new ArrayList();
                array.add(a);
                tree.put(b, array);
            } else {
                tree.get(b).add(a);
            }
        }
        int[] visited = new int[N];
        int component = 0;
        for (int i = 0;  i< N;i++){
            if (visited[i] == 0){
                component += 1;
                dfs(tree,i+1,visited,component, cows);
            }
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            String letter = st.nextToken();
            if (visited[start - 1] != visited[end - 1]){
                ans.append("1");
            }
            else if(Character.toString(cows.charAt(start - 1)).equals(letter)){
                ans.append("1");
            }
            else{
                ans.append("0");
            }
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milkvisits.out")));
        pw.println(ans);
        pw.close();
    }
}
