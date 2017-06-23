package pl.rozen.map;

public class HashMap<K, V> {

    private static final int DEFAULT_TABLE_SIZE = 16;
    private static final float LOAD_FACTOR = 0.66f;

    private Object[] table;
    private int size = 0;

    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public V insert(Entry<K, V> entry) {
            if (key.equals(entry.key)) {
                value = entry.value;
                return value;
            } else if (next == null) {
                this.next = entry;
                return entry.value;
            } else return next.insert(entry);
        }
    }

    public HashMap() {
        table = new Object[DEFAULT_TABLE_SIZE];
    }

    private void checkFill() {
        if ((double) size / (double) table.length > LOAD_FACTOR)
            enlargeTab(table.length * 2);
    }

    private void enlargeTab(int newSize) {
        Object[] oldTab = table;
        table = new Object[newSize];
        for (Object o : oldTab) {
            if (o != null)
                this.put((Entry<K, V>) o);
        }
    }

    private V put(Entry<K, V> entry) {
        int index = indexFor(entry.key.hashCode());
        if (table[index] == null) {
            table[index] = entry;
            return entry.value;
        } else {
            Entry<K, V> tmp = ((Entry<K, V>) table[index]);
            return tmp.insert(entry);
        }
    }


    public V put(K key, V value) {
        checkFill();
        size++;
        return put(new Entry<K, V>(key, value));
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    public V get(K key) {
        Object o = table[indexFor(key.hashCode())];
        if (o == null)
            return null;
        else {
            for (Entry<K, V> tmp = (Entry<K, V>) o; tmp != null; tmp = tmp.next) {
                if (tmp.key.equals(key))
                    return tmp.value;
            }
            return null;
        }
    }

    public int size() {
        return size;
    }

    public void clear() {
        this.table = new Object[DEFAULT_TABLE_SIZE];
        size = 0;
    }

    public void remove(K key) {
        int index = indexFor(key.hashCode());
        Object o = table[index];
        if (o != null) {
            Entry<K, V> tmp = ((Entry<K, V>) o);
            if (tmp.key.equals(key)) {
                table[index] = tmp.next;
                size--;
            } else {
                while (tmp.next != null && !tmp.next.key.equals(key))
                    tmp = tmp.next;
                if (tmp.next != null && tmp.next.key.equals(key)){
                    tmp.next = tmp.next.next;
                    size--;
                }
            }
        }

    }
}
