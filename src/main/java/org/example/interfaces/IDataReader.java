package org.example.interfaces;

import java.util.List;

public interface IDataReader<T> {
  List<T> read() throws Exception;
}
