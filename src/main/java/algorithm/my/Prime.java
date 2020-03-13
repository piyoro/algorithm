package algorithm.my;

import java.util.stream.IntStream;

/**
 * @author : piyoro
 * @date : 2020. 3. 13.
 * @desc :
 * 
 * <pre>
 * 입력한 숫자사이의 소수구하기 _ 제곱근
 * </pre>
 */
public class Prime {

	public static void main(String[] args) {
		Prime prime = new Prime();
		System.out.println(prime.solution(100_000));
	}

	public long solution(int num) {
		return IntStream.rangeClosed(2, num).filter(i -> isPrime(i)).count();
	}

	public boolean isPrime(int candidate) {
		int candidateRoot = (int) Math.sqrt(candidate);
		return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
	}
}
