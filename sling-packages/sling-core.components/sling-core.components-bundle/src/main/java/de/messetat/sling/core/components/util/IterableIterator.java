package de.messetat.sling.core.components.util;

import java.util.Collections;
import java.util.Iterator;


public class IterableIterator<Type> implements Iterator<Type>, Iterable<Type> {

    private final Iterator<Type> iterator;

    public IterableIterator(final Iterator<Type> iterator) {
        this.iterator = iterator == null ? Collections.<Type>emptyList()
                .iterator() : iterator;
    }

    @Override
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override
    public Type next() {
        return this.iterator.next();
    }

    @Override
    public void remove() {
        this.iterator.remove();
    }

    @Override
    public Iterator<Type> iterator() {
        return this.iterator;
    }
}
