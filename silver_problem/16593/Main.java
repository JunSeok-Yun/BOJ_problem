// BOJ 250208
// 38016KB 276ms
import java.util.*;

public class Main {
	static int N, res;
	static int minVal = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		res = sc.nextInt();

		dfs(N + "", 1);
		if (minVal == Integer.MAX_VALUE){
			System.out.println(-1);
			sc.close();
			return ;
		}
		System.out.println(minVal);
		sc.close();
	}

	public static void dfs(String numStr, int idx){
		Long num = Long.parseLong(numStr);
		if (num >= res){
			if (num > res)
				return ;
			minVal = Math.min(minVal, idx);
			return ;
		}

		dfs(num + "1", idx + 1);
		dfs((num * 2) + "", idx + 1);
	}
}