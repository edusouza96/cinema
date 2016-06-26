package view.consultas;

import exceptions.HourNotAvailable;
import util.MaskFieldUtil;
import exceptions.RNException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Filme;
import model.Sala;
import model.Sessao;
import rn.FilmeRN;
import rn.SalaRN;
import rn.SessaoRN;
import util.DateUtil;

/**
 *
 * @author 631420057
 */
public class ConsultaSessao extends Application {
    private SessaoRN sessaoRN;
    private FilmeRN filmeRN;
    private SalaRN salaRN;
    private int codigoSessao;
    @Override
    public void start(Stage primaryStage) {
        sessaoRN = new SessaoRN();
        filmeRN = new FilmeRN();
        salaRN = new SalaRN();
        
        primaryStage.setTitle("CINEMA - Sessão");//-------------------------------Titulo da janela
        Parent formulario = form(primaryStage);
        //codigo Scene
        Scene scene = new Scene(formulario, 600, 500);//------------------------------cria a janela passaando a grid e o tamanho por parametro
        primaryStage.setScene(scene);//-----------------------------------------
        scene.getStylesheets().add(ConsultaSessao.class.getResource("../../css/style.css").toExternalForm());
        primaryStage.show();//--------------------------------------------------Mostra a janela
        //fim codigo scene
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private Parent form(Stage primaryStage) {
        //Layout
        GridPane grid = new GridPane();//---------------------------------------cria o layout grid
        grid.setAlignment(Pos.CENTER);//----------------------------------------seta posição do grid
        grid.setHgap(10);//-----------------------------------------------------Espaço entre componentes por coluna
        grid.setVgap(10);//-----------------------------------------------------Espaço entre componetes por linha
        grid.setPadding(new Insets(25, 25, 25, 25));//--------------------------seta o espaço da grid em relação a janela
        //fim layout
        
        //Titulo dentro da aplicação
        Text scenetitle = new Text("Consulta de Sessão");//-----------------------Titulo 
        scenetitle.setId("titulo");//-------------------------------------------ID do titulo para o css
        grid.add(scenetitle, 0, 0, 2, 1);//-------------------------------------seta posição para o titulo
        //fim titulo da aplicação
        
        //Nome Filme
        Label lblNomeFilme = new Label("Nome do Filme:");//---------------------cria label do Nome do Filme
        grid.add(lblNomeFilme, 0, 1);//-----------------------------------------seta posição do label Nome do Filme
        ComboBox cbxNomeFilme = new ComboBox();//-------------------------------cria uma combobox para o nome de Filme
        grid.add(cbxNomeFilme, 1, 1);//-----------------------------------------seta a posição da combobox do nome do filme
        cbxNomeFilme.getItems().addAll(popularCbxNomeFilme());//----------------popula combobox Nome FIlme
        cbxNomeFilme.setMaxWidth(250);//----------------------------------------tamanho do campo
        //fim Nome Filme 
        
        
        //Número Sala
        Label lblNumeroSala = new Label("Número da Sala:");//-------------------cria label do Numero Sala
        grid.add(lblNumeroSala, 0, 2);//----------------------------------------seta posição do label Numero Sala
        ComboBox cbxNumeroSala = new ComboBox();//------------------------------cria uma combobox para o Número da Sala
        grid.add(cbxNumeroSala, 1, 2);//----------------------------------------seta a posição da combobox numero da sala
        cbxNumeroSala.getItems().addAll(popularCbxNumeroSala());//--------------popula combobox Numero Sala
        cbxNumeroSala.setMaxWidth(250);//---------------------------------------tamanho do campo
        //fim  Número Sala
        
        //horario
        Label lblHorario = new Label("Horário:");//-----------------------------cria label horario
        grid.add(lblHorario, 0, 3);//-------------------------------------------seta posição  do label horario
        TextField txtHorario = new TextField();//-------------------------------cria o campo horario
        grid.add(txtHorario, 1, 3);//-------------------------------------------seta posição do campo horario
        txtHorario.setMaxWidth(250);//------------------------------------------tamanho do campo
        MaskFieldUtil.timeField(txtHorario);//----------------------------------Aplica a mascara de formatação da mascara
        //fim horario
        
        //linha do botão
        Button btnAtulizar = new Button("Atualizar");//-------------------------cria o botão Atualizar
        Button btnDeletar = new Button("Deletar");//-------------------------cria o botão Deletar
        HBox hbBtn = new HBox(10);  //------------------------------------------layout para o botao do botão
        hbBtn.setAlignment(Pos.TOP_RIGHT);//---------------------------------Posiçao do layout botao
        hbBtn.getChildren().addAll(btnAtulizar,btnDeletar);//---------------------------------adiciona o botao no layout
        grid.add(hbBtn, 1, 5);//------------------------------------------------adiciona o layout do botao no grid
        //fim linha botão
        
        
        
        //Setando valores Tabela 
        List<Sessao> listaSessao = sessaoRN.listar();
        List<Object> listaSessaoAdaptada = new ArrayList<>();
        listaSessaoAdaptada = populaListaAdaptada(listaSessao,listaSessaoAdaptada);
             
        TableView tabela = new TableView<>();
        TableColumn colunaCodigoSessao = new TableColumn<>("Código");
        TableColumn colunaNumeroSala = new TableColumn<>("Numero da Sala");
        TableColumn colunaNomeFilme = new TableColumn<>("Nome do Filme");
        TableColumn colunaHorario = new TableColumn<>("Horario");
        
        colunaCodigoSessao.setCellValueFactory(new PropertyValueFactory<>("codigoSessao"));
        colunaNumeroSala.setCellValueFactory(new PropertyValueFactory<>("numeroSala"));
        colunaNumeroSala.setMinWidth(150);
        colunaNomeFilme.setCellValueFactory(new PropertyValueFactory<>("nomeFilme"));
        colunaNomeFilme.setMinWidth(150);
        colunaHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));
        
        tabela.setItems(FXCollections.observableArrayList(listaSessaoAdaptada));
        tabela.getColumns().addAll(colunaCodigoSessao, colunaNumeroSala, colunaNomeFilme, colunaHorario);
        //fim Setando valores Tabela 
        
        //layou tabela
        VBox vbTb = new VBox(10);  //-------------------------------------------layout para tabela
        vbTb.getChildren().add(tabela);//---------------------------------------adiciona a tabela no layout
        grid.add(vbTb, 0, 10, 10, 20);//----------------------------------------adiciona o layout da tabela no grid
        //fim layou tabela
        
        //Evento de clique na tabela
        tabela.setOnMousePressed((MouseEvent event) -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                Sessao sessao = (Sessao) (tabela.getSelectionModel().getSelectedItem());
                cbxNomeFilme.setValue(sessao.getNomeFilme());
                cbxNumeroSala.setValue(sessao.getNumeroSala());
                txtHorario.setText(DateUtil.hourToString(sessao.getHorario()));
                this.codigoSessao = sessao.getCodigoSessao();
            }
        });
        //fim Evento de clique na tabela
        
       ///evento botão atualizar
        btnAtulizar.setOnAction((ActionEvent e) -> {
                
            try {
                String nomeFilme = cbxNomeFilme.getSelectionModel().getSelectedItem().toString();
                Filme filme;
                filme = filmeRN.procuraPorNome(nomeFilme);
                
                String numeroSala = cbxNumeroSala.getSelectionModel().getSelectedItem().toString();
                Sala sala;
                sala = salaRN.procurarPorNumero(Integer.parseInt(numeroSala));
                
                String horario = txtHorario.getText();
                Date hora;
                hora = DateUtil.stringToHour(horario);
                
                sessaoRN.atualizar(new Sessao(codigoSessao, sala, filme, hora));
                Alert dialogoAviso = new Alert(Alert.AlertType.INFORMATION);
                dialogoAviso.setTitle("Confirmação");
                dialogoAviso.setContentText("Sessão Atualizada com Sucesso!");
                dialogoAviso.showAndWait();
                cbxNomeFilme.setValue("");
                cbxNumeroSala.setValue("");
                txtHorario.setText("");
                List<Object> listaRefresh = new ArrayList<>();
                listaRefresh = populaListaAdaptada(sessaoRN.listar(),listaRefresh);
                tabela.getItems().clear();
                tabela.setItems(FXCollections.observableArrayList(listaRefresh));
            } catch (RNException ex) {
                Alert dialogoAviso = new Alert(Alert.AlertType.ERROR);
                dialogoAviso.setTitle("Erro");
                dialogoAviso.setContentText("Revise os dados e tente novamente!");
                dialogoAviso.showAndWait();
                
            } catch (ParseException ex) {
                Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                dialogoAviso.setTitle("Alerta");
                dialogoAviso.setContentText("Horario fora do padrão!");
                dialogoAviso.showAndWait();
            } catch (HourNotAvailable ex) {
               
            }
                
           
        });
        //fim evento botão atualizar
        
        
        ///evento botão deletar
        btnDeletar.setOnAction((ActionEvent e) -> {
                
            try {
                
                Sessao sessao = sessaoRN.procurarPorId(codigoSessao);
                sessaoRN.deletar(sessao);
                Alert dialogoAviso = new Alert(Alert.AlertType.INFORMATION);
                dialogoAviso.setTitle("Confirmação");
                dialogoAviso.setContentText("Sessão Atualizada com Sucesso!");
                dialogoAviso.showAndWait();
                cbxNomeFilme.setValue("");
                cbxNumeroSala.setValue("");
                txtHorario.setText("");
                List<Object> listaRefresh = new ArrayList<>();
                listaRefresh = populaListaAdaptada(sessaoRN.listar(),listaRefresh);
                tabela.getItems().clear();
                tabela.setItems(FXCollections.observableArrayList(listaRefresh));
            } catch (RNException ex) {
                Alert dialogoAviso = new Alert(Alert.AlertType.ERROR);
                dialogoAviso.setTitle("Erro");
                dialogoAviso.setContentText("Revise os dados e tente novamente!");
                dialogoAviso.showAndWait();
                
            } 
                
           
        });
        //fim evento botão deletar
        
        return grid;
    }

    private List popularCbxNomeFilme() {
        FilmeRN filmeRN = new FilmeRN();
        List<Filme> listaFilme = filmeRN.listar();
        List<String> listaNomeFilme = new LinkedList<>();
        for(Filme filme : listaFilme)
                listaNomeFilme.add(filme.getNomeFilme());
        
        return listaNomeFilme;
    }

    private List popularCbxNumeroSala() {
        SalaRN salaRN = new SalaRN();
        List<Sala> listaSala = salaRN.listar();
        List<Integer> listaNumeroSala = new LinkedList<>();
        for(Sala sala : listaSala)
                listaNumeroSala.add(sala.getNumeroSala());
       
        return listaNumeroSala;
    }

    private List<Object> populaListaAdaptada(List<Sessao> listaSessao, List<Object> listaSessaoAdaptada) {
        
        for(Sessao sessao : listaSessao){
            sessao.SessaoAdaptada(
            sessao.getCodigoSessao(),         
            sessao.getSala().getNumeroSala(),
            sessao.getFilme().getNomeFilme(),
            sessao.getHorario());
            listaSessaoAdaptada.add(sessao);
        }
           return listaSessaoAdaptada;
    }
    
}
