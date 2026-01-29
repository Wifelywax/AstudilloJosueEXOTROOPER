package AJDataAccess.AJDAOs;

import AJDataAccess.AJDTOs.AJTipoExobotDTO;
import AJDataAccess.AJHelpers.DataHelperSQLiteDAO;
import AJInfrastructura.AJAppException;

public class AJTipoExobotDAO extends DataHelperSQLiteDAO<AJTipoExobotDTO> {
    public AJTipoExobotDAO() throws AJAppException {
        super(AJTipoExobotDTO.class, "TipoExobot", "IdTipoExobot");
    }
}
