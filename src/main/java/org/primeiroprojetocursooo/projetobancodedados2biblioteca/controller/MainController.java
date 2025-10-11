package org.primeiroprojetocursooo.projetobancodedados2biblioteca.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class MainController {

    @FXML
    private BorderPane mainBorderPane; // Linkar ao BorderPane no Scene Builder

    @FXML
    public void abrirCategorias(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CategoriaView.fxml"));
            AnchorPane pane = loader.load();
            mainBorderPane.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Mesma lógica para outros menus
    @FXML
    public void abrirUsuarios(ActionEvent event) { }
    @FXML
    public void abrirLivros(ActionEvent event) { }
    @FXML
    public void abrirLocacoes(ActionEvent event) { }
    @FXML
    public void abrirPagamentos(ActionEvent event) { }
}
