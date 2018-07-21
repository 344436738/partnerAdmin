package com.business.util.resume;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class ReandDocx {
	public static void main(String[] args) throws Exception {
		String path="F:\\51简历\\异常简历\\李文亮.docx";
		ReandDocx.testReadByDoc(path);
	}
	public  static void testReadByDoc(String path) throws Exception {
		InputStream is = new FileInputStream(path);
		XWPFDocument doc = new XWPFDocument(is);
		List<XWPFParagraph> paras = doc.getParagraphs();
		for (XWPFParagraph para : paras) {
			// 当前段落的属性
			// CTPPr pr = para.getCTP().getPPr();
			System.out.println("1:"+para.getText());
		}
		// 获取文档中所有的表格
		List<XWPFTable> tables = doc.getTables();
		List<XWPFTableRow> rows;
		List<XWPFTableCell> cells;
		for (XWPFTable table : tables) {
			// 表格属性
			// CTTblPr pr = table.getCTTbl().getTblPr();
			// 获取表格对应的行
			rows = table.getRows();
			for (XWPFTableRow row : rows) {
				// 获取行对应的单元格
				cells = row.getTableCells();
				for (XWPFTableCell cell : cells) {
					System.out.println(cell.getText());
				}
			}
		}
		close(is);
	}

	/**
	 * 关闭输入流
	 * 
	 * @param is
	 */
	private  static void close(InputStream is) {
		if (is != null) {
			try {
				is.close();
			} catch (java.io.IOException e) {
				e.printStackTrace();
			}
		}
	}

}
