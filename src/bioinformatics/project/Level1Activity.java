package bioinformatics.project;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import java.util.Random;

public class Level1Activity extends Activity {
	/* Set of properties used in the Replication Level */
	
	// Maximum number of elements (multiple of 6)
	private final int ELEM_NUM = 6;						
	
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
	private float penalty;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level1);
        initialise();
        generateSequence();
        setGenes();
        setButtons();
    }
	
	private void initialise() {
		res = this.getResources();
		genes = new ImageView[6];
		genes_ans = new ImageView[6];
		sequence = new int[ELEM_NUM];
		gen = new Random();
	}
	
	public void generateSequence() {
		for (int i = 0; i < ELEM_NUM; i++) {
			sequence[i] = gen.nextInt(4);
		}
		current = 0;
		penalty = 0;
	}
	
	public void setGenes() {
		genes[0] = (ImageView) findViewById(R.id.gene1);
		genes[1] = (ImageView) findViewById(R.id.gene2);
		genes[2] = (ImageView) findViewById(R.id.gene3);
		genes[3] = (ImageView) findViewById(R.id.gene4);
		genes[4] = (ImageView) findViewById(R.id.gene5);
		genes[5] = (ImageView) findViewById(R.id.gene6);
		for (int i = 0; i < 6; i++) {
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
		for (int i = 0; i < 6; i++) {
			if (sequence[i] == 0) genes_ans[i].setImageDrawable(res.getDrawable(R.drawable.base_g));
			else if (sequence[i] == 1) genes_ans[i].setImageDrawable(res.getDrawable(R.drawable.base_c));
			else if (sequence[i] == 2) genes_ans[i].setImageDrawable(res.getDrawable(R.drawable.base_t));
			else genes_ans[i].setImageDrawable(res.getDrawable(R.drawable.base_a));
			genes_ans[i].setVisibility(View.INVISIBLE);
		}
	}
	
	public void setButtons() {
		final ImageButton buttonA = (ImageButton) findViewById(R.id.A_button);
		final ImageButton buttonT = (ImageButton) findViewById(R.id.T_button);
		final ImageButton buttonG = (ImageButton) findViewById(R.id.G_button);
		final ImageButton buttonC = (ImageButton) findViewById(R.id.C_button);

		buttonA.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (sequence[current] == 3) {
					genes_ans[current].setVisibility(View.VISIBLE);
					current++;
				} else {
					penalty += 5;
				}
				if (current >= ELEM_NUM) {
					generateSequence();
					setGenes();
				}
			}
		});
		buttonT.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (sequence[current] == 2) {
					genes_ans[current].setVisibility(View.VISIBLE);
					current++;
				} else {
					penalty += 5;
				}
				if (current >= ELEM_NUM) {
					generateSequence();
					setGenes();
				}
			}
		});
		buttonG.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (sequence[current] == 0) {
					genes_ans[current].setVisibility(View.VISIBLE);
					current++;
				} else {
					penalty += 5;
				}
				if (current >= ELEM_NUM) {
					generateSequence();
					setGenes();
				}
			}
		});
		buttonC.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (sequence[current] == 1) {
					genes_ans[current].setVisibility(View.VISIBLE);
					current++;
				} else {
					penalty += 5;
				}
				if (current >= ELEM_NUM) {
					generateSequence();
					setGenes();
				}
			}
		});
	}
}