package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int seatRows = 7;   // 행
        int seatCols = 10;  // 열

        char[][] seats = new char[seatRows][seatCols];
        for (int i = 0; i < seatRows; i++) {
            for (int j = 0; j < seatCols; j++) {
                seats[i][j] = 'O';
            }
        }

        System.out.println("좌석 선택을 시작합니다.");

        while (true) {
            System.out.println("현재 좌석 상태:");
            displaySeat(seats);

            int colChoice;
            char rowChoice;

            try {
                System.out.print("열 번호를 선택하세요 (1-" + seatCols + ") 또는 '0'을 입력하여 종료: ");
                colChoice = sc.nextInt();
                if (colChoice == 0) {
                    System.out.println("좌석 선택을 취소합니다.");
                    break;
                }

                System.out.print("행 번호를 선택하세요 (A-" + (char) ('A' + seatRows - 1) + "): ");
                rowChoice = sc.next().charAt(0);

                if (colChoice < 1 || colChoice > seatCols || rowChoice < 'A' || rowChoice > (char) ('A' + seatRows - 1)) {
                    System.out.println("잘못된 좌석을 선택하셨습니다. 다시 선택하세요.");
                    continue;
                }

                int rowIndex = rowChoice - 'A';
                if (seats[rowIndex][colChoice - 1] == 'X') {
                    System.out.println("이미 예매된 좌석입니다. 다른 좌석을 선택하세요.");
                    continue;
                }

                seats[rowIndex][colChoice - 1] = 'X';
                displaySeat(seats);
                System.out.printf("좌석이 예매되었습니다.");
                break;
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 숫자 또는 알파벳을 다시 입력하세요.");
                sc.nextLine();
            }
        }
        sc.close();
    }

    public static void displaySeat(char[][] seats) {
        for (int i = 1; i <= seats[0].length; i++) {
            System.out.printf("%4d", i);
        }
        System.out.println();

        for (int i = 0; i < seats.length; i++) {
            System.out.print((char) ('A' + i) + " ");

            for (char c : seats[i]) {
                if (c == 'O') {
                    System.out.print("[ ] ");
                } else if (c == 'X') {
                    System.out.print("[X] ");
                }
            }
            System.out.println();
        }
    }
}