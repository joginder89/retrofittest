package com.anaadih.blinx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

public class GitHubClient extends Activity {
	private static final String API_URL = "https://api.github.com";
  private static final String TEST_URL = "http://anaadihsoftech.biz";
  
  Test testObj;
  List<Contributor> contributors;
  List<String> nameList;
  Response myNameResponse;
  JSONObject myName;
  JSONObject postJsonObj;
  String responseString;

  static class Contributor {
    String login;
    int contributions;
  }
  
  
  
  static class TestName {
	  String name;
	  String success;  
  }
  
  public class TestPostData {
	  final String username;
	  final String password;

	  TestPostData(String username, String password) {
	    this.username = username;
	    this.password = password;
	  }
	}
  
  interface GitHub {
    @GET("/repos/{owner}/{repo}/contributors")
    List<Contributor> contributors(
        @Path("owner") String owner,
        @Path("repo") String repo
    );
  }
  
  interface Test {
	  @FormUrlEncoded
	  @POST("/joginder/blinx/get.php")
	  //void testMethod(@Body TestPostData postbody,@Query("qName") String qName,Callback<Response> callback);
	  void testMethod(@Field("roomId") Integer myId, @Field("name") String myName,@Query("qName") String qName,Callback<Response> callback);
  }
  
  /*void testMethod(Callback<Response> cb) {
	  Log.e("SeverResponse==>", cb.toString());
  }*/
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		postJsonObj = new JSONObject();
		try {
			postJsonObj.put("type", "facebook_login");
			postJsonObj.put("name", "Joginder");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
    // Create a very simple REST adapter which points the GitHub API endpoint.
    RestAdapter restAdapter = new RestAdapter.Builder()
        .setEndpoint(TEST_URL)
        .setLogLevel(RestAdapter.LogLevel.FULL)
        .build();

    // Create an instance of our GitHub API interface.
    testObj = restAdapter.create(Test.class);
    new AsyntaskShow().execute();
  }
  
  
  class AsyntaskShow extends AsyncTask<Void, Integer, Void> {
      
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
		}

		@Override
		protected Void doInBackground(Void... params) {
			
			try {
				// Fetch and print a list of the contributors to this library.
			    /*contributors = github.contributors("square", "retrofit");*/
				testObj.testMethod(5,"Joginder Sharma","Joginder Sharma",new Callback<Response>() {
				    @Override
				    public void success(Response result, Response response) {

				        //Try to get response body
				        BufferedReader reader = null;
				        StringBuilder sb = new StringBuilder();
				        try {

				            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

				            String line;

				            try {
				                while ((line = reader.readLine()) != null) {
				                    sb.append(line);
				                }
				            } catch (IOException e) {
				                e.printStackTrace();
				            }
				        } catch (IOException e) {
				            e.printStackTrace();
				        }


				        responseString = sb.toString();
				        Log.e("SeverResponse==>", responseString);
				    }

				    @Override
				    public void failure(RetrofitError error) {

				    }

				});
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			Log.e("State==>","onPostExecute");
			if(responseString != null) {
				try {
					JSONObject responseJsonObj = new JSONObject(responseString);
					Log.e("SeverResponse==>1", responseJsonObj.toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				Log.e("SeverResponse==>", "Name is Null");
			}
			
			/*for (Contributor contributor : contributors) {
			      System.out.println(contributor.login + " (" + contributor.contributions + ")");
			    }*/
			
		}
	}
}