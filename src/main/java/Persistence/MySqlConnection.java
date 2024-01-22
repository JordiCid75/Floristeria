package Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.fasterxml.jackson.databind.JsonNode;

import java.sql.ResultSet;

public class MySqlConnection implements IConnection {
	// assume that conn is an already created JDBC connection (see previous
	// examples)
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	public MySqlConnection() {
		// TODO Auto-generated constructor stub
	}
	public void execStmt(String sqlStmt) {

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT foo FROM bar");

			// or alternatively, if you don't know ahead of time that
			// the query will be a SELECT...

			if (stmt.execute("SELECT foo FROM bar")) {
				rs = stmt.getResultSet();
			}
			// Now do something with the ResultSet ....
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			// it is a good idea to release
			// resources in a finally{} block
			// in reverse-order of their creation
			// if they are no-longer needed

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore

				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore

				stmt = null;
			}
		}
	}

	@Override
	public void write() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getField(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getContent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JsonNode getContentNodes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNameTable(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(String content) {
		// TODO Auto-generated method stub
		
	}
}
