import java.io.*;
import java.util.*;
public class bcount {
    public static ArrayList get_res(ArrayList<Integer> array1, ArrayList<Integer> array2){
        ArrayList res = new ArrayList();
        for (int i = 0; i < 3; i += 1)
            res.add(array2.get(i)-array1.get(i));
        return res;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("bcount.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q  = Integer.parseInt(st.nextToken());
        ArrayList<Integer> cows = new ArrayList();
        while (N > 0){
            N -= 1;
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            cows.add(num);
        }
        ArrayList<ArrayList> presums = new ArrayList();
        ArrayList<Integer> presum = new ArrayList();
        ArrayList<Integer> first = new ArrayList();
        first.add(0);
        first.add(0);
        first.add(0);
        presums.add(first);
        presum.add(0);
        presum.add(0);
        presum.add(0);
        for (int x: cows){
            ArrayList<Integer> copy = new ArrayList();
            presum.set(x - 1, presum.get(x-1)+1);
            copy.addAll(presum);
            presums.add(copy);
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("bcount.out")));
        int cnt = 0;
        int g = Q;
        while (Q > 0){
            Q -= 1;
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ArrayList<Integer> answer = get_res(presums.get(a-1),presums.get(b));
            for (int i = 0; i < 3; i += 1){
                if (i == 2)
                    pw.print(answer.get(i));
                else
                    pw.print(answer.get(i) + " ");
            }
            cnt += 1;
            if (cnt < g)
                pw.println();
        }
        pw.close();
    }
}
