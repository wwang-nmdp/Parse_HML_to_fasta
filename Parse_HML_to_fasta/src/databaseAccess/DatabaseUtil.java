package databaseAccess;
import java.sql.*;

import ParseData.SequenceData;
import parseExon.ExonIntronData;

public class DatabaseUtil
{
  public static final String DATA_BASE_NAME = "HLAsequence";
  public static final String HLA_TABLE_NAME = "HLAseqTable";
  public static final String EXON_TABLE_NAME = "ExonTable";
  public static final String ID = "ID";
  public static final String SAMPLE_ID = "SAMPLE_ID";
  public static final String LOCUS = "LOCUS";
  public static final String TYPE = "TYPE";
  public static final String GLS = "GLS";
  public static final String PHASE_SET = "PHASE_SET";
  public static final String SEQUENCE = "SEQ";
  public static final String FIVE_NS = "UTR5";
  public static final String EXON1 = "EXON1";
  public static final String EXON2 = "EXON2";
  public static final String EXON3 = "EXON3";
  public static final String EXON4 = "EXON4";
  public static final String EXON5 = "EXON5";
  public static final String EXON6 = "EXON6";
  public static final String EXON7 = "EXON7";
  public static final String EXON8 = "EXON8";
  public static final String EXON1_PL = "EXON1_PL";
  public static final String EXON2_PL = "EXON2_PL";
  public static final String EXON3_PL = "EXON3_PL";
  public static final String EXON4_PL = "EXON4_PL";
  public static final String EXON5_PL = "EXON5_PL";
  public static final String EXON6_PL = "EXON6_PL";
  public static final String EXON7_PL = "EXON7_PL";
  public static final String EXON8_PL = "EXON8_PL";
  public static final String INTRON1 = "INTRON1";
  public static final String INTRON2 = "INTRON2";
  public static final String INTRON3 = "INTRON3";
  public static final String INTRON4 = "INTRON4";
  public static final String INTRON5 = "INTRON5";
  public static final String INTRON6 = "INTRON6";
  public static final String INTRON7 = "INTRON7";
  public static final String THREE_NS = "UTR3";
  
  
  
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
			String sql = "CREATE TABLE IF NOT EXISTS " + HLA_TABLE_NAME + "("
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
  
  public static void creatExonTable(){
	  try {
			Statement stmt = connection.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS " + EXON_TABLE_NAME + "("
					+ ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ HLA_TABLE_NAME + " INTEGER NOT NULL,"
					+ SAMPLE_ID+" CHAR(50) NOT NULL,"
					+GLS+" TEXT NOT NULL,"
				+PHASE_SET+" CHAR(10) NOT NULL,"
				+FIVE_NS+" TEXT NOT NULL,"
				+EXON1+ " TEXT NOT NULL,"
				+INTRON1+ " TEXT NOT NULL,"
				+EXON2+ " TEXT NOT NULL,"
				+INTRON2+ " TEXT NOT NULL,"
				+EXON3+ " TEXT NOT NULL,"
				+INTRON3+ " TEXT NOT NULL,"
				+EXON4+ " TEXT NOT NULL,"
				+INTRON4+ " TEXT NOT NULL,"
				+EXON5+ " TEXT NOT NULL,"
				+INTRON5+ " TEXT NOT NULL,"
				+EXON6+ " TEXT NOT NULL,"
				+INTRON6+ " TEXT NOT NULL,"
				+EXON7+ " TEXT NOT NULL,"
				+INTRON7+ " TEXT NOT NULL,"
				+EXON8+ " TEXT NOT NULL,"
				+THREE_NS+ " TEXT NOT NULL,"
				+EXON1_PL+ " TEXT NOT NULL,"
				+EXON2_PL+ " TEXT NOT NULL,"
				+EXON3_PL+ " TEXT NOT NULL,"
				+EXON4_PL+ " TEXT NOT NULL,"
				+EXON5_PL+ " TEXT NOT NULL,"
				+EXON6_PL+ " TEXT NOT NULL,"
				+EXON7_PL+ " TEXT NOT NULL,"
				+EXON8_PL+ " TEXT NOT NULL,"
					+"UNIQUE ("+SAMPLE_ID+ ","+GLS+","+ PHASE_SET+" )"
					+");";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println("Create exon table successfully");
	  }
	  
  
  
  public static void insertSeqData(SequenceData data) throws SQLException {
		Statement stmt = connection.createStatement();
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO "+ HLA_TABLE_NAME);
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
  
  public static void insertExonData(ExonIntronData data) throws SQLException {
		Statement stmt = connection.createStatement();
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO "+ EXON_TABLE_NAME);
		sb.append("(");
		sb.append(HLA_TABLE_NAME + ",");
		sb.append(SAMPLE_ID + ",");
		sb.append(GLS + ",");
		sb.append(PHASE_SET + ",");
		sb.append(FIVE_NS + ",");
		sb.append(EXON1 + ",");
		sb.append(INTRON1 + ",");
		sb.append(EXON2 + ",");
		sb.append(INTRON2 + ",");
		sb.append(EXON3 + ",");
		sb.append(INTRON3 + ",");
		sb.append(EXON4 + ",");
		sb.append(INTRON4 + ",");
		sb.append(EXON5 + ",");
		sb.append(INTRON5 + ",");
		sb.append(EXON6 + ",");
		sb.append(INTRON6 + ",");
		sb.append(EXON7 + ",");
		sb.append(INTRON7 + ",");
		sb.append(EXON8 + ",");
		sb.append(THREE_NS + ",");
		sb.append(EXON1_PL + ",");
		sb.append(EXON2_PL + ",");
		sb.append(EXON3_PL + ",");
		sb.append(EXON4_PL + ",");
		sb.append(EXON5_PL + ",");
		sb.append(EXON6_PL + ",");
		sb.append(EXON7_PL + ",");
		sb.append(EXON8_PL + ")");
		
		sb.append("VALUES (");
		sb.append(data.getID() + ",");
		sb.append(wrapString(data.getSampleID()) + ",");
		
		sb.append(wrapString(data.getGls()) + ",");
		sb.append(wrapString(data.getPhase()) + ",");
		sb.append(wrapString(data.getFive_NS()) + ",");
		sb.append(wrapString(data.getExon1()) + ",");
		sb.append(wrapString(data.getIntron1()) + ",");
		sb.append(wrapString(data.getExon2()) + ",");
		sb.append( wrapString(data.getIntron2()) + ",");
		sb.append(wrapString(data.getExon3()) + ",");
		sb.append(wrapString(data.getIntron3()) + ",");
		sb.append(wrapString(data.getExon4()) + ",");
		sb.append(wrapString(data.getIntron4()) + ",");
		sb.append(wrapString(data.getExon5()) + ",");
		sb.append(wrapString(data.getIntron5()) + ",");
		sb.append(wrapString(data.getExon6()) + ",");
		sb.append(wrapString(data.getIntron6()) + ",");
		sb.append(wrapString(data.getExon7()) + ",");
		sb.append(wrapString(data.getIntron7()) + ",");
		sb.append(wrapString(data.getExon8()) + ",");
		sb.append(wrapString(data.getThree_NS())+ ",");
		sb.append(wrapString(data.getExon1_pl())+ ",");
		sb.append(wrapString(data.getExon2_pl())+ ",");
		sb.append(wrapString(data.getExon3_pl())+ ",");
		sb.append(wrapString(data.getExon4_pl())+ ",");
		sb.append(wrapString(data.getExon5_pl())+ ",");
		sb.append(wrapString(data.getExon6_pl())+ ",");
		sb.append(wrapString(data.getExon7_pl())+ ",");
		sb.append(wrapString(data.getExon8_pl()));
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
  
  private static  String wrapString(String str){
		return "'" +str+ "'";
	}
}
