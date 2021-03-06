package com.example.androidlesson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import com.example.androidlesson.ContactsService.MyBinder;

public class MainActivity extends AppCompatActivity implements ServiceProvider {
    ContactsService mService;
    boolean mBound = false;
    boolean createdFirstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this, ContactsService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        createdFirstTime = savedInstanceState == null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBinder binder = (MyBinder) service;
            mService = binder.getService();
            mBound = true;
            if (createdFirstTime){
                addContactListFragment();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    private void addContactListFragment(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ContactListFragment fragment = new ContactListFragment();
        ft.add(R.id.fragment_list, fragment);
        ft.commit();
    }

    @Override
    public ContactsService getService() {
        return mService;
    }
}
