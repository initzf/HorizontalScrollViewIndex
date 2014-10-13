package com.example.horizontal;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.horizontalscrollviewindex.R;

/**
 * �������Ƿ�ɼ���������ʾ�ĺ��򻬶��� ,ʹ����HorizontalScrollView��ȫһ��
 * 
 * @author jing
 * 
 */
public class IndicatorHorizontalScrollView extends FrameLayout {

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
				// ������ȡsv.getScrollX()��ȡ�Ĳ�׼ȷ
				handler.postDelayed(checkScroll, 1000);
			}
			return false;
		}

	}

	private CheckScroll checkScroll;

	private int childAllWidth = 0;
	private Handler handler;
	private ImageView left, right;
	private int screenWidth = 0;
	private HorizontalScrollView sv;
	private String tag = "IndicatorHorizontalScrollView";

	public IndicatorHorizontalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		View view = LayoutInflater.from(context).inflate(
				R.layout.indicatorlayout, null);
		this.addView(view);
		sv = (HorizontalScrollView) view.findViewById(R.id.svc);
		left = (ImageView) view.findViewById(R.id.ivleft);
		right = (ImageView) view.findViewById(R.id.ivright);
		// right.show();
		handler = new Handler();
		checkScroll = new CheckScroll();
		sv.setOnTouchListener(new MyOnTouchListener());
		handler.postDelayed(checkScroll, 100);
		// �˴���õ�ֻ����ӵĲ����ļ��е�view,��Ԫ��ͨ��addView��ӽ�ȥ
		int cout = this.getChildCount();
		printLog("cout:" + cout);
		// for (int i = 0; i < cout; i++) {
		// View child0 = getChildAt(i);
		// printLog(child0.getClass().toString());
		// if (child0 instanceof ViewGroup) {
		// int cout1 = ((ViewGroup) child0).getChildCount();
		// printLog("cout1:" + cout1);
		// for (int j = 0; j < cout1; j++) {
		// View child1 = ((ViewGroup) child0).getChildAt(j);
		// printLog(child1.getClass().toString());
		// }
		// }
		// }
	}

	/**
	 * ��Ԫ�ص����ͨ�������ӵ�,�ڴ����غ�Ӹ�Ԫ����ɾ��,��ӵ�HorizontalScrollView
	 */
	@Override
	public void addView(View child, int index,
			android.view.ViewGroup.LayoutParams params) {
		super.addView(child, index, params);

		// printLog("addView");
		// if (child instanceof ViewGroup) {
		// int cout1 = ((ViewGroup) child).getChildCount();
		// printLog("cout1:" + cout1);
		// for (int j = 0; j < cout1; j++) {
		// View child1 = ((ViewGroup) child).getChildAt(j);
		// printLog(child1.getClass().toString());
		// }
		// }
		if ((child instanceof LinearLayout)&&sv.getChildCount()==0) {
			printLog("child instanceof LinearLayout");
			ViewGroup layout = (ViewGroup) child.getParent();
			layout.removeView(child);
			sv.addView(child);

		}
	}

	private void checkTotalWidth() {
		if (childAllWidth > 0) {
			return;
		}
		// ������Ϊʲô��onresume��Ҳ�޷���õ��ߴ�
		ViewGroup group = (ViewGroup) sv.getChildAt(0);
		printLog("group.getChildCount():" + group.getChildCount());
		for (int i = 0; i < group.getChildCount(); i++) {
			int childWidth = group.getChildAt(i).getMeasuredWidth();
			printLog("childWidth:" + childWidth);
			childAllWidth += childWidth;
		}
	}

	public void printLog(String string) {
		Log.d(tag, string);

	}
	public void smoothScrollTo(int x,int y){
		printLog("x:"+x+",y:"+y);
		sv.scrollTo(0, 0);
		sv.smoothScrollTo(x, y);
		handler.postDelayed(checkScroll, 1000);
	}
}
