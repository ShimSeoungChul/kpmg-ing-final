package com.kpmg.kpmgclient.async;


import android.os.AsyncTask;
import android.util.Log;

import com.kpmg.kpmgclient.common.CmmnUtil;


public class GetUserPoint extends AsyncTask<Object, Integer, String> {
    String tag = "GetUserPoint";
    public GetUserPoint(){
    }
    @Override
    protected String doInBackground(Object... params) {
        try{
            //첫 번째 인자에 서버uri, 두 번째 인자에 이미지 정보 변수를 담아 POST 메소드를 통해 서버에 전달
            Log.d("test",CmmnUtil.GET((String) params[0]));
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

               /*
                point 가 유저 포인트 데이터임!! 이거 꺼내쓰3
               */
               int point = Integer.parseInt(data);
               Log.d("point***", Integer.toString(point));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}