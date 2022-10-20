package com.copiar.fix;

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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.content.Intent;
import android.content.ClipData;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import java.io.InputStream;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.content.ClipboardManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;
import android.Manifest;
import android.content.pm.PackageManager;


public class MainActivity extends  Activity { 
	
	public final int REQ_CD_UPA = 101;
	
	private String kk = "";
	private String strAll = "";
	private String A_1 = "";
	private String A_2 = "";
	private String StringA_3 = "";
	private String yuri_nenhum = "";
	
	private ArrayList<String> zzxx = new ArrayList<>();
	private ArrayList<String> clickAll = new ArrayList<>();
	
	private LinearLayout linear_all;
	private ScrollView vscroll_V;
	private TextView name_file;
	private Button file_clicked;
	private Spinner spinner_all;
	private Button copy_clicked;
	private TextView filee;
	
	private Intent upa = new Intent(Intent.ACTION_GET_CONTENT);
	private AlertDialog.Builder alertInfo;
	private Intent Ai = new Intent();
	private AlertDialog.Builder all;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		if (Build.VERSION.SDK_INT >= 23) {
			if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
				requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
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
		
		linear_all = (LinearLayout) findViewById(R.id.linear_all);
		vscroll_V = (ScrollView) findViewById(R.id.vscroll_V);
		name_file = (TextView) findViewById(R.id.name_file);
		file_clicked = (Button) findViewById(R.id.file_clicked);
		spinner_all = (Spinner) findViewById(R.id.spinner_all);
		copy_clicked = (Button) findViewById(R.id.copy_clicked);
		filee = (TextView) findViewById(R.id.filee);
		upa.setType("*/*");
		upa.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		alertInfo = new AlertDialog.Builder(this);
		all = new AlertDialog.Builder(this);
		
		file_clicked.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(upa, REQ_CD_UPA);
			}
		});
		
		spinner_all.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (_position == 0) {
					
				}
				if (_position == 1) {
					spinner_all.setSelection((int)(0));
					all.setTitle("pesquisar");
					final View kgok = (View)getLayoutInflater().inflate(R.layout.text, null);
					all.setView(kgok);
					LinearLayout linTxt1 = (LinearLayout) kgok.findViewById(R.id.linear_text1);
					LinearLayout linTxt2 = (LinearLayout) kgok.findViewById(R.id.linear_text2);
					TextView B_11 = (TextView) kgok.findViewById(R.id.lock1Text);
					TextView B_22 = (TextView) kgok.findViewById(R.id.lock2Text);
					B_11.setText("pesquisar text:");
					B_22.setText("substituir com:");
					final EditText Txt1 = new EditText(MainActivity.this);
					final EditText Txt2 = new EditText(MainActivity.this);
					LinearLayout.LayoutParams lpar = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
					Txt1.setLayoutParams(lpar);
					linTxt1.addView(Txt1);
					Txt2.setLayoutParams(lpar);
					linTxt2.addView(Txt2);
					all.setPositiveButton("substituir tudo", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							A_1 = Txt1.getText().toString();
							A_2 = Txt2.getText().toString();
							filee.setText(filee.getText().toString().replace(A_1, A_2));
						}
					});
					all.setNegativeButton("no", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							all.setCancelable(true);
						}
					});
					all.setCancelable(false);
					all.create().show();
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
		
		copy_clicked.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!filee.getText().toString().equals("")) {
					((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", filee.getText().toString()));
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "no");
				}
			}
		});
	}
	
	private void initializeLogic() {
		_click();
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_UPA:
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
				strAll = FileUtil.readFile(_filePath.get((int)(0)));
				filee.setText(strAll);
				name_file.setText(_filePath.get((int)(0)));
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	public void _click () {
		clickAll.add("Text");
		clickAll.add("pesquisar");
		spinner_all.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, clickAll));
		((ArrayAdapter)spinner_all.getAdapter()).notifyDataSetChanged();
	}
	
	
	public void _nenhuma () {
		yuri_nenhum = "hmmmmmmmmmmmmm";
		yuri_nenhum = "Vou github: yurifr4ddyJava !";
		yuri_nenhum = "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk";
		yuri_nenhum = "(͡° ͜ʖ ͡°)";
	}
	
	
	public void _string_Id () {
		
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