package com.example.androidlesson;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

public class ContactListFragment extends ListFragment {
    static final Contact[] contacts = {
            new Contact("Бабочка", "89512065656"),
            new Contact("Мотылёк", "89124591131"),
            new Contact("Займы", "89005553535")
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Список контактов");
        ArrayAdapter<Contact> contactAdapter = new ArrayAdapter<Contact>(getActivity(), 0, contacts){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null){
                    convertView = getLayoutInflater().inflate(R.layout.fragment_contact, null, false);
                }
                TextView nameView = (TextView) convertView.findViewById(R.id.contactName);
                TextView phoneNumberView = (TextView) convertView.findViewById(R.id.contactPhoneNumber);

                Contact currentContact = contacts[position];

                nameView.setText(currentContact.getName());
                phoneNumberView.setText(currentContact.getPhoneNumber());

                return convertView;
            }
        };
        setListAdapter(contactAdapter);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        ContactDetailsFragment detailsFragment = ContactDetailsFragment.newInstance((int) id);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_list, detailsFragment).addToBackStack(null);
        ft.commit();
    }
}
