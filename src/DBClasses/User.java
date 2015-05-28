package DBClasses;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User implements IDBObject {

	private int id;

	private String name;
	private String surname;
	private String eMail;
	private String Str;
	private String nr;
	private String plz;
	private String city;
	private String country;
	private String state;

	private IBankData bankData;

	public User() {
		eMail = new String();
		id = -1;
	}

	public String getUpdateStatement() throws SQLException {
		if (id == -1)
			throw new SQLException("No data loaded, nothing to update.");
		if (eMail.length() == 0)
			throw new SQLException("EMAIL is a NOT NULL field.");
		String sqlStatement = "UPDATE Nutzer SET ";
		sqlStatement += "Vorname=" + SQLDataUtilities.getSQLDataFormat(name)
				+ ", ";
		sqlStatement += "Nachname="
				+ SQLDataUtilities.getSQLDataFormat(surname) + ", ";
		sqlStatement += "EMAIL=" + SQLDataUtilities.getSQLDataFormat(eMail)
				+ ", ";
		sqlStatement += "Strasse=" + SQLDataUtilities.getSQLDataFormat(Str)
				+ ", ";
		sqlStatement += "Nr=" + SQLDataUtilities.getSQLDataFormat(nr) + ", ";
		sqlStatement += "PLZ=" + SQLDataUtilities.getSQLDataFormat(plz) + ", ";
		sqlStatement += "Ort=" + SQLDataUtilities.getSQLDataFormat(city) + ", ";
		sqlStatement += "Land=" + SQLDataUtilities.getSQLDataFormat(country)
				+ ", ";
		sqlStatement += "Zustand=" + SQLDataUtilities.getSQLDataFormat(state)
				+ " ";
		sqlStatement += "WHERE idNutzer="
				+ SQLDataUtilities.getSQLDataFormat(id) + ";";
		return sqlStatement;
	}

	public void fillFromResultSet(ResultSet rSet) throws SQLException {
		id = rSet.getInt("idNutzer");
		name = rSet.getString("Vorname");
		surname = rSet.getString("Nachname");
		eMail = rSet.getString("EMAIL");
		Str = rSet.getString("Strasse");
		nr = rSet.getString("Nr");
		plz = rSet.getString("PLZ");
		city = rSet.getString("Ort");
		country = rSet.getString("Land");
		state = rSet.getString("Zustand");
	}

	public String getCreateStatement() throws SQLException {
		if (eMail.length() == 0)
			throw new SQLException("EMAIL is a NOT NULL field.");
		String sqlStatement = "INSERT INTO Nutzer (Nachname, Vorname, EMAIL, Strasse, Nr, PLZ, Ort, Land, Zustand) VALUES(";
		sqlStatement += "" + SQLDataUtilities.getSQLDataFormat(surname) + ", ";
		sqlStatement += "" + SQLDataUtilities.getSQLDataFormat(name) + ", ";
		sqlStatement += "" + SQLDataUtilities.getSQLDataFormat(eMail) + ", ";
		sqlStatement += "" + SQLDataUtilities.getSQLDataFormat(Str) + ", ";
		sqlStatement += "" + SQLDataUtilities.getSQLDataFormat(nr) + ", ";
		sqlStatement += "" + SQLDataUtilities.getSQLDataFormat(plz) + ", ";
		sqlStatement += "" + SQLDataUtilities.getSQLDataFormat(city) + ", ";
		sqlStatement += "" + SQLDataUtilities.getSQLDataFormat(country) + ", ";
		sqlStatement += "" + SQLDataUtilities.getSQLDataFormat(state) + " ";
		sqlStatement += ");";
		return sqlStatement;
	}

	public String getDeleteStatement() throws SQLException {
		if (id == -1)
			throw new SQLException("No data loaded, nothing to delete.");
		String sqlStatement = "DELETE FROM Nutzer WHERE idNutzer=" + id + ";";
		return sqlStatement;
	}

	/**
	 * Getter and Setter methods
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getStr() {
		return Str;
	}

	public void setStr(String str) {
		Str = str;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getId() {
		return id;
	}

	public IBankData getBankData() {
		return bankData;
	}

	public void setBankData(IBankData bankData) {
		this.bankData = bankData;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
