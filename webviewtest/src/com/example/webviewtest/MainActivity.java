package com.example.webviewtest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.FileChooserParams;
import android.webkit.WebView;

public class MainActivity extends Activity {

	private ValueCallback<Uri> mUploadMessage;
	private final static int FILECHOOSER_RESULTCODE = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		WebView wv1=(WebView)findViewById(R.id.webView1);
		wv1.loadUrl("http://blueimp.github.io/jQuery-File-Upload/");
		//wv1.loadUrl("http://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_fileupload_get");
		
		wv1.getSettings().setJavaScriptEnabled(true);
		wv1.setWebChromeClient(new WebChromeClient(){

			@Override
			public boolean onJsAlert(WebView view, String url, String message,
					JsResult result) {
				// TODO Auto-generated method stub
				return super.onJsAlert(view, url, message, result);
			}
			
			  // For Android 3.0-
	        public void openFileChooser(ValueCallback<Uri> uploadMsg) {
	            mUploadMessage = uploadMsg;
	            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
	            i.addCategory(Intent.CATEGORY_OPENABLE);
	            i.setType("image/*");
	            startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
	        }

	        // For Android 3.0+
	        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
	            mUploadMessage = uploadMsg;
	            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
	            i.addCategory(Intent.CATEGORY_OPENABLE);
	            i.setType("*/*");
	            startActivityForResult(Intent.createChooser(i, "File Browser"), FILECHOOSER_RESULTCODE);
	        }

	        // For Android 4.1
	        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
	            mUploadMessage = uploadMsg;
	            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
	            i.addCategory(Intent.CATEGORY_OPENABLE);
	            i.setType("image/*");
	            startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
	        }

	      
			@SuppressLint("NewApi")
			@Override
			public boolean onShowFileChooser(WebView webView,
					ValueCallback<Uri[]> filePathCallback,
					FileChooserParams fileChooserParams) {
				// TODO Auto-generated method stub
				return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);
			}

			
	        
			
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		  if (requestCode == FILECHOOSER_RESULTCODE) {
	            if (null == mUploadMessage)
	            return;
	        Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
	        mUploadMessage.onReceiveValue(result);
	        mUploadMessage = null;
	        }
		
	}
	
	
	
}
