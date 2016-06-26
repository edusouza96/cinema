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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Filme;
import rn.FilmeRN;

/**
 *
 * @author 631420057
 */
public class CadastroFilme extends Application {
    private FilmeRN filmeRN;
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
        
        //Titulo dentro da aplicação
        Text scenetitle = new Text("Cadastro de Filme");//----------------------Titulo 
        scenetitle.setId("titulo");//-------------------------------------------ID do titulo para o css
        grid.add(scenetitle, 0, 0, 2, 1);//-------------------------------------seta posição para o titulo
        //fim do titulo da aplicação
        
        //linha Nome do Filme
        Label lblNomeFilme = new Label("Nome do Filme:");//---------------------cria label do nome filme
        grid.add(lblNomeFilme, 0, 1);//-----------------------------------------seta posição do label nome filme
        TextField txtNomeFilme = new TextField();//-----------------------------cria a caixa de texto nome filme
        grid.add(txtNomeFilme, 1, 1);//-----------------------------------------seta a posição da caixa do nome filme
        //fim linha Nome do Filme
        
        //linha genero
        Label lblGenero = new Label("Gênero:");//-------------------------------cria label do genero
        grid.add(lblGenero, 0, 2);//--------------------------------------------seta posição do label Genero
        TextField txtGenero = new TextField();//--------------------------------cria a caixa de texto do genero
        grid.add(txtGenero, 1, 2);//--------------------------------------------seta a posição da caixa do Genero
        //fim linha genero
        
        //linha Sinopse
        Label lblSinopse = new Label("Sinopse:");//-----------------------------cria label da Sinopse
        grid.add(lblSinopse, 0, 3);//-------------------------------------------seta posição da label da Sinopse
        TextField txtSinopse = new TextField();//-------------------------------cria a caixa de texto da Sinopse
        grid.add(txtSinopse, 1, 3);//-------------------------------------------seta a posição da caixa da Sinopse
        //fim linha Sinopse
        
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
        btnCadastrar.setOnAction(new EventHandler<ActionEvent>() {  //-----------
            @Override              //-------------------------------------------
            public void handle(ActionEvent e) {  //-----------------------------método de evento
                try {
                    String nomeFilme = txtNomeFilme.getText();
                    String genero = txtGenero.getText();
                    String sinopse = txtSinopse.getText();
                    filmeRN.adicionar(new Filme(nomeFilme, genero, sinopse));
                    txtNomeFilme.setText("");
                    txtGenero.setText("");
                    txtSinopse.setText("");
                    Alert dialogoAviso = new Alert(Alert.AlertType.INFORMATION);
                    dialogoAviso.setTitle("Confirmação");
                    dialogoAviso.setContentText("Filme Cadastrado com Sucesso!");
                    dialogoAviso.showAndWait();
                } catch (RNException ex) {
            
                }
               
            }
        });
        //fim metodo de evento do botão
 
        //codigo Scene
        Scene scene = new Scene(grid, 600, 500);//------------------------------cria a janela passaando a grid e o tamanho por parametro
        primaryStage.setScene(scene);//-----------------------------------------
        scene.getStylesheets().add(CadastroFilme.class.getResource("../../css/style.css").toExternalForm());
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
