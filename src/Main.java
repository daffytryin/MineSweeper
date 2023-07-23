import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Mayın Tarlası Oyuna Hoşgeldiniz!");

        System.out.print("Satır Sayısı Giriniz: ");
        int rows = scanner.nextInt();

        System.out.print("Sütun Sayısı Giriniz: ");
        int cols = scanner.nextInt();


        MineSweeper mineSweeper = new MineSweeper(rows, cols);
        mineSweeper.play();
        scanner.close();
    }
}
