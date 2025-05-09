// Допущения:
// без ООП

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HangmanApp {

    public static int mistakeCount = 3;
    public static int lifeCount = 3;

    public static void main(String[] args) {
        //printHangmanState();
        printStartWordState();
    }
    // Игровой процесс:
    // рандомно выбирается из dictionary.txt и выводится на консоль слово под маской (*****) -> getRandomWord()
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

    // Метод getDictionaryList() должен делать только одно: загрузить список слов
    public static List<String> getDictionaryList() {
        // Получаем поток байтов (InputStream) к файлу dictionary.txt, который лежит в ресурсах проекта.
        InputStream inputStream = HangmanApp.class.getClassLoader().getResourceAsStream("dictionary.txt");

        // Если файл не найден (например, не положил в resources или IDE его не видит), то поток будет null
        // В этом случае возвращаем null, чтобы программа не упала.
        if (inputStream == null) {
            System.out.println("Файл dictionary.txt не найден!");
            return null;
        }

        // Создаем список строк — сюда будем складывать слова из словаря.
        List<String> dictionaryList = new ArrayList<>();

        // Оборачиваем наш InputStream в InputStreamReader, чтобы читать текст из потока байтов.
        // Затем оборачиваем в BufferedReader — это класс, который удобно читает файл построчно.
        // try (...) {} — это конструкция try-with-resources, она автоматически закрывает reader после завершения блока. Это удобно и безопасно.
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            // Цикл читает файл построчно.
            // readLine() читает одну строку из файла.
            // Если файл закончился, readLine() вернёт null, и цикл завершится.
            String line;
            while ((line = reader.readLine()) != null) {
                // trim() удаляет лишние пробелы в начале и конце строки.
                line = line.trim();
                // Если строка не пустая, добавляем её в список dictionary
                if (!line.isEmpty()) {
                    dictionaryList.add(line);
                }
            } // Если при чтении файла возникла ошибка (напр., файл поврежден), она будет поймана здесь, и выведем сообщение об ошибке
        } catch (IOException e) {
            System.out.println("Ошибка при чтении словаря: " + e.getMessage());
            return null;
        }


        return dictionaryList;
    }

    // Метод getRandomWord() отвечает за получение одного случайного слова, и он должен убедиться, что список не пустой,
    // прежде чем вызывать random.nextInt(...)
    public static String getRandomWord() {
        List<String> words = getDictionaryList();
        // Если файл прочитан, но не содержит ни одной строки (или все строки были пустыми), мы сообщаем об этом.
        if (words.isEmpty()) {
            System.out.println("Словарь пуст или не загружен.");
            return null;
        }
        // Создаем объект Random
        Random random = new Random();
        // Выбираем случайный индекс от 0 до words.size() - 1. Возвращаем случайное слово из списка
        return words.get(random.nextInt(words.size()));
    }

    public static void printStartWordState() {
        String word = getRandomWord();

        if (word == null || word.isEmpty()) {
            System.out.println("Слово для игры не получено.");
            return;
        }

        for (int i = 0; i < word.length(); i++) {
            System.out.print("*");
        }
        System.out.println();
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

    public static void printHangmanState() {
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
