// student_utils.h
// Объявляет функцию для проверки дубликатов учеников в одном классе

#ifndef STUDENT_UTILS_H
#define STUDENT_UTILS_H

/*

 Функция проверяет, есть ли в каком-либо классе два ученика
 с одинаковыми именем, фамилией, номером и буквой класса
 
 Возвращает 1, если такие есть, 0 — если нет
 
 */
int has_duplicate_names_in_class(const char* filename);

#endif // STUDENT_UTILS_H