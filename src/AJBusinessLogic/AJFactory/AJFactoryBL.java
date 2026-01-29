//  © 2K26 ❱──💀──❰ pat_mic ? code is life : life is code
package AJBusinessLogic.AJFactory;

import AJDataAccess.AJInterfaces.AJIDAO;
import AJInfrastructura.AJAppException;
import java.util.List;

public class AJFactoryBL<T> {
    private final AJIDAO<T> oDAO;

    public AJFactoryBL(Class<? extends AJIDAO<T>> classDAO) {
        try {
            this.oDAO = classDAO.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            AJAppException er = new AJAppException("Error al instanciar classDAO<T>", e, getClass(),
                    "FactoryBL(<T>)");
            throw new RuntimeException(er);
        }
    }

    // Constructor que usa un Supplier para crear la instancia de T
    // public FactoryBL(Supplier<IDAO<T>> supplier) {
    // this.oDAO = supplier.get();
    // }

    public List<T> getAll() throws AJAppException {
        return oDAO.readAll();
    }

    public T getBy(Integer id) throws AJAppException {
        return oDAO.readBy(id);
    }

    public boolean add(T oT) throws AJAppException {
        return oDAO.create(oT);
    }

    public boolean upd(T oT) throws AJAppException {
        return oDAO.update(oT);
    }

    public boolean del(Integer id) throws AJAppException {
        return oDAO.delete(id);
    }

    public Integer getMaxReg(String cellName) throws AJAppException {
        return oDAO.getMaxReg(cellName);
    }

    public Integer getMinReg(String cellName) throws AJAppException {
        return oDAO.getMinReg(cellName);
    }

    public Integer getCountReg() throws Exception {
        return oDAO.getCountReg();
    }
}
