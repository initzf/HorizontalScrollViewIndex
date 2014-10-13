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

import com.example.horizontalscrollviewindex.R;

public class MainActivity extends Activity {
class CheckScroll implements Runnable {
	@Override
	public void run() {
		checkTotalWidth();
		printLog("screenWidth:" + screenWidth);
		printLog("childAllWidth:" + childAllWidth);
		int scrollX = sv.getScrollX();
		printLog("scrollX:" + scrollX);
		int right2 = sv.getRight();
		printLog("getRight:" + right2);
		if (scrollX > 0) {
			left.setVisibility(View.VISIBLE);
		} else {
			left.setVisibility(View.GONE);
		}
		int i = scrollX + right2;
		printLog("i:childAllWidth " + i + ":" + childAllWidth);
		if (i < childAllWidth) {
			right.setVisibility(View.VISIBLE);
		} else {
			right.setVisibility(View.GONE);
		}
	}
}

public class MyOnTouchListener implements OnTouchListener {

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int action = event.getAction();
		if (action == MotionEvent.ACTION_MOVE) {
			//立即获取sv.getScrollX()获取的不准确
			handler.postDelayed(checkScroll, 1000);
		}
		return false;
	}

}

private HorizontalScrollView sv;
private int childAllWidth = 0;
private String tag = "DemoHorizenActivity";
private int screenWidth = 0;
private ImageView left,right;
private Handler handler;
private CheckScroll checkScroll;

@Override
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main);
	sv = (HorizontalScrollView) findViewById(R.id.svc);
	left =(ImageView) findViewById(R.id.ivleft);
	right =(ImageView) findViewById(R.id.ivright);
	// right.show();
	handler = new Handler();
	checkScroll = new CheckScroll();
	sv.setOnTouchListener(new MyOnTouchListener());

}

public void printLog(String string) {
	Log.d(tag, string);

}

@Override
protected void onResume() {
	super.onResume();
	handler.postDelayed(checkScroll, 100);

}

private void checkTotalWidth() {
	if (childAllWidth > 0) {
		return;
	}
	//不明白为什么在onresume中也无法获得到尺寸
	ViewGroup group = (ViewGroup) sv.getChildAt(0);
	printLog("group.getChildCount():" + group.getChildCount());
	for (int i = 0; i < group.getChildCount(); i++) {
		int childWidth = group.getChildAt(i).getMeasuredWidth();
		printLog("childWidth:" + childWidth);
		childAllWidth += childWidth;
	}
}
}
