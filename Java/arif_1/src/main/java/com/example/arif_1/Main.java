// Указывает, в какой папке лежит этот файл. Java требует, чтобы структура папок совпадала с именем пакета.
package com.example.arif_1;

// Подключает нужные классы из JavaFX и стандартной библиотеки:
import javafx.application.Application; // Базовый класс для любого JavaFX-приложения — без него не запустится
import javafx.fxml.FXMLLoader;         // Класс, который умеет читать .fxml файлы (графический интерфейс)
import javafx.scene.Scene;             // "Сцена" — это как страница внутри окна, на ней всё отображается
import javafx.stage.Stage;             // "Stage" — это само окно приложения (с рамкой, кнопками свернуть/закрыть)
import java.io.IOException;            // Нужно, чтобы обработать возможные ошибки при загрузке FXML (например, файл не найден)

// Главный класс приложения. Должен наследовать Application — это обязательное требование JavaFX.
public class Main extends Application {

    // Этот метод вызывается автоматически при запуске JavaFX-приложения.
    // Stage — это окно, в которое программа будет помещать наш интерфейс.
    @Override
    public void start(Stage stage) throws IOException {
        // Создаёт объект FXMLLoader — он будет загружать наш интерфейс из файла ArithmeticView.fxml
        // getClass().getResource(...) — ищет файл в той же папке, где лежит Main.class
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ArithmeticView.fxml"));

        // Загружает интерфейс из FXML и создаёт сцену размером 700x600 пикселей
        // fxmlLoader.load() — возвращает корневой элемент интерфейса
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);

        // Устанавливает заголовок окна — текст в верхней части окна
        stage.setTitle("Арифметический калькулятор (Задание 11)");

        // Привязывает сцену (наш интерфейс) к окну (Stage)
        stage.setScene(scene);

        // Показывает окно пользователю.
        stage.show();
    }

    // Главный метод программы — точка входа. Без него Java не знает, с чего начать.
    public static void main(String[] args) {
        // launch() — стандартный метод JavaFX, который запускает приложение:
        // создаёт окно, вызывает start(), запускает цикл событий (кнопки, клики и т.д.)
        launch();
    }
}