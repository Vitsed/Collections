package ru.vital.test;

import java.util.*;

public class ArrayCollection<T> implements Collection<T> {
    private T[] m = (T[]) new Object[0];

    private int size = 0;

    @Override
    public int size() {
        // BEGIN (write your solution here)
        return this.size;
        // END
    }

    @Override
    public boolean isEmpty() {
        // BEGIN (write your solution here)
        if (this.size == 0)
            return true;
        return false;
        // END
    }

    @Override
    public boolean contains(final Object o) {
        // BEGIN (write your solution here)
        T which = (T)o;

        for (T t : this.m) {
            if (t.equals(which))
                return true;
        }
        return false;
        // END
    }

    @Override
    public Iterator<T> iterator() {
        // BEGIN (write your solution here)
        return new ElementsIterator();
        // END
    }

    @Override
    public Object[] toArray() {
        // BEGIN (write your solution here)
        Object[] o = new Object[this.m.length];
        for (int i = 0; i < this.m.length; i++) {
            o[i] = (Object)this.m[i];
        }
        return o;
        // END
    }

    @Override
    public <T1> T1[] toArray(final T1[] a) {
        // BEGIN (write your solution here)
        if (size == 0) {
            a[0] = null;
            return a;
        }

        /*if (a.length <= size) {
            T1[] b = a;
            for (int i = 0; i < size; i++) {
                b[i] = (T1)m[i];
            }
            return b;
        }*/
        if (a.length < size)
            return (T1[]) Arrays.copyOf(this.m, size, a.getClass());
        if (a.length == size) {
            System.arraycopy(this.m, 0, a, 0, size);
            return a;
        }

        if (a.length > size) {
            for (int i = 0; i < a.length; i++) {
                if (i < this.m.length) {
                    a[i] = (T1)m[i];
                }
                if (i == size)
                    a[i] = null;
            }
            return a;
        }

        return null;
        // END
    }

    @Override
    public boolean add(final T t) {
        // BEGIN (write your solution here)
        int length = m.length;
        if (length == 0) {
            m = (T[])new Object[1];
            m[0] = t;
            size++;
            return true;
        }

        if (length >= size) {

            if (length > size) {
                m[length-1] = t;
                size++;
                return true;
            }
            else {
                T[] newM = (T[]) new Object[length + 1];
                for (int i = 0; i < length; i++) {
                    newM[i] = m[i];
                }
                newM[length] = t;
                m = newM;
                size++;
                return true;
            }
        }
        return false;
        // END
    }

    @Override
    public boolean remove(final Object o) {
        // BEGIN (write your solution here)
        ArrayCollection<T> newM = new ArrayCollection<T>();

        if (size == 0) return false;

        for (int i = 0; i < size; i++) {
            if (!o.equals(m[i]))
                newM.add(m[i]);
        }
        m = (T[]) newM.toArray();
        size = newM.size();
        return true;
        // END
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        // BEGIN (write your solution here)
        for (Object o : c) {
            if(!contains(o)) return false;
        }
        return true;
        // END
    }

    @Override
    public boolean addAll(final Collection<? extends T> c) {
        // BEGIN (write your solution here)
        for (Object o : c) {
            add((T)o);
        }
        return true;
        // END
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        // BEGIN (write your solution here)
        for (Object o : c) {
            remove(o);
        }
        return true;
        // END
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        // BEGIN (write your solution here)
        ArrayCollection<T> newM = new ArrayCollection<T>();

        if (size == 0) return false;

        for (int i = 0; i < size; i++) {
            for (Object o : c) {
                if (o.equals(m[i])) {
                    newM.add((T) o);
                }
            }
        }
        m = (T[]) newM.toArray();
        size = newM.size();

        return true;
        // END
    }

    @Override
    public void clear() {
        // BEGIN (write your solution here)
        m = (T[])new Object[0];
        this.size = 0;
        // END
    }

    private class ElementsIterator implements Iterator<T> {
        // BEGIN (write your solution here)
        private int index = 0;
        @Override
        public boolean hasNext() {
            return ArrayCollection.this.m.length > index;
        }

        @Override
        public T next() {
            if (this.hasNext()) return ArrayCollection.this.m[index++];
            else throw new NoSuchElementException();
        }
        // END
    }

}
