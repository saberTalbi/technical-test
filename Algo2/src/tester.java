

public class tester {

	public static void main(String[] args) {
		int [] A = {4,4,5,5,1};
		int [] B = {3,2,4,3,1};
		Solution test = new Solution();
		int [] res = test.solution( A , B);
		for (int element : res) {
			System.out.print(element + ", ");
		}
	}
}
