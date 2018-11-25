package com.example.asus.startproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.latte_core.activities.ProxyActivity;
import com.example.latte_core.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {



    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}

