// Указывает пакет модели — должен совпадать с папкой
package model;

// Подключает классы JavaFX для рисования:
import javafx.scene.Group;           // Группа — контейнер для графических элементов
import javafx.scene.paint.Color;     // Цвета (например, Color.RED, Color.BLACK)
import javafx.scene.shape.Circle;    // Круг (для точки пересечения)
import javafx.scene.shape.Line;      // Линия (для перекрестия)

// Класс модели — здесь вся графическая логика (без интерфейса!)
public class GraphicModel {

    // Вложенный класс — чтобы удобно вернуть сразу группу элементов и отчёт
    public static class GraphicResult {
        public final Group crosshairGroup; // Группа с линиями и кругом
        public final String report;        // Текстовый отчёт о построении

        // Конструктор — создаёт объект с заданными значениями
        public GraphicResult(Group crosshairGroup, String report) {
            this.crosshairGroup = crosshairGroup;
            this.report = report;
        }
    }

    /**
     * Создаёт перекрёстие, проходящее через всю панель:
     * - Вертикальная линия: X = x, от Y = 0 до Y = paneHeight
     * - Горизонтальная линия: Y = y, от X = 0 до X = paneWidth
     * - Кружок в точке пересечения (x, y)
     */

    public GraphicResult createCrosshair(int x, int y, double paneWidth, double paneHeight) {
        // Создаёт "строитель строк" для отчёта
        StringBuilder report = new StringBuilder();
        report.append("Построение курсора-перекрёстия\n");
        report.append(String.format("Целевая точка: (%d, %d)%n", x, y));

        // Создаёт группу — в неё добавим все графические элементы
        Group group = new Group();

        // Создаёт вертикальную линию (от верха до низа панели)
        Line vertical = new Line(x, 0, x, paneHeight);
        vertical.setStrokeWidth(2);     // Толщина линии — 2 пикселя
        vertical.setStroke(Color.BLACK); // Цвет — чёрный

        // Создаёт горизонтальную линию (от левого края до правого)
        Line horizontal = new Line(0, y, paneWidth, y);
        horizontal.setStrokeWidth(2);
        horizontal.setStroke(Color.BLACK);

        // Добавляет все элементы в группу
        group.getChildren().addAll(vertical, horizontal);

        // Добавляет информацию в отчёт
        report.append(String.format("Вертикальная линия: X = %d, Y ∈ [0, %.0f]%n", x, paneHeight));
        report.append(String.format("Горизонтальная линия: Y = %d, X ∈ [0, %.0f]%n", y, paneWidth));
        report.append(String.format("Точка пересечения: (%d, %d)%n", x, y));
        report.append("Перекрёстие успешно построено!");

        // Возвращает результат
        return new GraphicResult(group, report.toString());
    }
}