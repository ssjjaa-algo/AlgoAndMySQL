package DataStructure;

public class SinglyLinkedList<E> implements SinglyLinkedListInterface<E>{

    private int size = 0;
    private Node<E> head;

    @Override
    public boolean isEmpty() {
        if (size == 0) return true;

        return false;
    }

    @Override
    public void add(E e) {

        Node<E> newNode = new Node<>(e);

        if (isEmpty()) {
            this.head = newNode;
        }
        else {
            Node<E> tempNode = head;

            while (tempNode.next != null) {
                tempNode = tempNode.next;
            }

            tempNode.next = newNode;
        }

        this.size++;
    }

    @Override
    public boolean add(int idx, E e) {

        if (idx < 0 || idx > this.size) return false;

        Node<E> newNode = new Node<>(e);

        if (idx == 0) {
            newNode.next = this.head;
            this.head = newNode;
        }
        else {
            Node<E> prevNode = null;
            Node<E> curNode = head;

            int cnt = 0;
            while (cnt != idx) {
                prevNode = curNode;
                curNode = curNode.next;
                cnt++;
            }

            newNode.next = curNode;
            prevNode.next = newNode;

        }
        this.size++;
        return true;
    }

    @Override
    public E get(int idx) {

        if (idx < 0 || idx >= this.size || isEmpty() ) return null;


        Node<E> curNode = head;

        int cnt = 0;
        while (curNode.next != null && cnt != idx) {
            curNode = curNode.next;
            cnt++;
        }

        return curNode.data;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean remove(E e) {
        if (isEmpty()) return false;

        Node<E> prevNode = null;
        Node<E> curNode = head;

        while (curNode.next != null && curNode.data != e) {
            prevNode = curNode;
            curNode = curNode.next;
        }

        if (curNode.next == null) return false;

        if (curNode == head) {
            head = curNode.next;
        }
        else {
            prevNode.next = curNode.next;
        }

        curNode = null;
        this.size--;
        return true;
    }

    @Override
    public void removeByIdx(int idx) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void set(int idx, Object o) {

    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    public void print() {

        Node<E> curNode = head;

        while (curNode!= null) {
            System.out.print(curNode.data + " ");
            curNode = curNode.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

    }
}
