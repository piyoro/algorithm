package algorithm.my;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * 피보나치 수열
 * 실제 알고리즘도 구현해보고,
 * 프로그래머스 같은 곳에 올릴내용이 아니라
 * gradle 을 이용한 logback도 적용해보고
 * 추후 사용할 StringUtil 클래스도 만들어 적용해보았다.
 * </pre>
 * 
 * @author piyor
 */
public class Fibonacci01 {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(Fibonacci01.class);

	public static void main(String[] args) {

		Fibonacci01 f = new Fibonacci01();
		int param = 100;
		BigInteger val = f.fibonacci(param);
		logger.debug("fibonacci result [{}]", new Object[] { val });

	}

	/**
	 * <pre>
	 * 피보나치수열..
	 * 자칫 int 형으로 계산했다가는 어마어마한 자릿수 폭탄을 맞아서
	 * 시스템 오류가 발생한다.
	 * BigInteger 처리에 주의.
	 * 원래 수식은 f(n-1) + f(n) 으로 초기에 수식은 수식일 뿐이고,
	 * 적당한 성능향상은 항상 염두해야겠다.
	 * 40초~1분 이상의 느린 처리속도에 초기 변수값과 계산값을 swap 하였더니
	 * 훨씬 빠르게 동작된다.
	 * </pre>
	 * 
	 * @param n
	 * @return
	 */
	public BigInteger fibonacci(int n) {

		BigInteger biCalc = new BigInteger("0");

		BigInteger biPre = new BigInteger("1");
		BigInteger biPost = new BigInteger("1");

		// 피보나치 수열은 1 , 2 를 입력받을 경우 무조건 1을 반환한다.
		if (n < 3) {
			if (logger.isDebugEnabled()) {
				logger.debug("fibonacci(int) - end"); //$NON-NLS-1$
			}
			return biPre;
		}

		for (int i = 2; i < n; i++) {
			biCalc = biPre.add(biPost);
			biPre = biPost;
			biPost = biCalc;
		}

		return biPost;
	}
}
