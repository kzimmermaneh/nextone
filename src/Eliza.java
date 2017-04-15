import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.Color;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.lang.Math;





public class Eliza extends JFrame implements KeyListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel p=new JPanel();
	JTextArea dialog = new JTextArea(20,50);
	JTextArea input = new JTextArea(1,50);
	JScrollPane scroll = new JScrollPane(
			dialog,
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	// 2 dimensional array that contains all phrases
	String [] [] chatBot= {
			//standard greetings from Eliza
			{"Hi", "Hello", "Hola"},
			{"Hi", "Hello", "Hey"},
			//question
			{"How are you", "how r you", "how r u"},
			{"good", "doing well"},
			//default
			{"Leave me alone", "I'm not upset", "I'm leaving"},
			
	};
	
	
	
	
	
	public static void main(String[] args) {
		//activate constructor method for class
		new Eliza();
	}

	
	public Eliza() {
		
		super ("Eliza");
		setSize (600,400);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		dialog.setEditable(false);
		input.addKeyListener(this);
		
		p.add(scroll);
		p.add(input);
		p.setBackground(new Color(255,200,0));
		//add p to the JFrame                                  
		add(p);
		
		setVisible(true);
		
	} 
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			input.setEditable(false);
			//---grab quote from input
			String quote=input.getText();
			input.setText("");
			addText("-->Eliza:\t" +quote);
			quote.trim();
			while(
					quote.charAt(quote.length()-1)=='!'  ||
					quote.charAt(quote.length()-1) == '.' ||
					quote.charAt(quote.length()-1) =='?'
					){
			
				quote=quote.substring(0, quote.length()-1);
			}	
			quote.trim();
					
				//take away the punctuation
		//	}
		
		byte response = 0;
		/* 0, 1, 2 */
		
			//---check for matches---
		int j=0; 
		while(response ==0) {
			if(inArray(quote.toLowerCase(),chatBot[j*2])){
				response=2;
				int r=(int)Math.floor(Math.random() * chatBot [(j*2)+1].length);
				addText("\n-->Me:\t" +chatBot[(j*2) +1] [r]);
			}
			
			j++;
			if(j*2==chatBot.length -1 && response==0) {
				response=1;
			}
		//----no matches, go for default
			if(response==1) {
				int r=(int)Math.floor(Math.random() * chatBot [chatBot.length-1].length);
				addText("\n-->Me:\t" +chatBot[chatBot.length-1] [r]);
				
			}
			
			addText("\n");
		}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() ==KeyEvent.VK_ENTER) {
			input.setEditable(true);	
		}
	}
	
	public void keyTyped (KeyEvent e) {}
	
	public void addText(String str){
		dialog.setText(dialog.getText() + str);
	}
	
	public boolean inArray(String in, String [] str) {
		boolean match = false;
		for (int i=0; i<str.length; i++) {
			if(str[i].equals(in)) {
				match=true;
			}
		}
		return match;
	}
	
}

	
	
	



















/**
Eliza
Submit Assignment
Due No Due Date  Points 2  Submitting a website url
Eliza is a solo project. You will be creating an interactive chat-bot type program.

Eliza is a therapist program that interacts with the user. Eliza will respond to your questions entered at the console by rephrasing them or indirectly asking for more information.

Eliza responds to questions in one of two ways: With a randomly chosen hedge, such as "Please tell me more" By changing some keywords in the user's input string and appending this string to a randomly chosen qualifier.

In this way, a statement such as "My teacher thinks I don't work hard enough" becomes replaced by "your teacher thinks you don't work hard enough" which is then appended to "Why do you say" resulting in the following exchange:

Eliza: "What is your problem?"

You: "My teacher thinks I don't work hard enough"

Eliza: "Why do you say your teacher thinks you don't work hard enough?"

To make this happen we revise the question by changing "my" and "I" to "your" and "you", then choose a random qualifier from our list. The qualifier is appended to the revised question.

It's fun, it works pretty well and feels like a conversation.

At other times we may just have Eliza randomly say.... "Tell me more"

You will be using HashMaps and HashSets to evaluate what the user asks. Then turn the user's input into a question. And it will sound like the therapist really cares.

Create a HashSet as follows

public Set<String> hedgeSet = new HashSet<String>();
public Set<String> qualifierSet = new HashSet<String>();
public Map<String,String> replacementMap = new HashMap<String,String>();


Sample hedges:

Please tell me more
Many of my patients tell me the same thing
It is getting late, maybe we had better quit
Sample qualifiers:

Why do you say that
You seem to think that
So, you are concerned that
You'll need some replacements to change the text from the user's question into something Eliza might say. You can use a hashmap to store the replacements.

Replacements:

replace i with you
replace me with you
replace my with your
replace am with are


When the user enters a statement the program responds in one of two ways:

1. With a randomly chosen hedge, such as "Please tell me more"

2. By changing some keywords  in the user's input string an appending this string to a randomly chosen qualifier.

How to get a random item from a set:

You can use an object called an iterator to loop through a collection like a set:

int index = rand.nextInt(set.size());
Iterator<String> iter = set.iterator();
for (int i = 0; i < index; i++) {
   iter.next();
}
return iter.next();
Here's how the replacement works:

The user enters something at the prompt: "my teacher hates me"

The program will loop through that string and replace "my" with "your" and "me" with "you" and then prepend the qualifier to the replacement string. So, my teacher hates me becomes "Why do you say that your teacher hates you?" The replacement method returns the same words as the user entered only the replacement words have been swapped. Then the qualifier is prepended to the user's words.



Example session with Eliza

Good day. What is your problem?Enter your response here or Q to quit: my teacher hates me
Why do you say that your teacher hates you
Enter your response here or Q to quit: she always calls on the girls in the class
Please tell me more
Enter your response here or Q to quit: i would like to get called on too
You seem to think that you would like to get called on too
Enter your response here or Q to quit: q

**/