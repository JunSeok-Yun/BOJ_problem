// BOJ 12789
// 19820KB 224ms
import java.util.*;

public class Main
{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
		int idx = 1, num = 0;
        String res = "Nice";
        Stack<Integer> stack = new Stack<>();
        int[] arr = new int[N];

        for (int i = 0; i < N; i++)
            arr[i] = sc.nextInt();

        for(int i = 0; i < N; i++)
        {
            num = arr[i];
            if (idx != num) {
                if (!stack.isEmpty() && stack.peek() == idx) {
                    idx++;
                    i--;
                    stack.pop();
                } else
                    stack.push(num);
            } else
                idx++;
        }

		while (!(stack.isEmpty())){
			if (stack.pop() == idx)
				idx++;
			else{
                res = "Sad";
				break;
            }
		}
        System.out.println(res);
        sc.close();
    }
}