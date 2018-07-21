package com.business.util.resume;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ReadXls {
	public static void main(String[] args) {
		File file=new File("F:\\腾信公司\\51猎\\51LIEWeb\\资料\\功能对照表.xls");
		testRead(file);
	}
	// 解析一个Excel文件
	public  static void testRead(File file) {
		HSSFWorkbook workbook = null;
		DecimalFormat df = new DecimalFormat("#");
		try {
			workbook = new HSSFWorkbook(new FileInputStream(file));
			// POIFSFileSystem fs = new POIFSFileSystem(new
			// FileInputStream(filePath));
			// 获得Sheet数
			System.out.println("===SheetsNum===" + workbook.getNumberOfSheets());
			// 开始读取
			for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
				if (null != workbook.getSheetAt(i)) {
					// 获得一个Sheet
					HSSFSheet sheet = workbook.getSheetAt(i);
					System.out.println("共有" + sheet.getLastRowNum() + "行");
					for (int rowNumOfSheet = 10; rowNumOfSheet <= sheet.getLastRowNum(); rowNumOfSheet++) {
						if (null != sheet.getRow(rowNumOfSheet)) {
							// 获得一个row
							HSSFRow row = sheet.getRow(rowNumOfSheet);
							//System.out.print("第" + rowNumOfSheet + "行   ");
							// System.out.print("一行有"+row.getLastCellNum()+"单元格");
							for (short cellNumOfRow = 0; cellNumOfRow < row.getLastCellNum(); cellNumOfRow++) {
								HSSFCell cell = row.getCell(cellNumOfRow);
								int cellType = cell.getCellType();
								switch (cellType) {
								case 0:
									String strCell = df.format(cell.getNumericCellValue());
									System.out.print(strCell + " ");
									break;
								case 1:
									strCell = cell.getRichStringCellValue().getString();
									System.out.print(strCell + " ");
									break;
								default:
									System.out.println("数据格式不对！");
								}
							}
							System.out.print("/n");
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
