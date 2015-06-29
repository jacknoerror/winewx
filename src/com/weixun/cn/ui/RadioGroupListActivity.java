package com.weixun.cn.ui;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.easemob.chatuidemo.activity.MainActivity.OkListAdapter;
import com.hm.view.pullview.HmPullToRefreshView;
import com.jacktao.utils.JackWindowTitleManager;
import com.jacktao.utils.JackWindowTitleManager.JackTitleConst;
import com.weixun.cn.Const;
import com.weixun.cn.R;
import com.weixun.cn.bean.CmListItem;

public class RadioGroupListActivity extends Activity implements OnClickListener {

	private static final long ANIMATION_DURATION = 330;
	private ImageView chosenRed;
	private float lastPos;
	private RadioGroup mRadioGroup;
	private RadioButton[] mRadioBtns;
	private final int START_BTNID = 0x1234;
	private ListView mListView;

	private final float[] POSITIONS = new float[] { Const.SCREEN_WIDTH / 3 * 0,
			Const.SCREEN_WIDTH / 3 * 1, Const.SCREEN_WIDTH / 3 * 2  };
	private String r1;
	private String r2;
	private String r3;
	private String r12;
	private String title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_radiogrouplist);
		
		Intent intent = getIntent();
		int intExtra = intent.getIntExtra(Const.EXTRA_EXRGB, 0);
		if (intExtra == 0) {
			title = "我的活动";
			r1 = "我的活动";
			r2 = "历史活动";
			r3 = "参与活动";
		} else {
			title = "我的拼箱";
			r1 = "我的拼箱";
			r2 = "历史拼箱";
			r3 = "参与拼箱";
		}
		
		new JackWindowTitleManager(this).setComponent(JackTitleConst.CUSTOMTITLE_ID_MIDTEXT, title);
		
		((RadioButton) findViewById(R.id.btn_user_r1)).setText(r1);
		((RadioButton) findViewById(R.id.btn_user_r2)).setText(r2);
		((RadioButton) findViewById(R.id.btn_user_r3)).setText(r3);
		
		mListView = (ListView) findViewById(R.id.list_rgl);
		HmPullToRefreshView ptrv_rgl = (HmPullToRefreshView ) findViewById(R.id.ptrv_rgl);
		
		initSubTabs(new int[]{R.id.btn_user_r1,R.id.btn_user_r2,R.id.btn_user_r3});
		
		ArrayList<CmListItem> dataList = new ArrayList<CmListItem>();
//		dataList.add(new CmListItem());
//		dataList.add(new CmListItem());
		mListView.setAdapter(new OkListAdapter(dataList,this));//
	}
	
	
	/**
	 * @param RADIOID 
	 * 
	 */
	private void initSubTabs(int[] RADIOID) {
		mRadioGroup = (RadioGroup)  findViewById(R.id.radiogroup_usercenter);
		int radio_count = RADIOID.length;
		mRadioBtns = new RadioButton[radio_count];
		for (int i = 0; i < radio_count; i++) {
			mRadioBtns[i] = (RadioButton) findViewById(RADIOID[i]);
			mRadioBtns[i].setId(START_BTNID + i);
			mRadioBtns[i].setOnClickListener(this);
		}


		chosenRed = (ImageView)  findViewById(R.id.img_user_red);
		FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)chosenRed.getLayoutParams();
		params.width=(int) (Const.SCREEN_WIDTH/3);
		//		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams((int) (Const.SCREEN_WIDTH/3), 3);
		params.gravity = Gravity.BOTTOM;
		chosenRed.setLayoutParams(params);
		
		setSelect(0);
	}
	
	private void setSelect(int index) {
		float fromPos = lastPos, toPos = POSITIONS[index];
		Animation animation = new TranslateAnimation(fromPos, toPos, 0, 0);
		animation.setFillAfter(true);
		animation.setDuration(ANIMATION_DURATION);
		animation.setInterpolator(new DecelerateInterpolator());
		chosenRed.setAnimation(animation);
		chosenRed.startAnimation(animation);
		lastPos = toPos;

		
		mRadioGroup.check(START_BTNID+index);
	}
	
	@Override
	public void onClick(View v) {
		int index = v.getId() - START_BTNID;
		//TODO
		setSelect(index);
		
	}
}
