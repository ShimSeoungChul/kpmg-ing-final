package com.kpmg.kpmgclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonParser;
import com.kpmg.kpmgclient.async.GetImageInfo;
import com.kpmg.kpmgclient.common.CmmnUtil;
import com.kpmg.kpmgclient.common.StatusUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    int [] ImageId = { R.drawable.fruit1, R.drawable.fruit2, R.drawable.fruit3, R.drawable.fruit4, R.drawable.fruit5};
    ImageView imageView, home, graph, main;
    Button nextButton;
    RadioGroup radioGroup;
    RadioButton option1, option2, option3;
    String selectedValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StatusUtil.setStatusBarColor(this, StatusUtil.StatusBarColorType.WHITE_STATUS_BAR);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); //statusbar 색상 검정으로 바꿈

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.fruit1);


        nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new MyListener());

        radioGroup = (RadioGroup)findViewById(R.id.radioGroup); // 라디오그룹 객체 맵핑
        option1 = (RadioButton) findViewById(R.id.option1);
        option2 = (RadioButton) findViewById(R.id.option2);
        option3 = (RadioButton) findViewById(R.id.option3);

        getImageInfo();

        home = (ImageView) findViewById(R.id.home);
        graph = (ImageView) findViewById(R.id.graph);
        main = (ImageView) findViewById(R.id.main);

        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GraphActivity.class );
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class );
                startActivity(intent);
            }
        });
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryActivity.class );
                startActivity(intent);
            }
        });

    }

    public void getImageInfo(){
        //이미지 서버에 전달하기
        HashMap<String, String> requestParam = new HashMap<>();
        //drawble 이미지 bitmap으로 변경
        Drawable drawable = getResources().getDrawable(R.drawable.fruit1,null);
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        String bitmapToString="";

        //비트맵을 String 형태로 변환
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, stream);
        byte[] byteArray = stream.toByteArray();
        bitmapToString = Base64.encodeToString(byteArray, Base64.DEFAULT);

        requestParam.put("stringImage",bitmapToString);//요청에 이미지 추가
        requestParam.put("userNm","test"); //요청에 사용자 아이디 추가

        Log.e("****이미지 전달",CmmnUtil.IMAGE_INFO_GET);

        //요청을 전달할 ascyntask 실행
        try {
            String jsonString = new GetImageInfo().execute(CmmnUtil.IMAGE_INFO_GET,requestParam).get();
            Log.d("getImageInfo   :::",  jsonString);

            try {
                JSONObject json = new JSONObject(jsonString);
                String optionSting1 = json.getString("0");
                String optionSting2 = json.getString("1");
                String optionSting3 = json.getString("2");
                option1.setText(optionSting1);
                option2.setText(optionSting2);
                option3.setText(optionSting3);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    class MyListener implements View.OnClickListener {

        int i = 1;

        @Override
        public void onClick(View v) {

            imageView.setImageResource(ImageId[i]);
            getImageInfo();
            RadioButton selectedRdo = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId()); // rg 라디오그룹의 체크된(getCheckedRadioButtonId) 라디오버튼 객체 맵핑
            selectedValue = selectedRdo.getText().toString(); // 해당 라디오버튼 객체의 값 가져오기

            Toast.makeText(getApplicationContext(),selectedValue, Toast.LENGTH_SHORT).show();
            i+=1;
            if(i == ImageId.length) {
                return;
            }
        }
    }
}
