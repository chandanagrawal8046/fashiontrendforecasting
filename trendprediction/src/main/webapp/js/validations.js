/**
 * 
 */

	function validateForm(formObj,str)
       {
    	   alert('calling'+str);
    	   
    	   
    	   if(str=='user')
    	   {
    	     if(!(formObj.txtmobile.value).startsWith("9"))
   	    	 {
    	    	 if(!(formObj.txtmobile.value).startsWith("8"))
    	    	 {
    	    		 if(!(formObj.txtmobile.value).startsWith("7"))
    	    		 {
    	    			if(!(formObj.txtmobile.value).startsWith("6"))
    	    			{
				   	     	alert("Invalid mobile no..9");
				   	     	formObj.txtmobile.focus();
				       		return false;
    	    			}
    	    		 }
    	    	 }
   	    	 }
    	     
    	   }
    	   
    	   if(str=='chpass')
    	   {
    	     if(formObj.newpass.value != formObj.retypepass.value)
   	    	 {    	    	 
				 alert("Retype password correctly");
				 formObj.retypepass.focus();
				 return false;
   	    	 }
    	     
    	   }
       }