package woni.FireFighter;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

public class FireFighterActivity extends Activity {
	
	final String url = "http://178.188.171.236/rpweb/onlinestatus.aspx?form=EVENT&bez=RA";
	final Activity activity = this;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        try {
        	onRefresh();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void onRefresh(View v){
    	onRefresh();
    }
    
    private void onRefresh(){
    	final Button refresh = (Button)findViewById(R.id.button1);
    	refresh.setVisibility(View.GONE);
        final LinearLayout progress = (LinearLayout)findViewById(R.id.progress);
        progress.setVisibility(View.VISIBLE);
    	
    	RetreiveMissionsTask task = new RetreiveMissionsTask();
    	task.setOnDocumentUpdateListener(new MissionReceivedListener(){

			public void onMissionsReceived(List<Mission> missions) {
		        ListView bookListView =(ListView)findViewById(R.id.bookListView);
		        
		        LitemItemAdapter mcqListAdapter = new LitemItemAdapter(activity,R.layout.row,missions.toArray(new Mission[missions.size()]));
		        bookListView.setAdapter(mcqListAdapter);
		        
		        progress.setVisibility(View.GONE);
		        refresh.setVisibility(View.VISIBLE);
			}
    		
    	});
    		
    	task.execute(url);
    	
    }
}