package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        // declaration of the cinema array
        char[][] cinema = new char[rows][seats];

        // declaration of the statistics of the cinema
        // {totalSeats, soldSeats, currentIncome, totalIncome}
        int[] stats = {rows * seats, 0, 0, calculateTotalIncome(rows, seats)};

        // initialization of cinema array
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinema[i][j] = 'S';
            }
        }

        while (true) {
            int choice = menu();

            if (choice == 1) {
                showSeats(cinema);
            } else if (choice == 2) {
                buyTicket(cinema, stats);
            } else if (choice == 3) {
                displayStats(stats);
            } else if (choice == 0) {
                break;
            }
        }
    }

    public static int menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        return scanner.nextInt();
    }

    public static void showSeats(char[][] array) {
        int rows = array.length;
        int seats = array[0].length;
        // drawing the cinema:
        System.out.println();
        System.out.println("Cinema:");
        for (int i = 0; i <= rows; i ++) {
            if (i == 0) {
                System.out.print("  ");
                for (int j = 1; j <= seats; j++) {
                    System.out.print(j + " ");
                }
                System.out.println();
            } else {
                System.out.print(i + " ");
                for (int j = 1; j <= seats; j++) {
                    System.out.print(array[i - 1][j - 1] + " ");
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    public static void buyTicket(char[][] array, int[] stats) {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        int rows = array.length;
        int seats = array[0].length;

        while (true) {
            System.out.println("Enter a row number:");
            int row = scanner.nextInt();

            System.out.println("Enter a seat number in that row:");
            int seat = scanner.nextInt();

            if (row > rows || seat > seats) {
                System.out.println("Wrong input!");
                continue;
            }

            if (array[row - 1][seat - 1] == 'B') {
                System.out.println("That ticket has already been purchased!");
                //continue;
            } else {
                int totalSeats = rows * seats;
                int ticketPrice;

                if (totalSeats > 60) {
                    ticketPrice = (rows / 2 < row) ? 8 : 10;
                    //ticketPrice = (rows / 2) * seats * 10 + (rows - rows / 2) * seats * 8;
                } else {
                    ticketPrice = 10;
                }
                System.out.println("Ticket price: $" + ticketPrice);
                System.out.println();

                array[row - 1][seat - 1] = 'B';
                stats[1] += 1;
                stats[2] += ticketPrice;
                break;
            }
        }
    }

    public static void displayStats(int[] array) {
        // {totalSeats, soldSeats, currentIncome, totalIncome}
        int totalSeats = array[0];
        int soldSeats = array[1];
        int currentIncome = array[2];
        int totalIncome = array[3];

        System.out.printf("Number of purchased tickets: %d\n", soldSeats);
        System.out.printf("Percentage: %.2f%%\n", (float) 100 * soldSeats / totalSeats);
        System.out.printf("Current income: $%d\n", currentIncome);
        System.out.printf("Total income: $%d\n", totalIncome);
        System.out.println();
    }

    public static int calculateTotalIncome(int rows, int seats) {
        int totalSeats = rows * seats;

        if (totalSeats > 60) {
            //ticketPrice = (rows / 2 < row) ? 8 : 10;
            return  (rows / 2) * seats * 10 + (rows - rows / 2) * seats * 8;
        } else {
            return rows * seats * 10;
        }
    }
}