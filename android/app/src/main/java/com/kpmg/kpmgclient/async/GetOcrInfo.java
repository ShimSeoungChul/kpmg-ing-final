package com.kpmg.kpmgclient.async;

import android.os.AsyncTask;
import android.util.Log;

import com.kpmg.kpmgclient.common.CmmnUtil;

import org.json.JSONObject;

import java.util.HashMap;

public class GetOcrInfo extends AsyncTask<Object, Integer, String> {
    String tag = "getOcrInfo";
    public GetOcrInfo (){
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
                String rslt = jsonObject.getString("rslt"); // 성공시 SUCC, 실패시 FAIL
                String totalPrice = jsonObject.getString("totalPrice"); //물건 가격 총합
                String goods = jsonObject.getString("goods"); //물품들 세부 정보   ex) {"0":{"price":"1,190","num":"1","name":"필라이트캔500셔뇨"}}
                String branch = jsonObject.getString("branch"); //지점명
                String ocrImageNm = jsonObject.getString("ocrImageNm"); //해당 이미지 파일을 저장한 이름. 이름을 서버에 데이터를 입력을 위한 key로 사용가능
                System.out.println(rslt);
                System.out.println(totalPrice);
                System.out.println(goods);
                System.out.println(branch);
                System.out.println(ocrImageNm);
                System.out.println(jsonObject);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //{totalPrice=1,190원, goods={0={price=1,190, num=1, name=필라이트캔500셔뇨}}, where=서을륙범시 동작구 사당르 215, rslt=SUCC, branch=수퍼 익스프레스 남성정, ocrImageNm=cb47a0dce9de4355af453356a2c70d4a915}
}