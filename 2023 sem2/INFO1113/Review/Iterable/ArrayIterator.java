import java.util.*;

public class ArrayIterator<T> implements Iterator<T>{
    private final T[] array;
    private int currentIndex = 0;

    public ArrayIterator(T[] array){
        this.array = array;
    }

    public boolean hasNext(){
        return this.currentIndex < array.length;
    }

    public T next(){
        if(!hasNext()){
            throw new NoSuchElementException("there is no next element!");
        }
        return array[currentIndex++];
    }


}