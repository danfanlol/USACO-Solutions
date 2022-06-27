import java.io.*;
import java.util.*;
public class highcardwins {
    public static int binsearch(ArrayList<Integer> cows, int number){
        int high = cows.size()-1;
        int low = 0;
        int mid;
        while (high >= low){
            mid = (high + low) / 2;
            if (cows.get(mid) > number){
                high = mid - 1;
            }
            else if (cows.get(mid) == number)
                return mid;
            else{
                low = mid + 1;
            }
        }
        return high;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("highcard.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        ArrayList<Integer> bcards = new ArrayList();
        ArrayList<Integer> ecards = new ArrayList();
        HashMap<Integer, Boolean> cows = new HashMap();
        for (int i = 0; i < N;i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            ecards.add(num);
            cows.put(num,true);
        }
        for (int i =0 ; i< N*2; i++){
            if (!cows.containsKey(i+1))
                bcards.add(i+1);
        }
        Collections.sort(ecards);
        Collections.sort(bcards); //find highest value in bessies card deck
        int high = bcards.size() -1 ;
        int index = binsearch(ecards,bcards.get(high));
        int cnt = 0;
        while (index >= 0){
            if (ecards.get(index) < bcards.get(high)){
                high -= 1;
                cnt += 1;
            }

            index -= 1;
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("highcard.out")));
        pw.println(cnt);
        pw.close();
    }

}
