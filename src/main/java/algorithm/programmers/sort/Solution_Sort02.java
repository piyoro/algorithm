package algorithm.programmers.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * 0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요.

 * 예를 들어, 주어진 정수가 [6, 10, 2]라면 [6102, 6210, 1062, 1026, 2610, 2106]를 만들 수 있고, 이중 가장 큰 수는 6210입니다.

 * 0 또는 양의 정수가 담긴 배열 numbers가 매개변수로 주어질 때, 순서를 재배치하여 만들 수 있는 가장 큰 수를 문자열로 바꾸어 return 하도록 solution 함수를 작성해주세요.

 * 제한 사항
 * numbers의 길이는 1 이상 100,000 이하입니다.
 * numbers의 원소는 0 이상 1,000 이하입니다.
 * 정답이 너무 클 수 있으니 문자열로 바꾸어 return 합니다.
 * </pre>
 * 
 * @author piyor
 */
public class Solution_Sort02 {

	public static void main(String[] args) {
		Solution_Sort02 solution = new Solution_Sort02();

		int[] array = {

				// 6, 2, 10
				//
				// 3, 30, 80, 8, 0, 11, 92, 633, 6, 340, 98, 533, 7, 450, 444, 999, 83, 4, 34, 5, 9, 99, 88
				// 100, 9, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 4, 44, 43, 42, 41, 40, 39, 38, 37, 36, 35, 34, 7, 77, 76, 75, 74, 73, 72, 71,
				// 70,
				// 69, 68, 67, 16, 15, 14, 13, 12, 1, 11, 10, 5, 55, 54, 53, 52, 51, 50, 49, 48, 47, 46, 45, 8, 88, 87, 86, 85, 84, 83, 82, 81, 80,
				// 79,
				// 78, 6, 66, 65, 64, 63, 62, 61, 60, 59, 58, 57, 56, 3, 33, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 2, 22, 21, 20, 19, 18, 17, 0

				// 3, 30, 34, 5, 9, 33

				5, 9, 9, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 5, 0, 0, 0, 6, 5, 9, 8, 4, 1, 2, 3, 6, 5, 5, 9, 9, 9, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 5

		};
		array = new int[1001];
		for (int i = 0; i < 1001; i++) {
			array[i] = i;
		}
		String answer = solution.solution_my_best(array);
		System.out.println(String.format("answer final %s", answer));
	}

	/**
	 * @author : piyor
	 * 
	 * <pre>
	 * 배열요소의 입력값이 0~1000 까지 최대 4자리이기 때문에
	 * 최대한 간결하게보다는 나만의 방식대로 풀어보려했다.
	 * 결국 if문이 덕지덕지 붙은 코드는 간결하지 못하고, 이해하기 힘들기 때문에
	 * 그냥 일반적인 방식대로 푸는게 더 좋겠다.
	 * 
	 * 긍정평가
	 * :예전의 어떤 경험을 바탕으로 결국엔 해냈다.
	 * :마지막 테스트 케이스 기상천외한 방법으로 결국엔 찾아냈다.
	 * :나름 좋은 공부한셈 친다.
	 * 
	 * 부정평가
	 * :실무에선 3자리 4자리 등.. 유연하지 못하기 때문에 쓰지말아야겠다.
	 * :if문이 더럽다. 다른 사람이 보기 이해하기 힘들것이다.
	 * </pre>
	 * 
	 * @param numbers
	 * @return
	 */
	public String solution_my_best(int[] numbers) {

		String answer = "";

		// 추가 리스트
		ArrayList<String> targetList = new ArrayList<>();

		String val = null;
		String subval = null;

		String substring1 = null;
		String substring2 = null;

		int leng = 0;

		leng = numbers.length;
		for (int i : numbers) {
			val = String.valueOf(i);
			substring1 = val.substring(0, 1);
			subval = String.valueOf(Integer.parseInt(substring1) - 1);
			leng = val.length();
			if (i > 0 && leng == 1) {
				val = val + val + val + "Z";
			}
			if (leng == 2) {
				substring2 = val.substring(1, 2);
				if (substring1.compareTo(substring2) > 0) {
					val = val + subval + "Y";
				} else {
					val = val + substring1 + "Y";
				}
			}

			if (i % 100 == 0 && leng == 3) {
				val = val + "1X";
			}

			targetList.add(val);
		}

		Collections.sort(targetList, Collections.reverseOrder());

		answer = String.join("", targetList);
		if (answer.startsWith("0")) {
			return "0";
		}
		answer = answer.replaceAll("\\d{2}Z|\\dY|1X", "");

		// startsWidth 0 하기 싫어서 BigInteger 로 숫자형 판단해봤는데, 응답속도가 너무 늦다.
		/*
		BigInteger bi = new BigInteger(answer);
		answer = bi.toString();
		 */
		return answer;
	}

	public String solution06(int[] numbers) {

		String answer = "";

		// 추가 리스트
		ArrayList<String> targetList = new ArrayList<>();

		String val = null;
		String subval = null;

		String substring1 = null;
		String substring2 = null;

		int leng = 0;

		leng = numbers.length;
		for (int i : numbers) {
			val = String.valueOf(i);
			substring1 = val.substring(0, 1);
			subval = String.valueOf(Integer.parseInt(substring1) - 1);
			// subval = val.substring(0, 1);
			leng = val.length();
			// val = pad(val, "X", numbers.length, 1);
			if (i > 0 && leng == 1) {

				// if (leng == 1) {
				val = val + val + val + "Z";
			}
			if (leng == 2) {
				substring2 = val.substring(1, 2);
				if (substring1.compareTo(substring2) > 0) {
					val = val + subval + "Y";
				} else {
					val = val + substring1 + "Y";
				}
			}

			if (i % 100 == 0 && leng == 3) {
				val = val + "1X";
			}

			targetList.add(val);
		}
		// Collections.sort(targetList);

		Collections.sort(targetList, Collections.reverseOrder());

		answer = String.join("", targetList);

		if (Integer.parseInt(targetList.get(0)) == 0) {
			return "0";
		}
		answer = String.join("", targetList);

		answer = answer.replaceAll("\\d{2}Z|\\dY|1X", "");
		return answer;

	}

	public String solution05(int[] numbers) {
		String answer = "";

		// 추가 리스트
		ArrayList<String> targetList = new ArrayList<>();

		for (int i : numbers) {
			targetList.add(pad(String.valueOf(i), "X", numbers.length, 1));
		}
		// Collections.sort(targetList);
		Collections.sort(targetList, Collections.reverseOrder());

		int leng = targetList.size();
		String[] strArray = targetList.toArray(new String[leng]);

		String beforeStr = "";
		String afterStr = null;

		String compStr = null;

		String val = null;

		int comp = 0;
		strArray[0] = strArray[0].replace("X", "");

		String[] copyArray = null;
		for (int i = 1; i < leng; i++) {
			val = strArray[i].replace("X", "");
			strArray[i] = val;
			System.out.println("-------> val :" + val);

			beforeStr += strArray[i - 1];

			copyArray = Arrays.copyOfRange(strArray, 0, i);
			copyArray[i - 1] = val;
			System.out.println("before:" + beforeStr);
			// System.out.println("before - :" + );
			compStr = String.join("", copyArray);
			afterStr = beforeStr + val;

			// 큰수가됏을때
			if (afterStr.compareTo(compStr) < 0) {
				System.out.println(compStr + " < " + afterStr);
				compStr = afterStr;
				strArray[i] = strArray[i - 1];
				strArray[i - 1] = val;
				// maxIdx = idx02;
				// System.out.println(compStr + "compare------>" + compStr);
			}

		}

		/*
		for (String item : targetList) {
			System.out.println(item);
		}
		*/
		answer = String.join("", strArray);
		return answer;
	}

	/**
	 * @author : piyor
	 * 
	 * <pre>
	 * 많이 고민 하고 간결하게 하려던게 결국엔 많은 소설이 이고,
	 * 실제 테스트 돌려봤을 때 [시간초과]라고 나오는 부분도 있고,
	 * 마지막 테스트를 통과 못한다.
	 * 간결하고 정확하게 할 수 있는 방법을 다시 고민해야겠다.
	 * </pre>
	 * 
	 * @param numbers
	 * @return
	 */
	public String solution04(int[] numbers) {
		String answer = "";
		// 원본 리스트
		ArrayList<Integer> originList = (ArrayList<Integer>) Arrays.stream(numbers).boxed().collect(Collectors.toList());

		// 추가 리스트
		ArrayList<String> targetList = new ArrayList<>();

		int leng = originList.size();

		if (leng == 1) {
			return String.valueOf(numbers[0]);
		}

		targetList.add(String.valueOf(originList.get(0)));

		int targetLeng = 0;

		int num = -1;

		String val = null;
		String afterStr = null;
		String maxStr = null;
		int maxIdx = 0;
		for (int idx01 = 1; idx01 < leng; idx01++) {
			num = originList.get(idx01);
			val = String.valueOf(num);

			targetLeng = targetList.size();

			// 원본문자열
			// cloneList = (ArrayList<String>) targetList.clone();
			// maxStr = String.join("", cloneList);
			// System.out.println("maxStr------>" + maxStr);

			maxStr = String.join("", targetList);
			for (int idx02 = targetLeng; idx02 > -1; idx02--) {
				targetList.add(idx02, String.valueOf(val));

				afterStr = String.join("", targetList);

				// System.out.println(" idx01 : " + idx01 + " idx02 : " + idx02 + " : " + maxStr + " : " + afterStr);

				// 큰수가됏을때
				if (maxStr.compareTo(afterStr) < 0) {
					// System.out.println(maxStr + " < " + afterStr);
					maxStr = afterStr;
					maxIdx = idx02;
					// System.out.println(maxIdx + "compare------>" + maxStr);
				}
				targetList.remove(idx02);
			}
			targetList.add(maxIdx, String.valueOf(val));
			// System.out.println("afterStr------>" + maxStr);
		}
		// System.out.println(maxStr);
		// answer = maxStr;
		answer = String.join("", targetList);

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
	public String solution03(int[] numbers) {
		String answer = "";

		Arrays.sort(numbers);
		int leng = numbers.length;
		int maxLeng = String.valueOf(numbers[leng - 1]).length();
		String[] strArray = new String[leng];

		int idx = 0;
		String tmp = null;
		for (int i : numbers) {
			// tmp = String.valueOf(i);
			tmp = pad(String.valueOf(i), "X", maxLeng, 1);
			strArray[idx++] = tmp;
		}
		Arrays.sort(strArray);

		List<String> tmplist = Arrays.asList(strArray);

		// 리스트 뒤집어 주기
		Collections.reverse(tmplist);

		// 리스트를 배열로 다시 변환
		strArray = tmplist.toArray(new String[tmplist.size()]);

		for (String item : strArray) {
			System.out.println(" " + item);
		}
		tmp = null;
		String strPre = null;
		int comp = 0;

		String beforStr = null;
		String afterStr = null;

		for (int i = 0; i < leng; i++) {
			// tmp = strArray[i];
			tmp = strArray[i].replace("X", "");

			// System.out.println(String.format("-------> %s", tmp));
			if (strPre == null) {
				strPre = tmp;
			}

			// beforStr = strPre.replace("X", "") + tmp.replace("X", "");
			// afterStr = tmp.replace("X", "") + strPre.replace("X", "");
			beforStr = strPre + tmp;
			afterStr = tmp + strPre;

			comp = afterStr.compareTo(beforStr);

			if (comp > 0) {
				// System.out.println(String.format("%s --- %s ----> %s ::: %s", tmp, strPre, beforStr, afterStr));
				if (!tmp.equals(strPre)) {
					answer = answer.substring(0, answer.length() - strPre.length());
					strArray[i - 1] = tmp;
					strArray[i] = strPre;
					answer = answer + tmp;
					tmp = strPre;
				}
			}
			strPre = tmp;

			answer = answer + tmp;
			// System.out.println(String.format("answer %s", answer));
		}

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
	public String solution02(int[] numbers) {
		String answer = "";

		Arrays.sort(numbers);
		int leng = numbers.length;

		String[] strArray = new String[leng];

		int idx = 0;
		String tmp = null;
		for (int i : numbers) {
			tmp = String.valueOf(i);
			strArray[idx++] = tmp;
		}
		Arrays.sort(strArray);

		List<String> tmplist = Arrays.asList(strArray);

		// 리스트 뒤집어 주기
		Collections.reverse(tmplist);

		// 리스트를 배열로 다시 변환
		strArray = tmplist.toArray(new String[tmplist.size()]);

		tmp = null;
		String strPre = null;
		int comp = 0;
		for (int i = 0; i < leng; i++) {
			tmp = strArray[i];

			if (strPre == null) {
				strPre = tmp;
			}

			comp = tmp.compareTo(strPre);
			if (comp < 0) {

				if (tmp.length() < strPre.length()) {
					answer = answer.substring(0, answer.length() - strPre.length());
					if (i - (leng - 1) < 1) {
						// strArray[i] = tmp;
						// strArray[i + 1] = strPre;
						strArray[i - 1] = tmp;
						strArray[i] = strPre;
						tmp = strPre;
						strPre = strArray[i - 1];
						answer = answer + strArray[i - 1];
					} else {
						answer = answer + tmp + strPre;
						break;
					}

				}
			}
			strPre = tmp;

			answer = answer + tmp;
		}

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
	public String solution01(int[] numbers) {
		String answer = "";

		Arrays.sort(numbers);
		int leng = numbers.length;

		int maxLeng = String.valueOf(numbers[leng - 1]).length();

		String[] strArray = new String[leng];

		int idx = 0;
		String tmp = null;
		for (int i : numbers) {
			// strArray[idx++] = String.valueOf(i);
			tmp = String.valueOf(i);
			strArray[idx++] = pad(tmp, "0", maxLeng, 1);
		}
		Arrays.sort(strArray);

		List<String> tmplist = Arrays.asList(strArray);

		// 리스트 뒤집어 주기
		Collections.reverse(tmplist);

		// 리스트를 배열로 다시 변환
		strArray = tmplist.toArray(new String[tmplist.size()]);

		for (int i = 0; i < leng; i++) {
			System.out.println("item : " + strArray[i]);
		}

		String strJoin = String.join("", strArray);
		leng = strJoin.length();
		tmp = null;
		String strPre = null;
		for (int i = 0; i < leng; i++) {
			tmp = strJoin.substring(i, i + 1);
			answer = answer + tmp;
			System.out.println("numberformat?" + tmp);
			if (strPre == null) {
				strPre = tmp;
				continue;
			}
			if (Integer.parseInt(strPre) < Integer.parseInt(tmp)) {
				answer = answer.substring(0, answer.length() - 2) + tmp;
				System.out.println("answer join?" + answer);
			}
			System.out.println("answer?" + answer);
			strPre = tmp;
		}
		System.out.println();
		return answer;
	}

	public static String pad(String src, String pad, int totLen, int mode) {
		String paddedString = "";

		if (src == null)
			return "";
		int srcLen = src.length();

		if ((totLen < 1) || (srcLen >= totLen))
			return src;

		for (int i = 0; i < (totLen - srcLen); i++) {
			paddedString += pad;
		}

		if (mode == -1)
			paddedString += src; // front padding
		else
			paddedString = src + paddedString; // back padding

		return paddedString;
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
	public String solution_simple(int[] numbers) {
		String[] arr = new String[numbers.length];
		for (int i = 0; i < numbers.length; i++)
			arr[i] = String.valueOf(numbers[i]);

		Arrays.sort(arr, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
		if (arr[0].equals("0"))
			return "0";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++)
			sb.append(arr[i]);

		return sb.toString();
	}

}
