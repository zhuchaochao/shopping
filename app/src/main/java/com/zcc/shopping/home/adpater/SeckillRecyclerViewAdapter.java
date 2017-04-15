package com.zcc.shopping.home.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zcc.shopping.R;
import com.zcc.shopping.home.bean.HomeFragmentBean;
import com.zcc.shopping.utils.Constants;

import java.util.List;

/**
 * Created by 朱超超 on 2017-03-23.
 * 作用：秒杀RecyclerView适配器
 */
public class SeckillRecyclerViewAdapter extends RecyclerView.Adapter<SeckillRecyclerViewAdapter.ViewHodler> {

    private final List<HomeFragmentBean.ResultBean.SeckillInfoBean.ListBean> list;
    private final Context mContext;

    public SeckillRecyclerViewAdapter(Context mContext, List<HomeFragmentBean.ResultBean.SeckillInfoBean.ListBean> list) {
        this.list = list;
        this.mContext  = mContext;

    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_seckill,null);
        return new ViewHodler(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHodler holder, int position) {
        //1.根据位置得到对应的数据
        HomeFragmentBean.ResultBean.SeckillInfoBean.ListBean listBean = list.get(position);

        //2.绑定数据

        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+listBean.getFigure()).into(holder.iv_figure);
        holder.tv_cover_price.setText(listBean.getCover_price());
        holder.tv_origin_price.setText(listBean.getOrigin_price());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHodler extends RecyclerView.ViewHolder{
        private ImageView iv_figure;
        private TextView tv_cover_price;
        private TextView tv_origin_price;

        public ViewHodler(View itemView) {
            super(itemView);
            iv_figure = (ImageView) itemView.findViewById(R.id.iv_figure);
            tv_cover_price = (TextView) itemView.findViewById(R.id.tv_cover_price);
            tv_origin_price = (TextView) itemView.findViewById(R.id.tv_origin_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(mContext, "秒杀="+getLayoutPosition(), Toast.LENGTH_SHORT).show();
                    if(onSeckillRecyclerView != null){
                        onSeckillRecyclerView.onItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    /**
     * 监听器
     */
    public interface OnSeckillRecyclerView{
        /**
         * 当某条被点击的时候回调
         * @param position
         */
        public void onItemClick(int position);
    }

    private  OnSeckillRecyclerView onSeckillRecyclerView;

    /**
     * 设置item的监听
     * @param onSeckillRecyclerView
     */
    public void setOnSeckillRecyclerView(OnSeckillRecyclerView onSeckillRecyclerView) {
        this.onSeckillRecyclerView = onSeckillRecyclerView;
    }
}
