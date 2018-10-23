/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

/**
 *
 * @author Dian
 */
public class ReportLoader extends Thread {

    private static ConcurrentHashMap<String, byte[]> h = new ConcurrentHashMap<String, byte[]>();

    public static void load() {
//	String baseFile = ParamLoader.getParam("REPORT-BASE-URL");
        String baseFile = "G:/TestJaspert";
        
        try {
            File dir = new File(baseFile);
            File files[] = dir.listFiles();
            for (File file : files) {
                if (file.getName().endsWith(".jrxml")) {
                    loadFile(file);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadFile(File file) throws Exception {
        if (!file.exists()) {
            System.out.println("file not found " + file.getAbsolutePath());
            System.exit(1);
        }
        InputStream in = new FileInputStream(file);
        try {
            byte[] b = new byte[(int) file.length()];
            in.read(b);
            h.put(file.getName().replaceAll(".jasper", ""), b);
        } finally {
            in.close();
        }
    }

    public static byte[] generatePDF(String reportName, HashMap param) throws Exception {
        String outputFilePDF = "G:/TestJaspert/test1.pdf";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(h.get(reportName));
        byte[] b = null;
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(in, param, new JREmptyDataSource());
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRPdfExporterParameter.IS_ENCRYPTED, Boolean.TRUE);
            exporter.setParameter(JRPdfExporterParameter.OWNER_PASSWORD, System.currentTimeMillis() + "");
            exporter.setParameter(JRPdfExporterParameter.USER_PASSWORD, "");
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRPdfExporterParameter.PERMISSIONS, new Integer(PdfWriter.ALLOW_DEGRADED_PRINTING));
            exporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, out);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outputFilePDF);

            exporter.exportReport();
            b = out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
            out.close();
        }

        return b;
    }
}
