package programmers.stackq;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import algorithm.programmers.stackq.Solution_StackQ01;

public class Solution_StaackQ01Test {

	Solution_StackQ01 st01;

	@Before
	public void init() {
		System.out.println("test start");
		st01 = new Solution_StackQ01();
	}

	@After
	public void complete() {
		System.out.println("teardown");
	}

	@Test
	public void test01() {
		int[] array01 = {};
		int[] array02 = {};
		int[] array03 = {};
		int[] array04 = {};
		int[] array05 = {};
		int[] array06 = {};
		int[] array07 = {};
		int[] array08 = {};
		int[] result = {};
		array01 = new int[] { 6, 9, 5, 7, 4 };
		array02 = new int[] { 3, 9, 9, 3, 5, 7, 2 };
		array03 = new int[] { 1, 5, 3, 6, 7, 100, 6, 5 };
		array04 = new int[] { 5, 3, 6, 7, 100, 6, 5 };
		array05 = new int[] { 0, 0, 0, 3, 0, 0, 6, 7 };
		array06 = new int[] { 1, 1, 2, 2, 1 };
		array07 = new int[] { 2, 3 };
		array08 = new int[] { 3, 2 };
		result = st01.solution01(array01);
		assertArrayEquals(new int[] { 0, 0, 2, 2, 4 }, result);

		result = st01.solution01(array02);
		assertArrayEquals(new int[] { 0, 0, 0, 3, 3, 3, 6 }, result);

		result = st01.solution01(array03);
		assertArrayEquals(new int[] { 0, 0, 2, 0, 0, 0, 6, 7 }, result);

		result = st01.solution01(array04);
		for (int item : result) {
			System.out.print(String.format("%s, ", item));
		}
		System.out.println("--------->");
		assertArrayEquals(new int[] { 0, 1, 0, 0, 0, 5, 6 }, result);

		result = st01.solution01(array05);
		assertArrayEquals(new int[] { 0, 0, 0, 0, 4, 4, 0, 0 }, result);

		result = st01.solution01(array06);
		assertArrayEquals(new int[] { 0, 0, 0, 0, 4 }, result);

		result = st01.solution01(array07);
		assertArrayEquals(new int[] { 0, 0 }, result);

		result = st01.solution01(array08);
		assertArrayEquals(new int[] { 0, 1 }, result);
		// assertThat(new int[] { 0, 0 }, not(equalTo(result)));
		for (int item : result) {
			System.out.print(String.format("%s, ", item));
		}
		assertTrue(true);
	}
}
