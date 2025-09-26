// Пакет контроллера — должен совпадать с папкой, иначе ошибка появится.
package controller;

// Подключает модель — класс, который умеет считать цифры в числе
import model.DigitModel;

// Подключает нужные классы JavaFX:
import javafx.event.ActionEvent; // Событие — например, нажатие кнопки
import javafx.fxml.FXML;         // Эта аннотация связывает поля с элементами из FXML
import javafx.scene.control.*;   // Все элементы управления: TextField, Label, TextArea и т.д.

// Контроллер — "мозг" приложения. Он связывает интерфейс (FXML) и логику (модель).
public class DigitController {

    // @FXML — говорит JavaFX: "Это поле связано с элементом из FXML, у которого fx:id='fieldN'"
    // TextField — поле ввода текста (пользователь будет вводить число сюда)
    @FXML private TextField fieldN;

    // Label — текстовая метка, сюда мы выведем результат (сколько цифр)
    @FXML private Label lblResult;

    // TextArea — многострочное поле для подробного отчёта
    @FXML private TextArea txtReport;

    // Создаёт объект модели — он будет считать цифры в числе
    private final DigitModel model = new DigitModel();

    // Метод, который вызывается при нажатии кнопки "Посчитать цифры"
    @FXML
    private void onCalculate(ActionEvent event) {
        try {
            // Берёт текст из поля ввода и преобразует его в целое число
            int n = Integer.parseInt(fieldN.getText());

            /// Подумай

            // Проверяет: если число отрицательное — выводит ошибку (натуральные числа >= 0)
            if (n < 0) {
                lblResult.setText("Ошибка!");
                txtReport.setText("Число должно быть >= 0");
                return; // выходит из метода, дальше не считает
            }

            // Передаёт число в модель — она посчитает цифры и вернёт результат
            var digitCount = model.countDigits(n);

            // Вызывает модель для генерации отчёта
            String report = model.generateReport(n);

            // Выводит количество цифр в лейбл
            lblResult.setText(String.valueOf(digitCount));

            // Выводит подробный отчёт в TextArea
            txtReport.setText(report);

        } catch (NumberFormatException ex) {
            // Если пользователь ввёл не число (например, "abc") — ловим ошибку
            lblResult.setText("Ошибка!");
            txtReport.setText("Введите корректное целое число.");
        }
    }
}