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
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.LinearLayout;
import android.widget.EditText;
import android.widget.Button;
import android.os.Bundle;
import java.io.InputStream;
import android.content.Intent;
import android.net.Uri;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.View;
import android.content.ClipData;
import android.content.ClipboardManager;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;


public class Main2Activity extends  Activity { 
	
	
	private String T1 = "";
	private String TitleTo = "";
	private String MessageTo = "";
	private String AvatarTo = "";
	private double BB_1 = 0;
	private String font1 = "";
	private String font2 = "";
	
	private ArrayList<HashMap<String, Object>> serverString = new ArrayList<>();
	
	private LinearLayout linear_All;
	private EditText editText_Hex;
	private Button button__1_but;
	private LinearLayout linear_H1;
	private Button button__2_but;
	
	private Intent imgY3 = new Intent();
	private AlertDialog.Builder Github_ImageDialog;
	private SharedPreferences enterTop;
	private RequestNetwork UploadTitle;
	private RequestNetwork.RequestListener _UploadTitle_request_listener;
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
		Github_ImageDialog = new AlertDialog.Builder(this);
		enterTop = getSharedPreferences("TopFile", Activity.MODE_PRIVATE);
		UploadTitle = new RequestNetwork(this);
		
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
		
		_UploadTitle_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				if (BB_1 == 0) {
					BB_1 = 1;
					if (!_response.equals("")) {
						serverString = new Gson().fromJson(_response, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
						TitleTo = serverString.get((int)0).get("title").toString();
						MessageTo = serverString.get((int)0).get("message").toString();
						AvatarTo = serverString.get((int)0).get("avatarLink").toString();
						_alertDialog();
					}
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
	}
	
	private void initializeLogic() {
		setTitle(DecryptingTheTextMethod("hNDnQnISAi2xVSu3Wmag4g==","1"));
		try {
				java.io.InputStream linear_AllInput = getAssets().open("img/anime_linear.jpg"); 
				Drawable linear_AllDraw = Drawable.createFromStream(linear_AllInput, null);
				linear_All.setBackgroundDrawable(linear_AllDraw);
				linear_AllInput.close();
		} catch(java.io.IOException ex) {}
		_URL();
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
	
	
	public void _alertDialog () {
		_alert();
		if (!enterTop.getString("String_E", "").equals("1")) {
			_textColor();
			Github_ImageDialog.setTitle(Html.fromHtml(font1));
			Github_ImageDialog.setMessage(Html.fromHtml(font2));
			final View kgok = (View)getLayoutInflater().inflate(R.layout.dialog_image, null);
			Github_ImageDialog.setView(kgok);
			LinearLayout Box1 = (LinearLayout) kgok.findViewById(R.id.BoxBut1);
			LinearLayout img = (LinearLayout) kgok.findViewById(R.id.imageDialog);
			final CheckBox u1 = new CheckBox(Main2Activity.this);
			final ImageView AL1 = new ImageView(Main2Activity.this);
			LinearLayout.LayoutParams lpar = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			u1.setLayoutParams(lpar);
			Box1.addView(u1);
			AL1.setLayoutParams(lpar);
			img.addView(AL1);
			u1.setText("no, thank");
			u1.setTextColor(0xFFFFFFFF);
			Glide.with(getApplicationContext()).load(Uri.parse(AvatarTo)).into(AL1);
			Github_ImageDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface _dialog, int _which) {
					if (u1.isChecked()) {
						enterTop.edit().putString("String_E", "1").commit();
					}
					else {
						enterTop.edit().remove("String_E").commit();
					}
				}
			});
			Github_ImageDialog.setCancelable(false);
			int strokeWidth = 3;
			int strokeColor = (0xFF6A1B9A);
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(0xFF000000);
			gd.setStroke(strokeWidth, strokeColor);
			AlertDialog alert = Github_ImageDialog.show();
			alert.getWindow().setBackgroundDrawable(gd);
			alert.show();
		}
	}
	
	
	public void _alert () {
		
	}
	
	
	public void _URL () {
		UploadTitle.startRequestNetwork(RequestNetworkController.GET, "https://raw.githubusercontent.com/yurifr4ddyJava/dialogJson/main/alertYuri.json", "", _UploadTitle_request_listener);
	}
	
	
	public void _textColor () {
		font1 = "<font color = \"#FFFFFF\" >" + TitleTo + "</font>";
		font2 = "<font color = \"#FFFFFF\" >" + MessageTo + "</font>";
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