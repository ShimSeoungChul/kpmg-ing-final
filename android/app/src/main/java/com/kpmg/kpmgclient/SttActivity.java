package com.kpmg.kpmgclient;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.kpmg.kpmgclient.common.CmmnUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class SttActivity extends AppCompatActivity{
    final static String SAMPLE_VIDEO_URL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";

    VideoView sttVideo;
    EditText sttEditText;
    Button sttProcessBtn;
    String text; //전처리후 만들어진 텍스트 데이터를 저장하는 변

    String videoDir = "http://49.247.215.15/";
    String videoFileNm ="test4";  //서버에 저장된 mp4 파일 이름: test1, test2, test3, test4
    String mp3FileNm = "";
    String sttTextFileNm = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stt);


        sttVideo = (VideoView)findViewById(R.id.sttVideo);
        sttProcessBtn = (Button)findViewById(R.id.sttProcessBtn);
        sttEditText = (EditText) findViewById(R.id.sttEditText);


    }

    //영상 전처리 버튼. 한 번 누르면 영상을 불러오고 두번 째로 누르면 stt를 통해 영상 자막을 불러온다.
    public void processVideo(View view) {
        if(sttProcessBtn.getText().equals("영상 불러오기")){
            sttVideo.setVideoPath(videoDir+videoFileNm+".mp4");
            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(sttVideo);
            mediaController.setPadding(0, 0, 0, 80); //상위 레이어의 바닥에서 얼마 만큼 패딩을 주는지 표기
            sttVideo.setMediaController(mediaController);
            sttVideo.start();
            sttProcessBtn.setText("음성 및 텍스트 데이터 추출");

        }else if(sttProcessBtn.getText().equals("음성 및 텍스트 데이터 추출")){
            try {
                String data = new GetVideoToText().execute(CmmnUtil.VIDEO_TO_TEXT_GET+"/"+videoFileNm).get();
                JSONObject jsonObject = new JSONObject(data);
                text = jsonObject.getString("text");
                sttEditText.setText(text);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            sttProcessBtn.setText("전처리 완료");
        }else if(sttProcessBtn.getText().equals("전처리 완료")){
            Toast myToast = Toast.makeText(this.getApplicationContext(),"이미 처리가 완료된 동영상 입니다.", Toast.LENGTH_SHORT);
            myToast.show();
        }
    }

    //stt 종료
    public void sttEnd(View view) {
        //전처리가 완료된 상태라면 전처리한 텍스트 데이터를 서버에 전달한다.
        if(sttProcessBtn.getText().equals("전처리 완료")){
            Toast myToast = Toast.makeText(this.getApplicationContext(),"데이터를 전송했습니다.", Toast.LENGTH_SHORT);
            Map<String,String> request = new HashMap<String, String>();
            request.put("fileId",videoFileNm);
            request.put("workerNm","test");
            request.put("sttInfo",text);
            new InsertSttInfo().execute(CmmnUtil.STT_INFO_INSERT,request);
        }
        //액티비티를 종료한다.
        finish();
    }
}
