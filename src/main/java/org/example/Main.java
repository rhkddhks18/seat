package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numRows = 7;   // 행
        int numCols = 10;  // 열

        char[][] seats = new char[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
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
                System.out.print("열 번호를 선택하세요 (1-" + numCols + ") 또는 '0'을 입력하여 종료: ");
                colChoice = sc.nextInt();
                if (colChoice == 0) {
                    break;
                }

                System.out.print("행 번호를 선택하세요 (A-" + (char)('A' + numRows - 1) + "): ");
                rowChoice = sc.next().charAt(0);

                if (colChoice < 1 || colChoice > numCols || rowChoice < 'A' || rowChoice > (char)('A' + numRows - 1)) {
                    System.out.println("잘못된 좌석을 선택하셨습니다. 다시 선택하세요.");
                    continue;
                }

                int rowIndex = rowChoice - 'A';
                if (seats[rowIndex][colChoice - 1] == 'X') {
                    System.out.println("이미 예매된 좌석입니다. 다른 좌석을 선택하세요.");
                    continue;
                }

                seats[rowIndex][colChoice - 1] = 'X';
                System.out.println("좌석이 성공적으로 예매되었습니다.");
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 숫자 또는 알파벳을 다시 입력하세요.");
                sc.nextLine();
            }
        }

        System.out.println("좌석 선택을 취소합니다.");
        sc.close();
    }

    public static void displaySeat(char[][] seats) {
        System.out.print("  ");
        for (int col = 1; col <= seats[0].length; col++) {
            System.out.print(" " + col + "  ");
        }
        System.out.println();

        for (int i = 0; i < seats.length; i++) {
            System.out.print((char)('A' + i) + " ");
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