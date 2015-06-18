package DBClasses;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Article implements IDBObject {

	private int id;

	public void setId(int id) {
		this.id = id;
	}

	private String shortDescription;
	private String longDescription;
	private float weight;
	private float amount;
	private float vat;
	private float grossPrice;
	private float netPrice;
	private Date expDate;
	private int idUser;
	private String state;

	public Article(int idUser) {
		id = -1;
		shortDescription = new String();
		amount = 0;
		vat = 0;
		expDate = new Date(200, 1, 1);
		this.idUser = idUser;
		state = "OK";
	}

	public void fillFromResultSet(ResultSet rSet) throws SQLException {
		id = rSet.getInt("idArtikel");
		shortDescription = rSet.getString("Bezeichnung");
		longDescription = rSet.getString("Beschreibung");
		weight = rSet.getFloat("Gewicht");
		amount = rSet.getFloat("Anzahl");
		vat = rSet.getFloat("MwSt");
		grossPrice = rSet.getFloat("Preis_Brutto");
		netPrice = rSet.getFloat("Preis_Netto");
		expDate = rSet.getDate("AblaufDatum");
		idUser = rSet.getInt("idNutzer");
		state = rSet.getString("Zustand");

	}

	public String getUpdateStatement() throws SQLException {
		if (shortDescription.length() == 0)
			throw new SQLException("Bezeichnung is a NOT NULL field.");
		if (shortDescription.length() > 140)
			throw new SQLException(
					"Bezeichnung can be miximal 140 characters long.");
		String sqlStatement = "UPDATE Artikel SET ";
		sqlStatement += "Bezeichnung="
				+ SQLDataUtilities.getSQLDataFormat(shortDescription) + ", ";
		sqlStatement += "Beschreibung="
				+ SQLDataUtilities.getSQLDataFormat(longDescription) + ", ";
		sqlStatement += "Gewicht=" + SQLDataUtilities.getSQLDataFormat(weight)
				+ ", ";
		sqlStatement += "Anzahl=" + SQLDataUtilities.getSQLDataFormat(amount)
				+ ", ";
		sqlStatement += "MwSt=" + SQLDataUtilities.getSQLDataFormat(vat) + ", ";
		sqlStatement += "Preis_Brutto="
				+ SQLDataUtilities.getSQLDataFormat(grossPrice) + ", ";
		sqlStatement += "Preis_Netto="
				+ SQLDataUtilities.getSQLDataFormat(netPrice) + ", ";
		sqlStatement += "AblaufDatum="
				+ SQLDataUtilities.getSQLDataFormat(expDate) + ", ";
		sqlStatement += "idNutzer=" + SQLDataUtilities.getSQLDataFormat(idUser)
				+ ", ";
		sqlStatement += "Zustand=" + SQLDataUtilities.getSQLDataFormat(state)
				+ " ";
		sqlStatement += "WHERE idArtikel="
				+ SQLDataUtilities.getSQLDataFormat(id) + ";";
		return sqlStatement;
	}

	public String getCreateStatement() throws SQLException {
		if (shortDescription.length() == 0)
			throw new SQLException("Bezeichnung is a NOT NULL field.");
		if (shortDescription.length() > 140)
			throw new SQLException(
					"Bezeichnung can be miximal 140 characters long.");
		String sqlStatement = "INSERT INTO Artikel (Bezeichnung, Beschreibung, Gewicht, Anzahl, MwSt, Preis_Brutto, Preis_Netto, AblaufDatum, idNutzer, Zustand) VALUES(";
		sqlStatement += ""
				+ SQLDataUtilities.getSQLDataFormat(shortDescription) + ", ";
		sqlStatement += "" + SQLDataUtilities.getSQLDataFormat(longDescription)
				+ ", ";
		sqlStatement += "" + SQLDataUtilities.getSQLDataFormat(weight) + ", ";
		sqlStatement += "" + SQLDataUtilities.getSQLDataFormat(amount) + ", ";
		sqlStatement += "" + SQLDataUtilities.getSQLDataFormat(vat) + ", ";
		sqlStatement += "" + SQLDataUtilities.getSQLDataFormat(grossPrice)
				+ ", ";
		sqlStatement += "" + SQLDataUtilities.getSQLDataFormat(netPrice) + ", ";
		sqlStatement += "" + SQLDataUtilities.getSQLDataFormat(expDate) + ", ";
		sqlStatement += "" + SQLDataUtilities.getSQLDataFormat(idUser) + ", ";
		sqlStatement += "" + SQLDataUtilities.getSQLDataFormat(state) + " ";
		sqlStatement += ");";
		return sqlStatement;
	}

	public String getDeleteStatement() throws SQLException {
		if (id == -1)
			throw new SQLException("No data loaded, nothing to delete.");
		String sqlStatement = "DELETE FROM Artikel WHERE idArtikel=" + id + ";";
		return sqlStatement;
	}

	/**
	 * Getter and Setter methods
	 */
	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		if (shortDescription.length() > 140)
			this.shortDescription = shortDescription.substring(0, 140);
		else
			this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getVat() {
		return vat;
	}

	public void setVat(float vat) {
		this.vat = vat;
		netPrice = grossPrice * (1 + vat / 100);
	}

	public float getGrossPrice() {
		return grossPrice;
	}

	public void setGrossPrice(float grossPrice) {
		this.grossPrice = grossPrice;
		netPrice = grossPrice * (1 + vat / 100);
	}

	public float getNetPrice() {
		return netPrice;
	}

	public void setNetPrice(float netPrice) {
		this.netPrice = netPrice;
		grossPrice = netPrice / (1 + vat / 100);
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public int getId() {
		return id;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
