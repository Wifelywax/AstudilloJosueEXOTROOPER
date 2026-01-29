package AJDataAccess.AJInterfaces;
//REFACTORIZACION

import AJInfrastructura.AJAppException;
import java.util.List;

public interface AJIDAO<T> {
    List<T> readAll() throws AJAppException;

    T readBy(Integer id) throws AJAppException;

    boolean create(T entity) throws AJAppException;

    boolean update(T entity) throws AJAppException;

    boolean delete(Integer id) throws AJAppException;

    
    Integer getCountReg() throws AJAppException;

    Integer getMinReg(String tableCelName) throws AJAppException;

    Integer getMaxReg(String tableCelName) throws AJAppException;
}