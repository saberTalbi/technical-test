
public class Solution {

	int[][] memo;
	int[] A;

	public int solution(int[] A) {
		this.A = A;
		int aLength = A.length;
		memo = new int[aLength][200];
		for (int i = 0; i < memo.length; i++) {
			for (int j = 0; j < memo[i].length; j++) {
				memo[i][j] = -1;
			}
		}
		return subSum(0, 0);
	}

	private int subSum(int index, int sum) {
		if (index == A.length)
			return sum;

		if (sum > 200)
			return (int) 10e5;

		if (memo[index][sum] == -1) {
			int result1 = subSum(index + 1, Math.abs(sum + A[index]));
			int result2 = subSum(index + 1, Math.abs(sum - A[index]));
			memo[index][sum] = result1 < result2 ? result1 : result2;
		}
		return memo[index][sum];
	}
}
