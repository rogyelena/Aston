package CustomArrayList;

import java.util.*;

/*
 + add(int index, E element)
 + addAll(Collection<? extends E> c)
 + get(int index)
 + remove(int index)
 + remove(Object o)
 + clear()
 + isEmpty()
 - sort(Comparator<? super E> c)
 */

public class CustomArrayList<T extends Comparable> {
    private final int INIT_SIZE = 16;
    private Object[] array = new Object[INIT_SIZE];
    private int pointer = 0;

    public static void main(String[] args) {

        CustomArrayList customList = new CustomArrayList();
        customList.fillListWithRandomStrategy(7);

        System.out.println("before");
        customList.printAll();

        customList.add(555);
        customList.remove(1);
        customList.quickSort();
        System.out.println("after");
        customList.printAll();
    }

    public T get(int index) {
        return (T) array[index];
    }

    public void add(T item) {
        if (pointer == array.length - 1)
            resize(array.length * 2);
        array[pointer++] = item;
    }

    public void addAll(Collection<? extends T> items) {
        Object[] arr = items.toArray();
        int i = arr.length;
        this.resize(this.size() + i);
        System.arraycopy(arr, 0, this.array, this.size(), i);
    }

    public void remove(int index) {
        for (int i = index; i < pointer; i++)
            array[i] = array[i + 1];
        array[pointer] = null;
        pointer--;
    }

    public void remove(Object o) {
        boolean isNull = o == null ? true : false;
        for (int i = 0; i < pointer; i++) {
            if (isNull) {
                if (get(i) == null) {
                    remove(i);
                }
            } else {
                if (o.equals(get(i))) {
                    remove(i);
                }
            }
        }
    }

    public void clear() {
        for (int i = 0; i < pointer; i++) {
            remove(i);
        }
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int size() {
        return pointer;
    }

    //Вспомогательный метод для масштабирования.
    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, pointer);
        array = newArray;
    }

    // Основной метод быстрой сортировки
    public void quickSort() {
        if (this == null || this.size() < 2) {
            return; // Check for null or empty list
        }
        quickSort(0, this.size() - 1);
    }

    // Рекурсивная быстрая сортировка
    public void quickSort(int low, int high) {
        if (low < high) {
            int pivotIndex = partition(low, high);
            quickSort(low, pivotIndex - 1);
            quickSort(pivotIndex + 1, high);
        }
    }

    // Метод разделения списка по опорному элементу
    private int partition(int low, int high) {
        Comparable pivot = this.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (this.get(j).compareTo(pivot) < 0) {
                i++;
                swap(i, j);
            }
        }

        swap(i + 1, high);
        return i + 1;
    }

    // Вспомогательный метод для обмена элементов
    private <T extends Comparable<T>> void swap(int i, int j) {
        T temp = (T) this.get(i);
        this.array[i] = this.get(j);
        this.array[j] = temp;
    }

    public int compareTo(Object o) {
        return 0;
    }

    private void fillListWithRandomStrategy(int arrayLength) {
        for (int i = 0; i < arrayLength; i++) {
            this.add((T) ((Integer) new Random().nextInt(20)));
        }
    }

    private void printAll(){
        for (int i = 0; i < this.size(); i++) {
            System.out.println(this.array[i]);
        }
    };
}