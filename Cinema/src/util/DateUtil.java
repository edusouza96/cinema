package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class para formatação de data
 */
public class DateUtil {
    /**
     * Recebe uma string e passa para formato date
     * @param data Recebe String
     * @return Retorna date dd/mm/yyyy
     * @throws ParseException tratamento erro
     */
    public static Date stringToDate(String data) throws ParseException
    {
        return(new SimpleDateFormat("dd/MM/yyyy").parse(data));
    }
    public static Date stringToDateEUA(String data) throws ParseException
    {
        return(new SimpleDateFormat("yyyy/MM/dd").parse(data));
    }
    /**
     * Recebe uma string e passa para formato date
     * @param data recebe uma string
     * @return retorna uma hora HH:mm
     * @throws ParseException tratamento erro
     */
    public static Date stringToHour(String data) throws ParseException
    {
        return(new SimpleDateFormat("HH:mm").parse(data));
    }

    /**
     * Recebe uma string e passar para o formato datetime
     * @param data recebe uma string
     * @return retorna date dd/mm/yyyy hh:mm
     * @throws ParseException tratamento erro
     */
    public static Date stringToDateHour(String data) throws ParseException
    {
        return(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(data));
    }
    /**
     * Recebe uma data no formato date e transforma em string
     * @param data recebe uma date
     * @return Retorna string
     */
    public static String dateToString(Date data){
        return(new SimpleDateFormat("dd/MM/yyyy").format(data));
    }
    public static String dateToStringEUA(Date data){
        return(new SimpleDateFormat("yyyy-MM-dd").format(data));
    }
    
    /**
     *Recebe uma hora no formato date e transforma em String
     * @param hora recebe uma hora
     * @return retorna String
     */
    public static String hourToString(Date hora){
        return(new SimpleDateFormat("HH:mm").format(hora));
    }
    /**
     * Recebe uma data no formato datetime e transforma em string
     * @param data recebe uma data e hora
     * @return Retorna string
     */
    public static String dateHourToString(Date data){
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dataString = formatador.format(data);
        return(dataString);
    }
    /**
     * recebe uma data e verifica se esta no padrao definido dd/mm/yyyy
     * @param data recebe por parametro uma data a verificar
     * @return retorna a data verificada
     */
    public static boolean verificaData(String data)
    {
       return(data.matches("\\d{2}/\\d{2}/\\d{4}"));
    }
    /**
     * recebe uma data e hora e verifica se esta no padrao definido dd/mm/yyyy hh:mm
     * @param data recebe por parametro uma data\hora a verificar
     * @return retorna a data\hora verificada
     */
    public static boolean verificaDataHora(String data)
    {
       return(data.matches("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}"));
    }
}
