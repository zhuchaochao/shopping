package com.zcc.shopping.home.adpater;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zcc.shopping.R;
import com.zcc.shopping.home.bean.HomeFragmentBean;
import com.zcc.shopping.utils.Constants;

import java.util.List;

/**
 * Created by 朱超超 on 2017-03-23.
 * 作用：频道GridView适配器
 */
public class GVChannelAdapter extends BaseAdapter {
    private List<HomeFragmentBean.ResultBean.ChannelInfoBean> channel_info;
    private Context mcontext;
    public GVChannelAdapter(Context mcontext, List<HomeFragmentBean.ResultBean.ChannelInfoBean> channel_info) {
        this.channel_info = channel_info;
        this.mcontext = mcontext;
    }

    @Override
    public int getCount() {
        return channel_info.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = View.inflate(mcontext, R.layout.item_channel,null);
            viewHolder = new ViewHolder();
            viewHolder.iv_channel = (ImageView) convertView.findViewById(R.id.iv_channel);
            viewHolder.tv_channel = (TextView) convertView.findViewById(R.id.tv_channel);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(mcontext).load(Constants.BASE_URL_IMAGE+channel_info.get(position).getImage()).into(viewHolder.iv_channel);
        viewHolder.tv_channel.setText(channel_info.get(position).getChannel_name());
        return convertView;
    }
    class ViewHolder{
        ImageView iv_channel;
        TextView tv_channel;
    }
}
