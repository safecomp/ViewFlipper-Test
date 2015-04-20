package safecomp.ir.viewflippertest;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ViewFlipper;
import android.app.Activity;

public class MainActivity extends Activity {

	ViewFlipper myViewFlipper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		myViewFlipper=(ViewFlipper)findViewById(R.id.myViewFlipper);
		
		findViewById(R.id.p1).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(setAnim(myViewFlipper.getDisplayedChild(),0)){
					myViewFlipper.setDisplayedChild(0);
				}
			}
		});
		findViewById(R.id.p2).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(setAnim(myViewFlipper.getDisplayedChild(),1)){
					myViewFlipper.setDisplayedChild(1);
				}
			}
		});
		findViewById(R.id.p3).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(setAnim(myViewFlipper.getDisplayedChild(),2)){
					myViewFlipper.setDisplayedChild(2);
				}
			}
		});
		findViewById(R.id.pre).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//always right to left
				setAnim(1,0);
				myViewFlipper.showPrevious();
			}
		});
		findViewById(R.id.next).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//always left to right
				setAnim(0,1);
				myViewFlipper.showNext();
			}
		});
		
		
	}
	
	float lastX=0;
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		if(event.getAction()==MotionEvent.ACTION_DOWN){
			lastX=event.getX();
		}else if(event.getAction()==MotionEvent.ACTION_UP){
			float currentX=event.getX();
			
			if(lastX>currentX){
				setAnim(0,1);
				myViewFlipper.showNext();
			}else {
				setAnim(1,0);
				myViewFlipper.showPrevious();
			}
			
		}
		
		return false;

	}
	
	boolean setAnim(int pre,int current){
		if(pre==current)
			return false;	
		
		if(pre<current){
			//right to left
			myViewFlipper.setInAnimation(this,R.anim.trans_left_in);
			myViewFlipper.setOutAnimation(this,R.anim.trans_left_out);
		}else {
			//left to right
			myViewFlipper.setInAnimation(this,R.anim.trans_right_in);
			myViewFlipper.setOutAnimation(this,R.anim.trans_right_out);			
		}
		return true;
	}
	
}
