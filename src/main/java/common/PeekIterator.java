package common;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Stream;

public class PeekIterator<T> implements Iterator<T> {

    private Iterator<T> it;
    private T _endToken = null;

    private LinkedList<T> queueCache = new LinkedList<>();
    private LinkedList<T> stackPutBacks = new LinkedList<>();
    private final static int CACHE_SIZE = 10;

    public PeekIterator(Stream<T> stream) {
        it = stream.iterator();
    }

    public PeekIterator(Stream<T> stream, T endToken) {
        it = stream.iterator();
        _endToken = endToken;
    }

    public T peek() {
        if (stackPutBacks.size() > 0) {
            return stackPutBacks.getFirst();
        }
        if (!it.hasNext()) {
            return _endToken;
        }
        T val = next();
        putBack();
        return val;
    }

    public void putBack() {
        if (queueCache.size() > 0) {
            this.stackPutBacks.push(this.queueCache.pollLast());
        }
    }

    @Override
    public boolean hasNext() {
        return _endToken != null || stackPutBacks.size() > 0 || it.hasNext();
    }

    @Override
    public T next() {

        T val = null;
        if (stackPutBacks.size() > 0) {
            val = stackPutBacks.pop();
        } else {
            if (!it.hasNext()) {
                T tmp = _endToken;
                _endToken = null;
                return tmp;
            }
            val = it.next();
        }
        while (queueCache.size() > CACHE_SIZE - 1) {
            queueCache.poll();
        }
        queueCache.add(val);
        return val;
    }
}
