package pl.rozen.list;


public class LinkedList<T> {
    final private Node<T> headAndTail;
    private int size;

    public LinkedList() {
        headAndTail = new Node<>(null);
        size = 0;
    }

    public void add(T data) {
        headAndTail.setPredecessor(new Node<T>(data));
        size++;
    }


    public int indexOf(T data) {
        if (data == null)
            return -1;
        int index = 0;
        Node tmp = headAndTail.next;
        while (tmp != headAndTail && data.equals(tmp.data)) {
            index++;
            tmp = tmp.next;
        }
        return index > size ? -1 : index;
    }

    public boolean contains(T data) {
        return indexOf(data) != -1;
    }

    private Node<T> getNode(int index) {
        return index < size / 2 ? getNodeForward(index) : getNodeBackward(size - index - 1);
    }

    private Node<T> getNodeForward(int index) {
        Node<T> node = headAndTail.next;
        while (index-- > 0) {
            node = node.next;
        }
        return node;
    }

    private Node<T> getNodeBackward(int index) {
        Node<T> node = headAndTail.previous;
        while (index-- > 0) {
            node = node.previous;
        }
        return node;
    }

    public T get(int index) {
        if (index < size && index > 0)
            return getNode(index).data;
        else return null;
    }

    public void remove(int index) {
        if (index < size && index >= 0) {
            Node<T> toRemove = getNode(index);
            toRemove.previous.setSuccessor(toRemove.next);
            toRemove.next.setPredecessor(toRemove.previous);
            size--;
        }
    }

    public T insert(T data, int index) {
        if (index < 0)
            return headAndTail.setSuccessor(new Node<>(data)).data;
        else if (index >= size)
            return headAndTail.setPredecessor(new Node<>(data)).data;
        else {
            return getNode(index).setPredecessor(new Node<>(data)).data;
        }
    }

    public void set(T data, int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Wrong index of value: " + index);
        else {
            getNode(index).data = data;
        }
    }

    public void clear() {
        headAndTail.next = headAndTail.previous = headAndTail;
        size = 0;
    }

    private static class Node<T> {
        T data;
        Node<T> previous;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = this.previous = this;
        }

        Node<T> setPredecessor(Node<T> predecessor) {
            predecessor.previous = this.previous.previous;
            predecessor.next = this;
            this.previous = predecessor;
            return predecessor;
        }

        Node<T> setSuccessor(Node<T> successor) {
            successor.next = this.next.next;
            successor.previous = this;
            this.next = successor;
            return successor;
        }
    }
}
