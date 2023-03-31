package utility;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;

public class ExcelReader {
     XSSFWorkbook wb;
    public ExcelReader()
    {
        File src= new File("./TestData/Data.xlsx");
        try
        {
            FileInputStream fis=new FileInputStream(src);
             wb =new XSSFWorkbook(fis);

//            String rs=wb.getSheet("Login").getRow(0).getCell(0).getStringCellValue();
        }
        catch(Exception e)
        {
            System.out.println("Exception"+ e);
        }


    }
//}
    public String getStringData(String sheetName, int row, int col)
    {
        return wb.getSheet(sheetName).getRow(row).getCell(col).getStringCellValue();
    }
    public String getStringData(int sheetIndex, int row, int col)
    {
        return wb.getSheetAt(sheetIndex).getRow(row).getCell(col).getStringCellValue();
    }
    public double getNumericData(int sheetIndex, int row, int col)
    {
        return wb.getSheetAt(sheetIndex).getRow(row).getCell(col).getNumericCellValue();
    }
}
