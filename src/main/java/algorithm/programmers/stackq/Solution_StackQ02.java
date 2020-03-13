package algorithm.programmers.stackq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * <pre>
 * 트럭 여러 대가 강을 가로지르는 일 차선 다리를 정해진 순으로 건너려 합니다. 
 * 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 알아내야 합니다. 
 * 트럭은 1초에 1만큼 움직이며, 다리 길이는 bridge_length이고 다리는 무게 weight까지 견딥니다.
 * ※ 트럭이 다리에 완전히 오르지 않은 경우, 이 트럭의 무게는 고려하지 않습니다.

 * 예를 들어, 길이가 2이고 10kg 무게를 견디는 다리가 있습니다. 
 * 무게가 [7, 4, 5, 6]kg인 트럭이 순서대로 최단 시간 안에 다리를 건너려면 다음과 같이 건너야 합니다.

 * 따라서, 모든 트럭이 다리를 지나려면 최소 8초가 걸립니다.

 * solution 함수의 매개변수로 다리 길이 bridge_length, 
 * 다리가 견딜 수 있는 무게 weight, 
 * 트럭별 무게 truck_weights가 주어집니다. 
 * 이때 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 return 하도록 solution 함수를 완성하세요.

 * 제한 조건
 * bridge_length는 1 이상 10,000 이하입니다.
 * weight는 1 이상 10,000 이하입니다.
 * truck_weights의 길이는 1 이상 10,000 이하입니다.
 * 모든 트럭의 무게는 1 이상 weight 이하입니다.
 * </pre>
 * 
 * @author piyor
 */
public class Solution_StackQ02 {

	public static void main(String[] args) {
		Solution_StackQ02 solution = new Solution_StackQ02();

		int bridge_length = 0;
		int weigth = 0;
		int[] truck_weights = {};

		bridge_length = 2;
		weigth = 10;
		truck_weights = new int[] { 7, 4, 5, 6 };

		/*
		bridge_length = 100;
		weigth = 100;
		truck_weights = new int[] { 10 };
		bridge_length = 100;
		weigth = 100;
		truck_weights = new int[] { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 };
		*/
		int answer = solution.solution03(bridge_length, weigth, truck_weights);
		// String[] result = Arrays.stream(answer).boxed().toArray(String[]::new);
		System.out.println(answer);
	}

	/**
	 * @author : piyor
	 * 
	 * <pre>
	 * </pre>
	 * 
	 * @param bridge_length
	 * @param weight
	 * @param truck_weights
	 * @return
	 */
	public int solution_my_best(int bridge_length, int weight, int[] truck_weights) {
		int answer = 0;
		return answer;
	}

	public int solution04(int bridge_length, int weight, int[] truck_weights) {
		int answer = 0;

		int leng = truck_weights.length;
		int head = 0, tail = 0, cnt = 0;
		// 배열을 큐 처럼 관리한다.
		int[] queue_weigths = new int[bridge_length];
		int sum_weigths = queue_weigths[0]; // 현재 누적 무게

		int clear_item = 0;
		// 트럭 하나 추출
		for (int item : truck_weights) {
			if (cnt > 0) {
				sum_weigths += item;

				// 현재누적무게 -> weigth 초과여부
				if (sum_weigths > weight) {
					for (int i = 0; i < tail + 1; i++) {
						clear_item = queue_weigths[i];
						if (clear_item > 0) {
							// 초과일 경우 추출
							if (sum_weigths > weight) {
								// 빼준다.
								answer += (leng - i);
								sum_weigths -= clear_item;
								queue_weigths[i] = 0;
							}
							// 초과 아닐 경우
							{
								// 태워준다.
								// 어느위치에 태워주느냐!!
								queue_weigths[tail] = item;
							}
						}
					}
				} else {
					queue_weigths[++tail] = item;
				}
			}
			cnt++;
		}

		return answer;
	}

	public int solution03(int bridge_length, int weight, int[] truck_weights) {
		int answer = 0;

		ArrayList<Integer> arrayList = (ArrayList<Integer>) Arrays.stream(truck_weights).boxed().collect(Collectors.toList());
		Queue<Integer> queue = new LinkedList<>(arrayList);

		int time = 1, peekVal, cnt = 0, lastInstacne = 0;
		int sum_weight = 0;
		while (!queue.isEmpty()) {
			peekVal = queue.peek();
			System.out.println(peekVal);

			if (peekVal > 0) {

				sum_weight += peekVal;
				if (sum_weight > weight) {
					sum_weight = 0;
					time += cnt - 1;
					cnt = 0;
					System.out.println(String.format("gt cnt [%s] val [%s] time [%s]", cnt, peekVal, time));
				} else {
					queue.poll();
					cnt++;

					if (sum_weight == peekVal) {
						time += bridge_length;
						lastInstacne = bridge_length;
					}

					System.out.println(String.format("leq val [%s] cnt [%s] sum_weight [%s] weight [%s]", peekVal, cnt, sum_weight, weight));

					if (sum_weight == weight) {
						sum_weight = 0;
						time += cnt - 1;
						cnt = 0;
						System.out.println(String.format("out sum_weight [%s] time [%s]", sum_weight, time));
						System.out.println(String.format("!!!!!out sum_weight [%s] time [%s]", sum_weight, time));
					}
				}
			}
		}
		/*
		if (sum_weight > 0) {
			time += cnt - 1;
		}
		*/
		System.out.println(String.format("after time [%s] sum_weight [%s] cnt [%s]", time, sum_weight, cnt));
		// 최종 시간 계산
		// time += cnt * bridge_length;
		// answer = time * cnt + (bridge_length);
		answer = time;
		// answer += cnt * bridge_length;
		// System.out.println(queue.size());
		return answer;

	}

	/**
	 * @author : piyor
	 * 
	 * <pre>
	 * </pre>
	 * 
	 * @param bridge_length
	 * @param weight
	 * @param truck_weights
	 * @return
	 */
	public int solution02(int bridge_length, int weight, int[] truck_weights) {
		int answer = 0;

		ArrayList<Integer> arrayList = (ArrayList<Integer>) Arrays.stream(truck_weights).boxed().collect(Collectors.toList());
		Queue<Integer> queue = new LinkedList<>(arrayList);

		int time = 0, peekVal, cnt = 0;
		int sum_weight = 0;
		while (!queue.isEmpty()) {
			peekVal = queue.peek();
			System.out.println(peekVal);

			if (peekVal > 0) {

				sum_weight += peekVal;
				if (sum_weight > weight) {
					sum_weight = 0;
					time += cnt + bridge_length;
					System.out.println(String.format("gt cnt [%s] val [%s] time [%s]", cnt, peekVal, time));
					cnt = 0;
					time--;
				} else {
					System.out.println(String.format("leq val [%s] sum_weight [%s] weight [%s]", peekVal, sum_weight, weight));
					queue.poll();
					cnt++;
					if (sum_weight == weight) {
						sum_weight = 0;
						time += cnt + bridge_length;
						System.out.println(String.format("out sum_weight [%s] time [%s]", sum_weight, time));
						cnt = 0;
						System.out.println(String.format("!!!!!out sum_weight [%s] time [%s]", sum_weight, time));
					}
				}
			}
		}
		System.out.println(String.format("after time [%s] sum_weight [%s] cnt [%s]", time, sum_weight, cnt));
		// 잔여처리
		if (cnt > 0) {
			time += cnt + bridge_length;
		}
		answer = time;
		// answer += cnt * bridge_length;
		// System.out.println(queue.size());
		return answer;

	}

	/**
	 * @author : piyor
	 * 
	 * <pre>
	 * </pre>
	 * 
	 * @param bridge_length
	 * @param weight
	 * @param truck_weights
	 * @return
	 */
	public int solution01(int bridge_length, int weight, int[] truck_weights) {
		int answer = 0;

		ArrayList<Integer> arrayList = (ArrayList<Integer>) Arrays.stream(truck_weights).boxed().collect(Collectors.toList());
		Queue<Integer> queue = new LinkedList<>(arrayList);

		int time = 1, peekVal, cnt = 0;
		int sum_weight = 0;
		while (!queue.isEmpty()) {
			peekVal = queue.peek();
			System.out.println(peekVal);

			if (peekVal > 0) {

				while (true) {
					sum_weight += peekVal;
					if (sum_weight > weight) {
						sum_weight = 0;
						time += cnt + bridge_length;
						System.out.println(String.format("gt cnt [%s] val [%s] time [%s]", cnt, peekVal, time));
						cnt = 0;
						break;
					} else {
						System.out.println(String.format("leq val [%s] sum_weight [%s] weight [%s]", peekVal, sum_weight, weight));
						queue.poll();
						cnt++;
						if (sum_weight == weight) {
							sum_weight = 0;
							time += cnt + bridge_length;
							System.out.println(String.format("out sum_weight [%s] time [%s]", sum_weight, time));
							cnt = 0;
							System.out.println(String.format("!!!!!out sum_weight [%s] time [%s]", sum_weight, time));
							break;
						}
					}
					if (queue.isEmpty()) {
						System.out.println("is Empty?" + peekVal);
						break;
					}
				}
			}
		}
		System.out.println(String.format("after time [%s] sum_weight [%s] cnt [%s]", time, sum_weight, cnt));
		// 잔여처리
		if (cnt > 0) {
			time += bridge_length * cnt;
		}
		answer = time;
		// answer += cnt * bridge_length;
		// System.out.println(queue.size());
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
	public int solution_simple(int bridge_length, int weight, int[] truck_weights) {
		int answer = 0;

		// 다리를 건너기 전에 대기 트럭 리스트
		Queue<Truck> q_wait = new LinkedList<>();

		// 다리를 건너는 트럭 리스트
		Queue<Truck> q_onBridge = new LinkedList<>();

		int onBridgeWeight = 0;

		for (int w : truck_weights) {
			q_wait.add(new Truck(w, 0));
		}

		onBridgeWeight += q_wait.peek().weight;
		q_onBridge.add(q_wait.poll());

		int time = 0;

		while (!q_onBridge.isEmpty()) {
			time++;

			for (Truck t : q_onBridge) {
				// 다리위 트럭들을 움직임
				t.index++;
			}

			// 트럭이 다리 끝에 다다름
			if (q_onBridge.peek().index > bridge_length) {
				onBridgeWeight -= q_onBridge.poll().weight;
			}

			// 대기 중 트럭을 다리에 올림 (무게 고려해야함 )
			if (!q_wait.isEmpty() && q_wait.peek().weight + onBridgeWeight <= weight) {
				onBridgeWeight += q_wait.peek().weight;
				q_wait.peek().index++;
				q_onBridge.add(q_wait.poll());
			}
		} // while 끝

		answer = time;

		return answer;
	}

	static class Truck {

		int weight;
		int index;

		public Truck(int weight, int index) {
			this.weight = weight;
			this.index = index;
		}
	}

	public int solution_simple01(int bridge_length, int weight, int[] truck_weights) {
		int answer = 0;
		int sum = 0;
		int i = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		ArrayList<Integer> arr = new ArrayList<Integer>();
		int[] car_d = new int[truck_weights.length];

		while (true) {
			answer++;
			// while문이므로 i를 직접 설정해서 새로운 트럭이 다리를 지나가면 증가시켜준다.
			if (i < truck_weights.length && sum + truck_weights[i] <= weight) {
				q.add(truck_weights[i]);
				sum += truck_weights[i];

				i++;
			}
			// -1은 다리를 이미 건넜다는 표시이다.
			for (int j = 0; j < i; j++) {
				if (car_d[j] != -1) {
					car_d[j]++;
				}
			}
			// 시간이 길이와 같으면 나오면서 전체 무게를 빼주고 시간 배열을 -1로 바꿔준다.
			for (int j = 0; j < i; j++) {
				if (car_d[j] == bridge_length) {
					int complete = q.poll();
					arr.add(complete);
					sum -= complete;
					car_d[j] = -1;
				}
			}
			// 마지막에는 시간 추가가 안되므로 나오면서 answer++을 해준다.
			if (arr.size() == truck_weights.length) {
				answer++;
				break;
			}

		}

		return answer;
	}

}
