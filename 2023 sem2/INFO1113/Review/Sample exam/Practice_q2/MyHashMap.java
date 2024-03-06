import java.util.*;


public class MyHashMap<K,V extends Number> {
    private ArrayList<K> keys;
    private ArrayList<V> values;

    public MyHashMap() {
        this.keys = new ArrayList<K>();
        this.values = new ArrayList<V>();
    }

    public V get(K key) {
        int index = this.keys.indexOf(key);
        if (index != -1) {
            return this.values.get(index);
        }
        return null;
    }


    public void put(K key,V value){
        if(this.keys.indexOf(key) != -1){
            int index = this.keys.indexOf(key);
            this.values.set(index,value);
        }else{
            this.keys.add(key);
            this.values.add(value);
        }
    }
}