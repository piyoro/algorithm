package algorithm.programmers.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * <pre>
 * 프로그래머스 코딩테스트 연습을 위한 코드
 * 해당 사이트 와 테스트 편의상 기본 jre 외의 라이브러리는 사용하지 않는다.
 * 
 * Hash 그룹 2번째 문제
 * 
 * 전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 확인하려 합니다.
 * 전화번호가 다음과 같을 경우, 구조대 전화번호는 영석이의 전화번호의 접두사입니다.
 * 
 * 구조대 : 119
 * 박준영 : 97 674 223
 * 지영석 : 11 9552 4421
 * 전화번호부에 적힌 전화번호를 담은 배열 phone_book 이 solution 함수의 매개변수로 주어질 때, 어떤 번호가 다른 번호의 접두어인 경우가 있으면 false를 
 * 그렇지 않으면 true를 return 하도록 solution 함수를 작성해주세요.
 * 
 * 제한 사항
 * phone_book의 길이는 1 이상 1,000,000 이하입니다.
 * 각 전화번호의 길이는 1 이상 20 이하입니다.
 * </pre>
 * 
 * @author piyor
 */
public class Solution_Hash02 {
	public static void main(String[] args) {
		Solution_Hash02 solution = new Solution_Hash02();
		// String[] phone_book = new String[] { "0", "01", "99999999", "1", "943", "99", "9991", "08888888", "999", "9438", "9441", "8888" };
		String[] phone_book = new String[] { "0", "99999999", "1", "949", "98", "9991", "8888888", "9993", "9438", "9441" };
		// String[] phone_book = new String[] { "11", "197674223", "1195524421" };
		System.out.println(solution.solution_my_best(phone_book));
	}

	/**
	 * <pre>
	 * 정확성 테스트11 이 처리속도의 문제가 아니라는 점을 뒤늦게 판단한 이후,
	 * 입력받은 파라미터: 문자열 배열을 정렬도 해볼까 싶어 검색해보다가
	 * TreeSet api 중 subSet 메서드를 알게되었고,
	 * 해당 시작문자열 포함되는 문자열의 값을 모두 알 수 있으면
	 * 반복하며 startsWith로 판단하는것이 접두어를 확인할 수 있는 최선이 아닐까 싶어
	 * 프로그래머스 코딩테스트 통과 완료.
	 * 내 선에서 최선이고 다른 사람의 코딩도 확인해봐야겠다.
	 * </pre>
	 * 
	 * @param phone_book
	 * @return
	 */
	public boolean solution_my_best(String[] phone_book) {
		boolean answer = true;

		// 최대 길이
		int maxLeng = 20;

		// 첫번째 자릿수 0~9까지 시작문자열 별 입력받은 수를 set으로 저장하는 map
		Map<String, TreeSet<String>> decimalMap = new HashMap<>();

		// 입력받은 수 트리셋
		TreeSet<String> setByDecimal = null;

		// 파라미터 첫 문자열
		String decimal = null;
		NavigableSet<String> set = null;
		String key = null;
		Iterator<String> decimalKeyIter = null;
		Iterator<String> itemIter = null;
		for (String item : phone_book) {
			decimal = item.substring(0, 1);
			setByDecimal = decimalMap.get(decimal);
			if (setByDecimal == null) {
				setByDecimal = new TreeSet<>();
				decimalMap.put(decimal, setByDecimal);
			}

			setByDecimal.add(item);
		}

		int keyLeng = 0;
		int itemLeng = 0;
		decimalKeyIter = decimalMap.keySet().iterator();

		// 파라미터로 입력받은 요소의 첫번째자리수가 존재하는대로 반복
		while (decimalKeyIter.hasNext()) {
			decimal = decimalKeyIter.next();
			setByDecimal = decimalMap.get(decimal);

			// 시작하는 문자열 + 20자리까지 "9999...." 까지의 문자열 밖에 없으므로 해당 범위의 set을 검색
			set = setByDecimal.subSet(decimal, true, String.format("%s%-" + (maxLeng - 1) + "s", decimal, "9").replace(' ', '9'), true);

			itemIter = setByDecimal.iterator();
			// 첫번째자릿수 별 입력받은 수 반복
			while (itemIter.hasNext()) {
				key = itemIter.next();

				keyLeng = key.length();
				// 첫번째자릿부 별 입력받은 모든수를 반복하며 접두어 체크 처리
				for (String item : set) {
					itemLeng = item.length();
					// 첫번째 반복의 key 값의 길이가 클 경우 key 값을 startsWith 로 판단
					if (keyLeng > itemLeng) {
						if (key.startsWith(item)) {
							System.out.println("1.=>" + item);
							return false;
						}
					}
					// 이중반복의 item 값의 길이가 클 경우 item 값을 startsWith 로 판단
					else if (keyLeng < itemLeng) {
						if (item.startsWith(key)) {
							System.out.println("2.=>" + item);
							return false;
						}
					}
				}
			}
		}

		return answer;
	}

	/**
	 * <pre>
	 * 현재 소스상에 없는 처리과정 중에 
	 * 정확성 테스트 11. 통과는 이유를 발견했다.
	 * 주석으로 정리해둔다는것이... 내용 자체를 잊어버렷지만...
	 * 테스트 케이스 중 문자열의 길이가 짧게 들어왔다가 길게 들어왔다가 하면서
	 * 접두어 판단을 정상적으로 처리하지 못한것으로 기억한다.
	 * 
	 * 접두어 존재여부의 정확한 확인을 위해서 첫 시작 문자열 0~9 까지의 자릿수를 설정하고,
	 * 최종 파라미터로 받은 배열 아이템을 반복하면서 해당 시작 문자열에 포함되는 자릿수가
	 * 현재 길이보다 적은 값이 존재할 경우 접두어 여부를 확인하는...
	 * 그런 처리를 하고 싶었는데, 소스상에 존재하지 않는 과정등에 생각보다 복잡해지고 처리가 안되는
	 * 부분이 있어서 의미없는 주석 / 기능자체동작하지 않는 소스이지만,
	 * 기록상 남겨둔다.
	 * </pre>
	 * 
	 * @param phone_book
	 * @return
	 */
	public boolean solution_04(String[] phone_book) {
		boolean answer = true;

		int maxLeng = 20; // 최대 자릿수

		// 첫번째 자릿수 0~9까지 자릿수별 존재 목록 map
		Map<String, Map<String, Integer>> mapRealByDecimal = new HashMap<>();

		Map<String, Integer> mapReal = null;

		// 첫번째 자릿수 0~9까지 자릿수별 허수 목록 map
		Map<String, Map<String, Integer>> mapXXXByDecimal = new HashMap<>();

		Map<String, Integer> mapXXX = null;

		// 첫번째 자릿수 0~9까지 자릿수별 셋 map
		Map<String, Set<Integer>> lengthMap = new HashMap<>();

		// 자리숫별 트리셋
		Set<Integer> setByDecimal = null;

		// 1. 각 첫번째 자릿수별 문자열 길이값을 구해서 저장한다.
		int myLeng = 0;
		int tmpLeng = 0;
		String decimal = null;
		String key = null;

		Iterator<Integer> lengIter = null;
		for (String item : phone_book) {
			myLeng = item.length();
			decimal = item.substring(0, 1);

			setByDecimal = lengthMap.get(decimal);
			if (setByDecimal == null) {
				setByDecimal = new TreeSet<>();
				lengthMap.put(decimal, setByDecimal);
			}
			mapReal = mapRealByDecimal.get(decimal);
			if (mapReal == null) {
				mapReal = new HashMap<>();
				mapRealByDecimal.put(decimal, mapReal);
			}
			mapXXX = mapXXXByDecimal.get(decimal);
			if (mapXXX == null) {
				mapXXX = new HashMap<>();
				mapXXXByDecimal.put(decimal, mapXXX);
			}

			// 아이템 첫번째 자리에 해당하는 자릿수별 접두어 존재 확인
			lengIter = setByDecimal.iterator();
			while (lengIter.hasNext()) {
				tmpLeng = lengIter.next();

				// 2 4 8
				// 2 4 5 8
				// 7
				if (tmpLeng > myLeng) {
					key = String.format("%-" + tmpLeng + "s", item).replace(' ', 'X');
				} else if (tmpLeng < myLeng) {
					key = item.substring(0, tmpLeng);
				}
			}

			setByDecimal.add(myLeng);
		}

		return answer;
	}

	/**
	 * <pre>
	 * 프로그래머스를 처음 접하고 어디선가 본 포스팅에 처리속도에 관련된 글을 보고,
	 * 정확성 테스트11 을 통과 못하는 부분이 처리속도라고 판단하여 처리 속도를 조금 늦추더라도
	 * 적당한 정렬과 나름의 기준으로 처리한 소스.
	 * 여전히 정확성 테스트 11. 마지막 단계를 여전히 통과 못한다.
	 * </pre>
	 * 
	 * @param phone_book
	 * @return
	 */
	public boolean solution_03(String[] phone_book) {
		boolean answer = true;
		Arrays.sort(phone_book);
		System.out.println(phone_book[0]);
		int leng = phone_book[0].length();
		Set<Integer> set = new HashSet<Integer>();
		int key = 0;
		for (String item : phone_book) {
			leng = Math.min(leng, item.length());
		}

		for (String item : phone_book) {
			key = Integer.parseInt(item.substring(0, leng));
			set.add(key);
		}

		Set<Integer> ts = new TreeSet<>(set);
		if (ts.size() != phone_book.length) {
			return false;
		}
		return answer;

	}

	/**
	 * <pre>
	 * 상당히 빠른 속도
	 * 여전히 정확성 테스트 11. 마지막 단계를 여전히 통과 못한다.
	 * </pre>
	 * 
	 * @param phone_book
	 * @return
	 */
	public boolean solution_02(String[] phone_book) {
		boolean answer = true;

		int leng = phone_book[0].length();

		for (String item : phone_book) {
			leng = Math.min(leng, item.length());
		}

		Map<String, Integer> map = new HashMap<>();
		String key = null;
		for (String item : phone_book) {
			key = item.substring(0, leng);
			if (map.putIfAbsent(key, 0) != null) {
				return false;
			}
		}
		return answer;
	}

	/**
	 * <pre>
	 * 프로그래머스 연습문제를 처음 접하고
	 * 막연히 혼자서 구현해본 소스.
	 * 대충 이렇게 하면 되겠지라는 생각이 상당한 착각이었던 것 같다.
	 * 정확성 테스트 11. 을 왜 통과 못하는지 전혀 이해를 못했다.
	 * </pre>
	 * 
	 * @param phone_book
	 * @return
	 */
	public boolean solution_01(String[] phone_book) {
		boolean answer = true;

		int leng = phone_book[0].length();
		for (String item : phone_book) {
			leng = Math.min(leng, item.length());
		}

		Set<Integer> set = new HashSet<Integer>();
		int key = 0;
		for (String item : phone_book) {
			key = Integer.parseInt(item.substring(0, leng));
			if (set.contains(key)) {
				System.out.println(key);
				return false;
			}
			set.add(key);
		}
		return answer;
	}

}
