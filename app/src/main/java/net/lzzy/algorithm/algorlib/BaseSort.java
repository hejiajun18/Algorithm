package net.lzzy.algorithm.algorlib;

import java.util.Calendar;

/**
 * Created by lzzy_gxy on 2019/6/15.
 * Description:类型参数T需要加约束，约束T实现Comparable<T>接口
 */
public abstract class BaseSort<T extends Comparable<? super T>> {
    //region l.field字段   最小权限原则

    T[] items;
    private long duration;
    private int compareCount;
    private int swapCount;
    int moveStep;
    //endregion
    BaseSort(T[] items) {
        this.items = items;
        compareCount = 0;
        swapCount = 0;
        moveStep = 0;
    }

    boolean bigger(T a, T b) {
        compareCount++;
        return a.compareTo(b) > 0;
    }

    void swap(int i, int j) {
        T tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }

    public String getResult(){
        String display = "";
        for (T i : items) {
            display = display.concat(i + ",");
        }
        return display.substring(0,display.length()-1);
    }

    public void sortWithTime(){
        long start = System.currentTimeMillis();
        sort();
        duration = System.currentTimeMillis() - start;
    }

    abstract void sort();

    public long getDuration() {
        return duration;
    }

    public int getCompareCount() {
        return compareCount;
    }

    public int getSwapCount() {
        return swapCount;
    }

    public int getMoveStep(){
        return moveStep;
    }
}
