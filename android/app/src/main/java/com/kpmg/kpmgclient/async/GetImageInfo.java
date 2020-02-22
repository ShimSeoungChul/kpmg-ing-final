package com.kpmg.kpmgclient.async;

import android.os.AsyncTask;
import android.util.Log;


import com.kpmg.kpmgclient.common.CmmnUtil;

import org.json.JSONObject;

import java.util.HashMap;

public class GetImageInfo extends AsyncTask<Object, Integer, String> {
    String tag = "getImageInfo";
    public GetImageInfo (){
    }
    @Override
    protected String doInBackground(Object... params) {
        try{
            //첫 번째 인자에 서버uri, 두 번째 인자에 이미지 정보 변수를 담아 POST 메소드를 통해 서버에 전달

            return CmmnUtil.POST((String) params[0], CmmnUtil.hashMapToJsonString((HashMap<String, String>)params[1]));
        } catch (Exception e){
            Log.e(tag,e.getMessage());
            return "ServerFail";
        }
    }
    @Override
    protected void onPostExecute(String data) {
        // 프로그래스바 숨기기

        if(data.equals("ServerFail")){
            Log.e(tag,"서버와의 통신에 이상이 있습니다.");
        } else {
            try {
                JSONObject jsonObject = new JSONObject(data);
                // 통신결과
                String rslt = jsonObject.getString("rslt");
                Log.i("****rslt",rslt);
                if (!rslt.equals("SUCC")) { //서버 통신 실패
                    Log.e(tag,"****rslt not SUCC :" + jsonObject.toString());
                } else {
                    //통신 성공
                    Log.e(tag,"****서버와에 값 정상적으로 전달22");
                    Log.e(tag,"****이미지 이름:"+jsonObject.getString("imageNm"));
                    Log.e(tag,"****이미지 전처리 예시1:"+jsonObject.getString("0"));
                    Log.e(tag,"****이미지 전처리 예시2:"+jsonObject.getString("1"));
                    Log.e(tag,"****이미지 전처리 예시3:"+jsonObject.getString("2"));


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}