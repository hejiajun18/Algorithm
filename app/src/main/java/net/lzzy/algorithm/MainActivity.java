package net.lzzy.algorithm;

import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Random;

/**
 * @author Administrator
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Integer[] items;
    private EditText edtItems;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtItems = findViewById(R.id.activity_main_edt_items);
        findViewById(R.id.activity_main_btn_generate).setOnClickListener(this);
        findViewById(R.id.activity_main_btn_sort).setOnClickListener(this);
        tvResult = findViewById(R.id.activity_main_tv_result);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_btn_generate:
                generateItems();
                displayItems(edtItems);
                break;
            case R.id.activity_main_btn_sort:
                insertSort();
//                directSort();
                displayItems(tvResult);
                break;
            default:
                break;
        }
    }

    private void displayItems(TextView tv) {
        String display = "";
        for (Integer i : items) {
            display = display.concat(i + ",");
        }
        display = display.substring(0, display.length() - 1);
        tv.setText(display);
    }

    private void directSort() {
        //todo:直接选择排序的具体实现
        //todo:for循环
        //分为有序区和无序区，每一趟排序都在无序区依次对比，记录对比区域的最小元素的位置
        //然后把无序区第一个元素和所记录的最小元素进行交换，无序区少一个、有序区多一个，循环往复直至无序区元素数量为0
        for (int i = 0; i < items.length - 1; i++) {
            int minPos = i;
            for (int j = i + 1; j < items.length; j++) {
                if (items[minPos].compareTo(items[j]) > 0) {
                    minPos = j;
                }
            }
            swap(minPos, i);
        }
    }

    private void insertSort() {
        //todo:直接插入排序的具体实现
        //分为有序区和无序区，从第一个元素开始，默认这个元素已经排序，取出下一个元素，在已经排序的元素中从后向前扫描
        //如果这个已排序元素大于取出元素，将这个元素移到下一个位置
        //直到找到已排序的元素小于或者等于取出元素，将取出的元素插入到这个位置中
        for (int i = 1; i < items.length; i++){
            int j = i - 1;
            if (items[j].compareTo(items[i]) < 0){
                continue;
            }
            Integer tmp = items[i];
            while (j >= 0 && items[j].compareTo(tmp) > 0){
                items[j + 1] = items[j];
                j--;
            }
            items[j + 1] = tmp;
        }
    }

    private void swap(int m, int n) {
        int tmp = items[m];
        items[m] = items[n];
        items[n] = tmp;
    }

    private void generateItems() {
        items = new Integer[10];
        Random generator = new Random();
        for (int i = 0; i < items.length; i++) {
            items[i] = generator.nextInt(99);
        }
    }
}
