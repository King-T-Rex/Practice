// Geometry.h
#ifndef GEOMETRY_H
#define GEOMETRY_H

/*
Вычисляет площадь треугольника по трём сторонам (формула Герона):
	- a длина стороны a
	- b длина стороны b
	- c длина стороны c
	- double Площадь треугольника или 0, если треугольник невозможен
*/

double triangle_area(double a, double b, double c);

#endif // GEOMETRY_H