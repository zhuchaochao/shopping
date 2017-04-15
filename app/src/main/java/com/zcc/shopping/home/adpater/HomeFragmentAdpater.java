package com.zcc.shopping.home.adpater;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zcc.shopping.GoodsInfoActivity;
import com.zcc.shopping.R;
import com.zcc.shopping.home.bean.GoodsBean;
import com.zcc.shopping.home.bean.HomeFragmentBean;
import com.zcc.shopping.utils.Constants;
import com.zcc.shopping.utils.GlideImageLoader;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by 朱超超 on 2017-03-22.
 * 作用：主页RecyclerView的适配器
 */
public class HomeFragmentAdpater extends RecyclerView.Adapter {
    /**
     * 广告条幅类型
     */
    public static final int BANNER = 0;

    /**
     * 频道类型
     */
    public static final int CHANNEL = 1;
    /**
     * 活动类型
     *
     */
    public static final int ACT = 2;
    /**
     * 秒杀类型
     */
    public static final int SECKILL = 3;
    /**
     * 推荐类型
     */
    public static final int RECOMMEND = 4;
    /**
     * 热卖
     */
    public static final int HOT = 5;

    /**
     * 当前类型
     */
    private  int CURRENTTYPE = BANNER;

    private Context mContext;

    private  HomeFragmentBean.ResultBean result;

    private final LayoutInflater mLayoutInflater;

    public HomeFragmentAdpater(Context mContext, HomeFragmentBean.ResultBean result) {
        this.mContext = mContext;
        this.result = result;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    /**
     * 初始化ViewHolder
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mContext, mLayoutInflater.inflate(R.layout.banner_viewpager, null));
        } else if (viewType == CHANNEL) {
            return new ChannelViewHolder(mContext, mLayoutInflater.inflate(R.layout.channel_item, null));
        } else if (viewType == ACT) {
            return new ActViewHolder(mContext, mLayoutInflater.inflate(R.layout.act_item, null));
        }else if (viewType == SECKILL) {
            return new SeckillViewHolder(mContext, mLayoutInflater.inflate(R.layout.seckill_item, null));
        }else if(viewType == RECOMMEND ){
            return new RecommendViewHolder(mContext, mLayoutInflater.inflate(R.layout.recommend_item, null));
        }else if(viewType == HOT){
            return new HotViewHolder(mContext, mLayoutInflater.inflate(R.layout.hot_item, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(result.getBanner_info());
        } else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData(result.getChannel_info());
        } else if (getItemViewType(position) == ACT) {
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            actViewHolder.setData(result.getAct_info());
        }else if(getItemViewType(position) == SECKILL){
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
            seckillViewHolder.setData(result.getSeckill_info());
        }else if(getItemViewType(position) == RECOMMEND){
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            recommendViewHolder.setData(result.getRecommend_info());
        }else if(getItemViewType(position)==HOT){
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            hotViewHolder.setData(result.getHot_info());
        }

    }

    @Override
    public int getItemCount() {
        return 6;
    }


    @Override
    public int getItemViewType(int position) {
        switch (position){
            case BANNER:
                CURRENTTYPE = BANNER;
                break;
            case CHANNEL:
                CURRENTTYPE = CHANNEL;
                break;
            case ACT:
                CURRENTTYPE = ACT;
                break;
            case SECKILL:
                CURRENTTYPE = SECKILL;
                break;
            case RECOMMEND:
                CURRENTTYPE = RECOMMEND;
                break;
            case HOT:
                CURRENTTYPE = HOT;
                break;
        }
        return CURRENTTYPE;
    }

    /**
     * 横幅视图容器
     */
    class BannerViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private Banner banner;

        public BannerViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            this.banner = (Banner) itemView.findViewById(R.id.banner);
            banner.setImageLoader(new GlideImageLoader());
            //设置循环指示点
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            //设置手风琴效果
            banner.setBannerAnimation(Transformer.Accordion);
        }

        public void setData(List<HomeFragmentBean.ResultBean.BannerInfoBean> banner_info) {
            //设置Banner的数据
            //得到图片集合地址
            List<String> imagesUrl = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                String imageUrl = banner_info.get(i).getImage();
                imagesUrl.add(Constants.BASE_URL_IMAGE+imageUrl);
            }

            banner.setImages(imagesUrl);
            banner.start();

            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext,"banner-->"+position,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void startGoodsInfoActivity(GoodsBean goodsBean) {
        Intent intent = new Intent(mContext, GoodsInfoActivity.class);
        intent.putExtra("goodsBean",goodsBean);
        mContext.startActivity(intent);
    }

    /**
     * 频道视图容器
     */
    class  ChannelViewHolder extends RecyclerView.ViewHolder{
        private Context mcontext;
        private GridView gv_channel;
        private GVChannelAdapter gvChannelAdapter;
        public ChannelViewHolder(Context context,View itemView) {
            super(itemView);
            mcontext = context;
            gv_channel = (GridView) itemView.findViewById(R.id.gv_channel);
            gv_channel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext,"gv_channel-->"+position,Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setData(List<HomeFragmentBean.ResultBean.ChannelInfoBean> channel_info) {
            gvChannelAdapter = new GVChannelAdapter(mcontext,channel_info);
            gv_channel.setAdapter(gvChannelAdapter);
        }
    }

    class HotViewHolder extends RecyclerView.ViewHolder{

        private final Context mContext;

        private TextView tv_more_hot;
        private GridView gv_hot;

        public HotViewHolder(final Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            tv_more_hot = (TextView) itemView.findViewById(R.id.tv_more_hot);
            gv_hot = (GridView) itemView.findViewById(R.id.gv_hot);

        }

        public void setData(final List<HomeFragmentBean.ResultBean.HotInfoBean> hot_info) {
            //1.有数据
            //2.设置GridView的适配器
            HotGridViewAdapter  adapter = new HotGridViewAdapter(mContext,hot_info);
            gv_hot.setAdapter(adapter);


            //设置item的监听
            gv_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position=="+position, Toast.LENGTH_SHORT).show();
                    //热卖商品信息类
                    HomeFragmentBean.ResultBean.HotInfoBean hotInfoBean =  hot_info.get(position);
                    //商品信息类
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setCover_price(hotInfoBean.getCover_price());
                    goodsBean.setFigure(hotInfoBean.getFigure());
                    goodsBean.setName(hotInfoBean.getName());
                    goodsBean.setProduct_id(hotInfoBean.getProduct_id());
                    startGoodsInfoActivity(goodsBean);
                }
            });
        }
    }


    class RecommendViewHolder extends RecyclerView.ViewHolder{

        private final Context mContext;
        private TextView tv_more_recommend;
        private GridView gv_recommend;
        private RecommendGridViewAdapter adapter;

        public RecommendViewHolder(final Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            tv_more_recommend = (TextView) itemView.findViewById(R.id.tv_more_recommend);
            gv_recommend = (GridView) itemView.findViewById(R.id.gv_recommend);


        }

        public void setData(final List<HomeFragmentBean.ResultBean.RecommendInfoBean> recommend_info) {
            //1.有数据了
            //2.设置适配器
            adapter = new RecommendGridViewAdapter(mContext,recommend_info);
            gv_recommend.setAdapter(adapter);

            gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position=="+position, Toast.LENGTH_SHORT).show();
                    HomeFragmentBean.ResultBean.RecommendInfoBean recommendInfoBean = recommend_info.get(position);

                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setCover_price(recommendInfoBean.getCover_price());
                    goodsBean.setFigure(recommendInfoBean.getFigure());
                    goodsBean.setName(recommendInfoBean.getName());
                    goodsBean.setProduct_id(recommendInfoBean.getProduct_id());
                    startGoodsInfoActivity(goodsBean);
                }
            });
        }
    }


    class SeckillViewHolder extends RecyclerView.ViewHolder{
        private final Context mContext;
        private TextView tv_time_seckill;
        private TextView tv_more_seckill;
        private RecyclerView rv_seckill;
        private SeckillRecyclerViewAdapter adapter;

        /**
         * 相差多少时间-毫秒
         */
        private long dt = 0;

        private Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                dt = dt - 1000;
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                String time = formatter.format(new Date(dt));
                tv_time_seckill.setText(time);

                handler.removeMessages(0);
                handler.sendEmptyMessageDelayed(0,1000);
                if(dt <=0){
                    //把消息移除
                    handler.removeCallbacksAndMessages(null);
                }

            }
        };

        public SeckillViewHolder(Context mContext, View itemView) {
            super(itemView);
            tv_time_seckill = (TextView) itemView.findViewById(R.id.tv_time_seckill);
            tv_more_seckill = (TextView) itemView.findViewById(R.id.tv_more_seckill);
            rv_seckill = (RecyclerView) itemView.findViewById(R.id.rv_seckill);
            this.mContext = mContext;
        }

        public void setData(final HomeFragmentBean.ResultBean.SeckillInfoBean seckill_info) {
            //1.得到数据了
            //2.设置数据：文本和RecyclerView的数据
            adapter = new SeckillRecyclerViewAdapter(mContext,seckill_info.getList());
            rv_seckill.setAdapter(adapter);

            //设置布局管理器
            rv_seckill.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
            //设置item的点击事件
            adapter.setOnSeckillRecyclerView(new SeckillRecyclerViewAdapter.OnSeckillRecyclerView() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(mContext, "秒杀" + position, Toast.LENGTH_SHORT).show();

                    HomeFragmentBean.ResultBean.SeckillInfoBean.ListBean listBean = seckill_info.getList().get(position);


                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setCover_price(listBean.getCover_price());
                    goodsBean.setFigure(listBean.getFigure());
                    goodsBean.setName(listBean.getName());
                    goodsBean.setProduct_id(listBean.getProduct_id());
                    startGoodsInfoActivity(goodsBean);
                }
            });


            //秒杀倒计时 -毫秒
            dt =   Integer.valueOf(seckill_info.getEnd_time()) - Integer.valueOf(seckill_info.getStart_time());


            handler.sendEmptyMessageDelayed(0, 1000);
        }
    }

    class ActViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private ViewPager act_viewpager;

        public ActViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            act_viewpager = (ViewPager) itemView.findViewById(R.id.act_viewpager);
        }

        public void setData(final List<HomeFragmentBean.ResultBean.ActInfoBean> act_info) {
            act_viewpager.setPageMargin(50);
            act_viewpager.setOffscreenPageLimit(3);//>=3

            //setPageTransformer 决定动画效果
            act_viewpager.setPageTransformer(true, new
                    ScaleInTransformer());
            //1.有数据了
            //2.设置适配器
            act_viewpager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return act_info.size();
                }

                /**
                 *
                 * @param view 页面
                 * @param object instantiateItem方法返回的值
                 * @return
                 */
                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view == object;
                }

                /**
                 *
                 * @param container ViewPager
                 * @param position 对应页面的位置
                 * @return
                 */
                @Override
                public Object instantiateItem(ViewGroup container, final int position) {
                    ImageView imageView = new ImageView(mContext);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    Glide.with(mContext).load(Constants.BASE_URL_IMAGE + act_info.get(position).getIcon_url()).into(imageView);
                    //添加到容器中
                    container.addView(imageView);

                    //设置点击事件
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                        }
                    });


                    return imageView;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    container.removeView((View) object);
                }
            });
        }
    }

}
