package ihc.p7.statstips;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class OddListViewAdapter extends ArrayAdapter<Odd> {

    public OddListViewAdapter(@NonNull Context context, ArrayList<Odd> resource) {
        super(context, R.layout.odd_listview, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Odd odd = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.odd_listview, parent, false);

        TextView nome = (TextView) convertView.findViewById(R.id.gameTitle);
        TextView win = (TextView) convertView.findViewById(R.id.winVal);
        TextView draw = (TextView) convertView.findViewById(R.id.drawVal);
        TextView loss = (TextView) convertView.findViewById(R.id.lossVal);

        nome.setText(odd.nome);
        win.setText(odd.win);
        draw.setText(odd.draw);
        loss.setText(odd.loss);
        return convertView;
    }
}
