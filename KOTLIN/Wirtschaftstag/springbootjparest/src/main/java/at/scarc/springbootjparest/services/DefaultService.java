package at.scarc.springbootjparest.services;

import at.scarc.springbootjparest.models.Department;

import java.util.List;

public interface DefaultService<T> {
    public List<T> getAll();
    public T getOne(Long id);
    public T createOrUpdateOne(T entity);
    public boolean delete(Long id);
}
