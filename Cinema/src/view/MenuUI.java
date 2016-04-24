package view;

/**
 *Classe que contem a exibição do menu
 */
public class MenuUI {
    
    public static final int FILME = 1;
    public static final int SALA = 2;
    public static final int SESSAO = 3;
    public static final int VENDA = 4;
    public static final int RELATORIO = 5;
    public static final int SAIR = 0;
    
    public static final int CADASTRAR = 1;
    public static final int LISTAR = 2;
    public static final int EDITAR = 3;
    public static final int DELETAR = 4;

    /**
     * Menu Principal
     * @return retorna o menu com opções para o acesso de cada modulo da aplicação
     */
    public static String menuPrincipal() {
        return ("\n--------------------------------------\n"
                + "1- Menu Filme\n"
                + "2- Menu Sala\n"
                + "3- Menu Sessões\n"
                + "4- Menu Venda\n"
                + "5- Menu Relatório\n"
                + "0- Sair da Aplicação"
                + "\n--------------------------------------");
    }
    /**
     * Menu do Modulo Filme
     * @return Retorna o menu com opções para acesso de cada operação do Filme
     */
    public static String menuFilme() {
        return ("\n--------------------------------------\n"
                + "1- Cadastrar Filme\n"
                + "2- Listar Filmes\n"
                + "3- Editar Filme\n"
                + "4- Deletar Filme\n"
                + "0- Voltar Menu Principal"
                + "\n--------------------------------------");
    }
    /**
     * Menu do modulo Sala
     * @return Retorna o menu com opções para acesso de cada operação do Sala
     */
    public static String menuSala() {
        return ("\n--------------------------------------\n"
                + "1- Cadastrar Sala\n"
                + "2- Listar Salas\n"
                + "3- Editar Sala\n"
                + "4- Deletar Sala\n"
                + "0- Voltar Menu Principal"
                + "\n--------------------------------------");
    }
    /**
     * Menu do modulo sessao
     * @return Retorna o menu para o acesso as operações da sessão
     */
    public static String menuSessao() {
        return ("\n--------------------------------------\n"
                + "1- Cadastrar Sessão\n"
                + "2- Listar Sessão\n"
                + "3- Editar Sessões\n"
                + "4- Deletar Sessão\n"
                + "0- Voltar Menu Principal"
                + "\n--------------------------------------");
    }
    /**
     * Menu do mudulo venda
     * @return Retorna o menu com opções para acesso de cada operação a ser realizada em venda
     */
    public static String menuVenda() {
        return ("\n--------------------------------------\n"
                + "1- Realizar Vendas\n"
                + "2- Listar Vendas\n"
                + "0- Voltar Menu Principal"
                + "\n--------------------------------------");
    }
    /***
     * Menu do modulo relatorios
     * @return retorna o menu para ter acesso aos relatorios do sistema
     */
    public static String menuRelatorio() {
        return ("\n--------------------------------------\n"
                + "1- \n"
                + "2- \n"
                + "0- Voltar Menu Principal"
                + "\n--------------------------------------");
    }
}
