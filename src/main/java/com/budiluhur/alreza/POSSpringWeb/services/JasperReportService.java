package com.budiluhur.alreza.POSSpringWeb.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.budiluhur.alreza.POSSpringWeb.Entity.MasterTransaction;
import com.budiluhur.alreza.POSSpringWeb.repository.TransactionDetailRepository.listTransactionDetail;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Service
@Slf4j
public class JasperReportService {

    private byte[] generateJasperPDF(String resourcePath, Map<String, Object> jasperParam, JRBeanCollectionDataSource dataSource) throws IOException, JRException{
        log.info("starting to compile...");
        System.out.println("starting to generateJasper");

        InputStream inputStream = null;
        byte[] out = null;

        try {
            System.out.println(resourcePath);
            inputStream = JasperReportService.class.getResourceAsStream(resourcePath);
            System.out.println("starting to compile");
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            System.out.println("after compile"+jasperReport);
//            inputStream.close();
            // JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParam, new JREmptyDataSource());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParam, dataSource);
            System.out.println("after jasperPrint");
            JRPdfExporter exporter = new JRPdfExporter();

            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
            exporter.exportReport();
            log.info("generate jasper document successfully!");
            System.out.println("generate jasper document successfully!");
            out = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("error jasper "+e.getMessage());
        } finally {
            if(inputStream !=null){
                inputStream.close();
            }
        }
        return out;
    }

    public byte[] generateInvoicePDF(MasterTransaction masterTransaction, List<listTransactionDetail> detail) throws JRException, IOException{
        Map<String, Object> jasperParam = new HashMap<String, Object>();
        jasperParam.put("id", masterTransaction.getId());
        jasperParam.put("username", masterTransaction.getUsername());
        jasperParam.put("grandTotal", masterTransaction.getGrandTotal());
        jasperParam.put("transactionTime", masterTransaction.getTransactionTime());

        JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(detail);
        return generateJasperPDF("/templates/invoice.jrxml", jasperParam, datasource);
    }
}
