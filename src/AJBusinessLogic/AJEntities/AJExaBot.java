package AJBusinessLogic.AJEntities;

import AJInfrastructura.AJAppException;

public class AJExaBot extends AJIAEXO {

    protected AJBrazo brazoDerecho;
    protected AJBrazo brazoIzquierdo;

    public AJExaBot(AJBrazo der, AJBrazo izq) {
        this.brazoDerecho = der;
        this.brazoIzquierdo = izq;
    }

    public void entrenarArma(AJSoldadoExperto soldado, boolean derecho) {
        if (derecho) {
            brazoDerecho.getArma().entrenar(soldado);
        } else {
            brazoIzquierdo.getArma().entrenar(soldado);
        }
    }

    public AJArma getArmaPorExtremidad(String extremidad) {
        if ("DER".equalsIgnoreCase(extremidad)) {
            return brazoDerecho.getArma();
        }
        return brazoIzquierdo.getArma();
    }

    @Override
    public void accionArma(int idExobot, String extremidad) throws AJAppException {

        throw new UnsupportedOperationException("Unimplemented method 'accionArma'");
    }
}
