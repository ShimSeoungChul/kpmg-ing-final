package com.kpmg.kpmgclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RewardAdapter extends RecyclerView.Adapter<RewardAdapter.ViewHolder> {

    private ArrayList<Dictionary> mData = null ;

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1, textView2, textView3 ;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            textView1 = itemView.findViewById(R.id.date) ;
            textView2 = itemView.findViewById(R.id.type) ;
            textView3 = itemView.findViewById(R.id.point) ;
        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    RewardAdapter(ArrayList<Dictionary> list) {
        mData = list ;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public RewardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.reward_item, parent, false) ;
        RewardAdapter.ViewHolder vh = new RewardAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(RewardAdapter.ViewHolder holder, int position) {
        holder.textView1.setText(mData.get(position).getDate());
        holder.textView2.setText(mData.get(position).getType());
        holder.textView3.setText(mData.get(position).getPoint());
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }
}