package AJDataAccess.AJDAOs;
//Refactorizacion
import AJDataAccess.AJDTOs.AJArmaDTO;
import AJDataAccess.AJHelpers.DataHelperSQLiteDAO;
import AJInfrastructura.AJAppException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AJArmaDAO extends DataHelperSQLiteDAO<AJArmaDTO> {
    public AJArmaDAO() throws AJAppException {
        super(AJArmaDTO.class, "Arma", "IdArma");
    }

    public boolean setEntrenada(int idArma) throws AJAppException {
        String sql = "UPDATE Arma SET Entrenada = 1 WHERE IdArma = ?";
        try {
            PreparedStatement ps = openConnection().prepareStatement(sql);
            ps.setInt(1, idArma);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new AJAppException(null, e, getClass(), "setEntrenada");
        }
}

}