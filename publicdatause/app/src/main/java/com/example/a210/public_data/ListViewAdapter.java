package com.example.a210.public_data;

/**
 * Created by ohkaning-office on 2017-05-03.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<ListViewItem> arItem;

    private View.OnClickListener telClickListener;
    private View.OnClickListener mapClickListener;

    // ListViewAdapter의 생성자

    private Context context;
    private AQuery aQuery;

    public ListViewAdapter(Context context) {
        this.arItem = new ArrayList<>();
        this.context = context;
        aQuery = new AQuery(context);
    }


    public ArrayList<ListViewItem> getArItem() {
        return arItem;
    }

    public void setTelClickListener(View.OnClickListener telClickListener) {
        this.telClickListener = telClickListener;
    }

    public void setMapClickListener(View.OnClickListener mapClickListener) {
        this.mapClickListener = mapClickListener;
    }


    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return arItem.size();
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        View cv_view;

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row, parent, false);
        }



        ListViewItem item = getItem(position);

        ImageView ivPhoto = (ImageView)convertView.findViewById(R.id.ivPhoto);
        TextView tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);
        TextView tvAdress = (TextView)convertView.findViewById(R.id.tvAdress);
        TextView tvTel = (TextView)convertView.findViewById(R.id.tvTel);
        Button btnMap = (Button)convertView.findViewById(R.id.btnMap);
        Button btnTel = (Button)convertView.findViewById(R.id.btnTel);

        aQuery.id(ivPhoto).image(item.getFirstimage(), true, false);
        tvTitle.setText(item.getTitle());
        tvAdress.setText(item.getAddress());
        tvTel.setText(item.getTel());

        btnTel.setTag(position);
        if(telClickListener != null){
            btnTel.setOnClickListener(telClickListener);
        }

        btnMap.setTag(position);
        if(mapClickListener!= null){
            btnMap.setOnClickListener(mapClickListener);
        }
        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public ListViewItem getItem(int position) {

        return arItem.get(position) ;
    }

}
