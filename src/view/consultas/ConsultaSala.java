package view.consultas;

import exceptions.RNException;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import model.Sala;
import rn.SalaRN;
import view.Cinema;

/**
 *
 * @author 631420057
 */
public class ConsultaSala extends Application {
    private SalaRN salaRN;
    private Cinema menuCinema;
    @Override
    public void start(Stage primaryStage) {
        salaRN = new SalaRN();
        //layout
        primaryStage.setTitle("CINEMA - Sala");//--------------------------------Titulo da janela
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
        Text scenetitle = new Text("Consulta de Sala");//------------------------Titulo 
        scenetitle.setId("titulo");//-------------------------------------------ID do titulo para o css
        grid.add(scenetitle, 0, 0, 2, 1);//-------------------------------------seta posição para o titulo
        //fim do titulo da aplicação
        
        //linha Busca
        Label lblBusca = new Label("Buscar Por Numero da Sala:");//-------------cria label busca
        grid.add(lblBusca, 0, 1);//---------------------------------------------seta posição do label busca
        TextField txtBusca = new TextField();//---------------------------------cria a caixa de texto busca
        grid.add(txtBusca, 1,1);//----------------------------------------------seta a posição da caixa busca
        //fim linha Busca
        
        
        //linha Numero da Sala
        Label lblNumeroSala = new Label("Numero da Sala:");//---------------------cria label do numero da sala
        grid.add(lblNumeroSala, 0, 2);//-----------------------------------------seta posição do label numero da sala
        TextField txtNumeroSala = new TextField();//-----------------------------cria a caixa de texto numero da sala
        grid.add(txtNumeroSala, 1, 2);//-----------------------------------------seta a posição da caixa do numero da sala
        //fim Numero da Sala
        
       //linha Capacidade Sala
        Label lblCapacidadeSala = new Label("Capacidade da Sala:");//-------------------------------cria label do  Capacidade Sala
        grid.add(lblCapacidadeSala, 0, 3);//--------------------------------------------seta posição do label  Capacidade Sala
        TextField txtCapacidadeSala = new TextField();//--------------------------------cria a caixa de texto do  Capacidade Sala
        grid.add(txtCapacidadeSala, 1, 3);//--------------------------------------------seta a posição da caixa do  Capacidade Sala
        //fim  Capacidade Sala
        
        
        
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
        List<Sala> listaSala = salaRN.listar();
        TableView tabela = new TableView<>();
        TableColumn colunaNumeroSala = new TableColumn<>("Numero Sala");
        TableColumn colunaCapacidadeSala = new TableColumn<>("Capacidade Sala");
        
        
        colunaNumeroSala.setCellValueFactory(new PropertyValueFactory<>("numeroSala"));
        colunaNumeroSala.setMinWidth(200);
        colunaCapacidadeSala.setCellValueFactory(new PropertyValueFactory<>("quantidadeSala"));
        colunaCapacidadeSala.setMinWidth(200);
        
        
        tabela.setItems(FXCollections.observableArrayList(listaSala));
        tabela.getColumns().addAll(colunaNumeroSala, colunaCapacidadeSala);
        //fim Setando valores Tabela 
        
        //metodo para setar um evento no botão atualizar
        btnAtualizar.setOnAction((ActionEvent e) -> {
                    
            try {
                String buscaString = txtBusca.getText();
                int busca = Integer.parseInt(buscaString);
                
                String numeroSalaString = txtNumeroSala.getText();
                int numeroSala = Integer.parseInt(numeroSalaString);
                
                String capacidadeSalaString = txtCapacidadeSala.getText();
                int capacidadeSala = Integer.parseInt(capacidadeSalaString);
                
                Sala sala = salaRN.procurarPorNumero(busca);
                sala.setNumeroSala(numeroSala);
                sala.setQuantidadeSala(capacidadeSala);
                salaRN.atualizar(sala);
                txtBusca.setText("");
                txtNumeroSala.setText("");
                txtCapacidadeSala.setText("");
                Alert dialogoAviso = new Alert(Alert.AlertType.INFORMATION);
                dialogoAviso.setTitle("Confirmação");
                dialogoAviso.setContentText("Sala Alterada com Sucesso!");
                dialogoAviso.showAndWait();
                tabela.getItems().clear();
                tabela.setItems(FXCollections.observableArrayList(salaRN.listar()));
                tabela.getItems().addAll(colunaNumeroSala, colunaCapacidadeSala);
            } catch (RNException ex) {
                System.err.println(ex.getMessage());
            }
        } 
        );
        //fim metodo de evento do botão Atualizar
        
        //metodo para setar um evento no botão deletar
        btnDeletar.setOnAction(new EventHandler<ActionEvent>() {  
            @Override            
            public void handle(ActionEvent e) { 
                try {
                   
                    String buscaString = txtBusca.getText();
                    int busca = Integer.parseInt(buscaString);
                    Sala sala = salaRN.procurarPorNumero(busca);
                    salaRN.deletar(sala);
                    txtBusca.setText("");
                    txtNumeroSala.setText("");
                    txtCapacidadeSala.setText("");
                    Alert dialogoAviso = new Alert(Alert.AlertType.INFORMATION);
                    dialogoAviso.setTitle("Confirmação");
                    dialogoAviso.setContentText("Filme Deletado com Sucesso!");
                    dialogoAviso.showAndWait();
                    tabela.getItems().clear();
                    tabela.setItems(FXCollections.observableArrayList(salaRN.listar()));
                    tabela.getItems().addAll(colunaNumeroSala, colunaCapacidadeSala);
                } catch (RNException ex) {
                    System.err.println(ex.getMessage());
                }
                
            }
        });
        //fim metodo de evento do botão Deletar
        
        //metodo para setar um evento no botão buscar
        btnBuscar.setOnAction(new EventHandler<ActionEvent>() {  
            @Override              
            public void handle(ActionEvent e) { 
                
                try {
                    String buscaString = txtBusca.getText();
                    int  busca = Integer.parseInt(buscaString);
                    //Filme filme = filmeRN.procurarPorId(idFilme);
                    Sala sala = salaRN.procurarPorNumero(busca);
                    
                    txtNumeroSala.setText(""+sala.getNumeroSala());
                    txtCapacidadeSala.setText(""+sala.getQuantidadeSala());
                } catch (RNException ex) {
                    
                }
            }
        });
        //fim metodo de evento do botão buscar
 
       
        //Evento de clique na tabela
        tabela.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override 
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    Sala sala = (Sala) (tabela.getSelectionModel().getSelectedItem());  
                    txtNumeroSala.setText(sala.getNumeroSala()+"");
                    txtCapacidadeSala.setText(sala.getQuantidadeSala()+"");
                    txtBusca.setText(sala.getNumeroSala()+"");
                }
            }
        });
        //fim Evento de clique na tabela
        
        //layou tabela
        VBox vbTb = new VBox(10);  //-------------------------------------------layout para tabela
        vbTb.getChildren().add(tabela);//---------------------------------------adiciona a tabela no layout
        grid.add(vbTb, 0, 10, 10, 20);//----------------------------------------adiciona o layout da tabela no grid
        //fim layou tabela
        
        //codigo Scene
        Scene scene = new Scene(root, 600, 500);//------------------------------cria a janela passaando a grid e o tamanho por parametro
        primaryStage.setScene(scene);//-----------------------------------------
        scene.getStylesheets().add(ConsultaSala.class.getResource("../../css/style.css").toExternalForm());
        primaryStage.show();//--------------------------------------------------Mostra a janela
        //fim codigo scene
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    
}
