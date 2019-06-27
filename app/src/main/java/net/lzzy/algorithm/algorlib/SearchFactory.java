package net.lzzy.algorithm.algorlib;

/**
 * Created by lzzy_gxy on 2019/6/22.
 * Description:
 */
public class SearchFactory {
    public static <T extends Comparable<? super T>> BaseSearch<T> getInstance(int key,T[] items){
        BaseSearch<T> Search;
        switch (key){
            case 0:
                Search=new DirectSearch<>(items);
                break;
            case 1:
                Search=new BinarySearch<>(items);
                break;
            default:
                return null;
        }
        return Search;
    }
    public static String[] getSearchNames(){
        return new String[]{"顺序查找","二分查找"};
    }
}
