package controller.application.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Locale;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dian
 */
public class Constants {
    
//    private static String 
    
    public final static DecimalFormat dfWithCurrency = new DecimalFormat("'Rp.' #,##0.00");
    public final static DecimalFormat df = new DecimalFormat("#,###.00");
    public final static SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", new Locale("id","INDOENSIA","in-ID"));
    public final static String PATH_FILE_REPORT_JASPER = "/report/";
}
