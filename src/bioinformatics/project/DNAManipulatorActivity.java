package bioinformatics.project;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class DNAManipulatorActivity extends Activity {
	
	/* This is our probable set of variables to pass back to the game levels */
	private int[] settings_array = {1,0};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup);
        final ImageButton start = (ImageButton) findViewById(R.id.startup_button);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	initialiseMainMenu();
            }
        });
    }
    
    public void initialiseMainMenu() {
    	setContentView(R.layout.main_menu);
    	final Button play = (Button) findViewById(R.id.play_button);
    	final Button help = (Button) findViewById(R.id.help_button);
    	final Button tutorial = (Button) findViewById(R.id.tutorial_button);
    	final Button settings = (Button) findViewById(R.id.settings_button);
    	play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	initialiseLevelSelector();
            }
        });
    	help.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				initialiseHelpMenu();
			}
		});
    	tutorial.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				initialiseTutorialPage();
			}
		});
    	settings.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				initialiseSettingsMenu();
			}
		});
    }
    
    public void initialiseLevelSelector() {
    	setContentView(R.layout.level_selector);
    	setBackToMainButton();
    	final Button level1 = (Button) findViewById(R.id.level1_button);
    	level1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
			}
		});
    }
    
    public void initialiseHelpMenu() {
    	setContentView(R.layout.help_menu);
    	setBackToMainButton();
    }
    
    public void initialiseTutorialPage() {
    	setContentView(R.layout.tutorial_page);
    	setBackToMainButton();
    	// TODO List of tutorials
    }
    
    public void initialiseSettingsMenu() {
    	setContentView(R.layout.settings_menu);
    	setBackToMainButton();
    }
    
    public void setBackToMainButton() {
    	final ImageButton back = (ImageButton) findViewById(R.id.back_button);
    	back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				initialiseMainMenu();
			}
		});
    }

}