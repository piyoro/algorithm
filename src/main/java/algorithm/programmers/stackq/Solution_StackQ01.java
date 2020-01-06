package algorithm.programmers.stackq;

import java.util.Stack;

/**
 * <pre>
 * 수평 직선에 탑 N대를 세웠습니다. 모든 탑의 꼭대기에는 신호를 송/수신하는 장치를 설치했습니다. 발사한 신호는 신호를 보낸 탑보다 높은 탑에서만 수신합니다.
 * 또한, 한 번 수신된 신호는 다른 탑으로 송신되지 않습니다.

 * 예를 들어 높이가 6, 9, 5, 7, 4인 다섯 탑이 왼쪽으로 동시에 레이저 신호를 발사합니다. 그러면, 탑은 다음과 같이 신호를 주고받습니다. 
 * 높이가 4인 다섯 번째 탑에서 발사한 신호는 높이가 7인 네 번째 탑이 수신하고, 높이가 7인 네 번째 탑의 신호는 높이가 9인 두 번째 탑이, 
 * 높이가 5인 세 번째 탑의 신호도 높이가 9인 두 번째 탑이 수신합니다. 
 * 높이가 9인 두 번째 탑과 높이가 6인 첫 번째 탑이 보낸 레이저 신호는 어떤 탑에서도 수신할 수 없습니다.

 * 송신 탑(높이)	수신 탑(높이)
 * 5(4)	4(7)
 * 4(7)	2(9)
 * 3(5)	2(9)
 * 2(9)	-
 * 1(6)	-
 * 맨 왼쪽부터 순서대로 탑의 높이를 담은 배열 heights가 매개변수로 주어질 때 각 탑이 쏜 신호를 어느 탑에서 받았는지 기록한 배열을 return 하도록 solution 함수를 작성해주세요.

 * 제한 사항
 * heights는 길이 2 이상 100 이하인 정수 배열입니다.
 * 모든 탑의 높이는 1 이상 100 이하입니다.
 * 신호를 수신하는 탑이 없으면 0으로 표시합니다.
 * </pre>
 * 
 * @author piyor
 */
public class Solution_StackQ01 {

	public static void main(String[] args) {
		Solution_StackQ01 solution = new Solution_StackQ01();

		int[] array = {

				// 6, 9, 5, 7, 4
				// 3, 9, 9, 3, 5, 7, 2
				// 1, 5, 3, 6, 7, 100, 6, 5
				5, 3, 6, 7, 100, 6, 5
				// 0, 0, 0, 3, 0, 0, 6, 7
				// 1, 1, 2, 2, 1

		};
		int[] answer = solution.solution_my_best(array);
		for (int item : answer) {
			System.out.print(String.format("%s, ", item));
		}
		// String[] result = Arrays.stream(answer).boxed().toArray(String[]::new);
		System.out.println();
	}

	/**
	 * @author : piyor
	 * 
	 * <pre>
	 * </pre>
	 * 
	 * @param heights
	 * @return
	 */
	public int[] solution_my_best(int[] heights) {
		int[] answer = {};

		Stack<Integer> st = new Stack<>();
		for (int item : heights) {
			st.push(item);
		}

		int leng = st.size();

		answer = new int[leng];

		int val = 0;
		int compVal = 0;
		int restoreIdx = -1;
		for (int idx01 = leng - 1; idx01 > 0; idx01--) {
			val = st.pop();
			restoreIdx = -1;
			for (int idx02 = (idx01 - 1); idx02 > -1; idx02--) {
				compVal = st.pop();
				if (compVal > val) {
					answer[idx01] = idx02 + 1;
					restoreIdx = idx02;
					break;
				}
				answer[idx01] = 0;
				restoreIdx = idx02;
			}

			for (int idx03 = answer[restoreIdx]; idx03 < idx01; idx03++) {
				st.push(heights[idx03]);
			}
		}

		return answer;
	}

	/**
	 * @author : piyor
	 * 
	 * <pre>
	 * </pre>
	 * 
	 * @param citations
	 * @return
	 */
	public int[] solution01(int[] heights) {
		int[] answer = {};

		Stack<Integer> st = new Stack<>();
		for (int item : heights) {
			st.push(item);
		}

		int leng = st.size();

		answer = new int[leng];

		int val = 0;
		int compVal = 0;
		int restoreIdx = -1;
		for (int idx01 = leng - 1; idx01 > 0; idx01--) {
			val = st.pop();
			restoreIdx = -1;
			System.out.println(String.format("idx01 : [%s] val : [%s]", idx01, val));
			for (int idx02 = (idx01 - 1); idx02 > -1; idx02--) {
				compVal = st.pop();
				System.out.println(String.format("idx02 : [%s] compVal : [%s]", idx02, compVal));
				if (compVal > val) {
					System.out.println(String.format("gt idx02 : [%s] compVal : [%s]", idx02, compVal));
					answer[idx01] = idx02 + 1;
					restoreIdx = idx02;
					System.out.println(String.format("restoreIdx1 : [%s]", restoreIdx));
					break;
				}
				answer[idx01] = 0;
				restoreIdx = idx02;
				System.out.println(String.format("restoreIdx2 : [%s]", restoreIdx));
			}

			System.out.println(String.format("restoreIdx3 : [%s] idx01 : [%s]", restoreIdx, idx01));
			// if (restoreIdx > -1) {
			for (int idx03 = answer[restoreIdx]; idx03 < idx01; idx03++) {
				System.out.println(String.format("heights[idx03] : [%s]", heights[idx03]));
				st.push(heights[idx03]);
			}
			// }
		}
		// System.out.println(st.size());

		return answer;
	}

	/**
	 * @author : piyor
	 * 
	 * <pre>
	 * </pre>
	 * 
	 * @param numbers
	 * @return
	 */
	public int[] solution_simple(int[] heights) {
		int[] answer = new int[heights.length];

		for (int i = heights.length - 1; i >= 0; i--) {
			for (int j = i - 1; j >= 0; j--) {
				if (heights[j] > heights[i]) {
					answer[i] = j + 1;
					break;
				}
			}
		}

		return answer;
	}

}
