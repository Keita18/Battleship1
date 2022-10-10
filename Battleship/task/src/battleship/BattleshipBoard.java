package battleship;

public class BattleshipBoard {
    public final char[][] board = new char[10][10];
    private Move move;

    public BattleshipBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = '~';
            }
        }
    }

    protected boolean setShip(String from, String to, int length, String shipName) {
        if (!checkCoordinates(from, to, length, shipName))
            return false;
        for (int i = move.fromY; i <= move.toY; i++) {
            for (int j = move.fromX; j <= move.toX; j++) {
                board[i][j] = 'O';
            }
        }
        return true;
    }

    protected boolean checkCoordinates(String from, String to, int length, String shipName) {
        try {
            int fromY = from.charAt(0) - 65;
            int fromX = Integer.parseInt(from.substring(1)) - 1;
            int toY = to.charAt(0) - 65;
            int toX = Integer.parseInt(to.substring(1)) - 1;
//            System.out.printf("%s--%s\n%s--%s\n%s--%s\n",from, to, fromY, toY, fromX, toX);
            int minY = Integer.min(fromY, toY);
            int maxY = Integer.max(fromY, toY);
            int minX = Integer.min(fromX, toX);
            int maxX = Integer.max(fromX, toX);


            if (fromY > 9 || toY > 9 || fromX > 9 || toX > 9)
                throw new IllegalArgumentException();
            if (fromY != toY && fromX != toX) {
                System.out.print("Error! Wrong ship location! Try again:");
                return false;
            }
            int correctLength = minX == maxX ? maxY - minY : maxX - minX;
            if (correctLength + 1 != length) {
                System.out.println("Error! Wrong length of the " + shipName + "! Try again:");
                return false;
            }

            move = new Move(minX, maxX, minY, maxY);

            if (minY > 0) {
                minY--;
            }
            if (minX > 0) {
                minX--;
            }
            if (maxX < 9) {
                maxX++;
            }
            if (maxY < 9) {
                maxY++;
            }

            for (int i = minY; i <= maxY; i++) {
                for (int j = minX; j <= maxX; j++) {
                    if (board[i][j] != '~') {
                        System.out.println("Error! You placed it too close to another one. Try again:\n");
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error wrong coordinates" + e);
            return false;
        }
        return true;
    }

    protected void printGame() {
        System.out.print("  1 2 3 4 5 6 7 8 9 10");
        var letter = 'A';
        for (int i = 0; i < 10; i++) {
            System.out.print("\n" + letter);
            for (int j = 0; j < 10; j++) {
                var value = board[i][j];
                System.out.print(" " + value);
            }
            letter++;
        }
        System.out.println();
    }

    private static class Move {
        int fromX;
        int toX;
        int fromY;
        int toY;

        Move(int fromX, int toX, int fromY, int toY) {
            this.fromX = fromX;
            this.toX = toX;
            this.fromY = fromY;
            this.toY = toY;
        }
    }
}
