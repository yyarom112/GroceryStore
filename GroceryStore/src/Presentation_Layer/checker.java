package Presentation_Layer;

public class checker {
	
	// checker function
	 public boolean check_number_range(String input,int first,int last){
		int temp=0;
		if(!checkIfInt(input))
			return false;
		temp=Integer.parseInt(input);
		if(temp>=first&&temp<=last)
			return true;
		return false;
		}
	 	 
	public boolean checkDouble(String s){
			try
			{
				Double.parseDouble(s);
				for(int i=0;i<s.length();i++)
					if(s.charAt(i)=='f')
						return false;

				return true;
			}
			catch(NumberFormatException e)
			{
				return false;
			}
		}


		public boolean checkIfInt(String s){
			try{
				Integer.parseInt(s);
				return true;
			}
			catch(NumberFormatException e){
				return false;
			}
		}


		public boolean checkOnlyNumbers(String s){
			if(s.length()==0)
				return false;

			for (int i = 0; i < s.length(); i++) {
				if (!Character.isDigit(s.charAt(i)))
					return false;
			}
			return true;
		}

		public boolean checkOnlyDouble(String s){
			if(s.length()==0)
				return false;
			String number=s;
			boolean isDigit=false,point=false;
			for (int i = 0; i < s.length(); i++) {
				if (Character.isDigit(s.charAt(i)))
					isDigit=true;
				if(s.charAt(i)=='.')
					point=true;
				if(!isDigit&&!point)
					return false;
				isDigit=false;
				point=false;
			}
			return true;
		}
		public boolean checkOnlyLetters(String s){
			if(s.length()==0)
				return false;

			char[] chars = s.toCharArray();

			for (char c : chars) {
				if(!Character.isLetter(c) && c!='-' && c!=' ') {
					return false;
				}
			}
			return true;
		}


		public boolean checkDate(String s){
			if(s.length()!=10)
				return false;
			for(int i=0;i<s.length();i++){
				if(i==2||i==5){
					if(s.charAt(i)!='.')
						return false;
				}
				else{
					if(s.charAt(i)<48&&s.charAt(i)>57)
						return false;
				}
			}
			String temp1=s.substring(0, 2);
			if(Integer.parseInt(temp1)>31||Integer.parseInt(temp1)<0)
				return false;
			String temp2=s.substring(3,5);
			if(Integer.parseInt(temp2)>12||Integer.parseInt(temp2)<0)
				return false;
			String temp3=s.substring(6);
			if(Integer.parseInt(temp3)<0)
				return false;
			return true;
		}
}
