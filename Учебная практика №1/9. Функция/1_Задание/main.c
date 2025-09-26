// Main_10.c
#include <stdio.h>      // Для работы с вводом/выводом
#include <locale.h>     // Для поддержки кириллицы
#include "Geometry.h"   // Модуль
#include <windows.h>     // Подключает для SetConsoleCP — настройка кодировки консоли Windows

/*
Автор: Кузнецов Александр

Назначение:
Программа вычисляет площадь пятиугольника, состоящего из трёх треугольников.
Используется формула Герона для расчёта площадей.
*/


int main() {
    // Настройка локали и кодировки консоли для корректного отображения русского текста
    setlocale(LC_ALL, ".UTF-8");
    SetConsoleCP(CP_UTF8);           // Кодировка ввода
    SetConsoleOutputCP(CP_UTF8);     // Кодировка вывода

    double a, b, c, d;

    printf("Введите длины сторон a, b, c, d:\n");
    scanf("%lf %lf %lf %lf", &a, &b, &c, &d);

    double area1 = triangle_area(1.0, a, 2.0);     // Треугольник с сторонами 1.0, a, 2.0
    double area2 = triangle_area(2.0, b, 2.5);     // Треугольник с сторонами 2.0, b, 2.5
    double area3 = triangle_area(2.5, c, d);       // Треугольник с сторонами 2.5, c, d

    // Общая площадь пятиугольника
    double total_area = area1 + area2 + area3;

    printf("Площадь первого треугольника: %.4f\n", area1);
    printf("Площадь второго треугольника: %.4f\n", area2);
    printf("Площадь третьего треугольника: %.4f\n", area3);
    printf("Общая площадь пятиугольника: %.4f\n", total_area);

    return 0;
}