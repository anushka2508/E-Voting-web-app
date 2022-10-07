
function redirectadministratorpage() {
    swal("Admin!", "Redirection to Admin Page!", "success").then(value=>{
        window.location = "adminactions.jsp";
    });
}

function redirectvotingpage() {
    swal("Admin!", "Redirection to voting Controller Page!", "success").then(value=>{
        window.location = "VotingControllerServlet";
    });
}
function manageuser(){
    swal("Admin!", "Redirection to User Management Page!", "success").then(value=>{
        window.location = "manageuser.jsp";
    }); 
}

function managecandidate(){
    swal("Admin!", "Redirection to Manage Candidate Page!", "success").then(value=>{
        window.location = "managecandidate.jsp";
    });
}

function resultpage(){
	swal("Admin!","Redirecting to Result Page!","success").then(value=>{
		window.location ="result.jsp";
	});
}

function showaddcandidateform() {
    removecandidateForm();
    var newdiv = document.createElement("div");
    newdiv.setAttribute("id", "candidateform");
    newdiv.setAttribute("float", "left");
    newdiv.setAttribute("padding-left", "12px");
    newdiv.setAttribute("border", "solid 2px red");
    newdiv.innerHTML = "<h3>Add New Candidate</h3>";
    newdiv.innerHTML = newdiv.innerHTML + "<form method='post' enctype='multipart/form-data' id='fileUploadForm'>\n\
<table><tr><th>Candidate Id:</th><td><input type='text' id='cid'></td></tr>\n\
<tr><th>User Id:</th><td><input type='text' id='uid' onkeypress='return getdetails(event)'></td></tr>\n\
<tr><th>Candidate Name:</th><td><input type='text' id='cname'></td></tr>\n\
<tr><th>City:</th><td><select id='city'></select></td></tr>\n\
<tr><th>Party:</th><td><input type='text' id='party'></td></tr>\n\
<tr><td colspan='2'><input type='file' name='files' value='Select Image'></td></tr>\n\
<tr><th><input type='button' value='Add Candidate' onclick='addcandidate()' id='addcnd'></th>\n\
<th><input type='reset' value='Clear' onclick='clearText()'</th></tr><table></form>";
    newdiv.innerHTML = newdiv.innerHTML + "<br><span id='addresp'</span>";
    var addcand = $("#result")[0];  //is line se vo div milega jiski id result hai (jab  js se dynamically koi cheez generate karke add karni hoti hai to us element ki parent ko lete h)
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn("3500");
    date = {id: "getid"};
    $.post("AddCandidateControllerServlet", date, function (responseText) {
        $("#cid").val(responseText);       //jquery syntax
        $("#cid").prop("disabled", true);  //jquery syntax
    });
}
function clearText(){
    $("#addresp").html("");
}

function addcandidate() {
    var form = $("#fileUploadForm")[0];
    var data = new FormData(form);
    var cid = $("#cid").val();
    var cname = $("#cname").val();
    var city = $("#city").val();
    var party = $("#party").val();
    var uid = $("#uid").val();
    data.append("cid", cid);
    data.append("uid", uid);
    data.append("cname", cname);
    data.append("party", party);
    data.append("city", city);
    $.ajax({
        type: "POST",
        enctype: "multiple/form-data",
        url: "AddNewCandidateControllerServlet",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
            str = data + "....";
            swal("Admin!", str, "success").then((value) => {
                showaddcandidateform();
            });
        },
        error: function (e) {
            swal("Admin", e, "error");
        }
    });
}
function showcandidate(){
	removecandidateForm();
    var newdiv = document.createElement("div");
    newdiv.setAttribute("id", "candidateform");
    newdiv.setAttribute("float", "left");
    newdiv.setAttribute("padding-left", "12px");
    newdiv.setAttribute("border", "solid 2px red");
    newdiv.innerHTML = "<h3>Show Candidate</h3>";
    newdiv.innerHTML = newdiv.innerHTML+"<div style='color:white; font-weight:bold'>Candidate Id</div><div><select id='cid'></select></div>";
    newdiv.innerHTML = newdiv.innerHTML+"<br><span id='addresp'></span>";
    
    var addcand = $("#result")[0];
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn("3500");
    date = {data: "cid"};
    $.post("ShowCandidateControllerServlet", date, function (responseText) {
        var candidatelist=JSON.parse(responseText);
		$("#cid").append(candidatelist.cid);
    });
	$("#cid").change(function(){
		var cid=$("#cid").val();
		if(cid===''){
			swal("No selection","Please select an id","error");
			return;
		}
		date = {data: cid};
    	$.post("ShowCandidateControllerServlet", date, function (responseText) {
			clearText();
        	var details=JSON.parse(responseText);
			$("#addresp").append(details.subdetails);
    	});
	});
}

function getdetails(e) {
    if (e.keyCode === 13) {
        data = {userid: $("#uid").val()};
        $.post("AddCandidateControllerServlet", data, function (responseText) {
            var details = JSON.parse(responseText);
            var city = details.city;
            var uname = details.username;
            if (uname == "wrong") {
                swal("Wrong Adhar No!", "Adhar no is invalid","error");
            } else {
                $("#city").empty();
                $("#city").append(city);
                $("#cname").val(uname);
                $("#cname").prop("disabled", true);
            }
        });
    }
}
function removecandidateForm(){
    var contdiv=document.getElementById("result");
    var formdiv=document.getElementById("candidateform");
    if(formdiv!=null){
        $("#candidateform").fadeOut("3500");
        contdiv.removeChild(formdiv);
    }
}

function electionresult(){
	data={data:"result"};
	$.post("ElectionResultControllerServlet",data,function(responseText){
		swal("Result fetched","Success","success");
		$("#result").html(responseText.trim());
	});
}

function deletecandidate(){
	removecandidateForm();
    var newdiv = document.createElement("div");
    newdiv.setAttribute("id", "candidateform");
    newdiv.setAttribute("float", "left");
    newdiv.setAttribute("padding-left", "12px");
    newdiv.setAttribute("border", "solid 2px red");
    newdiv.innerHTML = "<h3>Remove Candidate</h3>";
    newdiv.innerHTML = newdiv.innerHTML+"<div style='color:white; font-weight:bold'>Candidate Id</div><div><select id='cid'></select></div>";
    newdiv.innerHTML = newdiv.innerHTML+"<br><span id='addresp'></span>";
    
    var addcand = $("#result")[0];
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn("3500");
    date = {data: "cid"};
    $.post("ShowCandidateControllerServlet", date, function (responseText) {
        var candidatelist=JSON.parse(responseText);
		$("#cid").append(candidatelist.cid);
    });
	$("#cid").change(function(){
		var cid=$("#cid").val();
		if(cid===''){
			swal("No selection","Please select an id","error");
			return;
		}
		date = {data: cid};
    	$.post("ShowCandidateControllerServlet", date, function (responseText) {
			clearText();
        	var details=JSON.parse(responseText);
			$("#addresp").append(details.subdetails);
			$("#addresp").append(`<br><input type='button' value='Confirm' onclick="confirmdelete('${cid}')"/>`);

    	});
	});
}
function confirmdelete(cid){
	console.log("kundan");
	console.log(cid);
	data = {data: cid};
	console.log(data);
	$.post("RemoveCandidateControllerServlet",data,function(responseText){
		if(responseText.trim()==='success'){
			swal("Success!","Candidate Remove Successfully ", "success").then((value) => {
                deletecandidate();
            });
		}else if(responseText.trim()==='failed'){
			swal("Error","Error in deleting the candidate","error");
		}
	});
	/*
	$.ajax({
        type: "POST",
        url: "RemoveCandidateControllerServlet",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function () {
            swal("Success!","Candidate Remove Successfully ", "success").then((value) => {
                deletecandidate();
            });
        },
        error: function (e) {
            swal("Admin", e, "error");
        }
    });
	*/
}

function showupdatecandidateform(){
	removecandidateForm();
    var newdiv = document.createElement("div");
    newdiv.setAttribute("id", "candidateform");
    newdiv.setAttribute("float", "left");
    newdiv.setAttribute("padding-left", "12px");
    newdiv.setAttribute("border", "solid 2px red");
    newdiv.innerHTML = "<h3>Update Candidate</h3>";
    newdiv.innerHTML = newdiv.innerHTML+"<div style='color:white; font-weight:bold'>Candidate Id: </div><select id='cid'></select>";
    newdiv.innerHTML = newdiv.innerHTML+"<br><span id='addresp'></span>";
    
    var addcand = $("#result")[0];
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn("3500");
    date = {data: "cid"};
    $.post("ShowUpdateCandidateControllerServlet", date, function (responseText) {
        var candidatelist=JSON.parse(responseText);
		$("#cid").append(candidatelist.cid);
    });
	$("#cid").change(function(){
		var cid=$("#cid").val();
		if(cid===''){
			swal("No selection","Please select an id","error");
			return;
		}
		date = {data: cid};
    	$.post("ShowUpdateCandidateControllerServlet", date, function (responseText) {
			clearText();
        	var details=JSON.parse(responseText);
			$("#addresp").append(details.subdetails);
    	});
	});
}

function updatecandidate() {
    var form = $("#fileUploadForm")[0];
    var data = new FormData(form);
    var cid = $("#cid").val();
    var cname = $("#cname").val();
    var city = $("#city").val();
    var party = $("#party").val();
    var uid = $("#uid").val();
    data.append("cid", cid);
    data.append("uid", uid);
    data.append("cname", cname);
    data.append("party", party);
    data.append("city", city);
    $.ajax({
        type: "POST",
        enctype: "multiple/form-data",
        url: "UpdateCandidateControllerServlet",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
            str = data + "....";
            swal("Admin!", str, "success").then((value) => {
                showupdatecandidateform();
            });
        },
        error: function (e) {
            swal("Admin", e, "error");
        }
    });
}

function showuser(){
	console.log("In show User");
		$.post("ShowUserControllerServlet",null,function(responseText){
		swal("User Details fetched","Success","success").then((value) =>{
			$("#result").html(responseText.trim());
		});
	});
}

function removeuser(){
	removecandidateForm();
    var newdiv = document.createElement("div");
    newdiv.setAttribute("id", "candidateform");
    newdiv.setAttribute("float", "left");
    newdiv.setAttribute("padding-left", "12px");
    newdiv.setAttribute("border", "solid 2px red");
    newdiv.innerHTML = "<h3>Remove User</h3>";
    newdiv.innerHTML = newdiv.innerHTML+"<div style='color:white; font-weight:bold'>User Id</div><div><select id='uid'></select></div>";
    newdiv.innerHTML = newdiv.innerHTML+"<br><span id='addresp'></span>";

	var addcand = $("#result")[0];
    addcand.appendChild(newdiv);
    $("#candidateform").hide();
    $("#candidateform").fadeIn("3500");
    data = {data: "getId"};
	console.log('removeuser');
    $.post("ShowRemoveUserControllerServlet", data, function (responseText) {
        var userlist=JSON.parse(responseText);
		$("#uid").append(userlist.uid);
    });
	$("#uid").change(function(){
		var uid=$("#uid").val();
		console.log(uid);
		if(uid===''){
			swal("No selection","Please select an id","error");
			return;
		}
		data = {data: uid};
    	$.post("ShowRemoveUserControllerServlet", data, function (responseText) {
			clearText();
        	var details=JSON.parse(responseText);
			$("#addresp").append(details.userDetails);
			$("#addresp").append(`<br><input type='button' value='Confirm' onclick="confirmremove('${uid}')"/>`);

    	});
	});
}

function confirmremove(uid){
	data = {data: uid};
	console.log(data);
	$.post("RemoveUserControllerServlet",data,function(responseText){
		if(responseText.trim()==='success'){
			swal("Success!","Candidate Remove Successfully ", "success").then((value) => {
                removeuser();
            });
		}else if(responseText.trim()==='failed'){
			swal("Error","Error in deleting the candidate","error");
		}
	});
}

function partywiseresult(){
	data={data:"party"};
	$.post("ElectionResultControllerServlet",data,function(responseText){
		swal("Result fetched","Success","success");
		$("#result").html(responseText.trim());
	});
}

function genderpercentage(){
	data={data:"gender"};
	$.post("ElectionResultControllerServlet",data,function(responseText){
		swal("Result fetched","Success","success");
		$("#result").html(responseText.trim());
	});
}

