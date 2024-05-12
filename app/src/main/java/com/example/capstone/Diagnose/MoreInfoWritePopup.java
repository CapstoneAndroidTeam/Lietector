package com.example.capstone.Diagnose;

import static com.example.capstone.Diagnose.DiagnoseWrite.WsuspiciousOrder;
import static com.example.capstone.Diagnose.DiagnoseWrite.WsuspiciousWord;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.capstone.R;

public class MoreInfoWritePopup extends Activity {
    ListView listView;
    private MoreInfoWriteListView adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.diagnose_morepage);
        Button OKBtn = findViewById(R.id.OkayButton);
        listView = findViewById(R.id.listView);
        adapter = new MoreInfoWriteListView(MoreInfoWritePopup.this, WsuspiciousOrder, WsuspiciousWord);
        listView.setAdapter(adapter);
        listView.getEmptyView();
        OKBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ok = new Intent();
                setResult(RESULT_OK, ok);
                finish();

            }
        });
    }
    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        return;
    }
}
