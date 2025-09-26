// matrix.h
// Заголовочный файл

// Здесь содержатся прототипы функций из matrix.c, чтобы их можно было использовать в main.c.

#ifndef MATRIX_H
#define MATRIX_H

#define MAX_N 100

// Функция ввода матрицы с клавиатуры
void input_matrix(int n, double matrix[MAX_N][MAX_N]);

// Функция печати всей матрицы
void print_matrix(int n, double matrix[MAX_N][MAX_N]);

// Функция печати только заштрихованной области
void print_zone(int n, double matrix[MAX_N][MAX_N]);

// Функция поиска наибольшего элемента в заштрихованной области
double find_max_in_zone(int n, double matrix[MAX_N][MAX_N]);

#endif // MATRIX_H
