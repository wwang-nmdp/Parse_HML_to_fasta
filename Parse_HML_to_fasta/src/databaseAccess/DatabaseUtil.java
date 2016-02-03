package databaseAccess;
import java.sql.*;

import ParseData.SequenceData;

public class DatabaseUtil
{
  public static final String DATA_BASE_NAME = "HLAsequence";
  public static final String TABLE_NAME = "HLAseqTable";
  public static final String ID = "ID";
  public static final String SAMPLE_ID = "SAMPLE_ID";
  public static final String LOCUS = "LOCUS";
  public static final String TYPE = "TYPE";
  public static final String GLS = "GLS";
  public static final String PHASE_SET = "PHASE_SET";
  public static final String SEQUENCE = "SEQ";
  
  
  static Connection connection;
  
  public static void connectDatabase(){
	  connection = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      connection= DriverManager.getConnection("jdbc:sqlite:"+DATA_BASE_NAME+".db");
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Opened database successfully");
  }
  
  public static void createTable(){
	  try {
		Statement stmt = connection.createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
				+ ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ SAMPLE_ID+" CHAR(50) NOT NULL,"
				+LOCUS+" CHAR(20) NOT NULL,"
				+TYPE+" CHAR(20) NOT NULL,"
				+GLS+" TEXT NOT NULL,"
				+PHASE_SET+" CHAR(10) NOT NULL,"
				+SEQUENCE+" TEXT NOT NULL,"
				+"UNIQUE ("+SAMPLE_ID+ ","+GLS+","+ PHASE_SET+" )"
				+");";
		stmt.executeUpdate(sql);
		stmt.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 System.out.println("Create table successfully");
  }
  
  public static void insertRow(SequenceData data) throws SQLException {
		Statement stmt = connection.createStatement();
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO "+ TABLE_NAME);
		sb.append("(");
		sb.append(SAMPLE_ID + ",");
		sb.append(LOCUS + ",");
		sb.append(TYPE + ",");
		sb.append(GLS + ",");
		sb.append(PHASE_SET + ",");
		sb.append(SEQUENCE + ")");
		sb.append("VALUES (");
		sb.append(data.getSampleId() + ",");
		sb.append(data.getLocus() + ",");
		sb.append(data.getType() + ",");
		sb.append(data.getGls() + ",");
		sb.append(data.getPhaseSet() + ",");
		sb.append(data.getSequence());
		sb.append(");");
		stmt.executeUpdate(sb.toString());
		stmt.close();
	  
  }
  
  public static void cleanUp(){
	  try {
		connection.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("Connection closed.");  
  }
}
