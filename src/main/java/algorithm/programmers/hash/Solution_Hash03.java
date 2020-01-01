package algorithm.programmers.hash;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * <pre>
 * 프로그래머스 코딩테스트 연습을 위한 코드
 * 해당 사이트 와 테스트 편의상 기본 jre 외의 라이브러리는 사용하지 않는다.
 * 
 * Hash 그룹 3번째 문제
 * 
 * 예를 들어 스파이가 가진 옷이 아래와 같고 오늘 스파이가 동그란 안경, 긴 코트, 파란색 티셔츠를 입었다면 
 * 다음날은 청바지를 추가로 입거나 동그란 안경 대신 검정 선글라스를 착용하거나 해야 합니다.

 * 종류	이름
 * 얼굴	동그란 안경, 검정 선글라스
 * 상의	파란색 티셔츠
 * 하의	청바지
 * 겉옷	긴 코트
 * 
 * 스파이가 가진 의상들이 담긴 2차원 배열 clothes가 주어질 때 서로 다른 옷의 조합의 수를 return 하도록 solution 함수를 작성해주세요.
 * 
 * 제한사항
 * clothes의 각 행은 [의상의 이름, 의상의 종류]로 이루어져 있습니다.
 * 스파이가 가진 의상의 수는 1개 이상 30개 이하입니다.
 * 같은 이름을 가진 의상은 존재하지 않습니다.
 * clothes의 모든 원소는 문자열로 이루어져 있습니다.
 * 모든 문자열의 길이는 1 이상 20 이하인 자연수이고 알파벳 소문자 또는 '_' 로만 이루어져 있습니다.
 * 스파이는 하루에 최소 한 개의 의상은 입습니다.
 * </pre>
 * 
 * @author piyor
 */
public class Solution_Hash03 {

	public static void main(String[] args) {
		Solution_Hash03 solution = new Solution_Hash03();
		// int[][] array6 = { { 1 }, { 2, 3 }, { 4, 5, 6 } };
		//
		// System.out.println("->" + array6[1].length);

		String[][] clothes = new String[][] {

				// { "1", "1" }
				//
				// , { "2", "1" }
				//
				// , { "1", "2" }
				//
				// , { "2", "2" }

				// ,

				// { "yellow_hat", "headgear" }, { "blue_sunglasses", "eyewear" }, { "green_turban", "headgear" }

				{ "1_1", "1" }, { "1_2", "1" }, { "1_3", "1" }
				//
				, { "2_1", "2" }, { "2_2", "2" }, { "2_3", "2" }
				//
				, { "3_1", "3" }, { "3_2", "3" }, { "3_3", "3" }
				//
				, { "4_1", "4" }, { "4_2", "4" }, { "4_3", "4" }
				//
				, { "5_1", "3" }, { "5_2", "5" }, { "5_3", "5" }

				, { "6_1", "3" }, { "6_2", "6" }, { "6_3", "6" }
				//
				, { "7_1", "3" }, { "7_2", "7" }, { "7_3", "7" }

				// { "1_1", "1" }, { "1_2", "1" }, { "2_1", "2" }, { "2_2", "2" }

				// { "crow_mask", "face" }, { "blue_sunglasses", "face" }, { "smoky_makeup", "face" }

				// , { "blue_sunglasses", "face" }
				//
				// , { "smoky_makeup", "face" }

		};
		// System.out.println(solution.solution02(clothes));

		int answer = solution.solution_my_best(clothes);
		System.out.println(String.format("answer final %s", answer));
	}

	/**
	 * <pre>
	 * 결국엔 다른 사람의 해결법을 보고 원인은 찾았다.
	 * 해결법은 공식... 부위별 의상별 나올수있는 모든 경우의수 를 구하고
	 * 옷을 안입을 때의 경우를 뺀다.
	 * 
	 * 내가 처리한 로직은
	 * 각 요소별로 끝 요소까지 경우의 수를 구하는 것으로 제한 되었고,
	 * 부분적인 경우의 수는 구해지지 않았다.
	 * 3부위 3종류 는... 부분적인 요소가 없으니 답이 구해졌고, 4부위를 처리해보려 생각만 했었으면 
	 * 좀 더 구체적으로 생각했을 텐데... 
	 * 
	 * 긍정평가
	 * :hash 라는 힌트때문에 부위별 의상수를 구해서 뭔가는 해보려 했다.
	 * :많이 산으로 갔지만... 어떻게든 해보려했고, 머리속의 생각은 맞기는 맞았다.
	 * 
	 * 부정평가
	 * :문제자체가 난해했기에 문제를 이해하면 뭔가 수학적인 공식이 적용될거라고 생각은 했지만...
	 *  -생각하기 싫었고, 직접해보고 싶어서 결국 산으로 갔다.
	 * :화자의 의도를 먼저 파악하려 했다면 테스트케이스가 부족했다는 생각은 안했을 것이다.
	 * :알고리즘을 직접구현해야한다는 강박관념이 있는건가...
	 * :혼자서 공부하는 거라는 안일한 생각에 생각을 짧게 한다.
	 * :몇일전에 봤던 수학책의 내용이 생각나지 않는다 ㅠ
	 * </pre>
	 * 
	 * @param clothes
	 * @return
	 */
	public int solution_my_best(String[][] clothes) {
		int answer = 1;
		Map<String, Integer> cntMap = new HashMap<>();
		String key = null;
		int cnt = clothes.length;
		for (int idx01 = 0; idx01 < cnt; idx01++) {
			key = clothes[idx01][1];
			cntMap.put(key, cntMap.getOrDefault(key, 0) + 1);
			// System.out.println(clothes[idx01][1] + " " + clothes[idx01][0]);
		}

		Object[] kindArray = cntMap.values().toArray();
		int kindCnt = kindArray.length;
		Integer[] kindArr = new Integer[kindCnt];
		System.arraycopy(kindArray, 0, kindArr, 0, kindCnt);

		System.out.println("종류:" + kindArray.length);

		for (int item : kindArr) {
			answer *= item + 1;
		}

		return answer - 1;
	}

	/**
	 * hash 를 이용하여 부위별 의상갯수를 모두 구하고, 각 부위요수 마다 다음 부위의 경우의 수에 대한 계산을 재귀함수 호출을 이용해서 계산하면 간단하게 구해질거라 생각했다. 주어진 테스트케이스가 상당히 빈약해서.. 경우의 수를 손으로 계산하고 적당히 3가지
	 * 부위 3가지 의상으로 계산했을 때 답은 맞았다. 테스트케이스 제공이 너무 부족하다고 혼자 속앓이만 했다.
	 * 
	 * @param clothes
	 * @return
	 */
	public int solution02(String[][] clothes) {

		int answer = 0;
		Map<String, Integer> cntMap = new HashMap<>();
		String key = null;
		int cnt = clothes.length;
		for (int idx01 = 0; idx01 < cnt; idx01++) {
			key = clothes[idx01][1];
			cntMap.put(key, cntMap.getOrDefault(key, 0) + 1);
		}

		Object[] kindArray = cntMap.values().toArray();
		int kindCnt = kindArray.length;
		Integer[] kindArr = new Integer[kindCnt];
		System.arraycopy(kindArray, 0, kindArr, 0, kindCnt);

		for (int i : kindArr) {
			System.out.println("인티저 배열 :" + i);
		}
		System.out.println("종류:" + kindArr.length);
		if (kindCnt == 1) {
			answer = 0;
		} else {
			int curVal = 0;
			for (int idx01 = 0; idx01 < kindCnt - 1; idx01++) {
				System.out.println(String.format("1. %s %s %s가지의상", kindArr[idx01], idx01, kindCnt));
				curVal = kindArr[idx01];
				for (int idx02 = idx01 + 1; idx02 < kindCnt; idx02++) {

					answer = answer + fnRecursiveCalc(curVal, idx02, kindArr);
					System.out.println("1. recursive" + answer);
					if (idx02 < (kindCnt - 1)) {
						System.out.println("2. recursive-------->" + (curVal * kindArr[idx02]));
						answer = answer + (curVal * kindArr[idx02]);
						System.out.println("2. recursive" + answer);
					}
				}
				// System.out.println(String.format("1. %s %s %s final", kindArray[idx01], idx01, answer));
			}
		}

		// 최종 의상종류의 갯수를 누적
		System.out.println(String.format("answer %s %s", answer, cnt));
		answer = answer + cnt;
		System.out.println(String.format("answer final %s", answer));

		return answer;
	}

	/**
	 * 파라미터로 입력받은 배열 인덱스의 값부터 배열 끝의 값까지 곱한수를 반환 재귀호출
	 * 
	 * @param calcNum
	 * @param idx
	 * @param kindArr
	 * @return
	 */
	public static int fnRecursiveCalc(int calcNum, int idx, Integer[] kindArr) {

		int curVal = kindArr[idx];
		int endIdx = kindArr.length;
		int nextIdx = ++idx;
		if (nextIdx == endIdx) {
			System.out.println(nextIdx + "return :" + calcNum * curVal);
			return calcNum * curVal;
		}
		return calcNum * fnRecursiveCalc(curVal, nextIdx, kindArr);
	}

	/**
	 * <pre>
	 * 문제의도를 모르겠다.
	 * 28점 나왔는데, 태반이 틀렸다.
	 * 같은이름을 가진 의상은 존재하지 않기 때문에 맞는 로직인거 같은데. 모르겠다.
	 * </pre>
	 * 
	 * @param clothes
	 * @return
	 */
	public int solution01(String[][] clothes) {
		int answer = 0;

		Map<String, String> clothesMap = new HashMap<>();
		Map<String, Integer> cntMap = new HashMap<>();
		String key = null;
		int cnt = clothes.length;
		for (int idx01 = 0; idx01 < cnt; idx01++) {
			key = clothes[idx01][1];
			clothesMap.put(key, clothes[idx01][0]);
			cntMap.put(key, cntMap.getOrDefault(key, 0) + 1);
			System.out.println(clothes[idx01][1] + " " + clothes[idx01][0]);
		}

		Iterator<String> keyIter = clothesMap.keySet().iterator();
		Iterator<String> cntIter = cntMap.keySet().iterator();

		Object[] kindArray = cntMap.values().toArray();
		int kindCnt = kindArray.length;
		// Arrays.copyOf(kindArray, kindArr);
		Integer[] kindArr = new Integer[kindCnt];
		System.arraycopy(kindArray, 0, kindArr, 0, kindCnt);
		for (int i : kindArr) {
			System.out.println("인티저 배열 :" + i);
		}
		for (int i = 0; i < kindCnt - 1; i++) {
			int curVal = kindArr[i];
			System.out.println("?" + fnRecursiveCalc(curVal, ++i, kindArr));
		}
		System.out.println("종류:" + kindArray.length);
		if (kindCnt == 1) {
			answer = 0;
		} else {
			int loopCnt = 0;
			int curVal = 0;
			for (int idx01 = 0; idx01 < kindCnt - 1; idx01++) {
				System.out.println(String.format("1. %s %s %s가지의상", kindArray[idx01], idx01, kindCnt));
				curVal = kindArr[idx01];
				for (int idx02 = idx01 + 1; idx02 < kindCnt; idx02++) {
					answer = answer + fnRecursiveCalc(curVal, idx02, kindArr);
				}
			}
		}

		// 최종 의상종류의 갯수를 누적
		System.out.println(String.format("answer %s %s", answer, cnt));
		answer = answer + cnt;
		System.out.println(String.format("answer final %s", answer));
		return answer;
	}

	public static int fnCalculate(int num, int idx, int loopCnt, Object[] kindArray) {
		int rtnVal = 0;
		int sum = 0;

		int cnt = loopCnt;

		// sum = num;
		for (int idx01 = idx + 1; idx01 < kindArray.length; idx01++) {
			System.out.println(String.format("2. %s인덱스 %s %ssum", idx01, num, sum));
			for (int idx02 = idx01; idx02 < loopCnt; idx02--) {
				num = num + fnCalculate(((Integer) kindArray[idx02]), idx02, --loopCnt, kindArray);
				System.out.println(String.format("3. %s %s %s", idx02, sum, kindArray[idx02]));
			}
			// rtnVal = rtnVal + sum;
		}
		return num;
	}

}
