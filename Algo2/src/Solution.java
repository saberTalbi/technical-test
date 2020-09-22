
public class Solution {

	public int[] solution(int[] A, int[] B) {
		final int L = A.length;
		int[] result = new int[L];

		for (int i = 0; i < L; i++) {
			
			if(A[i] < 3) {
				result[i] = A[i];
				continue;
			}
			int x = 1;
			int y = 2;
			for (int j = 3; j <= A[i]; j++) {
				result[i] = (int) ((y + x) % (Math.pow(2, B[i])));
				x = y;
				y=result[i];
			}
		}

		return result;
	}
}
