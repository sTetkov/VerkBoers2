import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;


public interface IDBObject extends Serializable{
    public void fillFromResultSet(ResultSet rSet) throws SQLException;
    public String getUpdateStatement() throws SQLException;
    public String getCreateStatement() throws SQLException;
    public String getDeleteStatement() throws SQLException;
}
