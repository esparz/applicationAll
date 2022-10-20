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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.ScrollView;
import android.content.Intent;
import android.content.ClipData;
import java.util.Timer;
import java.util.TimerTask;
import android.view.View;
import java.text.DecimalFormat;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;
import android.Manifest;
import android.content.pm.PackageManager;


public class ImageActivity extends  Activity { 
	
	public final int REQ_CD_IMAGELOAD = 101;
	private Timer _timer = new Timer();
	
	private String imgView = "";
	private String copyString = "";
	private String FileAll = "";
	private String KL = "";
	private double SS = 0;
	private double MM = 0;
	private double HH = 0;
	
	private ArrayList<String> Filee = new ArrayList<>();
	
	private LinearLayout linear_All;
	private TextView titleFile;
	private ImageView imgLoad;
	private TextView time;
	private LinearLayout linear_H1;
	private Button button__1_but;
	private LinearLayout linear_H2;
	private ScrollView vscroll_V_H1;
	private Button Copy__1_All;
	private TextView imageText;
	
	private Intent imageLoad = new Intent(Intent.ACTION_GET_CONTENT);
	private TimerTask FileTime;
	private TimerTask VV;
	private TimerTask Tim;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.image);
		initialize(_savedInstanceState);
		if (Build.VERSION.SDK_INT >= 23) {
			if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
			|| checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
				requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
			}
			else {
				initializeLogic();
			}
		}
		else {
			initializeLogic();
		}
	}
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear_All = (LinearLayout) findViewById(R.id.linear_All);
		titleFile = (TextView) findViewById(R.id.titleFile);
		imgLoad = (ImageView) findViewById(R.id.imgLoad);
		time = (TextView) findViewById(R.id.time);
		linear_H1 = (LinearLayout) findViewById(R.id.linear_H1);
		button__1_but = (Button) findViewById(R.id.button__1_but);
		linear_H2 = (LinearLayout) findViewById(R.id.linear_H2);
		vscroll_V_H1 = (ScrollView) findViewById(R.id.vscroll_V_H1);
		Copy__1_All = (Button) findViewById(R.id.Copy__1_All);
		imageText = (TextView) findViewById(R.id.imageText);
		imageLoad.setType("image/*");
		imageLoad.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		
		button__1_but.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(imageLoad, REQ_CD_IMAGELOAD);
			}
		});
		
		Copy__1_All.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_but1();
			}
		});
	}
	
	private void initializeLogic() {
		Copy__1_All.setVisibility(View.GONE);
		imageText.setBackgroundColor(0xFF37474F);
		imageText.setTextColor(0xFFFFFFFF);
		titleFile.setLayoutParams(new LinearLayout.LayoutParams((int) (SketchwareUtil.getDip(getApplicationContext(), (int)(250))),(int) (android.widget.LinearLayout.LayoutParams.WRAP_CONTENT)));
		_imageBackground();
		_infoText();
		_AllTime();
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_IMAGELOAD:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				titleFile.setLayoutParams(new LinearLayout.LayoutParams((int) (SketchwareUtil.getDip(getApplicationContext(), (int)(250))),(int) (android.widget.LinearLayout.LayoutParams.WRAP_CONTENT)));
				titleFile.setText("loading wait...");
				imageText.setText("string wait...");
				try {
					java.io.InputStream imgLoadInput = getAssets().open("img/ads1.png"); 
					Drawable imgLoadDraw = Drawable.createFromStream(imgLoadInput, null);
					imgLoad.setImageDrawable(imgLoadDraw);
					imgLoadInput.close();
				} catch(java.io.IOException ex) {}
				FileAll = _filePath.get((int)(0));
				FileTime = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								imgLoad.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(FileAll, 1024, 1024));
								java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
								Bitmap LL = ((android.graphics.drawable.BitmapDrawable) imgLoad.getDrawable()).getBitmap();
								LL.compress(Bitmap.CompressFormat.JPEG, 100, baos);
								
								byte[] imageBytes = baos.toByteArray();
								
								String imgView = android.util.Base64.encodeToString(imageBytes, android.util.Base64.DEFAULT);
								
								
								KL = imgView;
								imageText.setText(imgView);
								Copy__1_All.setVisibility(View.VISIBLE);
								titleFile.setText(FileAll);
								titleFile.setVisibility(View.VISIBLE);
								titleFile.setLayoutParams(new LinearLayout.LayoutParams((int) (android.widget.LinearLayout.LayoutParams.MATCH_PARENT),(int) (android.widget.LinearLayout.LayoutParams.WRAP_CONTENT)));
							}
						});
					}
				};
				_timer.schedule(FileTime, (int)(1300));
			}
			else {
				SketchwareUtil.showMessage(getApplicationContext(), "back !");
			}
			break;
			default:
			break;
		}
	}
	
	public void _but1 () {
		titleFile.setVisibility(View.VISIBLE);
		copyString = FileUtil.getExternalStorageDir().concat("/Y3 image to bsae64/imageTextAll.txt");
		FileUtil.writeFile(copyString, KL);
		Copy__1_All.setVisibility(View.GONE);
		titleFile.setText("open file loading...");
		imageText.setText("wait...");
		titleFile.setLayoutParams(new LinearLayout.LayoutParams((int) (SketchwareUtil.getDip(getApplicationContext(), (int)(250))),(int) (android.widget.LinearLayout.LayoutParams.WRAP_CONTENT)));
		VV = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						titleFile.setText(FileUtil.getExternalStorageDir().concat("/Y3 image to bsae64/imageTextAll.txt"));
						imageText.setText("image to base64");
						titleFile.setLayoutParams(new LinearLayout.LayoutParams((int) (android.widget.LinearLayout.LayoutParams.MATCH_PARENT),(int) (android.widget.LinearLayout.LayoutParams.WRAP_CONTENT)));
					}
				});
			}
		};
		_timer.schedule(VV, (int)(1100));
	}
	
	
	public void _infoText () {
		imageText.setText("image to base64");
	}
	
	
	public void _AllTime () {
		Tim = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						SS++;
						if (SS == 60) {
							SS = 0;
							MM++;
						}
						if (MM == 60) {
							MM = 0;
							HH++;
						}
						if (HH == 100) {
							HH++;
							Tim.cancel();
						}
						time.setText(new DecimalFormat("00").format(HH).concat(":".concat(new DecimalFormat("00").format(MM).concat(":".concat(new DecimalFormat("00").format(SS))))));
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(Tim, (int)(1000), (int)(1000));
		time.setText(new DecimalFormat("00").format(HH).concat(":".concat(new DecimalFormat("00").format(MM).concat(":".concat(new DecimalFormat("00").format(SS))))));
	}
	
	
	public void _imageBackground () {
		try {
				java.io.InputStream linear_AllInput = getAssets().open("img/anime_linear.jpg"); 
				Drawable linear_AllDraw = Drawable.createFromStream(linear_AllInput, null);
				linear_All.setBackgroundDrawable(linear_AllDraw);
				linear_AllInput.close();
		} catch(java.io.IOException ex) {}
		try {
			java.io.InputStream imgLoadInput = getAssets().open("img/file.png"); 
			Drawable imgLoadDraw = Drawable.createFromStream(imgLoadInput, null);
			imgLoad.setImageDrawable(imgLoadDraw);
			imgLoadInput.close();
		} catch(java.io.IOException ex) {}
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