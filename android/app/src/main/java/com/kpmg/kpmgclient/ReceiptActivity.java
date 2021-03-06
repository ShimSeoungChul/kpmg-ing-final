package com.kpmg.kpmgclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.kpmg.kpmgclient.async.GetOcrInfo;
import com.kpmg.kpmgclient.common.CmmnUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
public class ReceiptActivity extends AppCompatActivity {

    ImageView receiptImage;
    TextView totalPriceTv, priceTv, numTv, nameTv, storeTv;
    EditText totalPriceEt, priceEt, numEt, nameEt, storeEt;
    Button modifyButton, sendButton;
    String totalPrice, num, price, name, store;
    LinearLayout contTextView, contEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        receiptImage = (ImageView) findViewById(R.id.receiptImage);
        totalPriceTv = (TextView) findViewById(R.id.totalPriceTv);
        totalPriceEt = (EditText) findViewById(R.id.totalPriceEt);

        contTextView = (LinearLayout) findViewById(R.id.contTextView);
        contEditText = (LinearLayout) findViewById(R.id.contEditText);

        priceTv = (TextView) findViewById(R.id.priceTv);
        priceEt = (EditText) findViewById(R.id.priceEt);

        numTv = (TextView) findViewById(R.id.numTv);
        numEt = (EditText) findViewById(R.id.numEt);

        nameTv = (TextView) findViewById(R.id.nameTv);
        nameEt = (EditText) findViewById(R.id.nameEt);

        storeTv = (TextView) findViewById(R.id.storeTv);
        storeEt = (EditText) findViewById(R.id.storeEt);

        modifyButton = (Button) findViewById(R.id.modifyButton);
        sendButton = (Button) findViewById(R.id.sendButton);

        int [] ImageId = { R.drawable.oc1, R.drawable.fruit2, R.drawable.fruit3, R.drawable.fruit4, R.drawable.fruit5};

        modifyButton.setOnClickListener(new ModifyListener());

        getOcrInfo();

    }

    public void getOcrInfo(){
        //이미지 서버에 전달하기
        HashMap<String, String> requestParam = new HashMap<>();
        //drawble 이미지 bitmap으로 변경
        Drawable drawable = getResources().getDrawable(R.drawable.oc2,null);
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        String bitmapToString="";

        //비트맵을 String 형태로 변환
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, stream);
        byte[] byteArray = stream.toByteArray();
        bitmapToString = Base64.encodeToString(byteArray, Base64.DEFAULT);

        requestParam.put("stringImage",bitmapToString);//요청에 이미지 추가
        requestParam.put("userNm","test"); //요청에 사용자 아이디 추가

        Log.e("imageOcrTest", CmmnUtil.OCR_INFO_GET);

        //요청을 전달할 ascyntask 실행
        try {
            String jsonString = new GetOcrInfo().execute(CmmnUtil.OCR_INFO_GET,requestParam).get();
            Log.d("jsonString",jsonString);
            try {
                JSONObject json = new JSONObject(jsonString);
                totalPrice = "구매총액: "+ json.getString("totalPrice");
                totalPriceTv.setText(totalPrice);
                store = "지점: "+ json.getString("branch");
                storeTv.setText(store);

                num = "구매수량: "+ json.getString("num");
                numTv.setText(num);
                price = "구매가격: "+ json.getString("price");
                priceTv.setText(totalPrice);
                name = "상품이름: "+ json.getString("name");
                nameTv.setText(name);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class ModifyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            if(modifyButton.getText().toString().equals("수정")){
                Toast.makeText(getApplicationContext(),"수정", Toast.LENGTH_SHORT).show();
                totalPrice = totalPriceTv.getText().toString();
                totalPriceEt.setText(totalPrice);
                contEditText.setVisibility(View.VISIBLE);
                contTextView.setVisibility(View.GONE);
                modifyButton.setText("완료");

                numEt.setText(num);
                totalPriceEt.setText(totalPrice);
                storeEt.setText(store);
                nameEt.setText(name);
                priceEt.setText(price);

            }else{
                Toast.makeText(getApplicationContext(),"실", Toast.LENGTH_SHORT).show();
                totalPrice = totalPriceEt.getText().toString();
                totalPriceTv.setText(totalPrice);
                contTextView.setVisibility(View.VISIBLE);
                contEditText.setVisibility(View.GONE);
                modifyButton.setText("수정");
            }
        }
    }

}