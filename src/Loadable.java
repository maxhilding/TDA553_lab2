public interface Loadable<T> {
    T loadOff();
    void loadOn(T t);
}
