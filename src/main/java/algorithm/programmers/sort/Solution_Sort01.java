package algorithm.programmers.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * 프로그래머스 코딩테스트 연습을 위한 코드
 * 해당 사이트 와 테스트 편의상 기본 jre 외의 라이브러리는 사용하지 않는다.
 * 
 * Sort 그룹 1번째 문제
 * 
 * 배열 array의 i번째 숫자부터 j번째 숫자까지 자르고 정렬했을 때, k번째에 있는 수를 구하려 합니다.

 * 예를 들어 array가 [1, 5, 2, 6, 3, 7, 4], i = 2, j = 5, k = 3이라면

 * array의 2번째부터 5번째까지 자르면 [5, 2, 6, 3]입니다.
 * 1에서 나온 배열을 정렬하면 [2, 3, 5, 6]입니다.
 * 2에서 나온 배열의 3번째 숫자는 5입니다.
 * 배열 array, [i, j, k]를 원소로 가진 2차원 배열 commands가 매개변수로 주어질 때, commands의 모든 원소에 대해 앞서 설명한 연산을 적용했을 때 나온 결과를 배열에 담아 return 하도록 solution 함수를 작성해주세요.

 * 제한사항
 * array의 길이는 1 이상 100 이하입니다.
 * array의 각 원소는 1 이상 100 이하입니다.
 * commands의 길이는 1 이상 50 이하입니다.
 * commands의 각 원소는 길이가 3입니다.
 * </pre>
 * 
 * @author piyor
 */
public class Solution_Sort01 {

	public static void main(String[] args) {
		Solution_Sort01 solution = new Solution_Sort01();

		int[] array = {

				1, 5, 2, 6, 3, 7, 4

		};
		int[][] commands = {

				{ 2, 5, 3 }, { 4, 4, 1 }, { 1, 7, 3 }

		};

		int[] answer = solution.solution_my_best(array, commands);
		System.out.println(String.format("answer final %s", answer));
	}

	/**
	 * <pre>
	 * 
	 * 얕은 복사(Shallow Copy)
	 * 얕은 복사는 객체가 가진 멤버들의 값을 새로운 객체로 복사하는데 만약 객체가 참조타입의 멤버를 가지고 있다면 참조값만 복사가 됩니다.
	 * 깊은 복사 (Deep Copy)
	 * 깊은 복사는 전체 복사라고도 합니다.
	 * 얕은 복사와는 달리 객체가 가진 모든 멤버(값과 참조형식 모두)를 복사하는 것을 말합니다.
	 * 객체가 참조 타입의 멤버를 포함할 경우 참조값의 복사가 아닌 참조된 객체 자체가 복사되는 것을 깊은 복사라 합니다.
	 * ArrayList.clone 을 사용하고 얕은 복사와 깊은 복사를 정리해본다.
	 * </pre>
	 * 
	 * @param array
	 * @param commands
	 * @return
	 */
	public int[] solution_my_best(int[] array, int[][] commands) {
		int[] answer = {};
		// 최초한번 리스트로 변환하고 사용하려 했는데, 이거 순서가 왜 바뀌지...
		ArrayList<Integer> arrayList = (ArrayList<Integer>) Arrays.stream(array).boxed().collect(Collectors.toList());
		ArrayList<Integer> cloneList = null;
		int tmpLeng = commands.length;
		int sIdx = -1;
		int eIdx = -1;
		int finalIdx = -1;

		List<Integer> tmpList = null;
		answer = new int[tmpLeng];
		int arrIdx = 0;
		for (int i = 0; i < tmpLeng; i++) {
			sIdx = commands[i][0] - 1;
			eIdx = commands[i][1];
			finalIdx = commands[i][2] - 1;
			cloneList = (ArrayList<Integer>) arrayList.clone();
			tmpList = cloneList.subList(sIdx, eIdx);

			// sublist 구한 리스트를 정렬하니. 원본 리스트 순서가 바뀐다.
			tmpList.sort(new Comparator<Integer>() {
				@Override
				public int compare(Integer before, Integer after) {
					int comparision = (before - after) * -1;
					return comparision == 0 ? comparision : before.compareTo(after);
				}
			});
			// System.out.println(tmpList);
			answer[arrIdx++] = tmpList.get(finalIdx);
		}

		return answer;
	}

	/**
	 * <pre>
	 * sublist를 구하고, 정렬했을 때, 원본 리스트의 순서가 변경된거에 당황한거 빼고 무리없이 테스트 통과
	 * </pre>
	 * 
	 * @param array
	 * @param commands
	 * @return
	 */
	public int[] solution01(int[] array, int[][] commands) {
		int[] answer = {};
		// 최초한번 리스트로 변환하고 사용하려 했는데, 이거 순서가 왜 바뀌지...
		ArrayList<Integer> arrayList = (ArrayList<Integer>) Arrays.stream(array).boxed().collect(Collectors.toList());
		ArrayList<Integer> cloneList = null;
		System.out.println("origin array: " + arrayList);
		int tmpLeng = commands.length;
		int sIdx = -1;
		int eIdx = -1;
		int finalIdx = -1;

		List<Integer> tmpList = null;
		answer = new int[tmpLeng];
		int arrIdx = 0;
		for (int i = 0; i < tmpLeng; i++) {
			System.out.println("origin array: " + arrayList);
			sIdx = commands[i][0] - 1;
			eIdx = commands[i][1];
			finalIdx = commands[i][2] - 1;
			cloneList = (ArrayList<Integer>) arrayList.clone();
			tmpList = cloneList.subList(sIdx, eIdx);
			System.out.println("sIdx : " + sIdx + " eIdx : " + eIdx + " finalIdx : " + finalIdx);
			System.out.println("sub array: " + tmpList);

			// sublist 구한 리스트를 정렬하니. 원본 리스트 순서가 바뀐다.
			tmpList.sort(new Comparator<Integer>() {
				@Override
				public int compare(Integer before, Integer after) {
					int comparision = (before - after) * -1;
					return comparision == 0 ? comparision : before.compareTo(after);
				}
			});
			// System.out.println(tmpList);
			System.out.println(tmpList.get(finalIdx));
			answer[arrIdx++] = tmpList.get(finalIdx);
		}

		return answer;
	}

	/**
	 * <pre>
	 * 다른 사람의 간단한 소스
	 * - 최근에 계속 리터럴 객체만 사용하다 보니... 너무 앞서나갔다.
	 * </pre>
	 * 
	 * @param array
	 * @param commands
	 * @return
	 */
	public int[] solution_simple(int[] array, int[][] commands) {
		int[] answer = new int[commands.length];

		for (int i = 0; i < commands.length; i++) {
			int[] temp = Arrays.copyOfRange(array, commands[i][0] - 1, commands[i][1]);
			Arrays.sort(temp);
			answer[i] = temp[commands[i][2] - 1];
		}

		return answer;
	}

}
