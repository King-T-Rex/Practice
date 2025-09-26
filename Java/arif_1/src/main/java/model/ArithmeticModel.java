// Указывает, что этот класс лежит в папке "model"
package model;

// Класс модели — здесь вся математическая логика. Никакого интерфейса!
public class ArithmeticModel {

    // Вложенный класс — нужен, чтобы удобно вернуть сразу три значения: a, b и отчёт
    public static class CalculationResult {
        public  double a;      // Результат вычисления a
        public  double b;      // Результат вычисления b
        public  String report; // Текстовый отчёт о всех шагах вычислений

        // Конструктор — позволяет создать объект и сразу задать все три поля
        public CalculationResult(double a, double b, String report) {
            this.a = a;
            this.b = b;
            this.report = report;
        }
    }

    // Основной метод — принимает x, y, z и возвращает объект с результатами
    public static CalculationResult calculate(double x, double y, double z) {
        // StringBuilder — "строитель строк", позволяет эффективно собирать большой текст по частям
        StringBuilder report = new StringBuilder();

        // Добавляет в отчёт входные данные
        report.append("Входные данные:\n");
        report.append(String.format("x = %.2f\n", x)); // %.2f — два знака после запятой
        report.append(String.format("y = %.2f\n", y));
        report.append(String.format("z = %.2f\n", z));

        // Вычисляет |x - 1|
        double absXMinus1 = Math.abs(x - 1); // Math.abs — модуль

        // Вычисляет кубический корень из |y|
        double absY = Math.cbrt(Math.abs(y)); // Math.cbrt — кубический корень

        // Вычисляет выражение под корнем для формулы a: |x−1| − кубический корень из |y|
        double underSqrt = absXMinus1 - absY;

        // Проверяет, не отрицательно ли выражение под корнем
        if (underSqrt < 0) {
            // Если да — добавляет сообщение об ошибке в отчёт
            report.append("\nОшибка: выражение под корнем отрицательно!\n");
            // Возвращает "битые" числа (NaN = Not a Number) и отчёт с ошибкой
            return new CalculationResult(Double.NaN, Double.NaN, report.toString());
        }

        // Вычисляет числитель для a: корень из |x−1| минус кубический корень из |y|
        double sqrtValue = Math.sqrt(absXMinus1) - absY; // Math.sqrt — квадратный корень

        // Вычисляет знаменатель для a: 1 + (x^2)/2 + (y^2)/4
        double denominatorA = 1 + (x * x) / 2 + (y * y) / 4;

        // Вычисляет a = числитель / знаменатель
        double a = sqrtValue / denominatorA;

        // Вычисляет b = x * (arctg(z) + e^(-(x+3)))
        double arctgZ = Math.atan(z);           // Math.atan — арктангенс (в радианах)
        double expPart = Math.exp(-(x + 3));    // Math.exp — экспонента (e в степени ...)
        double b = x * (arctgZ + expPart);

        // Добавляет в отчёт все промежуточные шаги (чтобы пользователь видел, как всё считалось)
        report.append("\nВычисления:\n");
        report.append(String.format("│x−1│ = %.4f\n", absXMinus1));
        report.append(String.format("Кубический корень из │y│ = %.4f\n", absY));
        report.append(String.format("Подкоренное выражение (для проверки): %.4f\n", underSqrt));
        report.append(String.format("Числитель a (корень из |x−1| − кубический корень из |y|): %.4f\n", sqrtValue));
        report.append(String.format("Знаменатель a: %.4f\n", denominatorA));
        report.append(String.format("a = %.4f\n", a));
        report.append(String.format("arctg(z) = %.4f\n", arctgZ));
        report.append(String.format("e^(-(x+3)) = %.4f\n", expPart));
        report.append(String.format("b = %.4f\n", b));

        // Возвращает результат: числа a, b и полный отчёт
        return new CalculationResult(a, b, report.toString());
    }
}