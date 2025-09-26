// Указывает пакет — это папка, где лежит файл.
package com.example.graphic;

// Подключает нужные классы JavaFX:
import javafx.application.Application; // Базовый класс для JavaFX-приложений
import javafx.fxml.FXMLLoader;         // Загружает .fxml-файлы (графический интерфейс)
import javafx.scene.Scene;             // Сцена — содержимое окна
import javafx.stage.Stage;             // Окно приложения

import java.io.IOException;            // Для обработки ошибок загрузки FXML

// Главный класс приложения — должен наследовать Application
public class Main extends Application {

    // Этот метод вызывается при запуске JavaFX-приложения
    @Override
    public void start(Stage stage) throws IOException {
        // Создаёт загрузчик FXML — он прочитает файл интерфейса
        // Main.class.getResource(...) — ищет файл в папке, где лежит Main.class
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GraphicView.fxml"));

        // Загружает интерфейс и создаёт сцену размером 820x820 пикселей
        Scene scene = new Scene(fxmlLoader.load(), 820, 820);

        // Устанавливает заголовок окна
        stage.setTitle("Курсор-перекрестье");

        // Привязывает сцену к окну
        stage.setScene(scene);

        // Показывает окно пользователю
        stage.show();
    }

    // Главный метод — точка входа в программу
    public static void main(String[] args) {
        // Запускает JavaFX-приложение
        launch();
    }
}