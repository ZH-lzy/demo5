package com.example.administrator.demo5_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener,
        OnItemSelectedListener, OnItemClickListener {

    private static final String TAG = "MainActivity";
    private ListView list;
    private ViewAdapter viewAdapter;
    private int selectedIndex = -1;
    private Bundle bundle = null;


    private class ViewAdapter extends BaseAdapter {
        private Context context;
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(inflater);
            LinearLayout linearLayout = null;

                linearLayout = (LinearLayout) layoutInflater.inflate(
                        R.layout.row, null);
                TextView Name1 = ((TextView) linearLayout
                        .findViewById(R.id.Name1));
                 Name1.setText(String.valueOf(list.get(position).get("name").toString()));
                TextView Address1 = (TextView) linearLayout.findViewById(R.id.Address1);
                Address1.setText(String.valueOf(list.get(position).get("address").toString()));
                TextView Number1 = (TextView) linearLayout.findViewById(R.id.Number1);
                Number1.setText(String.valueOf(list.get(position).get("number").toString()));
            return linearLayout;
        }

        public ViewAdapter(Context context) {
            this.context = context;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        public void addText(Bundle bundle) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", bundle.getString("text"));
            map.put("address", bundle.getString("text1"));
            map.put("number", bundle.getString("text2"));
            list.add(map);
            notifyDataSetChanged();
        }

        public void remove(int index) {
            if (index < 0)
                return;
            list.remove(index);
            notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case R.layout.add:

                if (resultCode == 2) {
                    bundle=data.getExtras();
                    viewAdapter.addText(bundle);
                } else if (resultCode == 6) {
                    Toast toast = Toast.makeText(this, "您取消了操作", Toast.LENGTH_LONG);
                    toast.show();

                }
                break;

            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                Intent addIntent = new Intent(this, Add.class);
                startActivityForResult(addIntent, R.layout.add);

                break;

            case R.id.btnDelete:
                viewAdapter.remove(selectedIndex);
                selectedIndex = -1;
                break;


        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        selectedIndex = position;
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        selectedIndex = arg2;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        list = (ListView) findViewById(R.id.list);
        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        Button btnDelete = (Button) findViewById(R.id.btnDelete);
        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        viewAdapter = new ViewAdapter(this);
        list.setAdapter(viewAdapter);
        list.setOnItemSelectedListener(this);
        list.setOnItemClickListener(this);

    }

}