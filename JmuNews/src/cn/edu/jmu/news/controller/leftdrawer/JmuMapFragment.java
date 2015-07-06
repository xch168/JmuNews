package cn.edu.jmu.news.controller.leftdrawer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup.OnCheckedChangeListener;
import cn.edu.jmu.news.R;
import cn.edu.jmu.news.controller.BaseFragment;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

/**
 * ������ѧ��ͼҳ
 * 
 * @author Administrator
 *
 */
public class JmuMapFragment extends BaseFragment {

	private MapView mMapView;
	private BaiduMap mBaiduMap;
	
	// ��λ���
	LocationClient mLocClient;
	public MyLocationListenner myListener = new MyLocationListenner();
	BitmapDescriptor mCurrentMarker;
	
	// UI���
	OnCheckedChangeListener radioButtonListener;
	Button requestLocButton;
	boolean isFirstLoc = true;// �Ƿ��״ζ�λ
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_jmu_map, container, false);
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		// ��ȡ��ͼ�ؼ�����  
        mMapView = (MapView) getView().findViewById(R.id.bmapView);  
        mBaiduMap = mMapView.getMap();
        // ������λͼ��
        mBaiduMap.setMyLocationEnabled(true);
        // ���ø���ģʽ
        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
        		LocationMode.FOLLOWING, true, mCurrentMarker));
        
        //�趨���ĵ����� 
        LatLng cenpt = new LatLng(24.590089, 118.102514); 
        //�����ͼ״̬
        MapStatus mMapStatus = new MapStatus.Builder()
        .target(cenpt)
        .zoom(16)
        .build();
        //����MapStatusUpdate�����Ա�������ͼ״̬��Ҫ�����ı仯
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //�ı��ͼ״̬
        mBaiduMap.setMapStatus(mMapStatusUpdate);
        
        // ��λ��ʼ��
 		mLocClient = new LocationClient(getActivity());
 		mLocClient.registerLocationListener(myListener);
 		LocationClientOption option = new LocationClientOption();
 		option.setOpenGps(true);// ��gps
 		option.setCoorType("bd09ll"); // ������������
 		option.setScanSpan(1000);
 		mLocClient.setLocOption(option);
 		mLocClient.start();
	}
	
	/**
	 * ��λSDK��������
	 */
	public class MyLocationListenner implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// map view ���ٺ��ڴ����½��յ�λ��
			if (location == null || mMapView == null)
				return;
			MyLocationData locData = new MyLocationData.Builder()
					.accuracy(location.getRadius())
					// �˴����ÿ����߻�ȡ���ķ�����Ϣ��˳ʱ��0-360
					.direction(100).latitude(location.getLatitude())
					.longitude(location.getLongitude()).build();
			mBaiduMap.setMyLocationData(locData);
			if (isFirstLoc) {
				isFirstLoc = false;
				LatLng ll = new LatLng(location.getLatitude(),
						location.getLongitude());
				MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
				mBaiduMap.animateMapStatus(u);
			}
		}

		public void onReceivePoi(BDLocation poiLocation) {
		}
	}
	
	@Override
	public void onResume() {
		mMapView.onResume();
		super.onResume();
	}
	
	@Override
	public void onPause() {
		mMapView.onPause();
		super.onPause();
	}
	
	@Override
	public void onDestroy() {
		// �˳�ʱ���ٶ�λ
		mLocClient.stop();
		// �رն�λͼ��
		mBaiduMap.setMyLocationEnabled(false);
		mMapView.onDestroy();
		mMapView = null;
		super.onDestroy();
		
	}
}
