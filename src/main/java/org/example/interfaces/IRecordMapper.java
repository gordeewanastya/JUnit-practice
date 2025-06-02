package org.example.interfaces;

public interface IRecordMapper <T>{
    public T mapToEntity(String[] record);
}
