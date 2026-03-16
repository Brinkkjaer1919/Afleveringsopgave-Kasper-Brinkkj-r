import java.util.Objects;

public class DictionaryOpenAddressing<K, V> implements Dictionary<K, V> {
    private static final int DEFAULT_CAPACITY = 13;

    private final Entry<K, V>[] table;
    private final Entry<K, V> deleted = new Entry<>(null, null);
    private int size;

    @SuppressWarnings("unchecked")
    public DictionaryOpenAddressing() {
        this.table = (Entry<K, V>[]) new Entry[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public V get(K key) {
        requireNonNullKey(key);

        int index = startIndex(key);
        for (int i = 0; i < table.length; i++) {
            int probeIndex = (index + i) % table.length;
            Entry<K, V> current = table[probeIndex];

            if (current == null) {
                return null;
            }
            if (current != deleted && current.key.equals(key)) {
                return current.value;
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V put(K key, V value) {
        requireNonNullKey(key);
        requireNonNullValue(value);

        int index = startIndex(key);
        int firstDeletedIndex = -1;

        for (int i = 0; i < table.length; i++) {
            int probeIndex = (index + i) % table.length;
            Entry<K, V> current = table[probeIndex];

            if (current == null) {
                int insertIndex = (firstDeletedIndex != -1) ? firstDeletedIndex : probeIndex;
                table[insertIndex] = new Entry<>(key, value);
                size++;
                return null;
            }

            if (current == deleted) {
                if (firstDeletedIndex == -1) {
                    firstDeletedIndex = probeIndex;
                }
                continue;
            }

            if (current.key.equals(key)) {
                V oldValue = current.value;
                current.value = value;
                return oldValue;
            }
        }

        if (firstDeletedIndex != -1) {
            table[firstDeletedIndex] = new Entry<>(key, value);
            size++;
            return null;
        }

        throw new IllegalStateException("Dictionary is full");
    }

    @Override
    public V remove(K key) {
        requireNonNullKey(key);

        int index = startIndex(key);
        for (int i = 0; i < table.length; i++) {
            int probeIndex = (index + i) % table.length;
            Entry<K, V> current = table[probeIndex];

            if (current == null) {
                return null;
            }
            if (current != deleted && current.key.equals(key)) {
                V oldValue = current.value;
                table[probeIndex] = deleted;
                size--;
                return oldValue;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    private int startIndex(K key) {
        return Math.floorMod(key.hashCode(), table.length);
    }

    private void requireNonNullKey(K key) {
        Objects.requireNonNull(key, "key må ikke være null");
    }

    private void requireNonNullValue(V value) {
        Objects.requireNonNull(value, "value må ikke være null");
    }

    private static class Entry<K, V> {
        private final K key;
        private V value;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
