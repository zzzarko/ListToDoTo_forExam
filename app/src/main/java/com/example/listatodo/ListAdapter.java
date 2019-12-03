package com.example.listatodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import  java.util.List;
import com.example.listatodo.Item;
import com.example.listatodo.R;

public class ListAdapter extends BaseAdapter {

    private List <Item> lista;

    public ListAdapter(List<Item> lista) {
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return  position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.item, parent, false);
        }
        Item currentItem = (Item) getItem(position);

        TextView tvNaziv = (TextView) convertView.findViewById(R.id.textViewNaziv);
        CheckBox cbDone = (CheckBox) convertView.findViewById(R.id.checkBox);
        TextView tvDate = (TextView) convertView.findViewById(R.id.textViewDate);

        tvNaziv.setText(currentItem.getNaziv());
        cbDone.setChecked(currentItem.isStatus());
        tvDate.setText(currentItem.getDate()+" "+currentItem.getTime());

        return convertView;


    }
    public void add(Item item) {
        lista.add(item);
        notifyDataSetChanged();
    }

    public void clear() {
        lista.clear();
        notifyDataSetChanged();
    }

    public void addData(List<Item> data){
        this.lista = data;
        notifyDataSetChanged();
    }

    public List<Item> getData(){
        return lista;
    }
}
