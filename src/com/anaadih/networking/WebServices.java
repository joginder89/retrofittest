package com.anaadih.networking;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import android.content.SharedPreferences;
import android.os.Binder;
import android.preference.PreferenceManager;


public class WebServices extends Service  {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public interface GitHubService {
		  @GET("/users/{user}/repos")
		  List<String> listRepos(@Path("user") String user);
		}
	
}
