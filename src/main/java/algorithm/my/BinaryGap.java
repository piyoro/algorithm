package algorithm.my;

public class BinaryGap {

	/**
	 * @author : piyoro
	 * 
	 * <pre>
	 * 입력한 숫자를 이진수로 변환하여
	 * 동일한 숫자가 가장 많이 반복한 횟수 출력
	 * </pre>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryGap binaryGap = new BinaryGap();

		System.out.println(binaryGap.solution99(18));
	}

	public int solution99(int N) {
		int result = 0;
		String s = Integer.toBinaryString(N);
		System.out.println(s);
		int iStart = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '1') {
				int length = i - iStart - 1;
				if (length > result)
					result = length;
				iStart = i;
			}
		}
		return result;
	}
}
