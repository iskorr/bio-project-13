package bioinformatics.project;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.Selection;
import android.util.Log;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Level2Activity extends Activity {
	/* When to use "@Override"?
	 * Use it every time you override a method for two benefits. Do it so that 
	 * you can take advantage of the compiler checking to make sure you actually 
	 * are overriding a method when you think you are. This way, if you make a 
	 * common mistake of misspelling a method name or not correctly matching the
	 * parameters, you will be warned that you method does not actually 
	 * override as you think it does. Secondly, it makes your code easier 
	 * to understand because it is more obvious when methods are overwritten.
	 * 
	 * Lifecycle Notes:
	 * 
	 * When the Activity first time loads the events are called as below: 
	 * onCreate()onStart()onResume()
	 * 
	 * When you click the back button OR try to finish() the activity the 
	 * events are called as below:onPause()onStop()onDestroy()
	 * 
	 * When you click on Phone button the Activity goes to the background & 
	 * below events are called:onPause()onStop()
	 */
	
	/*
	 * 
	 * On activity start 
	 * - load in the DNA sequence
	 * - initialize UI elements
	 * - start timer
	 * 
	 */
	
	/* onCreate() Called when the activity is first created. This is where you 
	 * should do all of your normal static set up: create views, bind data to 
	 * lists, etc. This method also provides you with a Bundle containing the 
	 * activity's previously frozen state, if there was one.
	 */
	
	//Variable Declaration
	private EditText edittext;//shows userinput
	private TextView textview;//displays the dnasequence
	private TextView feedback;//displays messages for user
	private String[] SequenceArray;//contains dnasequence
	//private StopWatchClass StopWatch;
	private static final String TAG = "MyActivity";//used for log
	
	Button b1, b2, b3, b4;
	
	private Handler mHandler; 
	private long startTime;
	private long elapsedTime;
	private final int REFRESH_RATE = 100;//defines how often we should update the timer to show how much time has elapsed. This value is in milliseconds.
	private String hours,minutes,seconds,milliseconds;
	private long secs,mins,hrs;
	private boolean stopped = false;
	private Runnable startTimer;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set the activity content from a layout resource. The resource will be 
        //inflated, adding all top-level views to the activity.
        setContentView(R.layout.level2); 
        Initialise();
        LoadSequence();
    }
	
	public void start() {
    	if(stopped){
    		this.startTime = System.currentTimeMillis() - elapsedTime;
    	}
    	else{
    		this.startTime = System.currentTimeMillis();
    	}
    	mHandler.removeCallbacks(startTimer);
        mHandler.postDelayed(startTimer, 0);        
    }

    public void stop() {        
    	mHandler.removeCallbacks(startTimer);//handler stops the app from looping in the runnable event 
    	this.stopped = true;//sets the stopped boolean value to true     	
    }
    
    public void reset() {    	
    	this.stopped = false;
    	//do the reset in the calling class
    	((TextView)findViewById(R.id.timer)).setText("00:00:00");
    }
    
    public String getTime() {
    	return(minutes + ":" + seconds + ":" + milliseconds);
    }
    /*
     * We want to execute a piece of runnable code to start the timer.
     * Handler is a better choice than Timer since Handler runs the update code 
     * as a part of your main thread, avoiding the overhead of a second thread 
     * and also making for easy access to the View hierarchy used for the user 
     * interface.
     */
	
	private void updateTimer (float time){
		secs = (long)(time/1000);
		mins = (long)((time/1000)/60);
		hrs = (long)(((time/1000)/60)/60);
		
		/* Convert the seconds to String 
		 * and format to ensure it has
		 * a leading zero when required
		 */
		secs = secs % 60;
		seconds=String.valueOf(secs);
    	
		if(secs == 0){
    		seconds = "00";
    	}
    	
		if(secs <10 && secs > 0){
    		seconds = "0"+seconds;
    	}
    	
		/* Convert the minutes to String and format the String */
    	
    	mins = mins % 60;
		minutes=String.valueOf(mins);
    	
		if(mins == 0) {
    		minutes = "00";
    	}
    	
    	if(mins <10 && mins > 0){
    		minutes = "0"+minutes;
    	}
		
    	/* Convert the hours to String and format the String */
    	
    	hours=String.valueOf(hrs);
    	if(hrs == 0){
    		hours = "00";
    	}
    	if(hrs <10 && hrs > 0){
    		hours = "0"+hours;
    	}
    	
    	milliseconds = String.valueOf((long)time);
    	if(milliseconds.length()==2){
    		milliseconds = "0"+milliseconds;
    	}
      	if(milliseconds.length()<=1){
    		milliseconds = "00";
    	}
		milliseconds = milliseconds.substring(milliseconds.length()-3, milliseconds.length()-2);
    	
		/* Setting the timer text to the elapsed time */
		((TextView)findViewById(R.id.timer)).setText(minutes + ":" + seconds + ":" + milliseconds);
	}
	
	
	 	
	private void Initialise() {
		//res = this.getResources();
		SequenceArray = new String[1];
		//genes = new ImageView[6];
		//genes_ans = new ImageView[6];
		//sequence = new int[ELEM_NUM];
		//gen = new Random();
		edittext = (EditText) findViewById(R.id.edittext);//pointer to EditText element
		textview = (TextView) findViewById(R.id.textview);//pointer to EditText element
		feedback = (TextView) findViewById(R.id.feedback);//pointer to EditText element
		
		b1 = (Button) findViewById(R.id.button1);
		b2 = (Button) findViewById(R.id.button2);
		b3 = (Button) findViewById(R.id.button3);
		b4 = (Button) findViewById(R.id.button4);
        //instantiate StopWatch object
        //StopWatch = new StopWatchClass();   
		mHandler = new Handler();
		startTimer = new Runnable() {
	 	   public void run() {
	 		   elapsedTime = System.currentTimeMillis() - startTime;
	 		   updateTimer(elapsedTime);
	 	       mHandler.postDelayed(this,REFRESH_RATE);
	 	       /*
	 	        * Alternatively:
	 	        * mHandler.postAtTime(this,start + (((minutes * 60) + seconds + 1) * 1000));
	 	        * would schedule another call to itself at a particular wall time. 
	 	        */
	 	   }
	 	};
	}
	
	//- Load DNA sequence depending on level selected
	private void LoadSequence(){
		SequenceArray[0] = "TCATAATACGTTTTGTATTC";	
		textview.setText(SequenceArray[0]);
	}
	
	//Read character from button - simplify code
	//G
	public void ClickHandler(View v) {
		
		String Input = "";
		
		if(v.getId()==b1.getId()) {
			Input = "G";
		}    
		if(v.getId()==b2.getId()) {
			Input = "C";
		}   
		if(v.getId()==b3.getId()) {
			Input = "A";
		}   
		if(v.getId()==b4.getId()) {
			Input = "U";
		}   		
		
		if(!CheckIfFinished()){
			if(CheckInput(Input)) {
				UpdateInput(Input);	
			}
		}
		
	}
	
	private void UpdateInput(String Input) {
		//Return a localized string from the application's package's default string table.
		String edittextstring = getString(R.string.level2edittext); 
		edittextstring = edittextstring + Input;
		edittext.setText(edittext.getText() + edittextstring);	
		//update cursor position.
		Selection.setSelection(edittext.getText(), edittext.length());
	}
	
	//Check if end of DNA sequence has already been reached, return message to user if so
	private boolean CheckIfFinished(){
		int AnswerLength = edittext.getText().length();
		int SequenceLength = textview.getText().toString().length();
		if(AnswerLength==SequenceLength) {
			//Return message to user
			feedback.setText("Finished! Press back to return to level select");
			return(true);
		}
		else {
			return(false);
		}
	}
	
	/* Check that correct key has been pressed
	 * Finds cursor position by checking how many characters are currently 
	 * displayed in the EditText box.
	 * Then makes comparison against returned substring of the TextView 
	 */
	private boolean CheckInput(String Input) {
		String Needed = "";
		int CursorPosition = edittext.getText().length();
		String CurrentDNA = textview.getText().toString().substring(CursorPosition,CursorPosition+1);
		//Log.v(TAG,"Input = " + Input);
		//Log.v(TAG,"CurrentDNA = " + CurrentDNA);
		//Log.v(TAG,"CursorPosition = " + CursorPosition);
		
		if(CurrentDNA.equals("G")){Needed= "C";}
		if(CurrentDNA.equals("C")){Needed= "G";}
		if(CurrentDNA.equals("T")){Needed= "A";}
		if(CurrentDNA.equals("A")){Needed= "U";}

		if(! Input.equals(Needed)) {
			//Return message to user
			feedback.setText("Try Again!");
			return(false);
		}
		else {
			feedback.setText("");
			return(true);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onStart()
	 * Called when the activity is becoming visible to the user. Followed by 
	 * onResume() if the activity comes to the foreground, or onStop() if it 
	 * becomes hidden.
	 */
	protected void onStart() {
		super.onStart();
	}
    
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onRestart()
	 * Called after your activity has been stopped, prior to it being started 
	 * again.Always followed by onStart()
	 */
    protected void onRestart() {
    	super.onRestart();
    }
    
    /*
     * (non-Javadoc)
     * @see android.app.Activity#onResume()
     * Called when the activity will start interacting with the user. At this 
     * point your activity is at the top of the activity stack, with user input 
     * going to it.Always followed by onPause().
     */
    protected void onResume() {
    	super.onResume();
    	//- starts timer again fresh.
    	start();	
    }
    
    /*
     * (non-Javadoc)
     * @see android.app.Activity#onPause()
     * Called when the system is about to start resuming a previous activity. 
     * This is typically used to commit unsaved changes to persistent data, 
     * stop animations and other things that may be consuming CPU, etc. 
     * Implementations of this method must be very quick because the next 
     * activity will not be resumed until this method returns. Followed by 
     * either onResume() if the activity returns back to the front, or onStop() 
     * if it becomes invisible to the user.
     */
    protected void onPause() {
    	//save the current elapsed time
    	super.onResume();
    	stop();
    }

    /*
     * (non-Javadoc)
     * @see android.app.Activity#onStop()
     * Called when the activity is no longer visible to the user, because 
     * another activity has been resumed and is covering this one. This may 
     * happen either because a new activity is being started, an existing one 
     * is being brought in front of this one, or this one is being destroyed. 
     * Followed by either onRestart() if this activity is coming back to 
     * interact with the user, or onDestroy() if this activity is going away.
     */
    protected void onStop() {
    	super.onStop();	
    }
    
    /*
     * (non-Javadoc)
     * @see android.app.Activity#onDestroy()
     * The final call you receive before your activity is destroyed. This can 
     * happen either because the activity is finishing (someone called finish()
     * on it, or because the system is temporarily destroying this instance of
     * the activity to save space. You can distinguish between these two 
     * scenarios with the isFinishing() method.
     */
    protected void onDestroy() {
    	super.onDestroy();
    	//Need to save achieved time into scoreboard
    }
    
}
