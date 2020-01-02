package algorithm.programmers.hash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 프로그래머스 코딩테스트 연습을 위한 코드
 * 해당 사이트 와 테스트 편의상 기본 jre 외의 라이브러리는 사용하지 않는다.
 * 
 * Hash 그룹 4번째 문제
 * 
 * 스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다. 
 * 노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같습니다.

 * 속한 노래가 많이 재생된 장르를 먼저 수록합니다.
 * 장르 내에서 많이 재생된 노래를 먼저 수록합니다.
 * 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
 * 노래의 장르를 나타내는 문자열 배열 genres와 노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때, 
 * 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.
 * 
 * 제한사항
 * genres[i]는 고유번호가 i인 노래의 장르입니다.
 * plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
 * genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
 * 장르 종류는 100개 미만입니다.
 * 장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
 * 모든 장르는 재생된 횟수가 다릅니다.
 * </pre>
 * 
 * @author piyor
 */
public class Solution_Hash04 {

	public static void main(String[] args) {
		Solution_Hash04 solution = new Solution_Hash04();

		String[] genres = new String[] {

				"classic", "pop", "classic", "classic", "pop"

		};
		int[] plays = new int[] {

				500, 600, 150, 800, 2500

		};

		int[] answer = solution.solution_my_best(genres, plays);
		System.out.println(String.format("answer final %s", answer));
	}

	/**
	 * <pre>
	 * int[] 반환을 Integer[] 로 바꾸고, 마지막 return 리스트의 toArray로 Integer 변형정도
	 * 단순화하려 했는데, 안된다. 테스트 프로그램의 한계인가보다.
	 * 정리한 라인수가 얼마 안된다.
	 * 내가 해결한 방법이 얼마나 주먹구구 식인지 잘알기에 다른 사람의 솔루션을 꼭 확인해본다.
	 * </pre>
	 * 
	 * @param clothes
	 * @return
	 */
	public int[] solution_my_best(String[] genres, int[] plays) {

		int[] answer = {};

		// 장르별 재생횟수 누적 맵
		Map<String, Integer> genresMap = new HashMap<>();
		// 장르별 노래 고유번호 정보를 담고 있는 맵
		Map<String, List<Integer>> genresIndexes = new HashMap<>();
		// 노래 고유번호별 재생횟수를 담고 있는 맵
		Map<Integer, Integer> playMap = new HashMap<>();
		// 장르별 노래 고유번호를 담는 리스트
		List<Integer> indexes = null;

		// genres/plays 의 배열의 크기는 무조건 동일하다 가정
		int loopCnt = genres.length;
		String genre = null;
		int play = -1;
		int sum_play = -1;
		for (int idx01 = 0; idx01 < loopCnt; idx01++) {
			genre = genres[idx01];
			play = plays[idx01];
			sum_play = genresMap.getOrDefault(genre, 0);
			sum_play += play;
			genresMap.put(genre, sum_play);

			indexes = genresIndexes.getOrDefault(genre, new ArrayList<Integer>());

			// 장르별 노래 고유번호 추가
			indexes.add(idx01);

			// 노래 고유번호별 재생횟수를 담기 때문에 중복/생성 여부 체크는 안한다.
			playMap.put(idx01, play);
			genresIndexes.put(genre, indexes);

		}
		// 장르의 순서부터 구하기
		List<Map.Entry<String, Integer>> genreSumlist = new LinkedList<>(genresMap.entrySet());

		Collections.sort(genreSumlist, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				int comparision = (o1.getValue() - o2.getValue()) * -1;
				return comparision == 0 ? o1.getKey().compareTo(o2.getKey()) : comparision;
			}
		});

		Map<String, Integer> genreSortedMap = new LinkedHashMap<>();
		for (Iterator<Map.Entry<String, Integer>> genreSortIter = genreSumlist.iterator(); genreSortIter.hasNext();) {
			Map.Entry<String, Integer> entry = genreSortIter.next();
			genreSortedMap.put(entry.getKey(), entry.getValue());
		}

		// 노래고유번호별 재생횟수 내림차순 정렬
		List<Map.Entry<Integer, Integer>> playMaplist = new LinkedList<>(playMap.entrySet());
		Collections.sort(playMaplist, new Comparator<Map.Entry<Integer, Integer>>() {
			@Override
			public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
				int comparision = (o1.getValue() - o2.getValue()) * -1;
				return comparision == 0 ? o1.getKey().compareTo(o2.getKey()) : comparision;
			}
		});

		Map<Integer, Integer> playSortedMap = new LinkedHashMap<>();
		for (Iterator<Map.Entry<Integer, Integer>> playSortIter = playMaplist.iterator(); playSortIter.hasNext();) {
			Map.Entry<Integer, Integer> entry = playSortIter.next();
			playSortedMap.put(entry.getKey(), entry.getValue());
		}

		Integer tmpIdx = null;
		// 장르에 따라 정렬된 인덱스를 구함

		// 정렬된 장르로,
		Iterator<String> genreKeyIter = genreSortedMap.keySet().iterator();
		String tmpKey = null;
		List<Integer> indexesListByGenre = null;
		Iterator<Integer> playKeyIter = null;

		List<Integer> sumArray = new LinkedList<>();
		List<Integer> genreArray = null;
		while (genreKeyIter.hasNext()) {
			playKeyIter = playSortedMap.keySet().iterator();
			genreArray = new LinkedList<>();
			tmpKey = genreKeyIter.next();
			// 맵의 정렬을 확인
			// 장르별 인덱스 목록 조회
			// System.out.println(tmpKey + " : " + genreSortedMap.get(tmpKey));
			indexesListByGenre = genresIndexes.get(tmpKey);
			System.out.println(tmpKey + " : " + indexesListByGenre);
			// 정렬된 인덱스 반복하며 장르별 인덱스 목록 조회여부 확인
			while (playKeyIter.hasNext()) {
				tmpIdx = playKeyIter.next();
				System.out.println("->" + tmpIdx);
				// 맵의 정렬을 확인
				if (indexesListByGenre.contains(tmpIdx)) {
					System.out.println("->" + tmpIdx + " : ");
					genreArray.add(tmpIdx);
					if (genreArray.size() < 3) {
						sumArray.add(tmpIdx);
					}
				}
			}
		}

		// 최종 반환 배열 정리
		System.out.println(sumArray);
		int rtnCnt = sumArray.size();
		answer = new int[rtnCnt];
		for (int i = 0; i < rtnCnt; i++) {
			answer[i] = sumArray.get(i);
		}
		return answer;
	}

	/**
	 * <pre>
	 * 잠시 머릿속으로 정리를 하고, 메서드 하나로 해결하려했기 때문에 정리 없이 쭉 이어나갔다.
	 * 적당히 하고 무조건 다른 사람의 솔루션을 확인해봐야지 했는데,
	 * 테스트는 한번에 통과됐다. 동일한 재생횟수일때 작은 고유번호 순으로 정렬한다는 로직 넣지도 않았는데...
	 * 소스만 간단히 정리해야겠다.
	 * </pre>
	 * 
	 * @param clothes
	 * @return
	 */
	public int[] solution(String[] genres, int[] plays) {
		int[] answer = {};

		// 장르별 재생횟수 누적 맵
		Map<String, Integer> genresMap = new HashMap<>();
		// 장르별 노래 고유번호 정보를 담고 있는 맵
		Map<String, List<Integer>> genresIndexes = new HashMap<>();
		// 장르별 노래고유번호 재생횟수 맵을 담고 있는 맵
		Map<String, Map<Integer, Integer>> totalMap = new HashMap<>();
		// 노래 고유번호별 재생횟수를 담고 있는 맵
		Map<Integer, Integer> playMap = new HashMap<>();
		// 노래 고유번호별 재생횟수를 담고 있는 맵
		Map<Integer, Integer> totalSubMap = null;
		// 장르별 노래 재생횟수를 담는 리스트
		List<Integer> playCnts = null;
		// 장르별 노래 고유번호를 담는 리스트
		List<Integer> indexes = null;

		// genres/plays 의 배열의 크기는 무조건 동일하다 가정
		int loopCnt = genres.length;
		String genre = null;
		int play = -1;
		int sum_play = -1;
		for (int idx01 = 0; idx01 < loopCnt; idx01++) {
			genre = genres[idx01];
			play = plays[idx01];
			sum_play = genresMap.getOrDefault(genre, 0);
			sum_play += play;
			genresMap.put(genre, sum_play);

			indexes = genresIndexes.getOrDefault(genre, new ArrayList<Integer>());
			totalSubMap = totalMap.getOrDefault(genre, new HashMap<>());

			// 장르별 재생횟수 리스트에 재생횟수 추가
			// playCnts.add(play);

			// 장르별 노래 고유번호 추가
			indexes.add(idx01);

			// 노래 고유번호별 재생횟수를 담기 때문에 중복/생성 여부 체크는 안한다.
			// System.out.println(idx01 + " : " + play);
			playMap.put(idx01, play);
			totalSubMap.put(idx01, play);
			genresIndexes.put(genre, indexes);

		}
		// 장르의 순서부터 구하기
		List<Map.Entry<String, Integer>> genreSumlist = new LinkedList<>(genresMap.entrySet());

		Collections.sort(genreSumlist, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				int comparision = (o1.getValue() - o2.getValue()) * -1;
				return comparision == 0 ? o1.getKey().compareTo(o2.getKey()) : comparision;
			}
		});

		// 리스트의 정렬을 확인
		/*
		for (Map.Entry<String, Integer> item : genreSumlist) {
			System.out.println(item);
		}
		*/

		Map<String, Integer> genreSortedMap = new LinkedHashMap<>();
		for (Iterator<Map.Entry<String, Integer>> genreSortIter = genreSumlist.iterator(); genreSortIter.hasNext();) {
			Map.Entry<String, Integer> entry = genreSortIter.next();
			genreSortedMap.put(entry.getKey(), entry.getValue());
		}

		// 노래고유번호별 재생횟수 내림차순 정렬
		List<Map.Entry<Integer, Integer>> playMaplist = new LinkedList<>(playMap.entrySet());
		Collections.sort(playMaplist, new Comparator<Map.Entry<Integer, Integer>>() {
			@Override
			public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
				int comparision = (o1.getValue() - o2.getValue()) * -1;
				return comparision == 0 ? o1.getKey().compareTo(o2.getKey()) : comparision;
			}
		});

		// 리스트의 정렬을 확인
		/*
		for (Map.Entry<Integer, Integer> item : playMaplist) {
			System.out.println(item);
		}
		*/

		Map<Integer, Integer> playSortedMap = new LinkedHashMap<>();
		for (Iterator<Map.Entry<Integer, Integer>> playSortIter = playMaplist.iterator(); playSortIter.hasNext();) {
			Map.Entry<Integer, Integer> entry = playSortIter.next();
			playSortedMap.put(entry.getKey(), entry.getValue());
		}

		// Iterator<Integer> playKeyIter = playSortedMap.keySet().iterator();
		Integer tmpIdx = null;
		/*
		while (playKeyIter.hasNext()) {
			tmpIdx = playKeyIter.next();
			// 맵의 정렬을 확인
			// System.out.println(tmpIdx + " : " + playSortedMap.get(tmpIdx));
		}
		*/
		// 장르에 따라 정렬된 인덱스를 구함

		// 정렬된 장르로,
		Iterator<String> genreKeyIter = genreSortedMap.keySet().iterator();
		String tmpKey = null;
		List<Integer> indexesListByGenre = null;
		Iterator<Integer> playKeyIter = null;

		List<Integer> sumArray = new LinkedList<>();
		List<Integer> genreArray = null;
		while (genreKeyIter.hasNext()) {
			playKeyIter = playSortedMap.keySet().iterator();
			genreArray = new LinkedList<>();
			tmpKey = genreKeyIter.next();
			// 맵의 정렬을 확인
			// 장르별 인덱스 목록 조회
			// System.out.println(tmpKey + " : " + genreSortedMap.get(tmpKey));
			indexesListByGenre = genresIndexes.get(tmpKey);
			System.out.println(tmpKey + " : " + indexesListByGenre);
			// 정렬된 인덱스 반복하며 장르별 인덱스 목록 조회여부 확인
			while (playKeyIter.hasNext()) {
				tmpIdx = playKeyIter.next();
				System.out.println("->" + tmpIdx);
				// 맵의 정렬을 확인
				if (indexesListByGenre.contains(tmpIdx)) {
					System.out.println("->" + tmpIdx + " : ");
					genreArray.add(tmpIdx);
					if (genreArray.size() < 3) {
						sumArray.add(tmpIdx);
					}
				}
			}
		}

		// 최종 반환 배열 정리
		System.out.println(sumArray);
		int rtnCnt = sumArray.size();
		int[] rtnArr = new int[rtnCnt];
		for (int i = 0; i < rtnCnt; i++) {
			rtnArr[i] = sumArray.get(i);
		}
		return rtnArr;
	}

}
