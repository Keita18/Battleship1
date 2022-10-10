package battleship;

import java.util.Scanner;

enum Ship {
    Aircraft_Carrier(5),
    Battleship(4),
    Submarine(3),
    Cruiser(3),
    Destroyer(2);
    int length;

    Ship(int length) {
        this.length = length;
    }
    void print() {
        if (this == Ship.Aircraft_Carrier)
            System.out.printf("Enter the coordinates of the %s (%d cells):\n", "Aircraft Carrier", length);
        else System.out.printf("Enter the coordinates of the %s (%d cells):\n", this.name(), length);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BattleshipBoard battleship = new BattleshipBoard();
        battleship.printGame();
        boolean stop;
        for (Ship ship : Ship.values()) {
            do {
                ship.print();
                var value = scanner.nextLine().split(" ");
                stop = battleship.setShip(value[0], value[1], ship.length, ship.name());
                System.out.println(stop);
            } while (!stop);
            battleship.printGame();
        }
    }
}
