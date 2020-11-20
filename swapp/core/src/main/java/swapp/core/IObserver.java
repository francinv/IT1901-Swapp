package swapp.core;

public interface IObserver<T> {

  void notify(T obj);

}
