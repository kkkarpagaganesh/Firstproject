package com.chainsys_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Connectdboptimize {

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		// TODO Auto-generated method stub

		Connection connection = getconnection();

		System.out
				.println("enter the option you want to do::\nPRESS 1-INSERT\nPRESS 2-UPDATE\nPRESS 3-DELETE\nPRESS 4-DISPLAY\nPRESS 5-PROCESS COMPLITION");
		Scanner scanner = new Scanner(System.in);

		// int change=scanner.nextInt();

		do {

			System.out.println("enter the option:");
			int change = scanner.nextInt();

			switch (change) {

			case 1:
				insertion(connection, scanner);

				break;

			case 2:
				
				updation(connection, scanner);
				

				break;

			case 3:
				deletion(connection, scanner);

				break;

			case 4:
				display(connection);
				break;

			default:
				System.out.println("INVALID OPTION");

				break;
			}
			System.out
					.println("if you want further process press respective number which is shown above:(otherwise press 5) ");

		} while (scanner.nextInt() != 5);

		scanner.close();
	}

	public static void display(Connection connection) throws SQLException {
		String sql3 = "select id,name,price from filesforjava ";
		System.out.println(sql3);
		System.out.println("your dataset is displayed ");
		PreparedStatement preparedstatement3 = connection
				.prepareStatement(sql3);

		ResultSet rset = preparedstatement3.executeQuery();
		while (rset.next()) { // "IF" STATEMENT USED TO ELIMINATE THE
								// COPY IF MORETHAN ONE
			System.out.print(rset.getInt("id")+"\t\t"); // "WHILE" STATEMENT
													// USED TO PRINT THE
													// ALL SAME COPY
			System.out.print(rset.getString("name")+"\t\t");
			System.out.println(rset.getInt("price"));}
			
			//descending order
			
			String sql4="select id,name,price from filesforjava order by name desc";
			System.out.println(sql4);
			System.out.println("your dataset is displayed based on descending order");
			PreparedStatement preparedstatement4 = connection
					.prepareStatement(sql4);

			ResultSet rset1 = preparedstatement4.executeQuery();
			while (rset1.next()) { // "IF" STATEMENT USED TO ELIMINATE THE
									// COPY IF MORETHAN ONE
				System.out.print(rset1.getInt("id")+"\t\t"); // "WHILE" STATEMENT
														// USED TO PRINT THE
														// ALL SAME COPY
				System.out.print(rset1.getString("name")+"\t\t");
				System.out.println(rset1.getInt("price"));}
			}	
	

	public static void deletion(Connection connection, Scanner scanner)
			throws SQLException {
		String sql2 = "delete from filesforjava where id=?";
		System.out.println(sql2);
		System.out.println("enter the file 'ID' you want to delete:");
		PreparedStatement preparedstatement2 = connection
				.prepareStatement(sql2);
		preparedstatement2.setInt(1, scanner.nextInt());

		preparedstatement2.executeUpdate();
		System.out.println("rows deleted: " );
	}

	public static Connection getconnection() throws ClassNotFoundException,
			SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver"); // load the class
		String url = "jdbc:oracle:thin:@localhost:1521:XE"; // get the
															// connection
		Connection connection = DriverManager.getConnection(url, "hr", "hr");
		System.out.println(connection);
		return connection;
	}

	public static void updation(Connection connection, Scanner scanner)
			throws SQLException {
		String sql1 = "update filesforjava set name= ? where id=?";
		System.out.println(sql1);
		System.out
				.println("enter the 'NAME' you want to update of the book where 'ID' is:");
		PreparedStatement preparedstatement1 = connection
				.prepareStatement(sql1);
		preparedstatement1.setInt(2, scanner.nextInt());
		preparedstatement1.setString(1, scanner.next());

		int rows1 = preparedstatement1.executeUpdate();
		System.out.println("rows updated: " + rows1);
	}

	public static void insertion(Connection connection, Scanner scanner)
			throws SQLException {
		String sql = "insert into filesforjava(id,name,price) values(?,?,?)";
		System.out.println(sql);
		System.out.println("enter the id,name,price of the book");
		PreparedStatement preparedstatement = connection
				.prepareStatement(sql);
		preparedstatement.setInt(1, scanner.nextInt());
		preparedstatement.setString(2, scanner.next());
		preparedstatement.setInt(3, scanner.nextInt());

		int rows = preparedstatement.executeUpdate();
		System.out.println("rows inserted: " + rows);
	}
}