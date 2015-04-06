package fotorealistico.pack;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;


public class ImageAdapter extends BaseAdapter {

	Context mContext;
	Integer[] imagens;
	
	
	public ImageAdapter(Context c, Integer[] ima) {
		mContext = c;
		imagens = ima;
	}

	public int getCount() {
		return imagens.length;
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		
		ImageView i = new ImageView(mContext);
		i.setImageResource(imagens[position]);
		i.setAdjustViewBounds(true);
		i.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		// i.setBackgroundResource(R.drawable.picture_frame);

		return i;
	}
	
}