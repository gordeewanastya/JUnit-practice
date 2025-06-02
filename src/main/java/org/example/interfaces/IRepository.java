package org.example.interfaces;

import java.sql.SQLException;

public interface IRepository<T> {
  public T save(T entity) throws SQLException;
}
