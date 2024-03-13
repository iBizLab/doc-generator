package cn.ibizlab.codegen.groovy.util;

/**
 * 数据类型
 * 
 * @author lionlau
 *
 */
public final class DataTypes {
	
	final public static int UNKNOWN = 0;
	final public static int BIGINT = 1;
	final public static int BINARY = 2;
	final public static int BIT = 3;
	final public static int CHAR = 4;
	final public static int DATETIME = 5;
	final public static int DECIMAL = 6;
	final public static int FLOAT = 7;
	final public static int IMAGE = 8;
	final public static int INT = 9;
	final public static int MONEY = 10;
	final public static int NCHAR = 11;
	final public static int NTEXT = 12;
	final public static int NVARCHAR = 13;
	final public static int NUMERIC = 14;
	final public static int REAL = 15;
	final public static int SMALLDATETIME = 16;
	final public static int SMALLINT = 17;
	final public static int SMALLMONEY = 18;
	final public static int SQL_VARIANT = 19;
	final public static int SYSNAME = 20;
	final public static int TEXT = 21;
	final public static int TIMESTAMP = 22;
	final public static int TINYINT = 23;
	final public static int VARBINARY = 24;
	final public static int VARCHAR = 25;
	final public static int UNIQUEIDENTIFIER = 26;
	final public static int DATE = 27; // 纯日期型
	final public static int TIME = 28; // 纯时间
	final public static int BIGDECIMAL = 29; // 大数值
	
	
	
	final public static String NAME_UNKNOWN = "UNKNOWN";
	final public static String NAME_BIGINT = "BIGINT";
	final public static String NAME_BINARY = "BINARY";
	final public static String NAME_BIT = "BIT";
	final public static String NAME_CHAR = "CHAR";
	final public static String NAME_DATETIME = "DATETIME";
	final public static String NAME_DECIMAL = "DECIMAL";
	final public static String NAME_FLOAT = "FLOAT";
	final public static String NAME_IMAGE = "IMAGE";
	final public static String NAME_INT = "INT";
	final public static String NAME_MONEY = "MONEY";
	final public static String NAME_NCHAR = "NCHAR";
	final public static String NAME_NTEXT = "NTEXT";
	final public static String NAME_NVARCHAR = "NVARCHAR";
	final public static String NAME_NUMERIC = "NUMERIC";
	final public static String NAME_REAL = "REAL";
	final public static String NAME_SMALLDATETIME = "SMALLDATETIME";
	final public static String NAME_SMALLINT = "SMALLINT";
	final public static String NAME_SMALLMONEY = "SMALLMONEY";
	final public static String NAME_SQL_VARIANT = "SQL_VARIANT";
	final public static String NAME_SYSNAME = "SYSNAME";
	final public static String NAME_TEXT = "TEXT";
	final public static String NAME_TIMESTAMP = "TIMESTAMP";
	final public static String NAME_TINYINT = "TINYINT";
	final public static String NAME_VARBINARY = "VARBINARY";
	final public static String NAME_VARCHAR = "VARCHAR";
	final public static String NAME_UNIQUEIDENTIFIER = "UNIQUEIDENTIFIER";
	final public static String NAME_DATE = "DATE";
	final public static String NAME_TIME = "TIME";
	final public static String NAME_BIGDECIMAL = "BIGDECIMAL";
	

	public DataTypes() {
	}

	/**
	 * 从字符串构建
	 * 
	 * @param strValue
	 * @return
	 */
	final public static int fromString(String strValue) {
		
		if (strValue.compareToIgnoreCase("BIGINT") == 0) {
			return DataTypes.BIGINT;
		}

		if (strValue.compareToIgnoreCase("BINARY") == 0) {
			return DataTypes.BINARY;
		}

		if (strValue.compareToIgnoreCase("BIT") == 0) {
			return DataTypes.BIT;
		}

		if (strValue.compareToIgnoreCase("CHAR") == 0) {
			return DataTypes.CHAR;
		}

		if (strValue.compareToIgnoreCase("DATETIME") == 0) {
			return DataTypes.DATETIME;
		}

		if (strValue.compareToIgnoreCase("DECIMAL") == 0) {
			return DataTypes.DECIMAL;
		}

		if (strValue.compareToIgnoreCase("FLOAT") == 0) {
			return DataTypes.FLOAT;
		}

		if (strValue.compareToIgnoreCase("IMAGE") == 0) {
			return DataTypes.IMAGE;
		}

		if (strValue.compareToIgnoreCase("INT") == 0) {
			return DataTypes.INT;
		}

		if (strValue.compareToIgnoreCase("MONEY") == 0) {
			return DataTypes.MONEY;
		}

		if (strValue.compareToIgnoreCase("NCHAR") == 0) {
			return DataTypes.NCHAR;
		}

		if (strValue.compareToIgnoreCase("NTEXT") == 0) {
			return DataTypes.NTEXT;
		}

		if (strValue.compareToIgnoreCase("NVARCHAR") == 0) {
			return DataTypes.NVARCHAR;
		}

		if (strValue.compareToIgnoreCase("NUMERIC") == 0) {
			return DataTypes.NUMERIC;
		}

		if (strValue.compareToIgnoreCase("REAL") == 0) {
			return DataTypes.REAL;
		}

		if (strValue.compareToIgnoreCase("SMALLDATETIME") == 0) {
			return DataTypes.SMALLDATETIME;
		}

		if (strValue.compareToIgnoreCase("SMALLINT") == 0) {
			return DataTypes.SMALLINT;
		}

		if (strValue.compareToIgnoreCase("SMALLMONEY") == 0) {
			return DataTypes.SMALLMONEY;
		}

		if (strValue.compareToIgnoreCase("SQL_VARIANT") == 0) {
			return DataTypes.SQL_VARIANT;
		}

		if (strValue.compareToIgnoreCase("SYSNAME") == 0) {
			return DataTypes.SYSNAME;
		}

		if (strValue.compareToIgnoreCase("TEXT") == 0) {
			return DataTypes.TEXT;
		}

		if (strValue.compareToIgnoreCase("TIMESTAMP") == 0) {
			return DataTypes.TIMESTAMP;
		}

		if (strValue.compareToIgnoreCase("TINYINT") == 0) {
			return DataTypes.TINYINT;
		}

		if (strValue.compareToIgnoreCase("VARBINARY") == 0) {
			return DataTypes.VARBINARY;
		}

		if (strValue.compareToIgnoreCase("VARCHAR") == 0) {
			return DataTypes.VARCHAR;
		}

		if (strValue.compareToIgnoreCase("UNIQUEIDENTIFIER") == 0) {
			return DataTypes.UNIQUEIDENTIFIER;
		}

		if (strValue.compareToIgnoreCase("DATE") == 0) {
			return DataTypes.DATE;
		}

		if (strValue.compareToIgnoreCase("TIME") == 0) {
			return DataTypes.TIME;
		}

		if (strValue.compareToIgnoreCase("BIGDECIMAL") == 0) {
			return DataTypes.BIGDECIMAL;
		}

		return DataTypes.VARCHAR;
	}

	/**
	 * 获取字符串名称
	 * 
	 * @param nDataType
	 * @return
	 */
	final public static String toString(int nDataType) {
		if (nDataType == DataTypes.BIGINT) {
			return "BIGINT";
		}

		if (nDataType == DataTypes.BINARY) {
			return "BINARY";
		}

		if (nDataType == DataTypes.BIT) {
			return "BIT";
		}

		if (nDataType == DataTypes.CHAR) {
			return "CHAR";
		}

		if (nDataType == DataTypes.DATETIME) {
			return "DATETIME";
		}

		if (nDataType == DataTypes.DECIMAL) {
			return "DECIMAL";
		}

		if (nDataType == DataTypes.FLOAT) {
			return "FLOAT";
		}

		if (nDataType == DataTypes.IMAGE) {
			return "IMAGE";
		}

		if (nDataType == DataTypes.INT) {
			return "INT";
		}

		if (nDataType == DataTypes.MONEY) {
			return "MONEY";
		}

		if (nDataType == DataTypes.NCHAR) {
			return "NCHAR";
		}

		if (nDataType == DataTypes.NTEXT) {
			return "NTEXT";
		}

		if (nDataType == DataTypes.NVARCHAR) {
			return "NVARCHAR";
		}

		if (nDataType == DataTypes.NUMERIC) {
			return "NUMERIC";
		}

		if (nDataType == DataTypes.REAL) {
			return "REAL";
		}

		if (nDataType == DataTypes.SMALLDATETIME) {
			return "SMALLDATETIME";
		}

		if (nDataType == DataTypes.SMALLINT) {
			return "SMALLINT";
		}

		if (nDataType == DataTypes.SMALLMONEY) {
			return "SMALLMONEY";
		}

		if (nDataType == DataTypes.SQL_VARIANT) {
			return "SQL_VARIANT";
		}

		if (nDataType == DataTypes.SYSNAME) {
			return "SYSNAME";
		}

		if (nDataType == DataTypes.TEXT) {
			return "TEXT";
		}

		if (nDataType == DataTypes.TIMESTAMP) {
			return "TIMESTAMP";
		}

		if (nDataType == DataTypes.TINYINT) {
			return "TINYINT";
		}

		if (nDataType == DataTypes.VARBINARY) {
			return "VARBINARY";
		}

		if (nDataType == DataTypes.VARCHAR) {
			return "VARCHAR";
		}

		if (nDataType == DataTypes.UNIQUEIDENTIFIER) {
			return "UNIQUEIDENTIFIER";
		}

		if (nDataType == DataTypes.DATE) {
			return "DATE";
		}

		if (nDataType == DataTypes.TIME) {
			return "TIME";
		}

		if (nDataType == DataTypes.BIGDECIMAL) {
			return "BIGDECIMAL";
		}

		return "VARCHAR";
	}

}
