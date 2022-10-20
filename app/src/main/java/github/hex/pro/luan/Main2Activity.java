package gifthub.hex.pro.lua;

import android.app.Activity;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import android.widget.LinearLayout;
import android.widget.EditText;
import android.widget.Button;
import android.os.Bundle;
import java.io.InputStream;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;


public class Main2Activity extends  Activity { 
	
	
	private String T1 = "";
	
	private LinearLayout linear_All;
	private EditText editText_Hex;
	private Button button__1_but;
	private LinearLayout linear_H1;
	private Button button__2_but;
	
	private Intent imgY3 = new Intent();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main2);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear_All = (LinearLayout) findViewById(R.id.linear_All);
		editText_Hex = (EditText) findViewById(R.id.editText_Hex);
		button__1_but = (Button) findViewById(R.id.button__1_but);
		linear_H1 = (LinearLayout) findViewById(R.id.linear_H1);
		button__2_but = (Button) findViewById(R.id.button__2_but);
		
		button__1_but.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_butt1();
			}
		});
		
		button__2_but.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_butt2();
			}
		});
	}
	
	private void initializeLogic() {
		setTitle(DecryptingTheTextMethod("hNDnQnISAi2xVSu3Wmag4g==","1"));
		try {
				java.io.InputStream linear_AllInput = getAssets().open("img/anime_linear.jpg"); 
				Drawable linear_AllDraw = Drawable.createFromStream(linear_AllInput, null);
				linear_All.setBackgroundDrawable(linear_AllDraw);
				linear_AllInput.close();
		} catch(java.io.IOException ex) {}
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public void _HEX () {
	}
	public String EcryptingTheTextMethod(final String _string, final String _key) {
				try{
			javax.crypto.SecretKey key = generateKey(_key);
			javax.crypto.Cipher c = javax.crypto.Cipher.getInstance("AES");
			c.init(javax.crypto.Cipher.ENCRYPT_MODE, key);
			byte[] encVal = c.doFinal(_string.getBytes());
			return android.util.Base64.encodeToString(encVal,android.util.Base64.DEFAULT);
				} catch (Exception e) {
				}
		return "";
		}
	
		public String DecryptingTheTextMethod(final String _string, final String _key) {
				try {
			javax.crypto.spec.SecretKeySpec key = (javax.crypto.spec.SecretKeySpec) generateKey(_key);
			javax.crypto.Cipher c = javax.crypto.Cipher.getInstance("AES");
			c.init(javax.crypto.Cipher.DECRYPT_MODE,key);
			byte[] decode = android.util.Base64.decode(_string,android.util.Base64.DEFAULT);
			byte[] decval = c.doFinal(decode);
			return new String(decval);
				} catch (Exception ex) {
				}
		return "";
		}
		public static javax.crypto.SecretKey generateKey(String pwd) throws Exception {
		final java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-256");
		byte[] b = pwd.getBytes("UTF-8");
		digest.update(b,0,b.length);
		byte[] key = digest.digest();
		javax.crypto.spec.SecretKeySpec sec = new javax.crypto.spec.SecretKeySpec(key, "AES");
		return sec;
		}
	{
	}
	
	
	public void _butt1 () {
		if (!editText_Hex.getText().toString().equals("")) {
			T1 = EcryptingTheTextMethod(editText_Hex.getText().toString(),"1").replace("\n", "");
			((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", T1));
			SketchwareUtil.showMessage(getApplicationContext(), T1);
		}
		else {
			((EditText)editText_Hex).setError("error (...)");
		}
	}
	
	
	public void _butt2 () {
		imgY3.setClass(getApplicationContext(), ImageActivity.class);
		imgY3.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		startActivity(imgY3);
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}