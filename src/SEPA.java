import java.sql.ResultSet;
import java.sql.SQLException;


public class SEPA implements IDBObject, IBankData {

    private int id;
    private String iban;
    private String bic_swift;
    private int userId;
    
    public String getType() {
	return "SEPA";
    }

    public void fillFromResultSet(ResultSet rSet) throws SQLException {
	id=rSet.getInt("idSEPA");
	iban=rSet.getString("IBAN");
	bic_swift=rSet.getString("BIC_SWIFT");
	userId=rSet.getInt("idNutzer");
    }

    public String getUpdateStatement() throws SQLException {
	String sqlStatement="UPDATE SEPA SET ";
	sqlStatement+="IBAN="+SQLDataUtilities.getSQLDataFormat(iban)+", ";
	sqlStatement+="BIC_SWIFT="+SQLDataUtilities.getSQLDataFormat(bic_swift)+" ";
	sqlStatement+="WHERE idSEPA="+SQLDataUtilities.getSQLDataFormat(id)+";";
	return sqlStatement;
    }

    public String getCreateStatement() throws SQLException {
	String sqlStatement="INSERT INTO SEPA (IBAN,BIC_SWIFT,idNutzer) VALUE(";
	sqlStatement+=""+SQLDataUtilities.getSQLDataFormat(iban)+", ";
	sqlStatement+=""+SQLDataUtilities.getSQLDataFormat(bic_swift)+", ";
	sqlStatement+=""+SQLDataUtilities.getSQLDataFormat(userId)+");";
	return sqlStatement;
    }

    public String getDeleteStatement() throws SQLException {
	return "DELETE FROM SEPA WHERE idSEPA="+id+";";
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic_swift() {
        return bic_swift;
    }

    public void setBic_swift(String bic_swift) {
        this.bic_swift = bic_swift;
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
