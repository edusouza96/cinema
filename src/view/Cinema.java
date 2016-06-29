/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import view.cadastros.CadastroFilme;
import view.cadastros.CadastroSala;
import view.cadastros.CadastroSessao;
import view.cadastros.CadastroVenda;
import view.consultas.ConsultaFilmes;
import view.consultas.ConsultaSala;
import view.consultas.ConsultaSessao;
import view.relatorios.FilmePorSessao;
import view.relatorios.SalasPorSessao;
import view.relatorios.VendaPorData;
import view.relatorios.VendaPorFilme;
import view.relatorios.VendaPorHorario;
import view.relatorios.VendaPorSala;
import view.relatorios.VendaPorSessao;

/**
 *
 * @author eduardo
 */
public class Cinema extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        

        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        root.setTop(menu(primaryStage));
        
       
        primaryStage.setTitle("CINEMA - Home");
        Scene scene = new Scene(root, 600, 500, Color.WHITE);
        primaryStage.setScene(scene);
       
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    public MenuBar menu(Stage primaryStage){
        MenuBar menuBar = new MenuBar();
        //menu Filme
        Menu menuFilme = new Menu("Filme");
        menuFilme.setMnemonicParsing(true);
        MenuItem menuItemFilmeCadastrar = new MenuItem("Cadastrar");
        MenuItem menuItemFilmeConsultar = new MenuItem("Consultar");
        menuFilme.getItems().addAll(menuItemFilmeCadastrar,menuItemFilmeConsultar);
        
        
        menuItemFilmeCadastrar.setOnAction((a) ->{
            CadastroFilme cadFilme = new CadastroFilme();
            cadFilme.start(primaryStage);
        });
        
        menuItemFilmeConsultar.setOnAction((a) ->{
            ConsultaFilmes conFilme = new ConsultaFilmes();
            conFilme.start(primaryStage);
        });

        //fim menu filme
        
        //menu sala
        Menu menuSala = new Menu("Sala");
        menuSala.setMnemonicParsing(true);
        MenuItem menuItemSalaCadastrar = new MenuItem("Cadastrar");
        MenuItem menuItemSalaConsultar = new MenuItem("Consultar");
        menuSala.getItems().addAll(menuItemSalaCadastrar,menuItemSalaConsultar);
        
        
        menuItemSalaCadastrar.setOnAction((a) ->{
            CadastroSala cadSala = new CadastroSala();
            cadSala.start(primaryStage);
        });
        
        menuItemSalaConsultar.setOnAction((a) ->{
            ConsultaSala conSala = new ConsultaSala();
            conSala.start(primaryStage);
        });
        //fim menu sala
        
        //menu Sessao
        Menu menuSessao = new Menu("Sessão");
        menuSessao.setMnemonicParsing(true);
        MenuItem menuItemSessaoCadastrar = new MenuItem("Cadastrar");
        MenuItem menuItemSessaoConsultar = new MenuItem("Consultar");
        menuSessao.getItems().addAll(menuItemSessaoCadastrar,menuItemSessaoConsultar);
        
        
        menuItemSessaoCadastrar.setOnAction((a) ->{
            CadastroSessao cadSessao = new CadastroSessao();
            cadSessao.start(primaryStage);
        });
        
        menuItemSessaoConsultar.setOnAction((a) ->{
            ConsultaSessao conSessao = new ConsultaSessao();
            conSessao.start(primaryStage);
        });
        //fim menu Sessao
        
        //menu Venda
        Menu menuVenda = new Menu("Venda");
        menuVenda.setMnemonicParsing(true);
        MenuItem menuItemVenda = new MenuItem("Realizar Vendas");
        menuVenda.getItems().addAll(menuItemVenda);
        menuItemVenda.setOnAction((a) ->{
            CadastroVenda cadVenda = new CadastroVenda();
            cadVenda.start(primaryStage);
        });
        //fim menu Venda
        
        //menu Relatório
        Menu menuRelatorio = new Menu("Relatórios");
        menuRelatorio.setMnemonicParsing(true);
       
        MenuItem menuItemFilmePorSessao = new MenuItem("Filme Por Sessão");
        MenuItem menuItemSalaPorSessao = new MenuItem("Sala Por Sessão");
        MenuItem menuItemVendaPorSessao = new MenuItem("Venda Por Sessão");
        MenuItem menuItemVendaPorData = new MenuItem("Venda Por Data");
        MenuItem menuItemVendaPorFilme = new MenuItem("Venda Por Filme");
        MenuItem menuItemVendaPorHorario = new MenuItem("Venda Por Horário");
        MenuItem menuItemVendaPorSala = new MenuItem("Venda Por Sala");
        
        menuRelatorio.getItems().addAll(menuItemFilmePorSessao,
                                        menuItemSalaPorSessao,
                                        menuItemVendaPorSessao,
                                        menuItemVendaPorData,
                                        menuItemVendaPorFilme,
                                        menuItemVendaPorHorario,
                                        menuItemVendaPorSala);
        
        menuItemFilmePorSessao.setOnAction((a) ->{
            FilmePorSessao filmeSessao = new FilmePorSessao();
            filmeSessao.start(primaryStage);
        });
        
        menuItemSalaPorSessao.setOnAction((a) ->{
            SalasPorSessao salaSessao = new SalasPorSessao();
            salaSessao.start(primaryStage);
        });
        
        menuItemVendaPorSessao.setOnAction((a) ->{
            VendaPorSessao vendaSessao = new VendaPorSessao();
            vendaSessao.start(primaryStage);
        });
        
        menuItemVendaPorData.setOnAction((a) ->{
            VendaPorData vendaData = new VendaPorData();
            vendaData.start(primaryStage);
        });
        
        menuItemVendaPorFilme.setOnAction((a) ->{
            VendaPorFilme vendaFilme = new VendaPorFilme();
            vendaFilme.start(primaryStage);
        });
        
        menuItemVendaPorHorario.setOnAction((a) ->{
            VendaPorHorario vendaHorario = new VendaPorHorario();
            vendaHorario.start(primaryStage);
        });
        
        menuItemVendaPorSala.setOnAction((a) ->{
            VendaPorSala vendaSala = new VendaPorSala();
            vendaSala.start(primaryStage);
        });
        
        //fim menu Relatório
        
        
        //menu Ações
        Menu menuSair = new Menu("Ações");
        menuSair.setMnemonicParsing(true);
        MenuItem menuItemSair = new MenuItem("Sair");
        menuSair.getItems().add(menuItemSair);
        menuSair.setOnAction(actionEvent -> Platform.exit());
        //fim menu Ações
        menuBar.getMenus().addAll(menuFilme,menuSala,menuSessao,menuVenda,menuRelatorio,menuSair);
        
        return menuBar;
    }
}
