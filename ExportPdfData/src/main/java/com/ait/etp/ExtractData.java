package com.ait.etp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ExtractData {

	public static void main(String[] args) throws SQLException, FileNotFoundException, DocumentException  {
		
		String sql="select * from tbl_students";
		Connection ct = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","jdbc","Abhijit");
	    Statement st = ct.createStatement();
	    ResultSet rs = st.executeQuery(sql);
	    
	    Document d=new Document();
	    
	    PdfWriter.getInstance(d, new FileOutputStream("E:\\ExtractPdf\\data.pdf"));
	    
	    d.open();
	    
	    PdfPTable pt=new PdfPTable(2);
	    
	    PdfPCell pc ;
	    pc=new PdfPCell(new Phrase("RollNO"));
	    pc=new PdfPCell(new Phrase("Name"));
	    
	    while(rs.next()) {
	    	int roll=rs.getInt(1);
	    	System.out.println(roll);
	    	pc=new PdfPCell(new Phrase(String.valueOf(roll)));
	    	pt.addCell(pc);
	    	String name=rs.getString(2);
	    	System.out.println(name);
	    	pc=new PdfPCell(new Phrase(name));
	    	pt.addCell(pc);
	    }
	    
	    d.add(pt);
	    d.close();
	    
	    st.close();
	    ct.close();
	}
}
