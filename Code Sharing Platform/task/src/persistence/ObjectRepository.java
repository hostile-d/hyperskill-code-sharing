package persistence;

public interface ObjectRepository<T> {

    public void store(T t);

    public T getById(int id);

    public T search(String name);


    public T delete(int id);
}