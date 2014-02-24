package objects;

public class LetterObject {
	
	private String letterUser;
	private long timeDate;
	private int letterID;
	private String letterContent;
	
	public String getLetterUser(){
		
		return letterUser;
	}
		
	public void setLetterUser(String letterUser){
			
		this.letterUser = letterUser;
	}
	
	public long getTimeDate(){
			
		return timeDate;
	}
			
	public void setTimeDate(long timeDate){
				
			this.timeDate = timeDate;
	}
	
	public int getLetterID(){
		
		return letterID;
	}
		
	public void setLetterID(int  letterID){
			
			this.letterID = letterID;
	}
	
	public String getLetterContent(){
		
		return letterContent;
	}
		
	public void setLetterContent(String letterContent){
			
		this.letterContent = letterContent;
	}

}
