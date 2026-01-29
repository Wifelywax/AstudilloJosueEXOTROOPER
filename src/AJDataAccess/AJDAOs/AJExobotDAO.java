package AJDataAccess.AJDAOs;    

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import AJDataAccess.AJDTOs.AJExobotDTO;
import AJDataAccess.AJHelpers.DataHelperSQLiteDAO;
import AJInfrastructura.AJAppConfig;
import AJInfrastructura.AJAppException;

public class AJExobotDAO extends DataHelperSQLiteDAO<AJExobotDTO> {
    public AJExobotDAO() throws AJAppException {
        super(AJExobotDTO.class, "Exobot", "IdExobot");
    }

    public boolean setEntreno(int idExobot) throws AJAppException {
        String sql = "UPDATE Exobot SET Entreno = 'SI' WHERE IdExobot = ?";
        try (PreparedStatement ps = openConnection().prepareStatement(sql)) {
            ps.setInt(1, idExobot);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new AJAppException(null, e, getClass(), "setEntreno");
        }
    }

    public boolean setNoEntreno(int idExobot) throws AJAppException {
        String sql = "UPDATE Exobot SET Entreno = 'NO' WHERE IdExobot = ?";
        try (PreparedStatement ps = openConnection().prepareStatement(sql)) {
            ps.setInt(1, idExobot);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new AJAppException(null, e, getClass(), "setNoEntreno");
        }
    }

    public boolean incrementarAccion(int idExobot) throws AJAppException {
        String sql = "UPDATE Exobot SET NoAccion = NoAccion + 1 WHERE IdExobot = ?";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(AJAppConfig.getDATABASE());
            conn.setAutoCommit(true);

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, idExobot);
                int resultado = ps.executeUpdate();
                return resultado > 0;
            }
        } catch (SQLException e) {
            throw new AJAppException(null, e, getClass(), "incrementarAccion");
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Obtiene todos los exobots con los datos detalados desde la vista
     */
    @Override
    public List<AJExobotDTO> readAll() throws AJAppException {
        List<AJExobotDTO> list = new ArrayList<>();
        String sql = "SELECT IdExobot, Nombre, Serie, TipoExobot, Entreno, NoAccion, " +
                "ArmaDerecha, ArmaIzquierda FROM AJ_vwExobotDetalle";
        try (PreparedStatement stmt = openConnection().prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                AJExobotDTO dto = new AJExobotDTO();
                dto.setIdExobot(rs.getInt("IdExobot"));
                dto.setNombre(rs.getString("Nombre"));
                dto.setSerie(rs.getString("Serie"));
                dto.setTipoExobot(rs.getString("TipoExobot"));
                dto.setEntreno(rs.getString("Entreno"));
                dto.setNoAccion(rs.getInt("NoAccion"));
                dto.setArmaDerecha(rs.getString("ArmaDerecha"));
                dto.setArmaIzquierda(rs.getString("ArmaIzquierda"));

                list.add(dto);
            }
        } catch (SQLException e) {
            throw new AJAppException(null, e, getClass(), "readAll");
        }
        return list;
    }
}
