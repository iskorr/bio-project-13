package bioinformatics.project;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Level1Activity extends Activity {
	/* Set of properties used in the Replication Level */

	/* Constants */
	private final int DELAY = 1000;
	private final int TIME_LIMIT = 120;
	private final int PENALTY = 5;
	private final int ELEM_NUM = 6;
	private final int C_BASE = 0;
	private final int G_BASE = 1;
	private final int A_BASE = 2;
	private final int T_BASE = 3;

	// Set of given genes sequence (pointers to ImageViews)
	private ImageView[] genes;

	// Set of answers to this sequence
	private ImageView[] genes_ans;

	// Generated sequence of genes
	private int[] sequence;

	// Random number generator
	private Random gen;

	// Loaded resources
	private Resources res;

	// Current element of the sequence
	private int current;

	// Penalty count
	private int timeleft;

	// Auxiliary time variable
	private long old_time;

	// Timer
    private Handler handler;
    
    // The game loop
    private Runnable run_seq;
    
    // The score
    private int score;
    
    // The timer and score displays
    private TextView time_disp;
    private TextView score_disp;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level1);
		initialise();
		generateSequence();
		setButtons();
		
	}

	// Variables' initialisation
	private void initialise() {
		res = this.getResources();
		time_disp = (TextView) findViewById(R.id.time);
		score_disp = (TextView) findViewById(R.id.score);
		genes = new ImageView[6];
		genes_ans = new ImageView[6];
		sequence = new int[ELEM_NUM];
		gen = new Random();
		timeleft = TIME_LIMIT;
		score = 0;
		old_time = System.currentTimeMillis();
		handler = new Handler();
		run_seq = new Runnable() {
			@Override
			public void run() {
				if ( timeleft > 0 ) {
					long curr = System.currentTimeMillis();
					long diff = curr - old_time;
					while (diff > DELAY) {
						timeleft--;
						diff-=DELAY;
					}
					showTime(timeleft);
					old_time = curr;
					handler.removeCallbacks(this);
					handler.postDelayed(this, DELAY);
				} else {
					endLevel();
				}
			}
		};
		handler.postDelayed(run_seq, DELAY);
	}

	// Sequence generation
	public void generateSequence() {
		for (int i = 0; i < ELEM_NUM; i++) {
			sequence[i] = gen.nextInt(4);
		}
		current = 0;
		setGenes();
	}

	// Sequence set up
	public void setGenes() {
		genes[0] = (ImageView) findViewById(R.id.gene1);
		genes[1] = (ImageView) findViewById(R.id.gene2);
		genes[2] = (ImageView) findViewById(R.id.gene3);
		genes[3] = (ImageView) findViewById(R.id.gene4);
		genes[4] = (ImageView) findViewById(R.id.gene5);
		genes[5] = (ImageView) findViewById(R.id.gene6);
		for (int i = 0; i < ELEM_NUM; i++) {
			if (sequence[i] == 0) genes[i].setImageDrawable(res.getDrawable(R.drawable.base_c));
			else if (sequence[i] == 1) genes[i].setImageDrawable(res.getDrawable(R.drawable.base_g));
			else if (sequence[i] == 2) genes[i].setImageDrawable(res.getDrawable(R.drawable.base_a));
			else genes[i].setImageDrawable(res.getDrawable(R.drawable.base_t));
		}
		genes_ans[0] = (ImageView) findViewById(R.id.gene1_ans);
		genes_ans[1] = (ImageView) findViewById(R.id.gene2_ans);
		genes_ans[2] = (ImageView) findViewById(R.id.gene3_ans);
		genes_ans[3] = (ImageView) findViewById(R.id.gene4_ans);
		genes_ans[4] = (ImageView) findViewById(R.id.gene5_ans);
		genes_ans[5] = (ImageView) findViewById(R.id.gene6_ans);
		for (int i = 0; i < ELEM_NUM; i++) {
			if (sequence[i] == 0) genes_ans[i].setImageDrawable(res.getDrawable(R.drawable.base_g));
			else if (sequence[i] == 1) genes_ans[i].setImageDrawable(res.getDrawable(R.drawable.base_c));
			else if (sequence[i] == 2) genes_ans[i].setImageDrawable(res.getDrawable(R.drawable.base_t));
			else genes_ans[i].setImageDrawable(res.getDrawable(R.drawable.base_a));
			genes_ans[i].setVisibility(View.INVISIBLE);
		}
	}

	// Method for setting up the buttons
	public void setButtons() {
		final ImageButton buttonA = (ImageButton) findViewById(R.id.A_button);
		final ImageButton buttonT = (ImageButton) findViewById(R.id.T_button);
		final ImageButton buttonG = (ImageButton) findViewById(R.id.G_button);
		final ImageButton buttonC = (ImageButton) findViewById(R.id.C_button);

		buttonA.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (sequence[current] == T_BASE) {
					genes_ans[current].setVisibility(View.VISIBLE);
					current++;
					score++;
					showScore();
				} else {
					timeleft -= PENALTY;
				}
				if (current >= ELEM_NUM) {
					generateSequence();
				}
			}
		});
		buttonT.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (sequence[current] == A_BASE) {
					genes_ans[current].setVisibility(View.VISIBLE);
					current++;
					score++;
					showScore();
				} else {
					timeleft -= PENALTY;
				}
				if (current >= ELEM_NUM) {
					generateSequence();
				}
			}
		});
		buttonG.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (sequence[current] == C_BASE) {
					genes_ans[current].setVisibility(View.VISIBLE);
					current++;
					score++;
					showScore();
				} else {
					timeleft -= PENALTY;
				}
				if (current >= ELEM_NUM) {
					generateSequence();
				}
			}
		});
		buttonC.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (sequence[current] == G_BASE) {
					genes_ans[current].setVisibility(View.VISIBLE);
					current++;
					score++;
					showScore();
				} else {
					timeleft -= PENALTY;
				}
				if (current >= ELEM_NUM) {
					generateSequence();
				}
			}
		});
	}

	// Method for time update
	public void showTime(int timeleft) {
		int a = timeleft%60;
		if (a < 10) time_disp.setText((timeleft/60) + ":0" + a);
		else time_disp.setText((timeleft/60) + ":" + a);
	}
	
	// Method for score update
	public void showScore() {
		score_disp.setText("SCORE: " + score);
	}

	// Finish game
	public void endLevel() {
		showTime(0);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("The level has finished, your score is " + score)
		       .setCancelable(false)
		       .setNeutralButton("Proceed", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                Level1Activity.this.finish();
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}
}