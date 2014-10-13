package com.example.horizontal.teest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import com.example.horizontal.IndicatorHorizontalScrollView;
import com.example.horizontal.IndicatorHorizontalScrollView;
import com.example.horizontalscrollviewindex.R;

public class MainActivity1 extends Activity {
	private IndicatorHorizontalScrollView sv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main1);
		sv = (com.example.horizontal.IndicatorHorizontalScrollView) findViewById(R.id.svc1);

	}
}
