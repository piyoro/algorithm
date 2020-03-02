package algorithm.my.sort;

import java.util.Arrays;
import java.util.Random;
// [83, 76, 76, 0, 26]

public class BubbleSort {

	public static void main(String[] args) {
		// 정렬을 위한 정수 배열 선언
		int[] arr = new int[5];

		int len = arr.length, tmp = 0;
		Random r = new Random();
		// 배열의 값을 랜덤 정수로 할당
		for (int i = 0; i < len; i++) {
			arr[i] = r.nextInt(100);
		}
		System.out.println("  배열 원본");
		System.out.println("  " + Arrays.toString(arr));
		System.out.println();
		for (int i = len; i > 0; i--) {

			for (int j = 0; j < i - 1; j++) {
				// System.out.println("\t" + Arrays.toString(arr));
				// 오름차순, 배열의 현재 인덱스가 가리키는 값과
				// 다음 인덱스가 가리키는 값을 비교하여 교체
				if (arr[j] > arr[j + 1]) {
					tmp = arr[j + 1];
					arr[j + 1] = arr[j];
					arr[j] = tmp;
				}
			}
		}
		System.out.println();
		System.out.println("  오름차순 버블정렬");
		System.out.println("  " + Arrays.toString(arr));

		// 파라미터를 이용한 오름/내림 정렬 구분

		// 배열의 값을 랜덤 정수로 할당
		for (int i = 0; i < len; i++) {
			arr[i] = r.nextInt(50);
		}

		System.out.println("배열 원본");
		System.out.println(Arrays.toString(arr));
		bubbleSort(arr, false);

		System.out.println("플래그에 따른 버블정렬");
		System.out.println(Arrays.toString(arr));
	}

	/**
	 * <pre>
	 * 버블 정렬
	 * </pre>
	 * 
	 * @param arr
	 * @param align
	 * true 오름차순 / false 내림차순
	 */
	public static void bubbleSort(int[] arr, boolean align) {

		int len = arr.length, tmp = 0;
		// 비교문
		boolean comp;
		for (int i = len; i > 0; i--) {

			for (int j = 0; j < i - 1; j++) {
				// 플래그에 따라 비교문 적용
				if (align)
					comp = arr[j] > arr[j + 1];
				else
					comp = arr[j] < arr[j + 1];

				// 오름/내림 정렬에 따라 비교문만 달라지고 교환 로직 동일
				if (comp) {
					tmp = arr[j + 1];
					arr[j + 1] = arr[j];
					arr[j] = tmp;
				}
			}
		}
	}
}
