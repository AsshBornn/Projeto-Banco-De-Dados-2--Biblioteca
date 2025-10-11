package org.primeiroprojetocursooo.projetobancodedados2biblioteca.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.entity.Categoria;
import org.primeiroprojetocursooo.projetobancodedados2biblioteca.services.CategoriaService;

public class CategoriaController {

    @FXML
    private TableView<Categoria> tabelaCategorias;

    @FXML
    private TableColumn<Categoria, Integer> colunaId;

    @FXML
    private TableColumn<Categoria, String> colunaDescricao;

    @FXML
    private TextField tfDescricao;

    private CategoriaService categoriaService = new CategoriaService();
    private ObservableList<Categoria> listaCategorias = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Conecta colunas aos atributos
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));

        // Carrega categorias do serviço
        listaCategorias.addAll(categoriaService.listar());
        tabelaCategorias.setItems(listaCategorias);
    }

    @FXML
    public void adicionarCategoria() {
        if (!tfDescricao.getText().isEmpty()) {
            Categoria cat = new Categoria();
            cat.setDescricao(tfDescricao.getText());
            categoriaService.salvar(cat);
            listaCategorias.add(cat);
            tfDescricao.clear();
        }
    }

    @FXML
    public void editarCategoria() {
        Categoria cat = tabelaCategorias.getSelectionModel().getSelectedItem();
        if (cat != null && !tfDescricao.getText().isEmpty()) {
            cat.setDescricao(tfDescricao.getText());
            categoriaService.atualizar(cat);
            tabelaCategorias.refresh();
        }
    }

    @FXML
    public void excluirCategoria() {
        Categoria cat = tabelaCategorias.getSelectionModel().getSelectedItem();
        if (cat != null) {
            categoriaService.excluir(cat.getId());
            listaCategorias.remove(cat);
        }
    }
}
