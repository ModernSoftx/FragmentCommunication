# FragmentCommunication

Lab Communication among Fragments
Objectives:

    Create fragments statically
    Use constraint layout to define interface
    Utilize seek bar
    Engage coding for the right call back method at fragment lifecycle
    Set up the soft keyboard
    Support communication among fragments through activity by implementing interface

Description:
This app consists of two fragments. The first/top fragment will allow user to put in some text and use seek bar to define the font size of the text.
When the Change Text button is pressed, the text will be shown in the second/bottom fragment.

View binding is not used in this lab

Turn off AutoConnection to Parent on the Layout Editor

Step 1: Start a project, FragmentCommunication, by using Empty Activity template
Step 2: Create the first fragment, ToolbarFragment, with its layout as follows:
Step 2.1: Right click your app select New > Fragment > Fragment (Blank)
Fragment Name: ToolbarFragment
Make sure the Source Language is Java and click Finish button.
ToolbarFragment.java will be open in the editor.
Step 2.2: Open fragment_toolbar.xml in the Design mode
Step 2.2.1: Right click FrameLayout in the Component Tree and select Convert FrameLayout to ConstraintLayout
Step 2.2.2: Accept default settings in the Convert to ConstraintLayout dialog and click OK button
Step 2.2.3: High light frameLayout in Component Tree. Go to the Attributes panel replace the id field by “constraintLayout” followed by pressing Enter key. Accept all default in the Rename dialog and click Refactor button
Step 2.2.4: Delete the textView and add a Plain Text, Seekbar and Button to the layout and change their ids to be editText1, seekBar1 and button1 respectively.
Step 2.2.5: Remove the Name text from editText1 and define imeOptions to be actionDone so that the soft keyboard goes away once you press on Done.
Step 2.2.6: For seekBar1, set its layout_width to match_constraint with margins set to 16dp on the right and left edges
Step 2.2.7: For button1, create a string resource named change_text with “Change Text” as its value and associate the string to be the text of button1
Step 2.2.8: Create constraints so that these three widgets (components) will be horizontally centered and editText1 is on the top and seekBar1 is in the middle and button1 is on the bottom. Make sure they only occupy the upper half of the screen.
Note: When you see errors in Design mode you may click on them to understand the problems. Sometimes you can click Fix button to solve the problem.
Step 3: Review the java code in ToolbarFragment.java
For us the most important portion is the onCreateView(). It creates the layout view. That line of code is equivalent to the following two lines. Don’t change it. I just “dissect” here for your understanding.

View view = inflater.inflate(R.layout.fragment_toolbar_, container, false);

return view;


               The following statement is just a note. No need to do it because I don’t want you to create any side effect accidently. That is, you can comment out all code in this newly created java class except the constructor and onCreateView()
Step 4: Create the second fragment
Step 4.1: Right click your app select New > Fragment > Fragment (Blank)
Fragment Name: TextFragment
Make sure the Source Language is Java and click Finish button.
TextFragment.java will be open in the editor.
Step 4.2: Open fragment_text.xml in the Design mode
Step 4.2.1: Right click FrameLayout in the Component Tree and select Convert FrameLayout to ConstraintLayout
Step 4.2.2: Accept default settings in the Convert to ConstraintLayout dialog and click OK button
Step 4.2.3: High light frameLayout in Component Tree. Go to the Attributes panel replace the id field by “constraintLayout2” followed by pressing Enter key. Accept all default in the Rename dialog and click Refactor button
Step 4.2.4: Highlight textView in Component Tree (i.e., select textView). Change attributes in the attributes panel as follows:
id: textView2
text: Clear the value in the text field. Create a string, Fragment2, with value “Fragment 2” in the strings.xml. Use this string as the text.
textAppearance: Large
layout_width: wrap_content
layout_height: wrap_content
layout_constraintHorizontal_bias: 0.50
layout_constraintVertical_bias: 0.50
Note: Just try to centralize the text view.
For your information: You will see a warning associated with textView2. You can click on it and if you click on Fix button the system will try to create a resource. But no need to do it right now.
Step 5: Open activity_main.xml
Step 5.1: delete the default “Hello World” text view
Step 5.2: Select Common in Palette. Drag FragmentContainerView into the design view (or You can drag it to the Component Tree)
Step 5.3: In the popup dialog move your cursor around and select ToolbarFragment and click OK button
Step 5.4: Change the width for this fragment in the Attributes panel to be match_constraint
Step 5.5: Click the error associated with fragmentContainerView. Select Unknown fragments error and drill down the error and select (Use @layout/fragment_toolbar, Pick Layout…). We will deal with missing constraints later.
Step 5.6: Drag in another FragmentContainerView into the Component Tree and put it below the one we created above. In Fragment dialog select TextFragment and click OK button. Follow step mentioned above to solve the error by pick @layout/fragment_text for this newly created FragmentContainerView.
Step 5.7: Let’s deal with Missing Constraints. Use skills you have learned so far to centralize these two fragment views with ToolbarFragement on the top. Use opposing constraints helps.
Now we need to make the fragment talk to activity.
Step 6: Select ToolbarFrament.java and Implement OnSeekBarChangeListener for ToolBarFragment by putting the code to the end of the class declaration as follows:

public class ToolbarFragment extends Fragment implements SeekBar.OnSeekBarChangeListener { …… }


I put it in red with underline to catch your attention. You don’t put it that way in your code.
Now you will find a red light bulb at the beginning of the statement. Click the red light bulb and select “implement methods”.
Three methods will be highlighted in the Select Methods to Implement dialog. Click on OK button to implement them.
Step 7: In Class ToolbarFragment.java continue the following actions:
Step 7.1: Create two class variables

private static int seekvalue = 10;

private static EditText editText;


               Step 7.2: Create onViewCreated() method if not exists as follows: (Warning: onCreateView() and onViewCreated() are different.)
@Override
public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
super.onViewCreated(view, savedInstanceState);

editText = view.findViewById(R.id.editText1);

final SeekBar seekbar = view.findViewById(R.id.seekBar1);

seekbar.setOnSeekBarChangeListener(this);

final Button button = view.findViewById(R.id.button1);

button.setOnClickListener(new View.OnClickListener(){

    public void onClick(View view){

        buttonClicked(view);

    }

});

}



        Hint: Could you put those statements into onCreateView()? Of course. But you need to split the inflate statement into two statements first and then utilize the view accordingly. See Step 3 above.

 
               Step 7.3: Create an empty buttonClicked() method (in ToolbarFragment.java) as follows:

public void buttonClicked(View view){}


               Step 7.4: Implenet onProgressChanged() method by putting the following line of code as follows:

seekvalue = i;


               Here I assume the method declaration is onProgressChanged(SeekBar seeBar, int I, boolean b)
//Now we need to call host activity when button inside the ToolbarFragment is clicked
Step 7.5: Declare an interface (for example, below private static variable EditText editText)

ToolbarListener activityCallback;

public interface ToolbarListener{

    public void onButtonClick(int fontSize, String text);

}


               Step 7.6: Create onAttach() if it does not exist. Make sure onAttach() is implemented with the following code:
@Override
public void onAttach(Context context){
super.onAttach(context);

try {

    activityCallback = (ToolbarListener)context;

} catch (ClassCastException e){

    throw new ClassCastException(context.toString() + " must implement ToolbarListener");

}

}


               Step 7.7: Put the following code into method buttonClicked() defined above

activityCallback.onButtonClick(seekvalue, editText.getText().toString());


Step 8: Implement the following in MainActivity.java
Step 8.1: Change its superclass and implement interface as follows:

public class MainActivity extends FragmentActivity implements ToolbarFragment.ToolbarListener{


               Step 8.2: Click the red light bulb at the beginning of the declaration and select “Implement methods”. onButtonClick method should be highlighted in the “Select Methods to Implement” dialog. Click OK button.
Step 9: In TextFragment.java
Step 9.1: Declare variable

private static TextView textView;


               Step 9.2: Create onViewCreated() method if not exists as follows: (Warning: onCreateView() and onViewCreated() are different.)
@Override
public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){

        textView = view.findViewById(R.id.textView2);

}



        Hint: Could you merge this statement into onCreateView()? Of course. But you need to split the inflate statement into two statements first and then utilize the view accordingly. See Step 3 above.

 
               Step 9.3: Change textView’s fontSize by defining the following new method

public void changeTextProperties(int fontSize, String text)

{

    textView.setTextSize(fontSize);

    textView.setText(text);

}



Step 10: Put the following code in onButtonClick method in MainActivity.java



public void onButtonClick(int fontSize, String text) {

    TextFragment textFragment = (TextFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2);

    textFragment.changeTextProperties(fontSize, text);

}





Step 11: Run the app