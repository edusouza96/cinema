package util;

import java.util.Scanner;
/**
 * Classe com metodos para fazer captura do teclado
 */

public class Console {
	/**
         * @param out: recebe uma mensagem a ser exibida
         * @return retorna um scanner para ler String
         */
	public static String scanString(Object out)
	{
		System.out.print(out);
		Scanner scanner = new Scanner (System.in);
		return(scanner.nextLine());
	}
	/**
         * @param out: recebe uma mensagem a ser exibida
         * @return retorna um scanner para ler Integer
         */
	public static int scanInt(Object out)
	{
		System.out.print(out);
		Scanner scanner = new Scanner (System.in);
		return(scanner.nextInt());		
	}
        /**
         * @param out: recebe uma mensagem a ser exibida
         * @return retorna um scanner para ler Double
         */
	public static double scanDouble(Object out)
	{
		System.out.print(out);
		Scanner scanner = new Scanner (System.in);
		return(scanner.nextDouble());		
	}
        /**
         * @param out: recebe uma mensagem a ser exibida
         * @return retorna um scanner para ler float
         */
	public static float scanFloat(Object out)
	{
		System.out.print(out);
		Scanner scanner = new Scanner (System.in);
		return(scanner.nextFloat());		
	}
        /**
         * @param out: recebe uma mensagem a ser exibida
         * @return retorna um scanner para ler Booleano
         */
	public static boolean scanBoolean(Object out)
	{
		System.out.print(out);
		Scanner scanner = new Scanner (System.in);
		return(scanner.nextBoolean());		
	}
	/**
         * @param out: recebe uma mensagem a ser exibida
         * @return retorna um scanner para ler Char
         */
	public static char scanChar(Object out)
	{
		System.out.print(out);
		Scanner scanner = new Scanner (System.in);
		return(scanner.next().charAt(0));				
	}
	
}
