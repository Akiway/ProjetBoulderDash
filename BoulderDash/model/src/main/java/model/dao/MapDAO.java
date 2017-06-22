package model.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Map;


public abstract class MapDAO extends AbstractDAO {

	private static String sqlshowMaps = "{call showMaps(?)}";
	
	public static Returnmap getMapById(final int id, String idColumnIndex, String nameColumnIndex) throws SQLException {
        final CallableStatement callStatement = prepareCall(sqlshowMaps);
        Returnmap map = null;
        callStatement.setInt(1, id);
        if (callStatement.execute()) {
            final ResultSet result = callStatement.getResultSet();
            if (result.first()) {
                map = new Returnmap(result.getInt(idColumnIndex), result.getString(nameColumnIndex));
            }
            result.close();
        }
        return map;
    }

}
