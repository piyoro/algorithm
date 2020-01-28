package algorithm.programmers.stackq;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * 프로그래머스 팀에서는 기능 개선 작업을 수행 중입니다. 
 * 각 기능은 진도가 100%일 때 서비스에 반영할 수 있습니다.

 * 또, 각 기능의 개발속도는 모두 다르기 때문에 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발될 수 있고, 
 * 이때 뒤에 있는 기능은 앞에 있는 기능이 배포될 때 함께 배포됩니다.

 * 먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 정수 배열 progresses와 
 * 각 작업의 개발 속도가 적힌 정수 배열 speeds가 주어질 때 
 * 각 배포마다 몇 개의 기능이 배포되는지를 return 하도록 solution 함수를 완성하세요.
 * </pre>
 * 
 * @author piyor
 */
public class Solution_StackQ3 {

	public static void main(String[] args) {
		Solution_StackQ3 solution = new Solution_StackQ3();

		int[] progresses = {};
		int[] speeds = {};
		// Integer[] result = Arrays.stream(answer).boxed().toArray(Integer[]::new);
		// progresses = new int[] { 93, 30, 55 };
		// speeds = new int[] { 1, 30, 5 };
		progresses = new int[] { 93, 55, 30 };
		speeds = new int[] { 1, 35, 5 };
		progresses = new int[] { 93, 30, 55, 90 };
		speeds = new int[] { 1, 30, 5, 10 };
		int[] answer = solution.solution01(progresses, speeds);
		ArrayList<Integer> originList = (ArrayList<Integer>) Arrays.stream(answer).boxed().collect(Collectors.toList());
		System.out.println(originList);
		/*
		answer = solution.solution_simple(progresses, speeds);
		originList = (ArrayList<Integer>) Arrays.stream(answer).boxed().collect(Collectors.toList());
		System.out.println(originList);
		*/
	}

	/**
	 * @author : piyor
	 * 
	 * <pre>
	 * progresses = new int[] { 93, 30, 55 };
	 * speeds = new int[] { 1, 30, 5 };
	 * 의 경우엔 [2,1] 결과가 나온다.
	 * ----
	 * progresses = new int[] { 93, 30, 55, 90 };
	 * speeds = new int[] { 1, 30, 5, 10 };
	 * 의 경우엔 어떤 결과가 나와야할까? 내 생각엔 [3,1] 이나와야할거 같은데,
	 * 소스의 결과는 [2,2] 이고, 테스트는 통과되었다. 찝찝하다.
	 * ----
	 * 이상허다...
	 * </pre>
	 * 
	 * @param progresses
	 * @param speeds
	 * @return
	 */
	public int[] solution01(int[] progresses, int[] speeds) {
		int[] answer = {};

		int idx = 0;
		double d = 0d;
		BigDecimal bd = null;

		int tmp = -1, cur_val = -1, cnt = 1;
		List<Integer> rtnList = new ArrayList<>();
		for (int item : progresses) {
			d = (double) (100 - item) / speeds[idx];
			bd = new BigDecimal(String.valueOf(d)).setScale(0, BigDecimal.ROUND_CEILING);
			cur_val = bd.intValue();
			if (tmp == -1) {
				tmp = cur_val;
				rtnList.add(1);
			} else {
				if (tmp < cur_val) {
					cnt++;
					rtnList.add(1);
					tmp = cur_val;
				} else {
					rtnList.set(cnt - 1, rtnList.get(cnt - 1) + 1);
				}
			}
			idx++;
		}
		answer = rtnList.stream().mapToInt(i -> i).toArray();
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
	public int[] solution_simple(int[] progresses, int[] speeds) {

		int[] dayOfend = new int[100];
		int day = -1;
		for (int i = 0; i < progresses.length; i++) {
			while (progresses[i] + (day * speeds[i]) < 100) {
				day++;
			}
			dayOfend[day]++;
		}
		return Arrays.stream(dayOfend).filter(i -> i != 0).toArray();
	}

}
