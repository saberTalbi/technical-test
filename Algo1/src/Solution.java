
public class Solution {

	public int[] solution(int N, int[] A) {
		int length = A.length;
		int[] result = new int[N];
		int max = 0;
		for (int element : A) {
			if (element == N + 1) {
				for (int i = 0; i < N; i++) {
					result[i] = max;
				}
			} else {
				int index = element - 1;
				result[index]++;
				if (result[index] > max)
					max = result[index];
			}
		}
		return result;
	}
}
