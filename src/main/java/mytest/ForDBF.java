package mytest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ForDBF {
    public static void main(String[] args) throws IOException {
        /* Создаем новый файл dbf */
        File file = new File("example.dbf");
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");

        /* Создаем заголовки столбцов */
        Row headerRow = sheet.createRow(0);
        Cell headerCell1 = headerRow.createCell(0);
        headerCell1.setCellValue("Column1");
        Cell headerCell2 = headerRow.createCell(1);
        headerCell2.setCellValue("Column2");

        /* Создаем новую строку с данными */
        Row dataRow = sheet.createRow(1);
        Cell dataCell1 = dataRow.createCell(0);
        dataCell1.setCellValue("Value1");
        Cell dataCell2 = dataRow.createCell(1);
        dataCell2.setCellValue("Value2");

        /* Сохраняем файл */
        FileOutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
