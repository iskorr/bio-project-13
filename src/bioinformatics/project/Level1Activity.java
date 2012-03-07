package bioinformatics.project;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Level1Activity extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level1);
        
    }
	
	public void setButtons() {
		final ImageButton buttonA = (ImageButton) findViewById(R.id.A_button);
		final ImageButton buttonT = (ImageButton) findViewById(R.id.T_button);
		final ImageButton buttonG = (ImageButton) findViewById(R.id.G_button);
		final ImageButton buttonC = (ImageButton) findViewById(R.id.C_button);
		buttonA.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
			}
		});
		buttonT.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
			}
		});
		buttonG.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
			}
		});
		buttonC.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
			}
		});
	}
	
}