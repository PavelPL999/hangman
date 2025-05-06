public class HangmanApp {

    public static int mistakeCount = 6;
    public static int lifeCount = 0;

    public static void printHangman() {
        if (mistakeCount == 0 && lifeCount == 6) {
            System.out.println("Допущено ошибок: " + mistakeCount + ". Осталось жизней: " + lifeCount);
        }
        if (mistakeCount == 1 && lifeCount == 5) {
            System.out.println("""
                     _
                     |
                     |
                     |
                     |
                     |
                    _|_
                    """);
            System.out.println("Допущено ошибок: " + mistakeCount + ". Осталось жизней: " + lifeCount);
        }
        if (mistakeCount == 2 && lifeCount == 4) {
            System.out.println("""
                     ________
                     |      |
                     |
                     |
                     |
                     |
                    _|_
                    """);
            System.out.println("Допущено ошибок: " + mistakeCount + ". Осталось жизней: " + lifeCount);
        }
        if (mistakeCount == 3 && lifeCount == 3) {
            System.out.println("""
                     ________
                     |      |
                     |      o
                     |
                     |
                     |
                    _|_
                    """);
            System.out.println("Допущено ошибок: " + mistakeCount + ". Осталось жизней: " + lifeCount);
        }
        if (mistakeCount == 4 && lifeCount == 2) {
            System.out.println("""
                     ________
                     |      |
                     |      o
                     |      |
                     |      |
                     |
                    _|_
                    """);
            System.out.println("Допущено ошибок: " + mistakeCount + ". Осталось жизней: " + lifeCount);
        }
        if (mistakeCount == 5 && lifeCount == 1) {
            System.out.println("""
                     ________
                     |      |
                     |      o
                     |     \\|/
                     |      |
                     |
                    _|_
                    """);
            System.out.println("Допущено ошибок: " + mistakeCount + ". Осталось жизней: " + lifeCount);
        }
        if (mistakeCount == 6 && lifeCount == 0) {
            System.out.println("""
                     ________
                     |      |
                     |      o
                     |     \\|/
                     |     / \\
                    _|_
                    """);
            System.out.println("Допущено ошибок: " + mistakeCount + ". Осталось жизней: " + lifeCount);
            System.out.println("Попробуйте сыграть еще раз!");
        }

    }

    public static void main(String[] args) {
        printHangman();
    }


}
