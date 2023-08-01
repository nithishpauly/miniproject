package Nithish;
import java.util.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	 public static boolean iscorrect(String ch,int len)
	 {
		 
		for(int i=0;i<len;i++)
		{
			if(Character.isDigit(ch.charAt(i)))
			{
				
				return true;
			}
		}
		return false;
	 }

	 public static boolean iscorrect(String ch)
	 {
		 int  count1=0,count2=0,count3=0;
		 
		 for(int i=0;i<ch.length();i++)
		 {
			 if(Character.isLetter(ch.charAt(i)))
			 {
				 count1++;
			 }
			 else if(Character.isDigit(ch.charAt(i)))
			 {
				 count2++;
			 }
			 else 
			 {
				 count3++;
			 }
		 }
		 if(ch.length()>=10&&count1>5 &&count2>2 && count3>0)
		 {
			 return true;
		 }
		 else
		 {
			 return false;
		 }
	 }
}
