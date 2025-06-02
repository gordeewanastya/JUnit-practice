package org.example.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.example.interfaces.IDataReader;
import org.example.interfaces.IRecordMapper;

public class ConsoleDataReader<T> implements IDataReader<T> {
  private final IRecordMapper<T> mapper;
  private final Scanner scanner;

  public ConsoleDataReader(IRecordMapper<T> mapper, Scanner scanner) {
    this.mapper = mapper;
    this.scanner = scanner;
  }

  @Override
  public List<T> read() throws Exception {
    List<T> entities = new ArrayList<>();
    readDataFromConsoleAndMapToEntities(entities);

    return entities;
  }

  private void readDataFromConsoleAndMapToEntities(List<T> entities) {
    System.out.print("Enter number of entities to input:");
    int count = scanner.nextInt();
    scanner.nextLine();

    for (int i = 0; i < count; i++) {
      System.out.printf(
          "Enter data for entity %d (example -> comma-separated: id,firstName,lastName):%n", i + 1);
      String line = scanner.nextLine();
      String[] record = line.split(",");
      entities.add(mapper.mapToEntity(record));
    }
  }
}
