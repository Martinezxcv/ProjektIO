package com.example.saper;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

    private Context context;
    private int gridSize;
    private int singleImageResource = R.drawable.z1; // Resource ID of the single image

    public ImageAdapter(Context context, int gridSize) {
        this.context = context;
        this.gridSize = gridSize;
    }

    @Override
    public int getCount() {
        return gridSize * gridSize;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView foto;
        if(convertView == null){
            foto = new ImageView(context);
            foto.setLayoutParams(new GridView.LayoutParams(85,85));
            foto.setScaleType(ImageView.ScaleType.CENTER_CROP);
            foto.setPadding(8,8,8,8);
        }
        else
            foto = (ImageView) convertView;

        foto.setImageResource(singleImageResource);
        return foto;
    }


}

