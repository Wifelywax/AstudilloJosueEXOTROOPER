package AJBusinessLogic.AJEntities;

import AJDataAccess.AJDAOs.AJArmaDAO;
import AJDataAccess.AJDAOs.AJExobotDAO;
import AJDataAccess.AJDTOs.AJExobotDTO;
import AJInfrastructura.AJAppException;
import AJInfrastructura.AJTools.CMD;

public class AJExaBotBL {

    public void entrenarArma(int idExobot, int idArma, String tipoArma, String accionArma) throws AJAppException {
        AJArmaDAO armaDAO = new AJArmaDAO();
        AJExobotDAO exobotDAO = new AJExobotDAO();

        boolean okArma = armaDAO.setEntrenada(idArma);

        if (!okArma) {
            CMD.print("ERROR : No se pudo entrenar arma");
            return;
        }

        boolean okExobot = exobotDAO.setEntreno(idExobot);

        if (!okExobot) {
            CMD.print("ERROR : No se pudo marcar Exobot entrenado");
            return;
        }

        CMD.print("GOOD : SoldadoExperto " + tipoArma + " ENTRENADA");
    }

    public void accionArma(int idExobot, String extremidad) throws AJAppException {

        AJExobotDAO exobotDAO = new AJExobotDAO();

        AJExobotDTO exobot = exobotDAO.readBy(idExobot);
        if (exobot == null) {
            CMD.print("ERROR : Exobot no encontrado");
            return;
        }

        if (!"S".equalsIgnoreCase(exobot.getEntreno())) {
            CMD.print("ERROR : Exobot NO_ENTRENADO");
            return;
        }

        String tipoArma;
        if ("DER".equalsIgnoreCase(extremidad)) {
            tipoArma = exobot.getArmaDerecha();
        } else {
            tipoArma = exobot.getArmaIzquierda();
        }

        if (tipoArma == null || tipoArma.isEmpty()) {
            CMD.print("ERROR : Sin arma en " + extremidad);
            return;
        }

        exobotDAO.incrementarAccion(idExobot);

        CMD.print("GOOD : " + tipoArma + " ACCION EJECUTADA");
    }
}
