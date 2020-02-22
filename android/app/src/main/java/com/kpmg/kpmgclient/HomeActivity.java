package com.kpmg.kpmgclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kpmg.kpmgclient.async.GetUserPoint;
import com.kpmg.kpmgclient.common.CmmnUtil;
import com.kpmg.kpmgclient.common.StatusUtil;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    ImageView home, graph, main;
    TextView totalCoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        StatusUtil.setStatusBarColor(this, StatusUtil.StatusBarColorType.MAIN_STATUS_BAR);

        totalCoin = (TextView) findViewById(R.id.totalCoinValue);

        getUserPoint();

        // 리사이클러뷰에 표시할 데이터 리스트 생성.
        ArrayList<Dictionary> list = new ArrayList<>();
            list.add(new Dictionary("2020-02-17 15:23:29","음성 자막 생성","30")) ;
        list.add(new Dictionary("2020-02-17 16:03:29","이미지 라벨링","10")) ;
        list.add(new Dictionary("2020-02-17 16:04:59","음성 자막 생성","30")) ;
        list.add(new Dictionary("2020-02-17 16:16:04","OCR 라벨","20")) ;
        list.add(new Dictionary("2020-02-17 16:16:07","음성 자막 생성","30")) ;
        list.add(new Dictionary("2020-02-17 16:23:09","OCR 라벨","20")) ;
        list.add(new Dictionary("2020-02-17 16:11:38","음성 자막 생성","30")) ;
        list.add(new Dictionary("2020-02-17 17:18:32","이미지 라벨링","10")) ;
        list.add(new Dictionary("2020-02-17 18:23:38","이미지 라벨링","10")) ;



        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        RecyclerView recyclerView = findViewById(R.id.recycler1) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        RewardAdapter adapter = new RewardAdapter(list) ;
        recyclerView.setAdapter(adapter) ;



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
                Intent intent = new Intent(getApplicationContext(), MainActivity.class );
                startActivity(intent);
            }
        });


    }

    //사용자의 현재 포인트 가져오기
    public void getUserPoint() {

        try {



            String jsonString = new GetUserPoint().execute(CmmnUtil.USER_POINT_GET+"/test").get();
            totalCoin.setText("+ "+3210);



        } catch (Exception e) {

            e.printStackTrace();

        }
        //요청을 전달할 ascyntask 실행
    }
}
