// Это пакет — как "папка" в проекте. Java требует, чтобы структура папок совпадала с именем пакета.
package com.example.cycle;

// Подключает нужные классы из JavaFX:
import javafx.application.Application; // Основной класс для запуска JavaFX-приложения
import javafx.fxml.FXMLLoader;         // Загружает интерфейс из FXML-файла (дизайн окна)
import javafx.scene.Scene;             // Сцена — как "экран" внутри окна
import javafx.stage.Stage;             // Окно приложения
import java.io.IOException;            // Нужен, чтобы обрабатывать ошибки, если FXML не найден

// Главный класс приложения. Должен наследовать Application — это обязательное требование JavaFX.
public class Main extends Application {

    // Этот метод вызывается автоматически при запуске программы.
    // Stage — это окно, которое мы будем настраивать и показывать пользователю.
    @Override
    public void start(Stage stage) throws IOException {
        // Создаёт "загрузчик" — он умеет читать FXML-файлы и превращать их в интерфейс
        FXMLLoader fxmlLoader = new FXMLLoader(
                // getClass().getResource(...) — ищет файл внутри папки ресурсов (resources)
                // Ищет файл с именем "DigitView.fxml" в той же папке, где лежит Main.class
                getClass().getResource("DigitView.fxml")
        );

        // Загружает интерфейс из FXML и создаёт "сцену" — экран размером 500x400 пикселей
        Scene scene = new Scene(fxmlLoader.load(), 500, 400);

        // Устанавливает заголовок окна — то, что видно вверху окна
        stage.setTitle("Счётчик цифр в числе");

        // Привязывает сцену (интерфейс) к окну
        stage.setScene(scene);

        // Показывает окно пользователю
        stage.show();
    }

    // Главный метод программы — точка входа. Без него Java не знает, с чего начать.
    public static void main(String[] args) {
        // launch() — стандартный способ запуска JavaFX-приложения.
        // Он вызовет метод start() выше.
        launch();
    }
}