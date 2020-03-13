package algorithm.programmers.stackq;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * <pre>
 * 일반적인 프린터는 인쇄 요청이 들어온 순서대로 인쇄합니다. 그렇기 때문에 중요한 문서가 나중에 인쇄될 수 있습니다. 
 * 이런 문제를 보완하기 위해 중요도가 높은 문서를 먼저 인쇄하는 프린터를 개발했습니다. 
 * 이 새롭게 개발한 프린터는 아래와 같은 방식으로 인쇄 작업을 수행합니다.

	1. 인쇄 대기목록의 가장 앞에 있는 문서(J)를 대기목록에서 꺼냅니다.
	2. 나머지 인쇄 대기목록에서 J보다 중요도가 높은 문서가 한 개라도 존재하면 J를 대기목록의 가장 마지막에 넣습니다.
	3. 그렇지 않으면 J를 인쇄합니다.

 * 예를 들어, 4개의 문서(A, B, C, D)가 순서대로 인쇄 대기목록에 있고 중요도가 2 1 3 2 라면 C D A B 순으로 인쇄하게 됩니다.
 * 내가 인쇄를 요청한 문서가 몇 번째로 인쇄되는지 알고 싶습니다. 위의 예에서 C는 1번째로, A는 3번째로 인쇄됩니다.

 * 현재 대기목록에 있는 문서의 중요도가 순서대로 담긴 배열 priorities와 내가 인쇄를 요청한 문서가 
 * 현재 대기목록의 어떤 위치에 있는지를 알려주는 location이 매개변수로 주어질 때, 
 * 내가 인쇄를 요청한 문서가 몇 번째로 인쇄되는지 return 하도록 solution 함수를 작성해주세요.
 * </pre>
 * 
 * @author piyor
 */
public class Solution_StackQ05 {

	public static void main(String[] args) {
		Solution_StackQ05 solution = new Solution_StackQ05();

		int[] priorities = { 2, 1, 3, 2 };
		int location = 2; // =>1
		priorities = new int[] { 4, 2, 1, 3, 2 };
		location = 2; // =>5
		// priorities = new int[] { 2, 4, 1, 3, 4 };
		// location = 2; // =>5
		priorities = new int[] { 1, 1, 9, 1, 1, 1 };
		location = 0; // 5
		int answer = solution.solution02(priorities, location);
		System.out.println("=>" + answer);
		// answer = solution.solution_simple(priorities, location);
		// System.out.println("=>" + answer);
		/*
		answer = solution.solution_simple(progresses, speeds);
		originList = (ArrayList<Integer>) Arrays.stream(answer).boxed().collect(Collectors.toList());
		System.out.println(originList);
		*/
	}

	public int solution02(int[] priorities, int location) {
		int answer = 1;
		int leng = priorities.length;
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		pq.addAll(Arrays.stream(priorities).boxed().map(i -> i).collect(Collectors.toList()));
		while (!pq.isEmpty()) {
			for (int i = 0; i < leng; i++) {
				if (priorities[i] == pq.peek()) {
					if (i == location) {
						return answer;
					}
					pq.poll();
					answer++;
				}
			}
		}

		return answer;
	}

	/**
	 * @author : piyor
	 * 
	 * <pre>
	 * 20개 중 5개 통과, 테스트도 통과못했지만, 소스자체가 챙피한 소스다.
	 * </pre>
	 * 
	 * @param priorities
	 * @param location
	 * @return
	 */
	public int solution01(int[] priorities, int location) {
		int answer = 0;

		int idx = 0, item_idx = -1, cnt = 0;

		int val = priorities[location];

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int max_val_idx = -1;
		int max_val = priorities[0];
		for (int item : priorities) {
			if (max_val <= item) {
				max_val = item;
				max_val_idx = idx;
			}
			if (item == val) {
				cnt++;
				if (idx == location) {
					// System.out.println("!!!");
					item_idx++;
				}
				if (max_val_idx > location && max_val_idx < idx) {
					// System.out.println("@@@");
					item_idx++;
				}
			}
			pq.offer(item);
			idx++;
		}
		// System.out.println(String.format("max_val_idx [%s] cnt [%s] item_idx [%s]", max_val_idx, cnt, item_idx));
		List<Integer> list = pq.stream().sorted((i, j) -> j.compareTo(i)).collect(Collectors.toList());
		int search_idx = 0;
		idx = 0;
		for (int item : list) {
			if (item == val) {
				// System.out.println(" " + (cnt - item_idx + 1));
				if (search_idx == item_idx) {
					answer = idx + 1;
				}
				search_idx++;
			}

			idx++;
			// System.out.println(item);
		}

		return answer;
	}

	/**
	 * @author : piyor
	 * 
	 * <pre>
	 * </pre>
	 * 
	 * @param priorities
	 * @param location
	 * @return
	 */
	public int solution_simple(int[] priorities, int location) {

		int answer = 1;

		PriorityQueue priority = new PriorityQueue<>(Collections.reverseOrder());

		for (int task : priorities) {
			priority.add(task);
			System.out.println(priority);
		}
		// {2,5,4,1,3};

		System.out.println(priority);
		while (!priority.isEmpty()) {
			for (int i = 0; i < priorities.length; i++) {
				if (priorities[i] == (int) priority.peek()) {
					if (i == location) {
						return answer;
					}
					priority.poll();
					answer++;
				}
			}
		}

		return answer;
	}

}
