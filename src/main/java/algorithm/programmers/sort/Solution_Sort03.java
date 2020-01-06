package algorithm.programmers.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <pre>
 * H-Index는 과학자의 생산성과 영향력을 나타내는 지표입니다. 어느 과학자의 H-Index를 나타내는 값인 h를 구하려고 합니다. 위키백과1에 따르면, H-Index는 다음과 같이 구합니다.

 * 어떤 과학자가 발표한 논문 n편 중, h번 이상 인용된 논문이 h편 이상이고 나머지 논문이 h번 이하 인용되었다면 h가 이 과학자의 H-Index입니다.

 * 어떤 과학자가 발표한 논문의 인용 횟수를 담은 배열 citations가 매개변수로 주어질 때, 이 과학자의 H-Index를 return 하도록 solution 함수를 작성해주세요.

 * 제한사항
 * 과학자가 발표한 논문의 수는 1편 이상 1,000편 이하입니다.
 * 논문별 인용 횟수는 0회 이상 10,000회 이하입니다.
 * </pre>
 * 
 * @author piyor
 */
public class Solution_Sort03 {

	public static void main(String[] args) {
		Solution_Sort03 solution = new Solution_Sort03();

		int[] array = {

				3, 0, 6, 1, 5
				// 10, 100
				// 6, 6, 6, 6, 6

		};
		int answer = solution.solution02(array);
		System.out.println(String.format("answer final %s", answer));
	}

	/**
	 * @author : piyor
	 * 
	 * <pre>
	 * 소 뒷걸음질 치다가 쥐를 잡았다.
	 * h-index 를 몰라서 위키백과를 찾아봤고
	 * 영어가 나와서 많이 놀랬고. 하나도 이해못했고.
	 * 다른 사람의 설명정도만 찾아보고 적당히 솔루션인거 같았는데,
	 * 몇 몇 케이스 통과가 안됐다.
	 * 자고 일어나서 이렇게도 저렇게 몇번 바꿔보니 테스트에 통과했다.
	 * 왜 통과 됐는지 솔직히 모르겠다.
	 * </pre>
	 * 
	 * @param citations
	 * @return
	 */
	public int solution_my_best(int[] citations) {

		int answer = 0;
		// Arrays.sort(citations);
		Integer[] originArr = Arrays.stream(citations).boxed().toArray(Integer[]::new);
		Arrays.sort(originArr, Comparator.reverseOrder());

		int leng = citations.length;
		int idx = 0;
		int val = 0;
		for (int i = 0; i < leng; i++) {
			val = originArr[i];
			if (i >= val) {
				break;
			}
			idx++;
		}

		answer = idx;
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
	public int solution02(int[] citations) {
		int answer = 0;
		// Arrays.sort(citations);
		Integer[] originArr = Arrays.stream(citations).boxed().toArray(Integer[]::new);
		Arrays.sort(originArr, Comparator.reverseOrder());

		int leng = citations.length;
		int idx = 0;
		int val = 0;
		for (int i = 0; i < leng; i++) {
			val = originArr[i];
			System.out.println(" val :" + val);
			if (i >= val) {
				System.out.println(" idx :" + idx);

				break;
			}
			idx++;
		}

		answer = idx;
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
	public int solution01(int[] citations) {
		int answer = 0;

		Integer[] originArr = Arrays.stream(citations).boxed().toArray(Integer[]::new);
		Arrays.sort(originArr, Comparator.reverseOrder());
		// Arrays.sort(citations);
		// Integer[] originArr = Arrays.stream(citations).boxed().toArray(Integer[]::new);
		int maxCnt = 0;
		int minCnt = 0;

		int leng = originArr.length;
		int idx = -1;
		int val = 0;
		for (int i = 0; i < leng; i++) {
			val = originArr[i];
			idx = -1;
			System.out.println(idx + " val:" + val);

			for (int item : originArr) {

				if (idx > item) {
					maxCnt++;
					System.out.println(" maxCnt:" + maxCnt);
				}
				if (idx < item) {
					minCnt++;
					System.out.println(" minCnt:" + minCnt);
				}
				idx++;
			}
			/*
			if (idx > val) {
				maxCnt++;
				// System.out.println(" maxCnt:" + maxCnt);
				return Math.min(i, 10000);
			}
			*/
			/*
			if (i < val) {
				minCnt++;
				System.out.println(" minCnt:" + minCnt);
				return val;
			}
			*/
			/*
			if(minCnt == i) {
				return i;
			}
			*/
			// System.out.println(i + " maxCnt:" + maxCnt + " minCnt:" + minCnt);
			if (maxCnt == minCnt) {
				return i;
			}
			/*
			*/
			idx++;
		}

		System.out.println(" maxCnt:" + maxCnt + " minCnt:" + minCnt);
		answer = leng;

		return answer;
	}

	/**
	 * @author : piyor
	 * 
	 * <pre>
	 * 원채 다른 사람의 소스가 다양한 중에
	 * 제일 상위에 있는 간결한 소스인데,
	 * 냄새나는 부분이 몇군데 있다.
	 * 나도 비슷하게 생각은 하고 있었기에 
	 * 내가 이해하고 있는 내용이 맞는가 보다.
	 * </pre>
	 * 
	 * @param numbers
	 * @return
	 */
	public int solution_simple(int[] citations) {
		Arrays.sort(citations);

		int max = 0;
		for (int i = citations.length - 1; i > -1; i--) {
			int min = Math.min(citations[i], citations.length - i);
			if (max < min)
				max = min;
		}

		return max;
	}

}
