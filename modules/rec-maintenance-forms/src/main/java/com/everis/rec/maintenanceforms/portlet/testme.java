package com.everis.rec.maintenanceforms.portlet;

public class testme {
	
	public static void main(String[] args)throws Exception {  
		 long num = 0;
		 
		 long i = 0; 
		    do{
		    	
		    	if ( num % 2 == 0 )
		    	{
		    		num = (num / 2);
		    		
		    	 }
		           
		         else
		         {
		        	 num = (num * 3) + 1;
		    		 
		    	 }
		      
		        System.out.println(num);    
		    i++;
		    }while(i != 150);
		    
		    
	} 

}
