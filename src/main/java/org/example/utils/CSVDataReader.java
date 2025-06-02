package org.example.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.example.interfaces.IDataReader;
import org.example.interfaces.IRecordMapper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVDataReader<T> implements IDataReader<T> {
  private final File filePath;
  private final IRecordMapper<T> mapper;

  public CSVDataReader(File filePath, IRecordMapper<T> mapper) {
    this.filePath = filePath;
    this.mapper = mapper;
  }

  @Override
  public List<T> read() throws Exception {
    List<T> entities = new ArrayList<>();
    readRecordsAndMapToEntities(entities);

    return entities;
  }

  private void readRecordsAndMapToEntities(List<T> entities)
      throws IOException, CsvValidationException {
    try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
      csvReader.skip(1);
      String[] record;
      while ((record = csvReader.readNext()) != null) {
        entities.add(mapper.mapToEntity(record));
      }
    }
  }
}
