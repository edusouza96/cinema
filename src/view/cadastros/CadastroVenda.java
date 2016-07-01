package view.cadastros;

import exceptions.RNException;
import java.awt.HeadlessException;
import java.text.ParseException;
import java.util.ArrayList;
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
import javafx.scene.control.DatePicker;
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
import model.Assento;
import model.Sessao;
import model.Venda;
import rn.AssentoRN;
import rn.FilmeRN;
import rn.SalaRN;
import rn.SessaoRN;
import rn.VendaRN;
import util.DateUtil;
import view.Cinema;

/**
 * Classe responsavel  por realizar as vendas
 * @author 631420057
 */
public class CadastroVenda extends Application {
    private VendaRN vendaRN;
    private AssentoRN assentoRN;
    private SessaoRN sessaoRN;
    private FilmeRN filmeRN;
    private SalaRN salaRN;
    private Sessao sessaoEscolhida;
    int codigoSessao;
    private Cinema menuCinema;
    
    /**
     * Método que monta a janela para exibição
     * @param primaryStage  janela
     */
    @Override
    public void start(Stage primaryStage) {
        vendaRN = new VendaRN();
        assentoRN = new AssentoRN();
        sessaoRN = new SessaoRN();
        filmeRN = new FilmeRN();
        salaRN = new SalaRN();
        sessaoEscolhida = new Sessao();
        
        primaryStage.setTitle("CINEMA - Venda");//-------------------------------Titulo da janela
        Parent formulario = form(primaryStage);
        
        //menu
        menuCinema = new Cinema();
        BorderPane root = new BorderPane();
        root.setTop(menuCinema.menu(primaryStage));
        root.setCenter(formulario);
        //fim menu
        //codigo Scene
        Scene scene = new Scene(root, 600, 500);//------------------------------cria a janela passaando a grid e o tamanho por parametro
        primaryStage.setScene(scene);//-----------------------------------------
        scene.getStylesheets().add(CadastroVenda.class.getResource("../../css/style.css").toExternalForm());
        primaryStage.show();//--------------------------------------------------Mostra a janela
        //fim codigo scene
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * metodo que coloca os componentes no formulario e monta todas as ações dos botoes
     * @param primaryStage
     * @return retorna um nó grid para adicionar no layot da janela
     */
    private Parent form(Stage primaryStage) {
        //Layout
        GridPane grid = new GridPane();//---------------------------------------cria o layout grid
        grid.setAlignment(Pos.CENTER);//----------------------------------------seta posição do grid
        grid.setHgap(10);//-----------------------------------------------------Espaço entre componentes por coluna
        grid.setVgap(10);//-----------------------------------------------------Espaço entre componetes por linha
        grid.setPadding(new Insets(25, 25, 25, 25));//--------------------------seta o espaço da grid em relação a janela
        //fim layout
        
        //Titulo dentro da aplicação
        Text scenetitle = new Text("Realizar Venda");//-------------------------Titulo 
        scenetitle.setId("titulo");//-------------------------------------------ID do titulo para o css
        grid.add(scenetitle, 0, 0, 2, 1);//-------------------------------------seta posição para o titulo
        //fim titulo da aplicação
        
        //data
        Label lblData = new Label("Data:");//-----------------------------------cria label data
        grid.add(lblData, 0, 1);//----------------------------------------------seta posição  do label data
        DatePicker dtpData = new DatePicker();//--------------------------------cria campo data
        grid.add(dtpData, 1, 1);//----------------------------------------------seta posição do campo data
        dtpData.setMaxWidth(200);//---------------------------------------------tamanho do campo
        //fim data 
        
        
        //Sessao
        Label lblSessao = new Label("Sessão:");//-------------------------------cria label sessão
        grid.add(lblSessao, 0, 2);//--------------------------------------------seta posição do label Numero Sala
        TextField txtSessao = new TextField();//--------------------------------cria campo sessão
        grid.add(txtSessao, 1, 2,20,1);//--------------------------------------------seta a posição do campo sessão
        txtSessao.setMaxWidth(300);//-------------------------------------------tamanho do campo
        txtSessao.setEditable(false);//-----------------------------------------campo não editavel
        //fim  Sessão
        
        
        
        //linha do botão
        Button btnCadastrar = new Button("Cadastrar");//-------------------------cria o botão
        HBox hbBtn = new HBox(2);  //------------------------------------------layout para o botao do botão
        hbBtn.setAlignment(Pos.TOP_CENTER);//---------------------------------Posiçao do layout botao
        hbBtn.getChildren().add(btnCadastrar);//---------------------------------adiciona o botao no layout
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
                txtSessao.setText(sessao.toString());
                sessaoEscolhida = sessao;
                codigoSessao = sessao.getCodigoSessao();
            }
        });
        //fim Evento de clique na tabela
        
       ///metodo para setar um evento no botão
        btnCadastrar.setOnAction((ActionEvent e) -> {
               
            try { 
                String data = dtpData.getValue()+"";
                data = data.charAt(8)+""+data.charAt(9)+"/"+data.charAt(5)+data.charAt(6)+"/"+data.charAt(0)+data.charAt(1)+data.charAt(2)+data.charAt(3);
             
                Assento assento = assentoRN.procurarPorDataCodigo(DateUtil.stringToDate(data), codigoSessao);
           
                if(assento == null){

                    Sessao sessao = sessaoRN.procurarPorId(codigoSessao);
                    vendaRN.adicionar(new Venda(sessao,DateUtil.stringToDate(data)));
                    int lugares = sessao.getSala().getQuantidadeSala() -1;
                    assentoRN.adicionar(new Assento(sessao,lugares,DateUtil.stringToDate(data)));
                    Alert dialogoAviso = new Alert(Alert.AlertType.INFORMATION);
                    dialogoAviso.setTitle("Confirmação");
                    dialogoAviso.setContentText("Venda Realizada com Sucesso!");
                    dialogoAviso.showAndWait();
                }else{
                    if(assento.getAssentoLivres() > 0){
                        vendaRN.adicionar(new Venda(assento.getSessao(),DateUtil.stringToDate(data)));
                        assento.ocuparLugar();
                        assentoRN.atualizar(assento);
                        Alert dialogoAviso = new Alert(Alert.AlertType.INFORMATION);
                        dialogoAviso.setTitle("Confirmação");
                        dialogoAviso.setContentText("Venda Realizada com Sucesso!");
                        dialogoAviso.showAndWait();
                    }else{
                        Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                        dialogoAviso.setTitle("Aviso");
                        dialogoAviso.setContentText("Não tem mais esta sessão para este dia!");
                        dialogoAviso.showAndWait();
                    }
                }

            } catch (RNException ex) {
                System.err.println(ex.getMessage());
            }catch(HeadlessException ex){
                 Alert dialogoAviso = new Alert(Alert.AlertType.ERROR);
                dialogoAviso.setTitle("Erro");
                dialogoAviso.setContentText("Erro não identificado, tente novamente");
                dialogoAviso.showAndWait();
            } catch (ParseException ex) {
                Alert dialogoAviso = new Alert(Alert.AlertType.ERROR);
                dialogoAviso.setTitle("Erro");
                dialogoAviso.setContentText("Data Inválida");
                dialogoAviso.showAndWait();
            } catch (Exception ex) {
                Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                dialogoAviso.setTitle("Aviso");
                dialogoAviso.setContentText("Data deve ser preenchida");
                dialogoAviso.showAndWait();
            } 
                
           
        });
        //fim metodo de evento do botão
        return grid;
    }
    
    /**
     * metodo responsabel por popular uma lista adaptada, para exibição correta na tela,
     * essa lista adaptada é a transformação de objeto filme em nome filme e do 
     * objeo sala em numero sala, tornando mais facil de manipular esses dados visualmente
     * @param listaSessao contem uma lista de sessoes cadastradas
     * @param listaSessaoAdaptada lista que será populada para ser exibida
     * @return retorna a lista adaptada
     */
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
