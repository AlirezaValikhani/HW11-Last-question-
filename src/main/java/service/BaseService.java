package service;

import java.util.List;

public interface BaseService<T> {
    Integer save(T t);
    T read(T t);
    List<T> readAll();
    void update(T t);
    void delete(T t);
}
