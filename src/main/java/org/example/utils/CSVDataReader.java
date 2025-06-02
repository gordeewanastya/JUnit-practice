package org.example.utils;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.example.interfaces.IDataReader;
import org.example.interfaces.IRecordMapper;

import java.io.File;
import java.io.FileNotFoundException;
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
    try (CSVReader csvReader = createReader()) {
      csvReader.skip(1);
      String[] record;
      while ((record = csvReader.readNext()) != null) {
        entities.add(mapper.mapToEntity(record));
      }
    }
  }

  private CSVReader createReader() throws FileNotFoundException {
    CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
    return new CSVReaderBuilder(new FileReader(filePath)).withCSVParser(parser).build();
  }
}
