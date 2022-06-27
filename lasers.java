import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class lasers {
    public static int bfs(HashMap<Integer, ArrayList<line>> horizontal, HashMap<Integer, ArrayList<line>> vertical, line source, line dest){
        HashMap<line,Integer> distances = new HashMap();
        HashMap<line,Boolean> visited = new HashMap();
        LinkedList<line> queue = new LinkedList();
        queue.add(source);
        distances.put(source,0);
        visited.put(source,true);
        while (!queue.isEmpty()){
            line node = queue.removeFirst();
            if (node.x == dest.x && node.y == dest.y)
                return distances.get(node) - 1;
            while (horizontal.get(node.y).size() != 0){
                line d = horizontal.get(node.y).get(0);
                if (!visited.containsKey(d)){
                    visited.put(d,true);
                    distances.put(d,distances.get(node)+1);
                    queue.add(d);
                }
                horizontal.get(node.y).remove(0);
            }
            while (vertical.get(node.x).size() != 0){
                line d = vertical.get(node.x).get(0);
                if (!visited.containsKey(d)){
                    visited.put(d,true);
                    distances.put(d,distances.get(node)+1);
                    queue.add(d);
                }
                vertical.get(node.x).remove(0);
            }
        }
        return -1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lasers.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashMap<Integer,ArrayList<line>> vertical = new HashMap();
        HashMap<Integer,ArrayList<line>> horizontal = new HashMap();
        int N = Integer.parseInt(st.nextToken());
        int sx = Integer.parseInt(st.nextToken());
        int sy = Integer.parseInt(st.nextToken());
        int dx = Integer.parseInt(st.nextToken());
        int dy = Integer.parseInt(st.nextToken());
        line s= new line(sx,sy);
        horizontal.put(sy,new ArrayList());
        horizontal.get(sy).add(s);
        vertical.put(sx,new ArrayList());
        vertical.get(sx).add(s);
        for (int i= 0 ;i  <N;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (!horizontal.containsKey(y)) horizontal.put(y,new ArrayList());
            line add = new line(x,y);
            horizontal.get(y).add(add);
            if (!vertical.containsKey(x)) vertical.put(x,new ArrayList());
            vertical.get(x).add(add);
        }
        if (!horizontal.containsKey(dy)) horizontal.put(dy,new ArrayList());
        line f = new line(dx,dy);
        horizontal.get(dy).add(f);
        if (!vertical.containsKey(dx)) vertical.put(dx,new ArrayList());
        vertical.get(dx).add(f);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lasers.out")));
        int ans = bfs(horizontal,vertical,new line(sx,sy), new line(dx,dy));
        pw.println(ans);
        pw.close();
    }
}
class line{
    public int x,y;
    public line(int first, int end){
        x = first;
        y = end;
    }
}
