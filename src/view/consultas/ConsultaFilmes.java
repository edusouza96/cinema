package view.consultas;

import exceptions.RNException;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Filme;
import rn.FilmeRN;
import view.Cinema;

/**
 * Classe responsavel  por exibir todos os filmes cadastrados e de altera-los 
 * ou deleta-los quando requerido
 * @author 631420057
 */
public class ConsultaFilmes extends Application {
    private FilmeRN filmeRN;
    private Cinema menuCinema;
    
    /**
     * método que monta a janela e suas respctivas ações
     * @param primaryStage  
     */
    @Override
    public void start(Stage primaryStage) {
        filmeRN = new FilmeRN();
        //layout
        primaryStage.setTitle("CINEMA - Filme");//------------------------------Titulo da janela
        GridPane grid = new GridPane();//---------------------------------------cria o layout grid
        grid.setAlignment(Pos.CENTER);//----------------------------------------seta posição do grid
        grid.setHgap(10);//-----------------------------------------------------Espaço entre componentes por coluna
        grid.setVgap(10);//-----------------------------------------------------Espaço entre componetes por linha
        grid.setPadding(new Insets(25, 25, 25, 25));//--------------------------seta o espaço da grid em relação a janela
        //fim layout
        
        //menu
        menuCinema = new Cinema();
        BorderPane root = new BorderPane();
        root.setTop(menuCinema.menu(primaryStage));
        root.setCenter(grid);
        //fim menu
        
        //Titulo dentro da aplicação
        Text scenetitle = new Text("Consulta de Filme");//----------------------Titulo 
        scenetitle.setId("titulo");//-------------------------------------------ID do titulo para o css
        grid.add(scenetitle, 0, 0, 2, 1);//-------------------------------------seta posição para o titulo
        //fim do titulo da aplicação
        
        //linha Busca
        Label lblBusca = new Label("Buscar Por Nome Filme:");//-----------------cria label busca
        grid.add(lblBusca, 0, 1);//---------------------------------------------seta posição do label busca
        TextField txtBusca = new TextField();//---------------------------------cria a caixa de texto busca
        grid.add(txtBusca, 1,1);//----------------------------------------------seta a posição da caixa busca
        
        
        //fim linha Busca
        
        
        //linha Nome do Filme
        Label lblNomeFilme = new Label("Nome do Filme:");//---------------------cria label do nome filme
        grid.add(lblNomeFilme, 0, 2);//-----------------------------------------seta posição do label nome filme
        TextField txtNomeFilme = new TextField();//-----------------------------cria a caixa de texto nome filme
        grid.add(txtNomeFilme, 1, 2);//-----------------------------------------seta a posição da caixa do nome filme
        //fim linha Nome do Filme
        
        //linha genero
        Label lblGenero = new Label("Gênero:");//-------------------------------cria label do genero
        grid.add(lblGenero, 0, 3);//--------------------------------------------seta posição do label Genero
        TextField txtGenero = new TextField();//--------------------------------cria a caixa de texto do genero
        grid.add(txtGenero, 1, 3);//--------------------------------------------seta a posição da caixa do Genero
        //fim linha genero
        
        //linha Sinopse
        Label lblSinopse = new Label("Sinopse:");//-----------------------------cria label da Sinopse
        grid.add(lblSinopse, 0, 4);//-------------------------------------------seta posição da label da Sinopse
        TextField txtSinopse = new TextField();//-------------------------------cria a caixa de texto da Sinopse
        grid.add(txtSinopse, 1, 4);//-------------------------------------------seta a posição da caixa da Sinopse
        //fim linha Sinopse
        
        
        
        //linha do botão
        Button btnBuscar= new Button("Buscar");//-------------------------------cria o botão
        Button btnAtualizar = new Button("Atualizar");//------------------------cria o botão
        Button btnDeletar = new Button("Deletar");//----------------------------cria o botão
        HBox hbBtn = new HBox(10);  //------------------------------------------layout para o botao do botão
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);//----------------------------------Posiçao do layout botao
        hbBtn.getChildren().add(btnBuscar);//-----------------------------------adiciona o botao no layout
        hbBtn.getChildren().add(btnAtualizar);//--------------------------------adiciona o botao no layout
        hbBtn.getChildren().add(btnDeletar);//-----------------------------------adiciona o botao no layout
        grid.add(hbBtn, 1,6);//-------------------------------------------------adiciona o layout do botao no grid
        //fim linha botão

        //Setando valores Tabela 
        List<Filme> listaFilme = filmeRN.listar();
        TableView tabela = new TableView<>();
        TableColumn colunaCodigoFilme = new TableColumn<>("Cód");
        TableColumn colunaNomeFilme = new TableColumn<>("Nome do Filme");
        TableColumn colunaGenero = new TableColumn<>("Gênero");
        TableColumn colunaSinopse = new TableColumn<>("Sinopse");
        
        colunaCodigoFilme.setCellValueFactory(new PropertyValueFactory<>("codigoFilme"));
        colunaNomeFilme.setCellValueFactory(new PropertyValueFactory<>("nomeFilme"));
        colunaNomeFilme.setMinWidth(150);
        colunaGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colunaGenero.setMinWidth(120);
        colunaSinopse.setCellValueFactory(new PropertyValueFactory<>("sinopse"));
        colunaSinopse.setMinWidth(220);
         
        tabela.setItems(FXCollections.observableArrayList(listaFilme));
        tabela.getColumns().addAll(colunaCodigoFilme, colunaNomeFilme, colunaGenero, colunaSinopse);
        //fim Setando valores Tabela 
        
        //metodo para setar um evento no botão atualizar
        btnAtualizar.setOnAction((ActionEvent e) -> {
                    
            try {
                String busca = txtBusca.getText();
                Filme filme = filmeRN.procuraPorNome(busca);
                filme.setNomeFilme(txtNomeFilme.getText());
                filme.setGenero(txtGenero.getText());
                filme.setSinopse(txtSinopse.getText());
                filmeRN.atualizar(filme);
                txtBusca.setText("");
                txtNomeFilme.setText("");
                txtGenero.setText("");
                txtSinopse.setText("");
                Alert dialogoAviso = new Alert(Alert.AlertType.INFORMATION);
                dialogoAviso.setTitle("Confirmação");
                dialogoAviso.setContentText("Filme Alterado com Sucesso!");
                dialogoAviso.showAndWait();
                tabela.getItems().clear();
                tabela.setItems(FXCollections.observableArrayList(filmeRN.listar()));
                tabela.getItems().addAll(colunaCodigoFilme, colunaNomeFilme, colunaGenero, colunaSinopse);
            } catch (RNException ex) {
                System.err.println(ex.getMessage());
            }
        } 
        );
        //fim metodo de evento do botão Atualizar
        
        //metodo para setar um evento no botão deletar
        btnDeletar.setOnAction((ActionEvent e) -> {
            try {
                String busca = txtBusca.getText();
                Filme filme = filmeRN.procuraPorNome(busca);
                filmeRN.deletar(filme);
                txtBusca.setText("");
                txtNomeFilme.setText("");
                txtGenero.setText("");
                txtSinopse.setText("");
                Alert dialogoAviso = new Alert(Alert.AlertType.INFORMATION);
                dialogoAviso.setTitle("Confirmação");
                dialogoAviso.setContentText("Filme Deletado com Sucesso!");
                dialogoAviso.showAndWait();
                tabela.getItems().clear();
                tabela.setItems(FXCollections.observableArrayList(filmeRN.listar()));
                tabela.getItems().addAll(colunaCodigoFilme, colunaNomeFilme, colunaGenero, colunaSinopse);
            } catch (RNException ex) {
                System.err.println(ex.getMessage());
            }
        });
        //fim metodo de evento do botão Deletar
        
        //metodo para setar um evento no botão buscar
        btnBuscar.setOnAction((ActionEvent e) -> {
            try {
                String busca = txtBusca.getText(); 
                //int idFilme = Integer.parseInt(busca);
                //Filme filme = filmeRN.procurarPorId(idFilme);
                Filme filme = filmeRN.procuraPorNome(busca);
                txtNomeFilme.setText(filme.getNomeFilme());
                txtGenero.setText(filme.getGenero());
                txtSinopse.setText(filme.getSinopse());
            } catch (RNException ex) {
                
            }
        });
        //fim metodo de evento do botão buscar
 
        //Evento de clique na tabela
        tabela.setOnMousePressed((MouseEvent event) -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                Filme filme = (Filme) (tabela.getSelectionModel().getSelectedItem());
                txtNomeFilme.setText(filme.getNomeFilme());
                txtGenero.setText(filme.getGenero());
                txtSinopse.setText(filme.getSinopse());
                txtBusca.setText(filme.getNomeFilme());
            }
        });
        //fim Evento de clique na tabela
        
        //layou tabela
        VBox vbTb = new VBox(10);  //-------------------------------------------layout para tabela
        vbTb.getChildren().add(tabela);//---------------------------------------adiciona a tabela no layout
        grid.add(vbTb, 0, 10, 10, 20);//----------------------------------------adiciona o layout da tabela no grid
        //fim layou tabela
        
        //codigo Scene
        Scene scene = new Scene(root, 600, 550);//------------------------------cria a janela passaando a grid e o tamanho por parametro
        primaryStage.setScene(scene);//-----------------------------------------
        scene.getStylesheets().add(ConsultaFilmes.class.getResource("../../css/style.css").toExternalForm());
        primaryStage.show();//--------------------------------------------------Mostra a janela
        //fim codigo scene
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    
}
