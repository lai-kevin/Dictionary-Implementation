
import java.util.function.Function;
import java.util.ArrayList;

public class DoubleHashTable<V extends Hashable> implements OpenAddressTable<V>{
    private Object[] backingArr;
    private Function<Integer, Integer> h1;
    private Function<Integer, Integer> h2;
    private int size;
    private int count;

    public boolean sameKeys(V value1, V value2){
        return value1.key() == value2.key();
    }

    public DoubleHashTable(int size, Function h1, Function h2){
        backingArr = new Object[size];
        this.h1 = h1;
        this.h2 = h2;
        this.size = size;
        this.count = 0;
    }

    @Override
    public boolean isEmpty() {
        if (count == 0){
            return true;
        }
        return false;
    }

    @Override
    public void insert(V value) {
        if (value == null) { throw new NullPointerException();}
        boolean inserted = false;
        int probeNum = 0;
        if (find(value) > -1){
            inserted = true;
        }
        while (!inserted){
            int location = hash(value.key(), probeNum);
            if (backingArr[location] == null || backingArr[location].equals("deleted")){
                backingArr[location] = value;
                inserted = true;
                count++;
            }
            else{
                probeNum++;
                if (probeNum >= size){
                    break;
                }
            }
        }
    }

    @Override
    public V delete(V value) {
        if (value == null) { throw new NullPointerException();}
        int location = find(value);
        if (location > -1){
            V save = (V)backingArr[location];
            backingArr[location] = "deleted";
            count--;
            return save;
        }
        else{
            return null;
        }
    }

    @Override
    public int find(V value) {
        int probeNum = 0;
        while(true){
            int location = hash(value.key(), probeNum);
            if (backingArr[location] == null){
                return -1;
            }
            if (!((backingArr[location] instanceof String) && (backingArr[location].equals("deleted")))){ //if not deleted
                if (sameKeys(value, (V) backingArr[location])){
                    return location;
                }
            }
            probeNum++;
            if (probeNum >= size){
                return -1;
            }
        }
    }

    @Override
    public int hash(int key, int probenumber) {
        int u = h1.apply(key) % size;
        int v = h2.apply(key) % size;
        return ((u + v*probenumber) % size);
    }

    public String toString(){
        String result = "";
        for (int i = 0; i < size; i++){
            result += String.valueOf(i) + " -> ";
            if ((backingArr[i] != null) && (!((backingArr[i] instanceof String) && (backingArr[i].equals("deleted"))))){
                result += "[" + String.valueOf(i) + ", " + backingArr[i].toString() + "]; ";  //fix later
            }
            else{
                result += "null; ";
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Function<Integer, Integer> h1 = new Function<Integer,Integer>(){
            public Integer apply(Integer x) {
                String y = String.valueOf(x);
                return Integer.parseInt(String.valueOf(y.charAt(0)));
            }
        } ;
        Function<Integer, Integer> h2 = new Function<Integer,Integer>(){
            public Integer apply(Integer x) {
                String y = String.valueOf(x);
                return Integer.parseInt(String.valueOf(y.charAt(y.length()-1)));
            }
        } ;
        // your implementation of the h1 function described above
        // your implementation of the h2 function described above
        DoubleHashTable<HashableString> t = new DoubleHashTable<>(10, h1, h2);
        
    }


}
