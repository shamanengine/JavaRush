package com.javarush.test.level22.lesson18.big01;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Raccoon
 * @version 1.0, 01.07.2016.
 */
public class TestX {
    public static Tetris game;

    public static void printField(Field field) {
        for (int i = 0; i < field.getHeight(); i++) {
            for (int j = 0; j < field.getWidth(); j++) {
                System.out.print(field.getMatrix()[i][j]);
            }
            System.out.println();
        }
        System.out.println("  ====  ");
    }

    public static void main(String[] args) {
        game = new Tetris(10, 20);
        Field field = new Field(3, 4);

        printField(field);
        field.setValue(0, 0, 1);
        field.setValue(1, 1, 1);
        field.setValue(2, 2, 1);

        field.setValue(0, 3, 1);
        field.setValue(1, 3, 1);
        field.setValue(2, 3, 1);

        printField(field);

        field.removeFullLines();
        printField(field);
    }
    /*public void removeFullLines() {
        List<int[]> lines = new ArrayList<>();
        int tmp;
        for (int i = 0; i < height; i++) {
            tmp = 0;
            for (int j = 0; j < width; j++) {
                tmp += matrix[i][j];
            }
            if (tmp > 0 && tmp < width) {
                lines.add(0, matrix[i]);
            }
        }

        int[] emptyLine = new int[width];
        for (int i = 0; i < width; i++) {
            emptyLine[i] = 0;
        }

        for (int i = 0; i< lines.size(); i++) {
            matrix[i] = lines.get(i);
        }

        for (int i = lines.size(); i < height; i++) {
            matrix[i] = emptyLine;
        }

        //Например так:
        //Создаем список для хранения линий
        //Копируем все непустые линии в список.
        //Добавляем недостающие строки в начало списка.
        //Преобразуем список обратно в матрицу
    }*/
}
