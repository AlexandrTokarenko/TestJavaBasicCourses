package dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    T get(long id);

    List<T> getAll();

    void save(T o) throws SQLException;

    void update(T o, int id) throws SQLException;

    void delete(int id) throws SQLException;
}
