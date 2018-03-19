package com.example.gabriele.tablayoutfragment2;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Gabriele on 08/03/2018.
 */

public class MailSendFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.mail_fragment, container, false);

        Button send = (Button) rootView.findViewById(R.id.btn);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText dest = (EditText) rootView.findViewById(R.id.dest);
                EditText obj = (EditText) rootView.findViewById(R.id.obj);
                EditText msg = (EditText) rootView.findViewById(R.id.msg);

                String mailto = "mailto:" + dest.getText() +
                        "?subject=" + Uri.encode("" + obj.getText()) +
                        "&body=" + Uri.encode("" + msg.getText());
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse(mailto));
                try {
                    startActivity(emailIntent);
                } catch (ActivityNotFoundException e) {

                }
            }
        });
        return rootView;

    }
}