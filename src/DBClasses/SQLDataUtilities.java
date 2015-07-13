package DBClasses;

import java.sql.Date;
import java.sql.Timestamp;

public class SQLDataUtilities {
	public static String getSQLDataFormat(String str) {
		if (str == null)
			return "NULL";
		if (str.length() == 0)
			return "NULL";
		return "'" + str + "'";
	}

	public static String getSQLDataFormat(Date date) {
		if (date == null)
			return "NULL";
		return "'" + date.toString() + "'";
	}

	public static String getSQLDataFormat(int i) {
		return "" + i;
	}

	public static String getSQLDataFormat(float f) {
		return "" + f;
	}

	public static String getSQLDataFormat(Timestamp t) {
		return "'" + t.toString() + "'";
	}
}
