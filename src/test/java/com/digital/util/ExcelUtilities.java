package com.digital.util;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtilities {

    XSSFWorkbook xlsxWorkbook;

    public ExcelUtilities() {
    }

    public ExcelUtilities(String workbookPathName) {
        File xlFile = new File(workbookPathName);
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(xlFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Assert.fail("XL File '" + workbookPathName + "' doesn't exist!!");
        }

        try {
            xlsxWorkbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ExcelUtilities(File xlFile) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(xlFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Assert.fail("XL File '" + xlFile.getName() + "' doesn't exist!!");
        }

        try {
            xlsxWorkbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void writeCSV(String strPath, List<String> listRecord) throws IOException {
        FileWriter writer = new FileWriter(strPath);
        boolean first = true;
        StringBuilder sb = new StringBuilder();
        for (String value : listRecord) {
            sb.append(value).append(",");
        }
        sb.append("\n");
        writer.append(sb.toString());
        writer.flush();
        writer.close();

    }

    public boolean deleteCSV(String strFilepath) {
        //Deletion of file if file exist
        boolean bFileDelete = false;
        try {
            File file = new File(strFilepath);
            if (file.exists())
                bFileDelete = file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bFileDelete;
    }


    String getCellData(XSSFCell cell) {
        String cellData = null;
        try {
            switch (cell.getCellType()) {
                case STRING:
                    cellData = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    cellData = String.valueOf((int) cell.getNumericCellValue());
                    break;
                case BLANK:
                    cellData = "";
                    break;
            }
        } catch (NullPointerException npe) {
            cellData = "";
        }
        return cellData;
    }

    public List<List<String>> readParallelExecutionData(String suiteName) {
        List<List<String>> dataList = new ArrayList<>();

        XSSFSheet worksheet = xlsxWorkbook.getSheet(suiteName);
        if (worksheet == null) {
            Assert.fail("Unable to get/read sheet '" + suiteName + "'");
        }

        int columnCount = worksheet.getRow(0).getPhysicalNumberOfCells();

        for (int colLoopVar = 0; colLoopVar < columnCount; colLoopVar++) {
            List<String> columnData = new ArrayList<>();

            for (int rowLoopVar = 1; rowLoopVar <= worksheet.getLastRowNum(); rowLoopVar++) {
                XSSFCell cell = worksheet.getRow(rowLoopVar).getCell(colLoopVar);
                String cellData = getCellData(cell);
                if (!cellData.isEmpty()) {
                    columnData.add(cellData);
                }
            }

            if (columnData.size() > 0) {
                dataList.add(columnData);
            }
        }

        try {
            xlsxWorkbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        xlsxWorkbook = null;

        return dataList;
    }

    public String generateDataFilePathPartner(String partner, String absPath, String fileName) {
        return absPath.concat(fileName.split("\\.")[0] + "_" + partner.toUpperCase()
                + "." + absPath.concat(fileName).split("\\.")[1]);
    }
}
