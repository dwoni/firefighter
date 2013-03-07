package woni.FireFighter;

import java.util.Hashtable;
import java.util.Map.Entry;

import com.egoclean.android.widget.flinger.ViewFlinger;
import com.egoclean.android.widget.flinger.ViewFlinger.OnScreenChangeListener;
import com.woni.firefighter.common.R;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;

public class FireFighterActivity extends Activity {
	private ViewFlinger flinger;
	private static String SettingsFile = "FireFighter.settings";
	private static String LastSelectedDistrictKey = "LastSelectedDistrict";
    private Hashtable<String, View> viewCache = new Hashtable<String, View>();
    protected IConfiguration configuration;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        flinger = (ViewFlinger)findViewById(R.id.views);
        flinger.setOnScreenChangeListener(new OnScreenChangeListener() {
			
			public void onScreenChanging(View newScreen, int newScreenIndex) {
				// TODO Auto-generated method stub
				
			}
			
			public void onScreenChanged(View newScreen, int newScreenIndex) {
				// TODO Auto-generated method stub
				if(newScreen != null){
					AlarmView alarms = (AlarmView)newScreen;
					if(!alarms.getIsLoaded())
						onRefresh();
					
					SharedPreferences settings = getSharedPreferences(SettingsFile, 0);
					Editor editor = settings.edit();
					editor.putString(LastSelectedDistrictKey, alarms.getDistrict());
					editor.commit();
				}
			}
		});

        for (Entry<String, String> district : configuration.getDistricts().entrySet()) {
        	flinger.addView(createView(district.getKey(), district.getValue(), configuration));
        }
        
        SharedPreferences settings = getSharedPreferences(SettingsFile, 0);
        String lastDistrict = settings.getString(LastSelectedDistrictKey, configuration.getDistricts().keySet().iterator().next());
        
        flinger.setCurrentScreen(viewCache.get(lastDistrict));
        
        onRefresh();
    }
    
    private View createView(String shortText, String longText, IConfiguration configuration){
    	if(!viewCache.containsKey(shortText)){
	    	AlarmView view = new AlarmView(this,new District(shortText, longText), configuration);
	    	view.setLoadedListener(new AlarmViewLoadedListener() {
				
				public void onLoaded() {
					// TODO Auto-generated method stub
					View progress = findViewById(R.id.progress);
					progress.setVisibility(View.GONE);
					View refresh = findViewById(R.id.refresh);
					refresh.setVisibility(View.VISIBLE);
				}
			});
	    	
	    	viewCache.put(shortText,  view);
    	}
    	return viewCache.get(shortText);
    }
    
	public void onRefresh(){
		onRefresh((AlarmView)flinger.getCurrentScreen());
	}

	public void onRefresh(View v){
		onRefresh();
    }
	
	private void onRefresh(AlarmView view){
		View progress = findViewById(R.id.progress);
		progress.setVisibility(View.VISIBLE);
		View refresh = findViewById(R.id.refresh);
		refresh.setVisibility(View.GONE);
		
    	AlarmView v = view;
    	v.onRefresh();
	}
	
	public void onSettingsClick(View view){
		
	}
}