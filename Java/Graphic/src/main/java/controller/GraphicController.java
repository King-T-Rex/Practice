// Указывает пакет контроллера — должен совпадать с папкой
package controller;

// Подключает модель — класс, где происходит построение графики
import model.GraphicModel;
import model.GraphicModel.GraphicResult; // Вложенный класс для возврата результата

// Подключает классы JavaFX:
import javafx.event.ActionEvent;     // Событие действия (например, нажатие кнопки)
import javafx.fxml.FXML;             // Аннотация для связи с элементами FXML
import javafx.scene.Cursor;          // Курсор мыши
import javafx.scene.control.*;       // TextField, Button, Label, TextArea
import javafx.scene.input.MouseButton; // Кнопки мыши (левая, правая и т.д.)
import javafx.scene.input.MouseEvent;  // Событие клика мыши
import javafx.scene.layout.Pane;       // Панель, на которой будем рисовать

// Контроллер — "мозг" приложения: связывает интерфейс и логику
public class GraphicController {

    // Поля ввода координат X и Y — связаны с FXML через fx:id
    @FXML private TextField fieldX, fieldY;

    // Кнопка "Построить" — тоже связана с FXML
    @FXML private Button btnDraw;

    // Метка для статуса (например, "Ошибка" или "Успешно")
    @FXML private Label lblStatus;

    // Текстовое поле для подробного отчёта
    @FXML private TextArea txtReport;

    // Панель, на которой будет рисовать перекрёстие
    @FXML private Pane drawingPane;

    // Создаёт объект модели — он будет строить графику
    private final GraphicModel model = new GraphicModel();

    // Запоминает последнюю точку, чтобы перерисовать при изменении размера окна
    private Double lastX = null;
    private Double lastY = null;

    // Метод initialize() вызывается автоматически после загрузки FXML
    @FXML
    private void initialize() {
        // Устанавливает курсор-перекрестье при наведении на панель
        drawingPane.setCursor(Cursor.CROSSHAIR);

        // При клике на панель — вызывает метод onPaneClick
        drawingPane.setOnMouseClicked(this::onPaneClick);

        // При нажатии кнопки "Построить" — вызывает onDraw (на всякий случай, если в FXML не прописан onAction)
        btnDraw.setOnAction(this::onDraw);

        // Если размер панели изменился — перерисовать последнее перекрёстие
        drawingPane.widthProperty().addListener((obs, oldV, newV) -> redrawIfHavePoint());
        drawingPane.heightProperty().addListener((obs, oldV, newV) -> redrawIfHavePoint());
    }

    // Метод вызывается при клике на панель
    private void onPaneClick(MouseEvent e) {
        // Проверяет, что кликнули левой кнопкой мыши
        if (e.getButton() != MouseButton.PRIMARY) return;

        // Берёт координаты клика и округляем до целых чисел
        int x = (int) Math.round(e.getX());
        int y = (int) Math.round(e.getY());

        // Записывает координаты в поля ввода
        fieldX.setText(String.valueOf(x));
        fieldY.setText(String.valueOf(y));

        // Рисует перекрёстие в этой точке
        drawAt(x, y);
    }

    // Метод вызывается при нажатии кнопки "Построить"
    @FXML
    private void onDraw(ActionEvent event) {
        try {
            // Получает текст из полей и преобразуем в целые числа
            int x = Integer.parseInt(fieldX.getText().trim());
            int y = Integer.parseInt(fieldY.getText().trim());

            // Рисует перекрёстие
            drawAt(x, y);
        } catch (NumberFormatException ex) {
            // Если введены не числа — выводим ошибку
            lblStatus.setText("Ошибка: введите целые числа!");
            txtReport.setText("Пожалуйста, введите корректные координаты X и Y (целые числа).");
        }
    }

    // Основной метод рисования перекрёстия
    private void drawAt(int rawX, int rawY) {
        // Если панель ещё не отрисована — принудительно обновляет её размеры
        if (drawingPane.getWidth() <= 0 || drawingPane.getHeight() <= 0) {
            drawingPane.applyCss(); // Применяет стили
            drawingPane.layout();   // Выполняет компоновку
        }

        // Получает текущие размеры панели
        double w = drawingPane.getWidth();
        double h = drawingPane.getHeight();

        // Ограничивает координаты, чтобы они не выходили за границы панели
        int x = Math.max(0, Math.min((int) Math.round(w), rawX));
        int y = Math.max(0, Math.min((int) Math.round(h), rawY));

        // Передаёт координаты в модель и получаем результат
        GraphicResult result = model.createCrosshair(x, y, w, h);

        // Очищает панель и добавляет новое перекрёстие
        drawingPane.getChildren().setAll(result.crosshairGroup);

        // Обновляет статус и отчёт
        lblStatus.setText("Перекрёстие в точке (" + x + ", " + y + ")");
        txtReport.setText(result.report);

        // Запоминает точку для перерисовки при изменении размера
        lastX = (double) x;
        lastY = (double) y;
    }

    // Перерисовывает последнее перекрёстие, если оно есть
    private void redrawIfHavePoint() {
        if (lastX != null && lastY != null) {
            drawAt(lastX.intValue(), lastY.intValue());
        }
    }
}