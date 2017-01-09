package ru.vital.test;

import java.util.List;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.ListIterator;
import java.util.Iterator;

public class LinkedList<T> implements List<T> {

    private Item<T> first = null;

    private Item<T> last = null;

    private int size;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean contains(final Object o) {
        // BEGIN (write your solution here)
        if (o == null) {
            for (Item<T> e = first; e != null; e = e.getNext()) {
                if (null == e.getElement())
                    return true;
            }
        }
        else {
            for (Item<T> e = first; e != null; e = e.getNext()) {
                if (o.equals(e.getElement()))
                    return true;
            }
        }
        return false;
        // END
    }

    @Override
    public Iterator<T> iterator() {
        return new ElementsIterator();
    }

    @Override
    public Object[] toArray() {
        // BEGIN (write your solution here)
        Object[] array = new Object[size];
        Item<T> current = first;
        array[0] = current.getElement();
        for (int i = 1; i < size; i++) {
            current = current.getNext();
            array[i] = current.getElement();
        }
        return array;
        // END
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        // BEGIN (write your solution here)
        if (a.length < size)
            a = (T1[])java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), size);
        int i = 0;
        Object[] result = a;
        for (Item<T> x = first; x != null; x = x.next)
            result[i++] = x.element;

        if (a.length > size)
            a[size] = null;

        return a;
        // END
    }

    @Override
    public boolean add(final T t) {
        // BEGIN (write your solution here)
        if (isEmpty()) {
            Item<T> item = new Item<>(t, null, null);
            first = last = item;
            size++;
        }
        else {
            Item<T> item = new Item<>(t, last, null);
            last.next = item;
            last = item;
            //item.next = item;
            size++;
        }
        return true;
        // END
    }

    @Override
    public boolean remove(final Object o) {
        // BEGIN (write your solution here)
        if(first ==  null)
            return false;
        if (o == first.element) {
            removeFirst();
            return true;
        }
        if (o == last.element) {
            removeLast();
            return true;
        }
        if (o == null) {

            for (Item<T> x = first; x != null; x = x.getNext()) {
                if (null == x.getElement()) {
                    removeKey(x);
                    return true;
                }
            }
        }
        else {
            for (Item<T> x = first; x != null; x = x.getNext()) {
                if (o.equals(x.getElement())) {
                    removeKey(x);
                    return true;
                }
            }
        }
        return false;
        // END
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        for (final Object item : c) {
            if (!this.contains(item)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(final Collection<? extends T> c) {
        for (final T item : c) {
            add(item);
        }
        return true;
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        for (final Object item : c) {
            remove(item);
        }
        return true;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        for (final Object item : this) {
            if (!c.contains(item)) this.remove(item);
        }
        return true;
    }

    @Override
    public void clear() {
        // BEGIN (write your solution here)
        first = last = null;
        size = 0;
        // END
    }

    @Override
    public T remove(final int index) {
        // BEGIN (write your solution here)
        if (checkIndex(index))
            throw new IndexOutOfBoundsException("Index:" + index +", Size: " + size);
        int count = 0;
        for (Item<T> e = first; e != null; e = e.getNext()) {
            if (count == index) {
                T result = e.element;
                removeKey(e);
                return result;
            }
            count++;
        }
        return null;
        // END
    }

    // BEGIN (write your solution here)
    private boolean checkIndex(final int index) {
        return (index < 0 || index >= size);
    }

     T removeFirst() {
        Item<T> temp = first;
        if (first.next == null)
            last = null;
        else
            first.next.prev = null;
        first = first.next;
        size--;
        return temp.getElement();
    }
     T removeLast() {
        Item<T> temp = last;
        if (first.next == null)
            first = null;
        else
            last.prev.next = null;
        last = last.prev;
        size--;
        return temp.getElement();
    }

    private void removeKey(Item<T> x) {
        if (x.next == null || x.prev == null) {
            x.element = null;
        }
        else {
            x.getPrev().next = x.getNext();
            x.getNext().prev = x.getPrev();
            x.next = x.prev = null;
            x.element = null;
            size--;
        }
    }
    // END
    @Override
    public List<T> subList(final int start, final int end) {
        int index = 0;
        List<T> newList = new java.util.LinkedList<>();
        for (Item<T> e = first; e != last; e = e.getNext()) {
            if (index >= start && index < end)
                newList.add(e.getElement());
            index++;
        }
        return newList;
    }

    @Override
    public ListIterator listIterator() {
        return new ElementsIterator(0);
    }

    @Override
    public ListIterator listIterator(final int index) {
        return new ElementsIterator(index);
    }

    @Override
    public int lastIndexOf(final Object target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(final Object target) {
        // BEGIN (write your solution here)
        int index = 0;
        Item<T> item = first;
        if (target == null) {
            for (Item<T> e = first; e != null; e = e.getNext()) {
                if (null == e.element)
                    return index;
                index++;
            }
        }

        else{
            while (!target.equals(item.getElement()) && index < size) {
                item = item.getNext();
                index++;
            }
            return index;
        }
        return -1;
        // END
    }

    @Override
    public void add(final int index, final T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(final int index, final Collection elements) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T set(final int index, final T element) {
        // BEGIN (write your solution here)
        if (checkIndex(index))
            throw new IndexOutOfBoundsException();
        int count = 0;
        for (Item<T> e = first; e != null; e = e.getNext()) {
            if (count == index)
                e.element = element;
            count++;
        }
        return element;
        // END
    }

    @Override
    public T get(final int index) {
        // BEGIN (write your solution here)
        if (checkIndex(index))
            throw new IndexOutOfBoundsException();
        int count = 0;
        for (Item<T> e = first; e != null; e = e.getNext()) {
            if (count == index)
                return e.getElement();
            count++;
        }
        return null;
        // END
    }

    // BEGIN (write your solution here)
    Item<T> node(int index) {
        // assert isElementIndex(index);

        if (index < (size >> 1)) {
            Item<T> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Item<T> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }
    // END

    private class ElementsIterator implements ListIterator<T> {

        private Item<T> current;

        private Item<T> lastReturned;

        private int nextIndex;

        private int flag = -1;
        public ElementsIterator() {
            this(0);
        }

        public ElementsIterator(final int index) {
            // BEGIN (write your solution here)
//            int count = 0;
//            for (current = first; current != null; current = current.getNext()) {
//                if (current == last)
//                    current = last;
//                if (index == count) {
//                    nextIndex = count;
//                    break;
//                }
//                count++;
//            }
            current = (index == size) ? null : node(index);
            nextIndex = index;
            // END
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            // BEGIN (write your solution here)
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = current;
            current = current.getNext();
            nextIndex++;
            flag = 1;
            return lastReturned.getElement();
            // END
        }

        @Override
        public void add(final T element) {
            LinkedList.this.add(element);
        }

        @Override
        public void set(final T element) {
            // BEGIN (write your solution here)
            if (lastReturned == null || flag == -1)
                throw new IllegalStateException();
            lastReturned.element = element;
            flag = -1;
            // END
        }

        @Override
        public int previousIndex() {
            // BEGIN (write your solution here)
            return nextIndex - 1;
            // END
        }

        @Override
        public int nextIndex() {
            // BEGIN (write your solution here)
            return nextIndex;
            // END
        }

        @Override
        public boolean hasPrevious() {
            // BEGIN (write your solution here)
            return nextIndex > 0;
            // END
        }

        @Override
        public T previous() {
            // BEGIN (write your solution here)
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            current = lastReturned;
            flag = 1;
            return current.getElement();
            // END
        }
        @Override
        public void remove() {
            // BEGIN (write your solution here)
            if (flag == -1)
                throw new IllegalStateException();
            if (lastReturned == first)
                removeFirst();
            if (lastReturned == last)
                removeLast();
            else
                removeKey(lastReturned);
            flag = -1;
            // END
        }

    }

    private static class Item<T> {

        private T element;

        private Item<T> next;

        private Item<T> prev;

        public Item(final T element, final Item<T> prev, final Item<T> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        public T getElement() {
            return element;
        }

        public Item<T> getNext() {
            return next;
        }

        public Item<T> getPrev() {
            return prev;
        }

    }

}

