package ru.vital.test;

import java.util.*;

public class ArrayBasedMap<K, V> implements Map<K, V> {

    private List<Pair> values = new ArrayList<>();

//    public void demoValuesContent () {
//        for (Pair p : values)
//            System.out.printf("[%s = %s]\n", p.getKey(), p.getValue());
//    }
    @Override
    public int size() {
        // BEGIN (write your solution here)
        return this.values.size();
        // END
    }

    @Override
    public boolean isEmpty() {
        // BEGIN (write your solution here)
        return this.values.isEmpty();
        // END
    }

    @Override
    public boolean containsKey(Object key) {
        // BEGIN (write your solution here)
        for (Pair p : values) {
            if (key == null) {
                if (p.getKey() == null)
                    return true;
            }
            else {
                if (key.equals(p.getKey()))
                    return true;
            }
        }
        return false;
        // END
    }

    @Override
    public boolean containsValue(Object value) {
        // BEGIN (write your solution here)
        for (Pair p : values) {
            if (value != null)
                if (value.equals(p.getValue()))
                    return true;
            else if (p.getValue() == null)
                return true;
        }
        return false;
        // END
    }

    @Override
    public V get(Object key) {
        // BEGIN (write your solution here)
        for (Pair p : values) {
            if (key != null)
                if (key.equals(p.getKey()))
                    return p.getValue();
            else {
                    if (p.getKey() == null)
                        return p.getValue();
                }
        }
        return null;
        // END
    }

    @Override
    public V put(K key, V value) {
        // BEGIN (write your solution here)
        if (values.isEmpty()) {
            values.add(new Pair(key, value));
            return null;
        }

        else {
            if (this.containsKey(key)) {
                for (Pair p : values) {
                    if (key == null) {
                        if(p.getKey() == null) {
                            V result = p.getValue();
                            p.setValue(value);
                            return result;
                        }
                    }
                    else {
                        if(key.equals(p.getKey())) {
                            V result = p.getValue();
                            p.setValue(value);
                            return result;
                        }
                    }
                }
            }
            else {
                values.add(new Pair(key, value));
                return null;
            }
        }
        return null;
        // END
    }

    @Override
    public V remove(Object key) {
        // BEGIN (write your solution here)
        if (this.containsKey(key)) {
            for (Pair p : values) {
                if (null == p.getKey()) {
                    V temp = p.getValue();
                    values.remove(p);
                    return temp;
                }

                else if (key.equals(p.getKey())) {
                    V temp = p.getValue();
                    values.remove(p);
                    return temp;
                }
            }
        }
        return null;
        // END
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<K, V> e : (Set<Map.Entry<K, V>>)(Set)m.entrySet())
            put(e.getKey(), e.getValue());
    }

    @Override
    public void clear() {
        // BEGIN (write your solution here)
        this.values.clear();
        // END
    }

    @Override
    public Set<K> keySet() {
        final Set<K> keys = new HashSet<K>();
        for (Pair p : values) keys.add(p.getKey());
        return keys;
    }

    @Override
    public Collection<V> values() {
        // BEGIN (write your solution here)
        Collection<V> collection = new ArrayList<>();
        for (Pair p : values) {
             collection.add(p.getValue());
        }
        return collection;
        // END
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return (Set<Entry<K, V>>)(Set)new HashSet<>(values);
    }

    private class Pair implements Map.Entry<K, V> {

        private final K key;

        private V value;

        private Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            final V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            Map.Entry<K, V> pair = (Map.Entry<K, V>) o;


            if (key != null ? !key.equals(pair.getKey()) : pair.getKey() != null) return false;
            return !(value != null ? !value.equals(pair.getValue()) : pair.getValue() != null);

        }

        @Override
        public int hashCode() {
            return (key   == null ? 0 :   key.hashCode()) ^
                    (value == null ? 0 : value.hashCode());
        }
    }
}