package com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.Util;

import com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.model.WithDrawalNotice;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class CSVFile {

    public static void createCSVFileOfNotices(Writer writer, List<WithDrawalNotice> withDrawalNotices){
        try{
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
            csvPrinter.printRecord("ID", "Product", "Amount", "Date");

            for (WithDrawalNotice notice: withDrawalNotices){
                csvPrinter.printRecord(notice.getId(), notice.getProductType(), notice.getAmount(), notice.getWithDrawalDate());
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
}
