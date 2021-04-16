package dfsdd;

import java.util.Calendar;

public class MakeCal {

	// 캘린더 배열 리턴
	public String[][] getNow(int year, int month) {

		Calendar c = Calendar.getInstance();

		// 만약 1월을 입력하면 전년도 12월을 출력해야함 > 그러므로 만약 month가 0이면 year을 빼고 month도 12월로 변화
		if (month == 0) {
			year--;
			month = 12;
		} else if (month == 13) {
			year++;
			month = 1;
		} 

		c.set(year, month - 1, 1);

		int week = c.get(Calendar.DAY_OF_WEEK);
		// 1일이 시작되는 요일
		int lastDay = c.getActualMaximum(Calendar.DATE);

		// 캘린더 만들어서 받아오기
		String[][] resultArr = makeCal(week, lastDay, year, month);

		return resultArr;
	}

	// 실제로 캘린더를 만드는 곳
	public String[][] makeCal(int week, int lastDay, int year, int month) {

		String[][] calArr = new String[8][7]; // 년월 요일 날짜 이렇게 출력할라고 8에 7

		String[] dayarr = { "일", "월", "화", "수", "목", "금", "토" };

		// 배열 공백으로 초기화
		for (int i = 0; i < calArr.length; i++) {
			for (int j = 0; j < calArr[0].length; j++) {
				calArr[i][j] = " ";
			}
		}

		for (int i = 1; i < 2; i++) {
			for (int j = 0; j < calArr[0].length; j++) {
				if (j == 2) {
					calArr[i - 1][j] = Integer.toString(year); // [0][n]에 출력할 연도 추가
				} else if (j == 3) {
					calArr[i - 1][j] = Integer.toString(month); // [0][n]에 출력할 월 추가
				}
				calArr[i][j] = dayarr[j]; // 요일을 [1][n]에 추가
			}
		}

		int date = 1;
		int startPoint = 0;

		loop1: for (int i = 2; i < calArr.length; i++) {
			// i가 2부터 시작하는 이유 = 0에는 연월, 1에는 요일이 들어갔기 때문에
			for (int j = 0; j < calArr[0].length; j++) {

				if (startPoint == 0) {
					j = week - 1; // 1일이 무슨 요일인지 확인하고 j를 아예 거기부터 시작
					startPoint = 1; // 그리고 여기서 startpoint가 1이 되기 때문에 다음부턴 이 분기문을 안타는거
				}

				if (date > lastDay) {
					break loop1;
				}

				calArr[i][j] = Integer.toString(date);
				date++;
			}
		}
		return calArr;
	}

	// 3개의 배열 하나로 합쳐서 리턴
	public String[][] combineResult(String[][] firstArr, String[][] secondArr, String[][] thirdArr) {

		String[][] resultArr = new String[8][23];

		// 배열 공백으로 초기화
		for (int i = 0; i < resultArr.length; i++) {
			for (int j = 0; j < resultArr[0].length; j++) {
				resultArr[i][j] = " ";
			}
		}

		// 3개의 배열 합치기
		for (int i = 0; i < resultArr.length; i++) {
			for (int j = 0; j < resultArr[0].length; j++) {
				// 연도에는 "년"이라는 글자를 붙이기 위해서
				if (i == 0 && j == 2 || i == 0 && j == 10 || i == 0 && j == 18) {

					if (j == 2) {
						resultArr[i][j] += firstArr[i][j] + "년";
					} else if (j == 10) {
						resultArr[i][j] += secondArr[i][j - 8] + "년";
					} else {
						resultArr[i][j] += thirdArr[i][j - 16] + "년";
					}

					// 월에는 "월"이라는 글자를 붙이기 위해서
				} else if (i == 0 && j == 3 || i == 0 && j == 11 || i == 0 && j == 19) {
					if (j == 3) {
						resultArr[i][j] += firstArr[i][j] + "월";
					} else if (j == 11) {
						resultArr[i][j] += secondArr[i][j - 8] + "월";
					} else {
						resultArr[i][j] += thirdArr[i][j - 16] + "월";
					}

				} else {
					// j, j-8, j-16 > 3개의 배열을 합치는 것이기 때문에 각 배열에는 7까지 밖에 없어서
					if (j >= 0 && j < 7) {
						resultArr[i][j] += firstArr[i][j];
					} else if (j == 7 || j == 15) {
						resultArr[i][j] += " ";
					} else if (j >= 8 && j <= 14) {
						resultArr[i][j] += secondArr[i][j - 8];
					} else {
						resultArr[i][j] += thirdArr[i][j - 16];
					}
				}
			}
		}

		return resultArr;
	}
}