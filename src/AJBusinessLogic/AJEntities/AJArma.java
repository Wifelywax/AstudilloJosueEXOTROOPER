package AJBusinessLogic.AJEntities;

import AJInfrastructura.AJTools.CMD;

public class AJArma {
    private String tipoArma;
    private String accionArma;
    private boolean entrenada;

    public void setTipoArma(String tipoArma) {
        this.tipoArma = tipoArma;
    }

    public void setAccionArma(String accionArma) {
        this.accionArma = accionArma;
    }

    public void setEntrenada(boolean entrenada) {
        this.entrenada = entrenada;
    }

    public AJArma(String tipoArma, String accionArma) {
        this.tipoArma = tipoArma;
        this.accionArma = accionArma;
        this.entrenada = false;
    }

    public void entrenar(AJSoldadoExperto soldado) {
        if (soldado.getTipoArma().equals(this.tipoArma)) {
            entrenada = true;
            CMD.print("GOOD : " + soldado.getNombre() + " " + tipoArma + " " + accionArma);
        } else {
            CMD.print("ERROR : " + soldado.getNombre() + " " + tipoArma + " " + accionArma);
        }
    }

    public boolean isEntrenada() {
        return entrenada;
    }

    public String getTipoArma() {
        return tipoArma;
    }

    public String getAccionArma() {
        return accionArma;
    }

}
