package ink.bonismo.notebook.arithmetic.data_structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyArrayList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private E[] elementData;

    public MyArrayList() {
        elementData = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public boolean add(E item) {
        grow(size + 1);
        elementData[size++] = item;
        return true;
    }

    public boolean add(int index, E item) {
        grow(size + 1);
        int movedNumber = size - index;
        System.arraycopy(elementData, index, elementData, index + 1, movedNumber);
        elementData[index] = item;
        size++;
        return true;
    }

    public E remove(int index) {
        E oldItem = elementData[index];
        int movedNumber = size - index - 1;
        if (movedNumber > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, movedNumber);
            elementData[--size] = null;
            return oldItem;
        }
        return null;
    }

    private void grow(int capacity) {
        // 需要处理细节：
        // 1. 有些 JVM 会给 array 分配 8 个字节，需要判断 capacity - Integer.MAX_VALUE > 0
        // 2. 如果有 addAll() 方法，需要判断 arr1.length + arr2.length 是否溢出变为负数，所以也要判断 capacity < 0 抛出异常。
        if (capacity == elementData.length) {
            capacity = (int) (capacity * 1.5);
            elementData = Arrays.copyOf(elementData, capacity);
        }
    }

    @Override
    public String toString() {
        if (elementData == null) {
            return "null";
        }
        int iMax = elementData.length - 1;
        if (iMax == -1) {
            return "[]";
        }
        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(elementData[i]);
            if (i == iMax) {
                return b.append(']').toString();
            }
            b.append(", ");
        }
    }

    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        System.out.println(list);
    }
}
