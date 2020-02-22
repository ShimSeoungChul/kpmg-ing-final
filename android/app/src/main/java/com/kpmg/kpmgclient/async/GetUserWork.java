package com.kpmg.kpmgclient.async;

import android.os.AsyncTask;
import android.util.Log;

import com.kpmg.kpmgclient.common.CmmnUtil;

import org.json.JSONObject;

public class GetUserWork extends AsyncTask<Object, Integer, String> {
    String tag = "GetUserWork";
    public GetUserWork(){
    }
    @Override
    protected String doInBackground(Object... params) {
        try{
            //첫 번째 인자에 서버uri, 두 번째 인자에 이미지 정보 변수를 담아 POST 메소드를 통해 서버에 전달
            return CmmnUtil.GET((String) params[0]);
        } catch (Exception e){
            Log.e(tag,e.getMessage());
            return "ServerFail";
        }
    }
    @Override
    protected void onPostExecute(String data) {
        if(data.equals("ServerFail")){
            Log.e(tag,"서버와의 통신에 이상이 있습니다.");
        } else {
            try {
                JSONObject jsonObject = new JSONObject(data);
                String info = jsonObject.getString("0");
                String info1 = jsonObject.getString("1");
                String info2 = jsonObject.getString("2");

                System.out.println(info+info1+info2);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}