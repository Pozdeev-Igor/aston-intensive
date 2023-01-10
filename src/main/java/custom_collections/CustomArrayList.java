package custom_collections;

import java.util.*;

public class CustomArrayList<T> {

    private final int INIT_SIZE = 10;
    private Object[] array = new Object[INIT_SIZE];
    int index;

    public void add(T t) {
        if (index == array.length - 1) {
            Object[] newArray = new Object[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, index);
            array = newArray;
        }
        array[index++] = t;
    }

    public Object get(int i) {
        if (i < 0 || i >= this.size())
            throw new IndexOutOfBoundsException("Ты вышел за пределы коллекции. Переделывай!");
        return array[i];
    }

    //  Этот метод удаляет элемент коллекции по индексу и сдвигает все остальные элементы "влево"
    public void remove(int i) {
        if (i < 0 || i >= this.size())
            throw new IndexOutOfBoundsException("Ты вышел за пределы коллекции. Переделывай!");
        for (int j = i; j < this.size() - 1; j++) {
            array[j] = array[j + 1];
        }
        array[this.size() - 1] = null;
        index = (this.size() - 1);
        if (array.length > INIT_SIZE && index < array.length / 2) {
            Object[] newArray = new Object[array.length / 2];
            System.arraycopy(array, 0, newArray, 0, newArray.length);
            array = newArray;
        }
    }

    // Этот метод просто использует перегруженный метод (подсмотрел в классе ArrayList)
    public void addAll(Collection<? extends T> c) {
        addAll(index, c);
    }


    //  Этот метод добавляет в коллекцию элементы того же типа. Добавляет начиная с того места,
    //  где заканчивается целевая коллекция "index".
    public void addAll(int index, Collection<? extends T> c) {
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Куда вставляешь? не видишь, что мимо?");
        }

        if (c.size() == 0)
            throw new NoSuchElementException("Ваша коллекция пуста, возвращайтесь домой.");
        else {
            Object[] elements = c.toArray();
            Object[] newArray;
            if (elements.length >= (array.length - index)) {
                newArray = new Object[(index + elements.length) * 2];
                System.arraycopy(array, 0, newArray, 0, array.length);
                System.arraycopy(elements, 0, newArray, index, elements.length);
                array = newArray;
            } else {
                newArray = new Object[index + elements.length];
                System.arraycopy(array, 0, newArray, 0, index);
                System.arraycopy(elements, 0, newArray, index, elements.length);
                array = newArray;
            }
        }
    }

    //  метод возвращает размер коллекции
    //  массив перевожу в стрим, фильтрую по принципу "оставить всё, что не null и считаю количество"
    public int size() {
        return (int) Arrays.stream(array).filter(Objects::nonNull).count();
    }

    public static void bubbleSortedCollection(CustomArrayList<?> t) {
        Comparator<Object> comparator = Comparator.comparing(Object::hashCode);
        Object temp;
        boolean hasSwapped = false; //Флаг
//        int count = 0;
        Object[] elements = t.array;
        for (int i = 0; i < t.size(); i++) {
            for (int j = 1; j <= t.size() - 1; j++) {
                if (comparator.compare(elements[j - 1], elements[j]) > 0) {
                    hasSwapped = true;
                    temp = elements[j - 1];
                    elements[j - 1] = elements[j];
                    elements[j] = temp;
                }
            }
//            count++;
            if (!hasSwapped) return;
            else {
                hasSwapped = false;
            }
//            System.out.println(count);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomArrayList<?> that = (CustomArrayList<?>) o;

        if (index != that.index) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        int result = INIT_SIZE;
        result = 31 * result + Arrays.hashCode(array);
        result = 31 * result + index;
        return result;
    }
}
