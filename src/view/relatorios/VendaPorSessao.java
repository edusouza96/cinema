
package view.relatorios;

import java.util.List;
import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Sessao;
import model.Venda;
import rn.SessaoRN;
import rn.VendaRN;
import view.Cinema;

/**
 * Classe que monta um relatorio em grafico monstrando quantas vendas tiveram em cada sessão
 *
 * @author eduardo
 */
public class VendaPorSessao extends Preloader {
    
    private SessaoRN sessaoRN;
    private VendaRN vendaRN;
    private Cinema menuCinema;
 
    /**
     * método que monta a janela e suas respctivas ações
     * @param stage 
     */
    @Override public void start(Stage stage) {
        sessaoRN = new SessaoRN();
        vendaRN = new VendaRN();
        List<Sessao> listaSessao = sessaoRN.listar();
        List<Venda> listaVendas = vendaRN.listar();
        
        stage.setTitle("Cinema - Relatório");
        final CategoryAxis caxCategoria = new CategoryAxis();//Eixo x categoria
        final NumberAxis cayValor = new NumberAxis();//eixo y valor
        final BarChart<String,Number> bcBarra = new BarChart<>(caxCategoria,cayValor);//barra
        bcBarra.setTitle("Venda Por Sessao");//titulo do grafico
        bcBarra.setId("tituloRelatorio");//ID do titulo para o css
        caxCategoria.setLabel("Sessão");//nome do eixo X
        cayValor.setLabel("Nº de Vendas");//Nome do eixo Y
        
 
        XYChart.Series barraVertical = new XYChart.Series();//barra vertical
         
        for(Sessao sessao : listaSessao){
            int cont =0;
            for(Venda venda : listaVendas){
                if(venda.getFilmeSalaSessao().getCodigoSessao() == sessao.getCodigoSessao()){
                    cont++;
                }
            }
            barraVertical.setName("Ingressos Vendidos");
            barraVertical.getData().add(new XYChart.Data((sessao.toString2()), cont));//inserção de dados <categoria, valor>       
        }
        
        //menu
        menuCinema = new Cinema();
        BorderPane root = new BorderPane();
        root.setTop(menuCinema.menu(stage));
        root.setCenter(bcBarra);
        //fim menu
        Scene scene  = new Scene(root,600,500);
        bcBarra.getData().addAll( barraVertical);
        scene.getStylesheets().add(VendaPorSessao.class.getResource("../../css/styleGrafic.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    
    }
     public static void main(String[] args) {
        launch(args);
    }
    
}