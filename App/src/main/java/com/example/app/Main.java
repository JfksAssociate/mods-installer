package com.example.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    static Scene scene = null;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        scene = new Scene(fxmlLoader.load(), 620, 440);

        WebView webView = (WebView) scene.lookup("#webView");
        WebEngine engine = webView.getEngine();


        URL url = this.getClass().getResource("window.html");
        engine.load(url.toString());

        stage.setResizable(false);
        stage.setOnCloseRequest(e -> close(stage));
        stage.getIcons().addAll(new Image("https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/159648c2-8de7-4641-a129-8d8c2d06bd9c/dee1zh0-f9e45f11-8ff2-4032-8215-72e54c647a9f.png/v1/fill/w_512,h_512,strp/curseforge_honeycomb_icon_by_tastyburger122_dee1zh0-fullview.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9NTEyIiwicGF0aCI6IlwvZlwvMTU5NjQ4YzItOGRlNy00NjQxLWExMjktOGQ4YzJkMDZiZDljXC9kZWUxemgwLWY5ZTQ1ZjExLThmZjItNDAzMi04MjE1LTcyZTU0YzY0N2E5Zi5wbmciLCJ3aWR0aCI6Ijw9NTEyIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmltYWdlLm9wZXJhdGlvbnMiXX0.5NoUSSmaHx45Q4b9RNU2v9GnHgdyMQBL-WmD8UtbuYU"));
        stage.setTitle("PTCommunity Mods Installer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    public void close(Stage stage) {
        stage.close();
    }
}