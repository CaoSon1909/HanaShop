/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.formatters;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ACER
 */
public class DateFormatter implements Serializable{
    
    private final String DELIMETER = "dd/MM/yyyy";
    
    public String formatDateToString(Date date){
        SimpleDateFormat format = new SimpleDateFormat(DELIMETER);
        String dateString = format.format(date);
        return dateString;
    }
    
    public Date formatStringToDate(String dateString) throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat(DELIMETER);
        Date date = format.parse(dateString);
        return date;
    }
    
}
