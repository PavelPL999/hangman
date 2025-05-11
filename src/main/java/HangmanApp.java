import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class HangmanApp {

    private static final int MAX_MISTAKES = 6;
    private static final String GAME_STATE_PLAYER_WON = "Вы победили!";
    private static final String GAME_STATE_PLAYER_LOSS = "Вы проиграли!";
    private static final String GAME_STATE_GAME_NOT_FINISHED = "Игра не закончена!";

    private static int mistakeCount = 0;
    private static String usedLetters = "";

    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) {
        System.out.println("Добро пожаловать на игру Виселица");
        startGameRound();
        System.out.println("Спасибо за игру!");

    }
    // startGameRound() - запускает игровой цикл,
    // после цикла одной игры (одно слово) спрашиваем - Хотите сыграть ещё раз? (д/н)
    // д -> запускает новый startGameLoop();  н -> выходим из startGameRound() и заканчиваем всю игру
    public static void startGameRound() {
        boolean isPlayAgain;
        do {
            startGameLoop();
            System.out.print("Хотите сыграть ещё раз? (д/н): ");
            String answer = scanner.nextLine().trim().toLowerCase();
            isPlayAgain = answer.equals("д") || answer.equals("да");
        } while (isPlayAgain);
    }

    // startGameLoop() — одна игра (одно слово)
    public static void startGameLoop() {
        // Получаем случайное слово
        String word = getRandomWord();

        if (word == null) {
            System.out.println("Словарь пуст или не удалось загрузить слово.");
            return;
        }

        char[] currentGuess = resetGameState(word.length());

        // Каждый ход: игрок вводит букву -> проверяем состояние игры
        // Если игра закончена — выводим результат и выходим из цикла
        while (true) {
            makePlayerTurn(word, currentGuess);
            String gameState = checkGameState(word, currentGuess, mistakeCount);
            if (!gameState.equals(GAME_STATE_GAME_NOT_FINISHED)) {
                printGameResult(word, currentGuess);
                break;
            }
        }
    }

    // resetGameState() - создаём массив символов (*****) и обнуляем счётчики
    public static char[] resetGameState(int wordLength) {
        mistakeCount = 0;
        usedLetters = "";
        char[] guess = new char[wordLength];
        Arrays.fill(guess, '*');
        System.out.println(guess);
        return guess;
    }

    // Метод getDictionaryList() отвечает за чтение словаря и получение списка слов для игры
    public static List<String> getDictionaryList() {
        List<String> dictionary = new ArrayList<>();
            try {
                InputStream inputStream = HangmanApp.class.getClassLoader().getResourceAsStream("dictionary.txt");
                if (inputStream == null) {
                    System.out.println("Файл dictionary.txt не найден!");
                    return dictionary;
                }
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    while ((line = reader.readLine()) != null ) {
                        dictionary.add(line.trim().toLowerCase());
                    }
            } catch (IOException e) {
                System.out.println("Ошибка при чтении словаря: " + e.getMessage());
            }

            return dictionary;
        }

    // Метод getRandomWord() генерирует случайное слово для игры
    public static String getRandomWord() {
        List<String> dictionary = getDictionaryList();
        if (dictionary.isEmpty()) {
            System.out.println("Словарь пуст или не загружен.");
            return null;
        }

        return dictionary.get(random.nextInt(dictionary.size()));
    }

    // Метод getInputPlayer() отвечает за ввод буквы игроком
    // Проверяем, что это русская буква; повторяем цикл, если ввод ошибочный
    public static char getInputPlayer() {
        String input;

        do {
            System.out.println("Введите одну букву: ");
            input = scanner.nextLine().trim().toLowerCase();

            if (!input.matches("[а-яё]")) {
                System.out.println("Некорректный ввод. Попробуйте ещё раз.");
            } else {
                return input.charAt(0);
            }
        } while (true);
    }

    // makePlayerTurn - ход игрока
    public static void makePlayerTurn(String word, char[] currentGuess) {
        char letter = getInputPlayer();
        // Получаем букву, проверяем - вводил ли игрок её раньше
        if (usedLetters.indexOf(letter) != -1) {
            System.out.println("Вы уже вводили эту букву: " + letter);
        }

        usedLetters += letter;

        boolean guessCorrect = false;
        // Если буква есть в слове — открываем её.
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                currentGuess[i] = letter;
                guessCorrect = true;
            }
        }
        // Печатаем состояние игры после хода игрока
        // Если буквы нет — увеличиваем счётчик ошибок, выводим виселицу
        if (guessCorrect) {
            System.out.println("Верно! Буква " + letter + " есть в этом слове.");
        } else {
            mistakeCount++;
            System.out.println("Неверно! Буквы " + letter + " нет в этом слове.");
            System.out.println("Ошибок допущено: " + mistakeCount + " из " + MAX_MISTAKES);
            printHangman(mistakeCount);
        }

        printGameState(currentGuess, usedLetters);
    }

    // Метод printGameState выводит состояние игры, показывает текущее слово (*а*а***) и использованные буквы
    public static void printGameState(char[] currentGuess, String usedLetters) {
        System.out.print("Слово: ");
        for (char c : currentGuess) {
            System.out.print(c + " ");
        }
        System.out.println();

        System.out.print("Использованные буквы: ");
        for (char c : usedLetters.toCharArray()) {
            System.out.print(c + " ");
        }
        System.out.println("\n");
    }

    // checkGameState - проверка окончания игры (угадано ли слово, превышен лимит ошибок, игра не окончена))
    public static String checkGameState(String word, char[] currentGuess, int mistakeCount) {
        if (mistakeCount >= MAX_MISTAKES) {
            return GAME_STATE_PLAYER_LOSS;
        } else if (String.valueOf(currentGuess).equals(word)) {
            return GAME_STATE_PLAYER_WON;
        } else {
            return GAME_STATE_GAME_NOT_FINISHED;
        }
    }

    // printGameResult - выводим финальный результат (победа/поражение)
    public static void printGameResult(String word, char[] currentGuess) {
        if (mistakeCount >= MAX_MISTAKES) {
            printHangman(mistakeCount);
            System.out.println(GAME_STATE_PLAYER_LOSS + " Загаданное слово: " + word);
        } else {
            System.out.println(GAME_STATE_PLAYER_WON + " Слово: " + word);
        }
    }

    // printHangman - рисует виселицу (в зависимости от кол-ва ошибок)
    public static void printHangman(int mistakes) {
        String[] stages = new String[7];
        stages[0] = """
                     ________
                     |      |
                     |
                     |
                     |
                     |
                    _|_
                    """;
        stages[1] = """
                     ________
                     |      |
                     |      o
                     |
                     |
                     |
                    _|_
                    """;

        stages[2] = """
                     ________
                     |      |
                     |      o
                     |      |
                     |      |
                     |
                    _|_
                    """;
        stages[3] = """
                     ________
                     |      |
                     |      o
                     |      |/
                     |      |
                     |
                    _|_
                    """;
        stages[4] = """
                     ________
                     |      |
                     |      o
                     |     \\|/
                     |      |
                     |
                    _|_
                    """;
        stages[5] = """
                     ________
                     |      |
                     |      o
                     |     \\|/
                     |      |
                     |       \\
                    _|_
                    """;
        stages[6] = """
                     ________
                     |      |
                     |      o
                     |     \\|/
                     |      |
                     |     / \\
                    _|_
                    """;
        System.out.println(stages[mistakes]);
    }
}
