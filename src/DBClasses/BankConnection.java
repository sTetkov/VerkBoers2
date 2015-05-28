package DBClasses;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankConnection implements IBankData, IDBObject {

	private int id;
	private String acctNr;
	private String bankNr;
	private String bankName;
	private int userId;

	public String getType() {
		return "Bankverbindung";
	}

	public void fillFromResultSet(ResultSet rSet) throws SQLException {
		id = rSet.getInt("idBankverbindung");
		acctNr = rSet.getString("Konto_nr");
		bankNr = rSet.getString("BLZ");
		bankName = rSet.getString("Bank");
		userId = rSet.getInt("idNutzer");
	}

	public String getUpdateStatement() throws SQLException {
		String sqlStatement = "UPDATE Bankverbindung SET ";
		sqlStatement += "Konto_nr=" + SQLDataUtilities.getSQLDataFormat(acctNr)
				+ ", ";
		sqlStatement += "BLZ=" + SQLDataUtilities.getSQLDataFormat(bankNr)
				+ ", ";
		sqlStatement += "Bank=" + SQLDataUtilities.getSQLDataFormat(bankName)
				+ " ";
		sqlStatement += "WHERE idBankverbindung="
				+ SQLDataUtilities.getSQLDataFormat(id) + ";";
		return sqlStatement;
	}

	public String getCreateStatement() throws SQLException {
		String sqlStatement = "INSERT INTO Bankverbindung (Konto_nr,BLZ,Bank,idNutzer) VALUE(";
		sqlStatement += "" + SQLDataUtilities.getSQLDataFormat(acctNr) + ", ";
		sqlStatement += "" + SQLDataUtilities.getSQLDataFormat(bankNr) + ", ";
		sqlStatement += "" + SQLDataUtilities.getSQLDataFormat(bankName) + ", ";
		sqlStatement += "" + SQLDataUtilities.getSQLDataFormat(userId) + ");";
		return sqlStatement;
	}

	public String getDeleteStatement() throws SQLException {
		return "DELETE FROM SEPA WHERE Bankverbindung=" + id + ";";
	}

	/**
	 * Getter and Setter methods
	 */

	public String getAcctNr() {
		return acctNr;
	}

	public void setAcctNr(String acctNr) {
		this.acctNr = acctNr;
	}

	public String getBankNr() {
		return bankNr;
	}

	public void setBankNr(String bankNr) {
		this.bankNr = bankNr;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

}
