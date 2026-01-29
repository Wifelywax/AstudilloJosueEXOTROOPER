package AJBusinessLogic.AJEntities;

import AJDataAccess.AJDAOs.AJExobotDAO;
import AJInfrastructura.AJAppException;
import AJInfrastructura.AJTools.CMD;
import AJInfrastructura.AJTools.ExoMunicion;

public class AJExoInfanteria extends AJExaBot {

    public AJExoInfanteria(AJBrazo der, AJBrazo izq) {
        super(der, izq);
    }

    @Override
    public void accionArma(int idExobot, String extremidad) throws AJAppException {

        AJArma arma = getArmaPorExtremidad(extremidad);

        if (arma == null) {
            CMD.print("ERROR : ARMA NO DEFINIDA");
            return;
        }

        if (!arma.isEntrenada()) {
            CMD.print(
                    "ERROR : " + arma.getTipoArma() + " " +
                            arma.getAccionArma() + " NO_ENTRENADA");
            return;
        }

        boolean hayMunicion = ExoMunicion.hayMunicion(arma.getTipoArma());

        if (hayMunicion) {
            CMD.print(
                    "GOOD : " + arma.getTipoArma() + " " +
                            arma.getAccionArma());

            AJExobotDAO dao = new AJExobotDAO();
            dao.incrementarAccion(idExobot);

        } else {
            CMD.print(
                    "ERROR : " + arma.getTipoArma() + " " +
                            arma.getAccionArma() + " 0");
        }
    }

}
