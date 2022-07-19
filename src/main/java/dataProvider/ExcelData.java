package dataProvider;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelData {
    public void excelData()
    {
        try {
            String  projectPath=System.getProperty("user.dir");
            XSSFWorkbook workbook=new XSSFWorkbook(projectPath+"/dataFiles/BookingSheet.xlsx");
            XSSFSheet sheet=workbook.getSheet("Sheet1");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExcelData excelData=new ExcelData();
    }


    public void getRowCount()
    {
        try {
            String  projectPath=System.getProperty("user.dir");
            XSSFWorkbook workbook=new XSSFWorkbook(projectPath+"/dataFiles/BookingSheet.xlsx");
            XSSFSheet sheet=workbook.getSheet("Sheet1");
            int rowCount=sheet.getPhysicalNumberOfRows();
            System.out.println(rowCount);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public String getCellDataString(int numRow,int numCol)
    {
        String cellData="";
        try {
            String  projectPath=System.getProperty("user.dir");
            XSSFWorkbook workbook=new XSSFWorkbook(projectPath+"/dataFiles/BookingSheet.xlsx");
            XSSFSheet sheet=workbook.getSheet("Sheet1");
            cellData=sheet.getRow(numRow).getCell(numCol).getStringCellValue();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return cellData;
    }

    public int getCellDataNumber(int numRow, int numCol)
    {
        int cellData=0;
        try {
            String  projectPath=System.getProperty("user.dir");
            XSSFWorkbook workbook=new XSSFWorkbook(projectPath+"/dataFiles/BookingSheet.xlsx");
            XSSFSheet sheet=workbook.getSheet("Sheet1");
            cellData= (int) sheet.getRow(numRow).getCell(numCol).getNumericCellValue();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return cellData;
    }

}
