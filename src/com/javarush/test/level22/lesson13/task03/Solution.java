package com.javarush.test.level22.lesson13.task03;

/* Проверка номера телефона
Метод checkTelNumber должен проверять, является ли аргумент telNumber валидным номером телефона.
Критерии валидности:
1) если номер начинается с '+', то он содержит 12 цифр
2) если номер начинается с цифры или открывающей скобки, то он содержит 10 цифр
3) может содержать 0-2 знаков '-', которые не могут идти подряд
4) может содержать 1 пару скобок '(' и ')'  , причем если она есть, то она расположена левее знаков '-'
5) скобки внутри содержат четко 3 цифры
6) номер не содержит букв
7) номер заканчивается на цифру

Примеры:
+380501234567 - true
+38(050)1234567 - true
+38050123-45-67 - true
050123-4567 - true

+38)050(1234567 - false
+38(050)1-23-45-6-7 - false
050ххх4567 - false
050123456 - false
(0)501234567 - false
*/
public class Solution {

    public static boolean checkTelNumber(String telNumber) {
        return (
                (
                        (telNumber.matches("^\\+.*") && (telNumber.length() - telNumber.replaceAll("\\d", "").length() == 12))
                        || (telNumber.matches("(^\\d|\\().*") && (telNumber.length() - telNumber.replaceAll("\\d", "").length()) == 10)
                )
                        && telNumber.matches("(\\+\\d+)?\\d*(\\(\\d{3}\\))?\\d+\\-?\\d+\\-?\\d+")
        );
    }

    public static void main(String[] args) {
        String test000 = "0(111)1-1-1";
        String test001 = "0(111)111";
        String test01 = "0501234567";
        String test1 = "+380501234567";
        String test2 = "+38(050)1234567";
        String test3 = "+38050123-45-67";
        String test4 = "050123-4567";

        String test5 = "+38)050(1234567";
        String test6 = "+38(050)1-23-45-6-7";
        String test7 = "050ххх4567";
        String test8 = "050123456";
        String test9 = "(0)501234567";

        System.out.println("==True==");
        System.out.println(checkTelNumber(test000));
        System.out.println(checkTelNumber(test001));
        System.out.println(checkTelNumber(test1));
        System.out.println(checkTelNumber(test2));
        System.out.println(checkTelNumber(test3));
        System.out.println(checkTelNumber(test4));

        System.out.println("==False==");
        System.out.println(checkTelNumber(test5));
        System.out.println(checkTelNumber(test6));
        System.out.println(checkTelNumber(test7));
        System.out.println(checkTelNumber(test8));
        System.out.println(checkTelNumber(test9));
    }
}
