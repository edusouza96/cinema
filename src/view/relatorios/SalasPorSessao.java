/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.relatorios;

import java.util.List;
import static javafx.application.Application.launch;
import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Sala;
import model.Sessao;
import rn.SalaRN;
import rn.SessaoRN;
import view.Cinema;

/**
 * Simple Preloader Using the ProgressBar Control
 *
 * @author eduardo
 */
public class SalasPorSessao extends Preloader {
    
    private SessaoRN sessaoRN;
    private SalaRN salaRN;
    private Cinema menuCinema;
 
    @Override public void start(Stage stage) {
        sessaoRN = new SessaoRN();
        salaRN = new SalaRN();
        List<Sessao> listaSessoes = sessaoRN.listar();
        List<Sala> listaSalas = salaRN.listar();
        
        stage.setTitle("Cinema - Relatório");
        final CategoryAxis caxCategoria = new CategoryAxis();//Eixo x categoria
        final NumberAxis cayValor = new NumberAxis();//eixo y valor
        final BarChart<String,Number> bcBarra = new BarChart<>(caxCategoria,cayValor);//barra
        bcBarra.setTitle("Salas Por Sessão");//titulo do grafico
        bcBarra.setId("tituloRelatorio");//ID do titulo para o css
        caxCategoria.setLabel("Salas");//nome do eixo X
        cayValor.setLabel("Nº de Sessões");//Nome do eixo Y
        
 
        XYChart.Series barraVertical = new XYChart.Series();//barra vertical
         
       
        for(Sala sala : listaSalas){
            int cont =0;
            for(Sessao sessao : listaSessoes){
                if(sessao.getSala().getNumeroSala() == sala.getNumeroSala()){
                    cont++;
                }
            }
            barraVertical.setName("quantidade de Sala");
            barraVertical.getData().add(new XYChart.Data((sala.getNumeroSala()+""), cont));//inserção de dados <categoria, valor>       
        }
        
        //menu
        menuCinema = new Cinema();
        BorderPane root = new BorderPane();
        root.setTop(menuCinema.menu(stage));
        root.setCenter(bcBarra);
        //fim menu
        Scene scene  = new Scene(root,600,500);
        bcBarra.getData().addAll( barraVertical);
        //scene.getStylesheets().add(VendaPorFilme.class.getResource("../../css/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    
    }
     public static void main(String[] args) {
        launch(args);
    }
    
}