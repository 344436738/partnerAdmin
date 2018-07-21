function saveDownloadSetting(){
	 var data = new FormData($('#uploadScoreForm')[0]); 
	 
     $.ajax({  
         url: 'saveUploadResumeActivity.do?',  
         type: 'POST',  
         data: data,  
         dataType: 'JSON',  
         cache: false,  
         processData: false,  
         contentType: false  
     }).done(function(ret){  
         alert(ret.msg);
        
     });  
     return false;  
	
}



function cancel(){
	history.back();
}