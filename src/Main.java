import java.util.Scanner;

public class Main {
    static Tower tower;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Height: ");
        int height = scanner.nextInt();

        tower = new Tower(height);
        System.out.println(tower);
        tower.solve();

    }
}