import java.util.Random;
import java.util.Scanner;

    public class MineSweeper {
        private int[][] board;
        private boolean[][] revealed;
        private int rows;
        private int cols;
        private int mineCount;
        private boolean gameover;

        public MineSweeper(int rows, int cols) {
            this.rows = rows;
            this.cols = cols;
            this.board = new int[rows][cols];
            this.revealed = new boolean[rows][cols];
            this.mineCount = rows * cols / 4;
            this.gameover = false;
            initializeBoard();
            initializeMines();
        }


        private void initializeBoard() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    board[i][j] = 0;
                    revealed[i][j] = false;
                }
            }
        }

        private void initializeMines() {
            int count = 0;
            Random rand = new Random();

            while (count < mineCount) {
                int randRow = rand.nextInt(rows);
                int randCol = rand.nextInt(cols);

                if (board[randRow][randCol] != -1) {
                    board[randRow][randCol] = -1;
                    count++;
                }
            }
        }

        private boolean isValidMove(int row, int col) {
            return row >= 0 && row < rows && col >= 0 && col < cols && !revealed[row][col];
        }

        private void checkAndReveal(int row, int col) {
            if (board[row][col] == -1) {
                gameover = true;
                return;
            }

            int count = 0;
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = col - 1; j <= col + 1; j++) {
                    if (i >= 0 && i < rows && j >= 0 && j < cols && board[i][j] == -1) {
                        count++;
                    }
                }
            }

            board[row][col] = count;
            revealed[row][col] = true;

            if (count == 0) {
                for (int i = row - 1; i <= row + 1; i++) {
                    for (int j = col - 1; j <= col + 1; j++) {
                        if (i >= 0 && i < rows && j >= 0 && j < cols && !revealed[i][j]) {
                            checkAndReveal(i, j);
                        }
                    }
                }
            }
        }

        public void play() {
            while (!gameover) {
                displayBoard();
                int row, col;
                do {
                    System.out.print("Satır Giriniz: ");
                    row = new Scanner(System.in).nextInt();
                    System.out.print("Sütun Giriniz: ");
                    col = new Scanner(System.in).nextInt();
                } while (!isValidMove(row, col));

                checkAndReveal(row, col);
            }

            displayBoard();
            if (gameover) {
                System.out.println("Game Over!!");
            } else {
                System.out.println("Oyunu Kazandınız !");
            }
        }

        private void displayBoard() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (revealed[i][j]) {
                        if (board[i][j] == -1) {
                            System.out.print("* ");
                        } else {
                            System.out.print(board[i][j] + " ");
                        }
                    } else {
                        System.out.print("- ");
                    }
                }
                System.out.println();
            }
        }
    }

