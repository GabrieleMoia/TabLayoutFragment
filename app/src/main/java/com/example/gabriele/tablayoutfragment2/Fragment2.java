package com.example.gabriele.tablayoutfragment2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by Gabriele on 08/03/2018.
 */

public class Fragment2 extends Fragment {

    AdapterActivity adapterActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment2, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DataAccessUtils.initDataSource(getContext());
        adapterActivity = new AdapterActivity(getContext());
        ListView listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(adapterActivity);


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder adb1 = new AlertDialog.Builder(getContext());
                adb1.setTitle("Remove");
                adb1.setMessage("Sei sciuro di rimuovere l'elemento");

                adb1.setPositiveButton(R.string.alert_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //cose
                        DataAccessUtils.removeItem(Singleton.getInstance().getItemList().get(position), getContext());
                        adapterActivity.setValues();
                    }
                });
                adb1.setNegativeButton(R.string.alert_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                adb1.show();
                return true;

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent det = new Intent(getActivity(), DetailActivity.class); //Far partire un'altra applicazione

                Contatto cont = DataAccessUtils.getItemByPosition(getContext(), position);

                String name = cont.getNome();
                String number = cont.getNumero();


                det.putExtra("nome", name);
                det.putExtra("numero", number);

                startActivity(det);
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        adapterActivity.setValues();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {

            String nameResult = (String) data.getExtras().getString("name");
            String numberResult = (String) data.getExtras().getString("number");

            Contatto contactResult = new Contatto(nameResult, numberResult);

            DataAccessUtils.addItem(contactResult, getContext());
            adapterActivity.setValues();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add: {

                Intent i = new Intent(getActivity(), SecretActivity.class); //Far partire un'altra applicazione
                startActivityForResult(i, 2);
            }
            default: {
                return super.onOptionsItemSelected(item);
            }

        }

    }
}
