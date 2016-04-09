package com.example.mostafawattad.sportbuddy;

import android.content.Context;
import android.util.AttributeSet;

import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import de.codecrafters.tableview.toolkit.SortStateViewProviders;
import de.codecrafters.tableview.toolkit.TableDataRowColorizers;
import com.example.mostafawattad.sportbuddy.data.event;


public class SortableCarTableView extends SortableTableView<event> {


    public SortableCarTableView(Context context) {
        this(context, null);
    }

    public SortableCarTableView(Context context, AttributeSet attributes) {
        this(context, attributes, 0);
    }

    public SortableCarTableView(Context context, AttributeSet attributes, int styleAttributes) {
        super(context, attributes, styleAttributes);


        SimpleTableHeaderAdapter simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(context, "Sport", "Time", "Location");
        simpleTableHeaderAdapter.setTextColor(context.getResources().getColor(R.color.table_header_text));
        simpleTableHeaderAdapter.setTextSize(16);
        setHeaderAdapter(simpleTableHeaderAdapter);

        int rowColorEven = context.getResources().getColor(R.color.table_data_row_even);
        int rowColorOdd = context.getResources().getColor(R.color.table_data_row_odd);
        setDataRowColoriser(TableDataRowColorizers.alternatingRows(rowColorEven, rowColorOdd));
        setHeaderSortStateViewProvider(SortStateViewProviders.brightArrows());

        setColumnWeight(0, 2);
        setColumnWeight(1, 3);
        setColumnWeight(2, 3);

        setColumnComparator(0, CarComparators.getCarProducerComparator());
        setColumnComparator(1, CarComparators.getCarNameComparator());
        setColumnComparator(2, CarComparators.getCarPowerComparator());
    }

}
