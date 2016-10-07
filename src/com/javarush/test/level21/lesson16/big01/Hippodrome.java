package com.javarush.test.level21.lesson16.big01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Raccoon
 * @version 1.0, 29.06.2016.
 */
public class Hippodrome {
    private ArrayList<Horse> horses = new ArrayList<>();
    public static Hippodrome game;

    public ArrayList<Horse> getHorses() {
        return horses;
    }

    /*Задание 8
    В методе run сделай цикл от 1 до 100. Это и будет наш забег.
    В теле цикла вызываем сначала move, затем print.
    Чтобы весь цикл не отработал за долю секунды - добавь в него еще Thread.sleep(200);*/
    public void run() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            move();
            print();
            Thread.sleep(200);
            /*try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }

    /*    Задание 9
Теперь вернемся к методам move и print. Начнем с move.
В методе move класса Hippodrome в цикле у каждой лошади мы вызываем метод move.

Да ты прав, его еще нет у класса Horse.
Поэтому в класс Horse надо добавить свой метод move :)
И метод print, кстати тоже.
Если я не говорю ничего насчет параметров метода, значит метод без параметров.
Делай все методы public, если явно не указано обратное. */
    public void move() {
        for (Horse horse : horses) {
            horse.move();
        }
    }

    /*Задание 10
    Еще нужно написать метод print класса Hippodrome.
    В нем тоже все просто: в цикле для каждой лошади вызываем ее метод print.
    Ну, и еще выведи после цикла пару пустых строк: System.out.println() - чтобы было красивее.*/
    public void print() {
        for (Horse horse : horses) {
            horse.print();
        }
        System.out.println();
        System.out.println();
    }


/*
    Задание 15
    Добавим определение победителя.
    В классе Hippodrome сделаем два метода:
    public Horse getWinner() и public void printWinner()

    Метод getWinner должен возвращать лошадь пробежавшую самую большую дистанцию.
    Метод printWinner выводит на экран имя победителя в виде:
    Winner is <NAME>!
    Пример:
    Winner is Lucky!
*/

    public Horse getWinner() {
        return Collections.max(horses, new HorseByDistanceComparator());
    }

    public void printWinner() {
        System.out.println("Winner is " + getWinner().getName() + "!");
    }
    /*Создай статическую переменную game типа Hippodrome.
    Должно получиться что-то вроде:
    public static Hippodrome game;

    В методе main надо:
    а) Создать объект типа Hippodrome и сохранить его в переменную game.
    б) Создать три объекта "лошадь". Имена придумай сам. Начальные скорость у всех лошадей - 3, дистанция - 0.
    в) Добавить созданных лошадей в список лошадей ипподрома (horses). Используйте для этого геттер.*/
    public static void main(String[] args) {
        game = new Hippodrome();

        Horse horse1 = new Horse("Horse1", 3, 0);
        Horse horse2 = new Horse("Horse2", 3, 0);
        Horse horse3 = new Horse("Horse3", 3, 0);

        game.getHorses().add(horse1);
        game.getHorses().add(horse2);
        game.getHorses().add(horse3);

        try {
            game.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        game.printWinner();
    }
}
