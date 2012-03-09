package bioinformatics.project;

import android.app.Activity;
import android.content.Intent;
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
		final ImageButton play = (ImageButton) findViewById(R.id.play_button);
		final ImageButton tutorial = (ImageButton) findViewById(R.id.tutorials_button);
		final ImageButton settings = (ImageButton) findViewById(R.id.settings_button);
		final ImageButton credits = (ImageButton) findViewById(R.id.credits_button);
		play.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				initialiseLevelSelector();
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
		credits.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				initialiseCreditsPage();
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
				final Intent int_level_1 = new Intent(DNAManipulatorActivity.this,Level1Activity.class);
				startActivity(int_level_1);
			}
		});
		final Button level2 = (Button) findViewById(R.id.level2_button);
		level2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				final Intent int_level_2 = new Intent(DNAManipulatorActivity.this, Level2Activity.class);
				startActivity(int_level_2);
			}
		});
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

	public void initialiseCreditsPage() {
		setContentView(R.layout.credits_page);
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