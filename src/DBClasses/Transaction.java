package DBClasses;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


public class Transaction implements IDBObject {

    private int id;
    private int sellerId;
    private int buyerId;
    private int articleId;
    private float amount;
    private float grossPrice;
    private float vat;
    private Timestamp tStamp;
    
    public Transaction(User seller, User buyer, Article article)
    {
	id=-1;
	sellerId=seller.getId();
	buyerId=buyer.getId();
	articleId=article.getId();
	grossPrice=article.getGrossPrice();
	vat=article.getVat();
	amount=article.getAmount();
	
	java.util.Calendar calendar = java.util.Calendar.getInstance();
	java.util.Date now = calendar.getTime();
	tStamp = new java.sql.Timestamp(now.getTime());
    }
    
    public void fillFromResultSet(ResultSet rSet) throws SQLException {
	id=rSet.getInt("idTransaktion");
	sellerId=rSet.getInt("idVerkaeufer");
	buyerId=rSet.getInt("idKaeufer");
	articleId=rSet.getInt("idArtikel");
	amount=rSet.getFloat("Menge");
	grossPrice=rSet.getFloat("Preis_Bruto");
	vat=rSet.getFloat("MwSt");
	tStamp=rSet.getTimestamp("Zeitstempel");
    }

    public String getUpdateStatement() throws SQLException {
	//Transaction should not be updated
	throw new SQLException("Transaction data should not be updated");
    }

    public String getCreateStatement() throws SQLException {
	String sqlStatement="INSERT INTO Transaktion (idVerkaeufer, idKaeufer, idArtikel, Menge, Preis_Bruto, MwSt, Zeitstempel) VALUE(";
	sqlStatement+=""+SQLDataUtilities.getSQLDataFormat(sellerId)+", ";
	sqlStatement+=""+SQLDataUtilities.getSQLDataFormat(buyerId)+", ";
	sqlStatement+=""+SQLDataUtilities.getSQLDataFormat(articleId)+", ";
	sqlStatement+=""+SQLDataUtilities.getSQLDataFormat(amount)+", ";
	sqlStatement+=""+SQLDataUtilities.getSQLDataFormat(grossPrice)+", ";
	sqlStatement+=""+SQLDataUtilities.getSQLDataFormat(vat)+", ";
	sqlStatement+=""+SQLDataUtilities.getSQLDataFormat(tStamp)+" ";
	sqlStatement+=");";
	return sqlStatement;
    }

    public String getDeleteStatement() throws SQLException {
	return "DELETE FROM Transaktion WHERE idTransaktion="+id+";";
    }

    /**
     * Get and Setter methods
     */
    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Timestamp gettStamp() {
        return tStamp;
    }

    public void settStamp(Timestamp tStamp) {
        this.tStamp = tStamp;
    }

    public int getId() {
        return id;
    }

    public int getSellerId() {
        return sellerId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public int getArticleId() {
        return articleId;
    }

    public float getGrossPrice() {
        return grossPrice;
    }

    public float getVat() {
        return vat;
    }
}
