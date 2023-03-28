package com.ait.main;

import java.io.File;
import java.io.FileOutputStream;
// Step 1: Importing Database modules
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
// Importing API modules
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// Main (App) class shown only
// not its Connection class
public class ExportTableData {

	// Main driver method
	public static void main(String[] args) throws Exception
	{

		// Step 2 : Load and Register driver
		
		// Registering drivers using Driver Manager
		// Step 3: Establish. a connection
		Connection connection = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/mydb", "jdbc",
			"Abhijit");

		// Step 4: Process the statement
		// Getting data from the table details
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(
			"select * from tbl_students");

		// Step 5: Execute a query
		// Create a workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a spreadsheet inside a workbook
		XSSFSheet spreadsheet1
			= workbook.createSheet("student db");
		XSSFRow row = spreadsheet1.createRow(1);
		XSSFCell cell;

		// Step 6: Process the results
		cell = row.createCell(0);
		cell.setCellValue("RollNo");

		cell = row.createCell(1);
		cell.setCellValue("Name");

		// i=2 as we will start writing from the
		// 2'nd row
		int i = 2;

		while (resultSet.next()) {
			row = spreadsheet1.createRow(i);
			cell = row.createCell(0);
			cell.setCellValue(resultSet.getInt(1));

			cell = row.createCell(1);
			cell.setCellValue(resultSet.getString(2));

			i++;
		}

		// Local directory on computer
		FileOutputStream output = new FileOutputStream(new File(
			"E:\\ExcelSheet\\my.xlsx"));
		// write
		workbook.write(output);

		// Step 7: Close the connection
		output.close();

		// Display message for successful compilation of
		// program
		System.out.println(
			"exceldatabase.xlsx written successfully");
	}
}
