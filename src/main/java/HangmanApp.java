// Допущения:
// без ООП

public class HangmanApp {

    public static int mistakeCount = 0;
    public static int lifeCount = 6;

    public static void main(String[] args) {
        printHangman();
    }
    // Игровой процесс:
    // рандомно выбирается из dictionary.txt и выводится на консоль слово под маской (*****)
    // печатается сообщение - Выберите букву и игрок вводит букву
    // далее выводится сообщение о кол-ве ошибок/жизней и хангман
    // цикл повторяется до тех пор, пока не будет угадано полностью слово или полностью хангман

    // TODO: сначала пишем структуры функций, без деталей кода

    //ф-ция gameLoop() - в ней будет повторятся цикл, пока игра не закончилась
    public static void gameLoop() {
        // while (gameNotOver)
        //  * вывод загаданного слова (маска, буквы)
        //  * playerTurn: запрашиваем у человека ввод из консоли, проверяем его (д/б быть введена одна буква, не числа, не знаки)
        //  * checkGameState (player_WON, player_LOSS, GAME_NOT_OVER)
        //  * вывод счета и хангмана -> next round or game over
    }

    public static void makePlayerTurn() {
        // get input
        // validate input
        // открывает букву/-ы или выводит сообщение, что такой буквы нет
    }

    public static void checkGameState() {
        // Что такое победа? Когда в маске не осталось ни одной *, т.е. все буквы открыты Игроком, при этом
        // количество допущенных ошибок mistakeCount <= 6 и осталась хотя бы одна жизнь lifeCount >= 0 -> player_WON
        // mistakeCount > 6, lifeCount < 0, остались * в маске слова -> player_LOSS

        // Какие проверки делаем:
        // if (mistakeCount <= 6 && lifeCount >= 0 && !word.contains('*')) -> GAME_NOT_OVER
        // if (mistakeCount > 6 && lifeCount < 0 && word.contains('*')) -> player_LOSS
        // if (mistakeCount <= 6 && lifeCount >= 0 && word.contains('*')) -> GAME_NOT_OVER
    }

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




}
