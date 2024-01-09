import java.lang.reflect.Array;

public class DynamicArray<typ> {
    private typ[] arr;
    private int size;
    public DynamicArray(Class<typ> type) {
        arr = (typ[]) Array.newInstance(type, 1);
        size = 0;
    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public typ get(int a){
        if(a < 0 || a >= size) return null;
        return arr[a];
    }
    public boolean contains(typ b) {
        for(int i = 0; i < size; i++) {
            if(b.equals(arr[i])) return true;
        }
        return false;
    }
    private void resizeArray() {
        int newarrsize = arr.length*2;
        typ[] newarr = (typ[]) Array.newInstance((Class<typ>) arr.getClass().getComponentType(), newarrsize);
        System.arraycopy(arr, 0, newarr, 0, size);
        arr = newarr;
    }
    public void add(typ b) {
        if(size == arr.length) resizeArray();
        arr[size++] = b;
    }
    public void add(int a, typ b) {
        if(a < 0 || a > size) throw new IllegalArgumentException("Array Index out of Bounds!");
        if(size == arr.length) resizeArray();
        System.arraycopy(arr, a, arr, a+1, size++ -a);
        arr[a] = b;
    }
    public void set(int a, typ b) {
        if (a < 0 || a >= size) throw new IllegalArgumentException("Array Index out of Bounds!");
        arr[a] = b;
    }
    public typ remove(int a) {
        if(a < 0 || a >= size) throw new IllegalArgumentException("Array Index out of Bounds!");
        typ b = arr[a];
        System.arraycopy(arr, a+1, arr,a, size-a-1);
        size--;
        arr[size] = null;
        return b;
    }
    public typ remove(typ b) {
        int a =-1;
        for(int i = 0; i < size; i++) {
            if(b.equals(arr[i])) {
                a = i;
                break;
            }
        }
        if (a !=-1) remove(a);
        return b;
    }
    public typ removeAll(typ b) {
        for(int i = 0; i < size; i++) {
            if(b.equals(arr[i])) {
                remove(i);
                i--;
            }
        }
        return b;
    }
    public void clear() {
        for(int i = 0; i < size; i++) arr[i] = null;
        size = 0;
    }

}
