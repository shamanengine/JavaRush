package com.javarush.test.level20.lesson10.bonus03;

import java.util.ArrayList;
import java.util.List;

/* Кроссворд
1. Дан двумерный массив, который содержит буквы английского алфавита в нижнем регистре.
2. Метод detectAllWords должен найти все слова из words в массиве crossword.
3. Элемент(startX, startY) должен соответствовать первой букве слова, элемент(endX, endY) - последней.
text - это само слово, располагается между начальным и конечным элементами
4. Все слова есть в массиве.
5. Слова могут быть расположены горизонтально, вертикально
и по диагонали как в нормальном, так и в обратном порядке.
6. Метод main не участвует в тестировании
*/
public class Solution {
    static int ii, jj;
    public static void main(String[] args) {
        // detectAllWords(crossword, "home", "same");
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        // detectAllWords(crossword, "home", "same");
        detectAllWords(crossword, "home", "same", "red", "rek", "fsg", "rrj", "gsf", "plg", "vhj", "fulm", "eejj", "rlk", "poe");

        /*
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
red - (3, 0) - (1, 0)
rek - (3, 2) - (5, 0)
fsg - (0, 0) - (2, 2)
rrj - (3, 2) - (5, 4)
gsf - (2, 2) - (0, 0)
plg - (0, 4) - (2, 2)
vhj - (5, 2) - (5, 4)
fulm - (0, 0) - (0, 3)
eejj - (2, 4) - (5, 4)
rlk - (3, 0) - (5, 0)
poe - (0, 4) - (2, 4)
        */
        // detectAllWords(crossword, "red");
        // System.out.println(detectAllWords(crossword, "home", "same"));
       /* int[][] crossword2 = new int[][]{
                {'e', 'l', 'm', 'e', 'l', 'k'},
                {'l', 'm', 'a', 'm', 'e', 'o'},
                {'m', 'n', 'o', 'r', 'o', 'v'},
                {'e', 'l', 'p', 'h', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        System.out.println(detectAllWords(crossword2, "home", "same"));*/

        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    private static enum Direction {
        U,
        U_R,
        R,
        R_D,
        D,
        D_L,
        L,
        L_U,
        NO
    }

    private static Word compose(int[][] crossword, int i, int j, char[] chars) {
        Word detectedWord = new Word(new String(chars));
        detectedWord.setStartPoint(j, i);
        // detectedWord.setEndPoint(-1, -1);
        return compose(crossword, i, j, chars, Direction.NO, 0, detectedWord);
    }

    private static Word compose(int[][] crossword, int i, int j, char[] chars, Direction direction, int ind, Word detectedWord) {
        int n = crossword.length;
        int m = crossword[0].length;

        if (ind == chars.length - 1) {
            // detectedWord.setEndPoint(i, j);
            detectedWord.setEndPoint(j, i);
            return detectedWord;
        } else {
            ind++;
            int di, dj;
            switch (direction) {
                case NO:
                case U: {
                    di = i - 1;
                    dj = j;
                    if (checkBounds(di, dj, n, m) && chars[ind] == crossword[di][dj]) {
                        direction = Direction.U;
                        return compose(crossword, di, dj, chars, direction, ind, detectedWord);
                    } else {
                        ind = 1;
                        i = ii;
                        j = jj;
                    }
                }

                case U_R: {
                    di = i - 1;
                    dj = j + 1;
                    if (checkBounds(di, dj, n, m) && chars[ind] == crossword[di][dj]) {
                        direction = Direction.U_R;
                        return compose(crossword, di, dj, chars, direction, ind, detectedWord);
                    } else {
                        ind = 1;
                        i = ii;
                        j = jj;
                    }
                }

                case R: {
                    di = i;
                    dj = j + 1;
                    if (checkBounds(di, dj, n, m) && chars[ind] == crossword[di][dj]) {
                        direction = Direction.R;
                        return compose(crossword, di, dj, chars, direction, ind, detectedWord);
                    } else {
                        ind = 1;
                        i = ii;
                        j = jj;
                    }
                }

                case R_D: {
                    di = i + 1;
                    dj = j + 1;
                    if (checkBounds(di, dj, n, m) && chars[ind] == crossword[di][dj]) {
                        direction = Direction.R_D;
                        return compose(crossword, di, dj, chars, direction, ind, detectedWord);
                    } else {
                        ind = 1;
                        i = ii;
                        j = jj;
                    }
                }

                case D: {
                    di = i + 1;
                    dj = j;
                    if (checkBounds(di, dj, n, m) && chars[ind] == crossword[di][dj]) {
                        direction = Direction.D;
                        return compose(crossword, di, dj, chars, direction, ind, detectedWord);
                    } else {
                        ind = 1;
                        i = ii;
                        j = jj;
                    }
                }

                case D_L: {
                    di = i + 1;
                    dj = j - 1;
                    if (checkBounds(di, dj, n, m) && chars[ind] == crossword[di][dj]) {
                        direction = Direction.D_L;
                        return compose(crossword, di, dj, chars, direction, ind, detectedWord);
                    } else {
                        ind = 1;
                        i = ii;
                        j = jj;
                    }
                }

                case L: {
                    di = i;
                    dj = j - 1;
                    if (checkBounds(di, dj, n, m) && chars[ind] == crossword[di][dj]) {
                        direction = Direction.L;
                        return compose(crossword, di, dj, chars, direction, ind, detectedWord);
                    } else {
                        ind = 1;
                        i = ii;
                        j = jj;
                    }
                }

                case L_U: {
                    di = i - 1;
                    dj = j - 1;
                    if (checkBounds(di, dj, n, m) && chars[ind] == crossword[di][dj]) {
                        direction = Direction.L_U;
                        return compose(crossword, di, dj, chars, direction, ind, detectedWord);
                    }
                    /*else if (direction == Direction.L_U) {
                        return null;
                    }*/
                }

                default:
                    return null;
            }
        }
    }

    private static boolean checkBounds(int i, int j, int n, int m) {
        return i >= 0 && i < n && j >= 0 && j < m;
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> detectedWords = new ArrayList<>();

        int n = crossword.length;
        int m = crossword[0].length;

        for (String word : words) {
            char[] chars = word.toCharArray();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (crossword[i][j] == chars[0]) {
                        ii = i;
                        jj = j;
                        Word detectedWord = compose(crossword, i, j, chars);
                        // detectedWord.setStartPoint(i, j);
                        if (detectedWord != null) detectedWords.add(detectedWord);
                    }
                }
            }

        }
        for (Word word : detectedWords) {
            System.out.println(word);
        }
        return detectedWords;
    }


    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
