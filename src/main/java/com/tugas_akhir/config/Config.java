package com.tugas_akhir.config;
import java.sql.*;

public final class Config {
	private static String DRIVER = "com.mysql.jdbc.Driver";

	/* Aturlah nilai URL sesuai Database
	* 	format = "jdbc:mysql://host:port/DatabaseName" */
	private static String URL = "jdbc:mysql://localhost:3306/HiPetani";

	/* Aturlah nilai ROOT sesuai dengan username database (default = root) */
	private static String ROOT = "root";

	/* Aturlah nilai PASSWORD sesuai dengan password database (default = "") */
	private static String PASSWORD = "root1234";
	private static Connection conn = null;
	
	// method untuk koneksi ke database
	public static Connection connection() throws ClassNotFoundException,SQLException{
		Class.forName(DRIVER);
		conn = DriverManager.getConnection(URL, ROOT, PASSWORD);
		return conn;
	}
}
