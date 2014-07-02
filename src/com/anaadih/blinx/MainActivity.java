package com.anaadih.blinx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
/*
import com.anaadih.blinx.GitHubClient.Contributor;
import com.anaadih.blinx.GitHubClient.GitHub;
import com.anaadih.volley.app.AppController;
import com.anaadih.volley.app.CustomRequest;
import com.anaadih.volley.utils.Const;
import com.android.volley.Request.Method;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
*/
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;



public class MainActivity extends ActionBarActivity {
	ProgressDialog pDialog;
	private String TAG = "ServerResponse";
	String tag_json_obj = "json_obj_req";
	
	interface GitHub {
	    @GET("/joginder/blinx/get.php")
	    List<String> contributors(
	        @Path("joginder") String joginder,
	        @Path("blinx") String blinx
	    );
	  }
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		RestAdapter restAdapter = new RestAdapter.Builder()
        .setEndpoint("http://anaadihsoftech.biz")
        .build();
		
		GitHub github = restAdapter.create(GitHub.class);
		
		List<String> contributors = github.contributors("joginder", "blinx");
		
		Log.i("contributors", contributors.toString());
		
		/*if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}*/
		
		/*pDialog = new ProgressDialog(this);
		pDialog.setMessage("Loading...");
		pDialog.setCancelable(false);*/
		
		
		addUser();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/*private void showProgressDialog() {
		if (!pDialog.isShowing()) {
			pDialog.show();
		}
	}

	private void hideProgressDialog() {
		if (pDialog.isShowing()) {
			pDialog.hide();
		}
	}*/
	
	public void addUser() {
		
		
	}
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			//addUser();
			return rootView;
		}
		
		
	}
	

}
