package net.lzzy.algorithm.algorlib;

/**
 * Created by lzzy_gxy on 2019/6/13.
 * Description:
 */
class InsertSort<T extends Comparable<? super T>> extends BaseSort<T>{

    InsertSort(T[] items) {
        super(items);
    }

    @Override
    public void sort() {
        for (int i = 1; i < items.length; i++) {
            int j = i - 1;
            if (bigger(items[i], items[j])) {
                continue;
            }
            T tmp = (T) items[i];
            while (j >= 0 && bigger(items[j], tmp)) {
                items[j + 1] = items[j];
                moveStep++;
                j--;
            }
            items[j + 1] = tmp;
        }
    }
}
