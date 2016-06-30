package view.cadastros;

import exceptions.RNException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Sala;
import rn.SalaRN;
import view.Cinema;

/**
 * Classe responsavel  por cadastrar as salas
 * @author 631420057
 */
public class CadastroSala extends Application {
    private SalaRN salaRN;
    private Cinema menuCinema;
    
     /**
     *  método que monta a janela e suas respctivas ações
     * @param primaryStage janela
     */
    @Override
    public void start(Stage primaryStage) {
        salaRN = new SalaRN();
        //layout
        primaryStage.setTitle("CINEMA - Sala");//-------------------------------Titulo da janela
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
        Text scenetitle = new Text("Cadastro de Sala");//-----------------------Titulo 
        scenetitle.setId("titulo");//-------------------------------------------ID do titulo para o css
        grid.add(scenetitle, 0, 0, 2, 1);//-------------------------------------seta posição para o titulo
        //fim do titulo da aplicação
        
        //linha Numero da Sala
        Label lblNumeroSala = new Label("Numero da Sala:");//---------------------cria label do numero da sala
        grid.add(lblNumeroSala, 0, 1);//-----------------------------------------seta posição do label numero da sala
        TextField txtNumeroSala = new TextField();//-----------------------------cria a caixa de texto numero da sala
        grid.add(txtNumeroSala, 1, 1);//-----------------------------------------seta a posição da caixa do numero da sala
        //fim Numero da Sala
        
        //linha Capacidade Sala
        Label lblCapacidadeSala = new Label("Capacidade da Sala:");//-------------------------------cria label do  Capacidade Sala
        grid.add(lblCapacidadeSala, 0, 2);//--------------------------------------------seta posição do label  Capacidade Sala
        TextField txtCapacidadeSala = new TextField();//--------------------------------cria a caixa de texto do  Capacidade Sala
        grid.add(txtCapacidadeSala, 1, 2);//--------------------------------------------seta a posição da caixa do  Capacidade Sala
        //fim  Capacidade Sala
        
        
        //linha do botão
        Button btnCadastrar = new Button("Cadastrar");//-------------------------cria o botão
        HBox hbBtn = new HBox(10);  //------------------------------------------layout para o botao do botão
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);//---------------------------------Posiçao do layout botao
        hbBtn.getChildren().add(btnCadastrar);//---------------------------------adiciona o botao no layout
        grid.add(hbBtn, 1, 4);//------------------------------------------------adiciona o layout do botao no grid
        //fim linha botão
        
        //mensagem a ser exibida quando apertar o botão
       
        final Text msgBotao = new Text();//-------------------------------------cria uma variavel texto
        grid.add(msgBotao, 1, 6);//---------------------------------------------seta posição para exibir o texto
        //fim da mensagem a ser exibida

        //metodo para setar um evento no botão
        btnCadastrar.setOnAction(new EventHandler<ActionEvent>() {  
            @Override              
            public void handle(ActionEvent e) {  
                try {
                    String numeroSalaString = txtNumeroSala.getText();
                    String quantidadeSalaString = txtCapacidadeSala.getText();
                    
                    int numeroSala = Integer.parseInt(numeroSalaString);
                    int quantidadeSala = Integer.parseInt(quantidadeSalaString);
                    
                    salaRN.adicionar(new Sala(numeroSala, quantidadeSala));
                    txtNumeroSala.setText("");
                    txtCapacidadeSala.setText("");
                    Alert dialogoAviso = new Alert(Alert.AlertType.INFORMATION);
                    dialogoAviso.setTitle("Confirmação");
                    dialogoAviso.setContentText("Sala Cadastrado com Sucesso!");
                    dialogoAviso.showAndWait();
                } catch (RNException ex) {
            
                }
               
            }
        });
        //fim metodo de evento do botão
 
        //codigo Scene
        Scene scene = new Scene(root, 600, 500);//------------------------------cria a janela passaando a grid e o tamanho por parametro
        primaryStage.setScene(scene);//-----------------------------------------
        scene.getStylesheets().add(CadastroSala.class.getResource("../../css/style.css").toExternalForm());
        primaryStage.show();//--------------------------------------------------Mostra a janela
        //fim codigo scene
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
