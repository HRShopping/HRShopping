package com.example.helloworld.huaruanshopping.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.helloworld.huaruanshopping.R;
import com.example.helloworld.huaruanshopping.bean.address;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by helloworld on 2017/1/22.
 */

public class fragmentAddress extends Fragment {
    private EditText guestName;
    private EditText guestAddress;
    private EditText guestPhone;
    private EditText guestRemark;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.from(getContext()).inflate(R.layout.fragment_address, container, false);
        initView();
        return view;
    }

    private void initView() {
        guestName = (EditText) view.findViewById(R.id.GuestName);
        guestAddress = (EditText) view.findViewById(R.id.GuestAddress);
        guestPhone = (EditText) view.findViewById(R.id.GuestPhone);
        guestRemark = (EditText) view.findViewById(R.id.GuestRemark);
    }

    public ArrayList<String> sendInfo() {
        ArrayList<String> info = new ArrayList<>();
        info.add(guestName.getText().toString());
        info.add(guestAddress.getText().toString());
        info.add(guestPhone.getText().toString());
        info.add(guestRemark.getText().toString());
        return info;
    }
}
