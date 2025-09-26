// Указывает, что этот класс лежит в папке "controller"
package controller;

// Подключает класс модели — в нём происходит вся математика
import model.ArithmeticModel;

// Подключает нужные классы JavaFX:
import javafx.event.ActionEvent; // Событие действия — например, нажатие кнопки
import javafx.fxml.FXML;         // Аннотация, чтобы связать элементы из FXML с полями в этом классе
import javafx.scene.control.*;   // Все элементы управления: TextField, Label, TextArea, Button и т.д.

// Контроллер — "мозг" приложения. Он связывает интерфейс (FXML) и логику (модель).
public class ArithmeticController {

    // @FXML — говорит JavaFX: "свяжи это поле с элементом в FXML, у которого fx:id совпадает с именем поля"
    // TextField — поле ввода текста (одна строка)
    @FXML private TextField fieldX, fieldY, fieldZ;

    // Label — просто текстовая метка для вывода результата
    @FXML private Label lblA, lblB;

    // TextArea — многострочное поле для вывода подробного отчёта
    @FXML private TextArea txtReport;

    // Создаёт объект модели — он будет выполнять все вычисления
    // final — значит, поле нельзя перезаписать (это безопасно, модель одна на всё приложение)
    //  private final ArithmeticModel model = new ArithmeticModel();

    // Метод, который вызывается при нажатии кнопки "Вычислить"
    // В FXML у кнопки должно быть: onAction="#onCalculate"
    @FXML
    private void onCalculate(ActionEvent event) {
        try {
            // Получает текст из полей ввода и преобразует в числа типа double
            // Double.parseDouble(...) — превращает строку в число (например, "3.14" -> 3.14)
            double x = Double.parseDouble(fieldX.getText());
            double y = Double.parseDouble(fieldY.getText());
            double z = Double.parseDouble(fieldZ.getText());

            // Передаёт числа в модель и получает результат вычислений
            var result = ArithmeticModel.calculate(x, y, z);

            // Проверяет, не произошла ли ошибка в модели (например, корень из отрицательного числа)
            // Double.isNaN(...) — проверяет, является ли число "не числом" (NaN = Not a Number)
            if (Double.isNaN(result.a)) {
                // Если ошибка — выводит "Ошибка!" в оба поля
                lblA.setText("Ошибка!");
                lblB.setText("Ошибка!");
            } else {
                // Если всё хорошо — выводит результаты a и b, округлённые до 4 знаков после запятой
                // String.format("%.4f", число) — форматирует число (например, 3.14159 → "3.1416")
                lblA.setText(String.format("%.4f", result.a));
                lblB.setText(String.format("%.4f", result.b));
            }

            // Выводит подробный отчёт о вычислениях в текстовое поле
            txtReport.setText(result.report);

        } catch (NumberFormatException ex) {
            // Если пользователь ввёл не число (например, "abc") — ловим ошибку и выводим сообщение
            lblA.setText("Ошибка!");
            lblB.setText("Ошибка!");
            txtReport.setText("Введите корректные числа во все поля.");
        }
    }
}