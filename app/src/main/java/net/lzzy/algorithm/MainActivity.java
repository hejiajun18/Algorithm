package net.lzzy.algorithm;

import android.net.sip.SipSession;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AndroidException;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import net.lzzy.algorithm.algorlib.BaseSearch;
import net.lzzy.algorithm.algorlib.BaseSort;
import net.lzzy.algorithm.algorlib.SearchFactory;
import net.lzzy.algorithm.algorlib.SortFactory;

import java.util.Locale;
import java.util.Objects;
import java.util.Random;

/**
 * @author Administrator
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Integer[] items;
    private EditText edtItems;
    private TextView tvResult;
    private Spinner spinner;
    private LinearLayout container;
    private Spinner spSearch;
    private Button btnSort;
    //单一职责原则
    //ocp:open-close principle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSpinner();
        iniViews();
        initSearch();
    }

    /**
     * 查找部分的视图逻辑
     */
    private void initSearch() {
        Spinner spSearch = findViewById(R.id.activity_main_sp_search);
        spSearch.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, SearchFactory.getSearchNames()));
        LinearLayout container = findViewById(R.id.activity_main_btn_container);
        findViewById(R.id.activity_main_btn_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetSearch();
            }
        });
        resetSearch();
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            BaseSearch<Integer> search =
                    SearchFactory.getInstance(spSearch.getSelectedItemPosition(), items);
            if (search != null) {
                int pos = search.search(v.getId());
                tvResult.setText("该元素位于数组的第".concat((pos + 1) + "位"));
            }
        }
    };

    private void resetSearch() {
        container.removeAllViews();
        generateItems();
        if (spSearch.getSelectedItemPosition() == 1) {
            btnSort.callOnClick();
        }
        for (Integer i : items) {
            Button btn = new Button(this);
            btn.setText(String.format(i.toString(), Locale.CHINA));
            btn.setId(i);
            btn.setLayoutParams(new LinearLayout.LayoutParams(0,
                    ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            btn.setOnClickListener(listener);
            container.addView(btn);
        }
    }

    private void initSpinner() {
        Spinner spinner = findViewById(R.id.activity_main_sp);
        spinner.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, SortFactory.getSortNames()));
    }

    private void iniViews() {
        edtItems = findViewById(R.id.activity_main_edt_items);
        findViewById(R.id.activity_main_btn_generate).setOnClickListener(this);
        btnSort = findViewById(R.id.activity_main_btn_sort);
        btnSort.setOnClickListener(this);
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
                BaseSort<Integer> sort = SortFactory.getInstance(spinner.getSelectedItemPosition(), items);
                BaseSort<Integer> sortNotNull = Objects.requireNonNull(sort);
                sortNotNull.sortWithTime();
                String result = sort.getResult();
                tvResult.setText(result);
                Toast.makeText(this, "总时长：" + sort.getDuration(),
                        Toast.LENGTH_SHORT).show();
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

    private void generateItems() {
        items = new Integer[10];
        Random generator = new Random();
        for (int i = 0; i < items.length; i++) {
            items[i] = generator.nextInt(99);
        }
    }
}
