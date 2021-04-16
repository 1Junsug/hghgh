package dfsdd;

import java.util.Calendar;
import java.util.Scanner;

public class CalCode {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.println("년도 입력: ");
		int year = scan.nextInt();

		System.out.println("월 입력: ");
		int month = scan.nextInt();

		if (month < 0 || month > 12) {

			System.out.println("잘못 입력하셨습니다.");

		} else {

			MakeCal mc = new MakeCal();

			// 저번달 배열 받아오기
			String[][] firstArr = mc.getNow(year, month - 1);
			// 입력한 달 배열 받아오기
			String[][] secondArr = mc.getNow(year, month);
			// 다음달 배열 받아오기
			String[][] thirdArr = mc.getNow(year, month + 1);

			// 그리고 그 세개의 배열로 하나의 큰 배열 받아오기
			String[][] resultArr = mc.combineResult(firstArr, secondArr, thirdArr);

			System.out.println();

			// 전체 출력
			for (int i = 0; i < resultArr.length; i++) {
				for (int j = 0; j < resultArr[0].length; j++) {
					if (i == 0 && j == 3 || i == 0 && j == 11 || i == 0 && j == 19) {
						// ****년 **월 출력할때 공백
						System.out.print("\t" + resultArr[i][j]);
					} else {
						System.out.print(resultArr[i][j] + "\t");
					}
				}
				System.out.println();
			}

		}

	}
 

}