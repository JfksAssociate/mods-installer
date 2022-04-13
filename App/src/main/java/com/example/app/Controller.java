package com.example.app;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;


public class Controller {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        TextField textField = (TextField) Main.scene.lookup("#filePath");
        TextField download = (TextField) Main.scene.lookup("#url");
        welcomeText.setText("Installing to "+textField.getText()+"...");
        try {
            if (textField.getText().toLowerCase(Locale.US).contains("%appdata%")) {
                String appdata = System.getenv("APPDATA");
                download(download.getText(), appdata + "\\.minecraft\\Mods\\Mods.zip");
                File file = Paths.get(appdata + "\\.minecraft\\Mods\\Mods.zip").toFile();
                System.out.println(file + " success");
                unzip(file.getPath(), appdata+"\\.minecraft\\Mods");
                file.delete();
                welcomeText.setText("Installed to appdata!");
            } else {
                download(download.getText(), textField.getText()+"Mods.zip");
                File file = Paths.get(textField.getText()+"Mods.zip").toFile();
                System.out.println(file + " success");
                unzip(file.getPath(), textField.getText());
                file.delete();
                welcomeText.setText("Installed to directory!");
            }

        } catch (IOException e) {
            welcomeText.setText("ERROR WRITING FILES "+textField.getText());
            try {
                wait(100);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            welcomeText.setText(e.getMessage());
            welcomeText.setBlendMode(BlendMode.RED);
            e.printStackTrace();
        }
    }

    int reopen = 0;
    public void openFileExplorer() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        TextField textField = (TextField) Main.scene.lookup("#filePath");

        JFileChooser f = new JFileChooser();
        if (reopen == 1) {
            f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            f.showSaveDialog(null);

            if (OSValidator.isMac()) {
                System.setProperty("apple.awt.fileDialogForDirectories", "true");
            }
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            f.setVisible(true);

            textField.setText(f.getCurrentDirectory().getAbsolutePath());
        } else {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            reopen++;
        }
    }

    public void installForge() throws URISyntaxException, IOException {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            Desktop.getDesktop().browse(new URI("https://files.minecraftforge.net/net/minecraftforge/forge/index_1.12.2.html"));
        }
    }

    public static void unzip(String source, String destination) throws IOException {
        String password = "password";

        try {
            ZipFile zipFile = new ZipFile(source);
            zipFile.extractAll(destination);
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }

    static long download(String url, String fileName) throws IOException {
        try (InputStream in = URI.create(url).toURL().openStream()) {
            return Files.copy(in, Paths.get(fileName));
        }
    }
    @FXML
    public void setColor() {
        Button btn = (Button) Main.scene.lookup("#button");
        btn.setStyle("-fx-background-color: #32a852;");
    }
}