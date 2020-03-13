package algorithm.my;

import java.util.Arrays;

public class EratostenesPrime {

	public static void main(String[] args) {
		int num = 10;
		num = 1_000;
		num = 100_000;
		num = 1_000_000;
		num = 5_000_000;
		long start = 0, end = 0;
		start = System.currentTimeMillis();
		int answer = solution(num);
		end = System.currentTimeMillis();
		System.out.format("answer [%s] exec[%s]%n", answer, (end - start));
	}

	public static int solution(int num) {
		int answer = 0;

		int len = num + 1;
		boolean[] primes = new boolean[len];
		Arrays.fill(primes, true);

		for (int p = 2; p * p < len; p++) {
			if (primes[p]) {
				for (int i = p * p; i < len; i += p) {
					primes[i] = false;
				}
			}
		}

		for (int i = 2; i < len; i++) {
			if (primes[i]) {
				answer++;
			}
		}
		return answer;
	}
}
