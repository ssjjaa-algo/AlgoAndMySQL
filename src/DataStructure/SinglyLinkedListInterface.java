package DataStructure;

public interface SinglyLinkedListInterface<E> {

    boolean isEmpty();

    void add(E e);

    boolean add(int idx, E e);

    E get(int idx);

    int size();

    boolean remove(E e);

    void removeByIdx(int idx);

    void clear();

    void set(int idx, E e);

    int indexOf(E e);


}
