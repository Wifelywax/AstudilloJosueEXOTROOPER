package AJBusinessLogic.AJEntities;
//Refactorizacion
public class AJSoldadoExperto {
    private String nombre;
    private String tipoArma;

    public AJSoldadoExperto(String nombre, String tipoArma) {
        this.nombre = nombre;
        this.tipoArma = tipoArma;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipoArma() {
        return tipoArma;
    }

    public String AJEntrenar(AJArma arma) {
        arma.setEntrenada(true);
        return nombre + " " + tipoArma + " " + arma.getAccionArma();
    }

}
