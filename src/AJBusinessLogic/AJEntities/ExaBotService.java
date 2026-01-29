

package AJBusinessLogic.AJEntities;
//Refactorizacion
import AJDataAccess.AJDAOs.AJArmaDAO;
import AJDataAccess.AJDAOs.AJExobotDAO;
import AJDataAccess.AJDTOs.AJExobotDTO;
import AJInfrastructura.AJAppException;
import AJInfrastructura.AJTools.CMD;


public class ExaBotService {

    // ==============================
    // ENTRENAR ARMA
    // ==============================
    public void AJEntrenarArma(int idExobot, int idArma, String tipoArma, String accionArma) throws AJAppException {

        AJArmaDAO armaDAO = new AJArmaDAO();
        AJExobotDAO exobotDAO = new AJExobotDAO();

        // 1. Entrenar arma
        boolean okArma = armaDAO.setEntrenada(idArma);

        if (!okArma) {
            CMD.print("ERROR : No se pudo entrenar arma");
            return;
        }

        // 2. Marcar exobot como entrenado
        boolean okExobot = exobotDAO.setEntreno(idExobot);

        if (!okExobot) {
            CMD.print("ERROR : No se pudo marcar Exobot entrenado");
            return;
        }

        CMD.print("GOOD : SoldadoExperto " + tipoArma + " ENTRENADA");
    }

    // ==============================
    // ACCIÓN ARMA
    // ==============================
    public void accionArma(int idExobot, String extremidad) throws AJAppException {

        AJExobotDAO exobotDAO = new AJExobotDAO();

        // 1. Obtener datos del Exobot
        AJExobotDTO exobot = exobotDAO.readBy(idExobot);

        if (exobot == null) {
            CMD.print("ERROR : Exobot no encontrado");
            return;
        }

        // 2. Validar entrenamiento
        if (!"SI".equalsIgnoreCase(exobot.getEntreno())) {
            CMD.print("ERROR : Exobot NO_ENTRENADO");
            return;
        }

        // 3. Obtener arma por extremidad
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

        // 4. Incrementar número de acción
        exobotDAO.incrementarAccion(idExobot);

        // 5. Log correcto
        CMD.print("GOOD : " + tipoArma + " ACCION EJECUTADA");
    }
}
