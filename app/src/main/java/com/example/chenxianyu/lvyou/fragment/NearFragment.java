package com.example.chenxianyu.lvyou.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.chenxianyu.lvyou.R;

import java.util.List;

import static cn.bmob.v3.Bmob.getApplicationContext;

/**
 * 项目名：lvyou
 * 包名：com.example.chenxianyu.lvyou.fragment
 * 文件名：NearFragment
 * 创建者：陈贤煜
 * 创建时间：2017/2/18 14:34
 * 描述：附近
 */

public class NearFragment extends Fragment{
    private MapView bmapView;
    private BaiduMap mBaiduMap;
    public LocationClient mLocationClient = null;

    public BDLocationListener myListener = new MyLocationListener() {
        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_near,null);
        init(view);
        /**
         * 1、定位
         * 2、绘制图层
         */
        return view;
    }

    private void init(View view) {

        bmapView = (MapView) view.findViewById(R.id.bmapView);

        mBaiduMap=bmapView.getMap();
        //声明LocationClient类
        mLocationClient = new LocationClient(getApplicationContext());
        //注册监听函数
        mLocationClient.registerLocationListener( myListener );
        initLocation();
        mLocationClient.start();
    }

    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span=1000;
        option.setScanSpan(0);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bmapView.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        bmapView.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        bmapView.onPause();
    }

    //定位回调
    public abstract class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {

            //获取定位结果
            StringBuffer sb = new StringBuffer(256);

            sb.append("time : ");
            sb.append(location.getTime());    //获取定位时间

            sb.append("\nerror code : ");
            sb.append(location.getLocType());    //获取类型类型

            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());    //获取纬度信息

            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());    //获取经度信息

            sb.append("\nradius : ");
            sb.append(location.getRadius());    //获取定位精准度

            if (location.getLocType() == BDLocation.TypeGpsLocation){

                // GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());    // 单位：公里每小时

                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());    //获取卫星数

                sb.append("\nheight : ");
                sb.append(location.getAltitude());    //获取海拔高度信息，单位米

                sb.append("\ndirection : ");
                sb.append(location.getDirection());    //获取方向信息，单位度

                sb.append("\naddr : ");
                sb.append(location.getAddrStr());    //获取地址信息

                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation){

                // 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());    //获取地址信息

                sb.append("\noperationers : ");
                sb.append(location.getOperators());    //获取运营商信息

                sb.append("\ndescribe : ");
                sb.append("网络定位成功");

            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {

                // 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");

            } else if (location.getLocType() == BDLocation.TypeServerError) {

                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");

            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {

                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");

            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {

                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");

            }

            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());    //位置语义化信息

            List<Poi> list = location.getPoiList();    // POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }

            Log.i("BaiduLocationApiDem", sb.toString());
            //移动到我的位置
            //设置缩放，确保屏幕内有我
            MapStatusUpdate mapUpdate = MapStatusUpdateFactory.zoomTo(18);
            mBaiduMap.setMapStatus(mapUpdate);
            //开始移动
            MapStatusUpdate mapLatlng=MapStatusUpdateFactory.newLatLng(new LatLng(location.getLatitude(),location.getLongitude()));
            mBaiduMap.setMapStatus(mapLatlng);
            //定义Maker坐标点
            LatLng point = new LatLng(location.getLatitude(),location.getLongitude());
//构建Marker图标
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.location_pin);
//构建MarkerOption，用于在地图上添加Marker
            OverlayOptions option = new MarkerOptions()
                    .position(point)
                    .icon(bitmap);
//在地图上添加Marker，并显示
            mBaiduMap.addOverlay(option);
        }

    }
}