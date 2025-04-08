package repository;

public interface IRepository<T> {
    void add(T entity);
    boolean search(T entity);
    void remove(T entity);
}
