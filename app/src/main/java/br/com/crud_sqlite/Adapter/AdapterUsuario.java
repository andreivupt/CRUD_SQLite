package br.com.crud_sqlite.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.crud_sqlite.Model.UsuarioGitHub;
import br.com.crud_sqlite.R;

/**
 * Created by andrei.vupt on 12/11/2017.
 */

public class AdapterUsuario extends ArrayAdapter<UsuarioGitHub> {

    private Context context;
    private List<UsuarioGitHub> list;

    public AdapterUsuario(Context context, List<UsuarioGitHub> list) {
        super(context, R.layout.row_user, list);
        this.context = context;
        this.list = list;
    }

    public View getView(int position, View view, ViewGroup group){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_user, group, false);

        TextView usuario = (TextView) rowView.findViewById(R.id.tvName);
        TextView id = (TextView) rowView.findViewById(R.id.tvId);
        TextView login = (TextView) rowView.findViewById(R.id.tvLogin);

        usuario.setText(list.get(position).getName());
        id.setText(list.get(position).getId());
        login.setText(list.get(position).getLogin());

        return rowView;
    }
}
