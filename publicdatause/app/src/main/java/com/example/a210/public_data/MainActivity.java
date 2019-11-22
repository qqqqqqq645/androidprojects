package com.example.a210.public_data;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ListViewItem data;

    ListView listView = null;
    ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listview);
        // Adapter 생성
        adapter = new ListViewAdapter(MainActivity.this);
        adapter.setTelClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListViewItem item = adapter.getItem((Integer) v.getTag());

                String tel = "tel:" + item.getTel();
//                startActivity(new Intent("android.intent.action.CALL", Uri.parse(tel)));
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tel)));
            }
        });
        adapter.setMapClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListViewItem item = adapter.getItem((Integer) v.getTag());
                Uri gmmIntentUri = Uri.parse("geo:" + item.getMapy() + "," + item.getMapx());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        listView.setAdapter(adapter);

        //데이터 로드 부분
        loadData();
    }

    private void loadData() {
        AQuery aq = new AQuery(MainActivity.this);

        HashMap<String, String> params = new HashMap<>();
        params.put("ServiceKey","WMoao2wlyH%2Fg8VDiX%2Bg4dmAimYFxy58FB7Qu%2FNbKl4wOGlNq%2FJGYs7dfK3x3FpKQK9zysPxgunNGdE4bsO15dA%3D%3D");
        params.put("numOfRows","10");
        params.put("pageNo","1");
        params.put("MobileOS","ETC");
        params.put("MobileApp","AppTest");
        params.put("arrange","A");
        params.put("listYN","Y");
        params.put("areaCode","1");
//        params.put("sigunguCode","1");
        params.put("eventStartDate","20191001");
//        params.put("eventStartDate","20170901");
//        params.put("eventEndDate","");
        params.put("_type","json");

        String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival";
        url = addParams(url, params);
        //http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival?numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&arrange=A&listYN=Y&areaCode=1&eventStartDate=20191001&ServiceKey=WMoao2wlyH%2Fg8VDiX%2Bg4dmAimYFxy58FB7Qu%2FNbKl4wOGlNq%2FJGYs7dfK3x3FpKQK9zysPxgunNGdE4bsO15dA%3D%3D&return_type=json
        //주소?파라미터=값&파라미터=값&Servicekey=서비스키&return_type=json
        aq.ajax(url, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject resutl, AjaxStatus status) {
                if (resutl != null) {
                    //sucess
                    Log.i("test", resutl.toString());

                    try {
                        JSONArray jar = resutl.optJSONObject("response").optJSONObject("body").optJSONObject("items").optJSONArray("item");

                        ArrayList<ListViewItem> arItem = new ArrayList<ListViewItem>();
                        for(int i=0; i<jar.length(); i++){
                            JSONObject jobj = jar.optJSONObject(i);

                            ListViewItem item = new ListViewItem();
/*                            item.setTitle(jobj.optString("title"));
                            item.setAddress(jobj.optString("addr1"));
                            item.setFirstimage(jobj.optString("firstimage"));
                            item.setMapx(jobj.optDouble("mapx"));
                            item.setMapy(jobj.optDouble("mapy"));
                            item.setTel(jobj.optString("tel"));
*/
                            item.setTitle(jobj.optString("contentid"));
                            item.setAddress(jobj.optString("createdtime"));
                            item.setFirstimage(jobj.optString("firstimage"));
                            item.setMapx(jobj.optDouble("mapx"));
                            item.setMapy(jobj.optDouble("mapy"));
                            item.setTel(jobj.optString("tel"));
                            arItem.add(item);
                        }

                        if(arItem.size()>0){
                            adapter.getArItem().addAll(arItem);
                            adapter.notifyDataSetChanged();
                        }
                    }
                    catch (Exception e){
                        Toast.makeText(MainActivity.this, "JSON Parsing 오류 발생", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //fail
                    Toast.makeText(MainActivity.this, "잘못된 요청입니다", Toast.LENGTH_SHORT).show();
                }
            }
        }.timeout(20000));
    }

    private String addParams(String url, HashMap<String, String> mapParam) {
        StringBuilder stringBuilder = new StringBuilder(url+"?");

        if(mapParam != null){
            for ( String key : mapParam.keySet() ) {
                stringBuilder.append(key+"=");
                stringBuilder.append(mapParam.get(key)+"&");
            }
        }
        return stringBuilder.toString();
    }

}