package com.example.mostafawattad.sportbuddy;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;
import com.example.mostafawattad.sportbuddy.data.event;


public class CarTableDataAdapter extends TableDataAdapter<event> {

    private static final int TEXT_SIZE = 14;
    private static final NumberFormat PRICE_FORMATTER = NumberFormat.getNumberInstance();


    public CarTableDataAdapter(Context context, List<event> data) {
        super(context, data);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        event event = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = renderProducerLogo(event, parentView);
                break;
            case 1:
                renderedView = renderString(event.getDate());
                break;

            case 2:
                renderedView = renderString(event.getLocation());
                break;

            case 3:
                renderedView = renderInfoLogo(event, parentView);
                break;
        }

        return renderedView;
    }

    private View renderPrice(event event) {
        String priceString = PRICE_FORMATTER.format(event.getPrice()) + " €";

        TextView textView = new TextView(getContext());
        textView.setText(priceString);
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);

        if (event.getPrice() < 50000) {
            textView.setTextColor(0xFF2E7D32);
        } else if (event.getPrice() > 100000) {
            textView.setTextColor(0xFFC62828);
        }

        return textView;
    }

    private View renderPower(event event, ViewGroup parentView) {
        View view = getLayoutInflater().inflate(R.layout.table_cell_power, parentView, false);
        TextView kwView = (TextView) view.findViewById(R.id.kw_view);
        TextView psView = (TextView) view.findViewById(R.id.ps_view);

        kwView.setText(event.getKw() + " kW");
        psView.setText(event.getPs() + " PS");

        return view;
    }

    private View renderCatName(event event) {
        return renderString(event.getName());
    }

    private View renderProducerLogo(event event, ViewGroup parentView) {
        View view = getLayoutInflater().inflate(R.layout.table_cell_image, parentView, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageResource(event.getType().getLogo());
        return view;
    }
    private View renderInfoLogo(event event, ViewGroup parentView) {
        View view = getLayoutInflater().inflate(R.layout.table_cell_image, parentView, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageResource(R.mipmap.location_marker);
        return view;
    }

    private View renderString(String value) {
        TextView textView = new TextView(getContext());
        textView.setText(value);
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }

}
