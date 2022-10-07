/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function addvote(){
	var id=$('input[type=radio][name=flat]:checked').attr('id');
	data={candidateid:id};
	$.post("AddVoteControllerServlet",data,processresponse);
}
function processresponse(responseText){
	responseText=responseText.trim();
	if(responseText==="success"){
		swal("Success","Voting done","success").then((value)=>{
			window.location="votingresponse.jsp";
		});
	}else{
		swal("Failure","Voting failed","error").then((value)=>{
			window.location="votingresponse.jsp";
		});
	}
}

