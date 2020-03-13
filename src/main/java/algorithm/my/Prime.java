package algorithm.my;

import java.util.stream.IntStream;

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
