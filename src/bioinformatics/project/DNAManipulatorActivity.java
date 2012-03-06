package bioinformatics.project;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class DNAManipulatorActivity extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final ImageButton start = (ImageButton) findViewById(R.id.startup_button);
        
        start.setOnClickListener(new View.OnClickListener() {
        	
            public void onClick(View v) {
            	start.setVisibility(0);
            	setContentView(R.layout.menu);	
            }
            
        });
    }
}