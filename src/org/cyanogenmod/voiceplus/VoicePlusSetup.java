package org.cyanogenmod.voiceplus;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

// TODO: phase 2
public class VoicePlusSetup extends Activity {
    class AccountAdapter extends ArrayAdapter<Account> {
        AccountAdapter() {
            super(VoicePlusSetup.this, android.R.layout.simple_list_item_single_choice);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);

            CheckedTextView tv = (CheckedTextView) view.findViewById(android.R.id.text1);
            Account account = getItem(position);
            tv.setText(account.name);

            return view;
        }
    }

    Account NULL;

    ListView lv;
    AccountAdapter accountAdapter;
    SharedPreferences settings;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        accountAdapter = new AccountAdapter();
        settings = getSharedPreferences("settings", MODE_PRIVATE);

        LinearLayout statusContainer = (LinearLayout)findViewById(R.id.status_container);
        TextView status = (TextView)findViewById(R.id.status);
        final String[] permissions = new String[] {
            Manifest.permission.BROADCAST_SMS,
            Manifest.permission.WRITE_SECURE_SETTINGS,
            "android.permission.INTERCEPT_SMS",
        };
        boolean ok = true;
        for (String permission: permissions) {
            if (checkCallingOrSelfPermission(permission) == PackageManager.PERMISSION_DENIED) {
                status.setText(getString(R.string.not_granted, permission));
                ok = false;
            }
        }
        if (ok)
            statusContainer.setVisibility(View.GONE);

        lv = (ListView) findViewById(R.id.list);
        lv.setAdapter(accountAdapter = new AccountAdapter());

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Account account = accountAdapter.getItem(position);

                final String previousAccount = settings.getString("client_id", null);
                new Thread() {
                    @Override
                    public void run() {
                        super.run();invalidateToken(previousAccount);
                    }
                }.start();

                if (account == NULL) {
                    settings.edit().remove("account").remove("_rnr_se").commit();
                    return;
                }

                lv.clearChoices();
                lv.requestLayout();
                getToken(account, position);
            }
        });

        String selectedAccount = settings.getString("client_id", null);

        NULL = new Account(getString(R.string.disable), "com.google");
        accountAdapter.add(NULL);
        int selected = 0;
        for (Account account : AccountManager.get(this).getAccountsByType("com.google")) {
            if (account.name.equals(selectedAccount))
                selected = accountAdapter.getCount();
            accountAdapter.add(account);
        }

        lv.setItemChecked(selected, true);
        lv.requestLayout();

        startService(new Intent(this, VoicePlusService.class));
    }

    void invalidateToken(String account) {
        Log.e(LOGTAG, "invalidateToken() called.");
    }

    private static final String LOGTAG = "VoicePlusSetup";

    void getToken(final Account account, final int position) {
        Log.e(LOGTAG, "getToken() called.");
    }
}
