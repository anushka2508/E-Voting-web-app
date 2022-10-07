 let userid,password;
function connectUser(){
    userid =$("#username").val();
    password=$("#password").val();
    if(validateUser() === true){
        let data = {
            password:password,
            userid:userid
        };
        console.log(data);
        let xhr = $.post("LoginControllerServlet",data,processResponse);
        xhr.fail(handleError);
    }
    
}

function validateUser() {
    if (userid === "" || password === "" ) {
        swal("Access Denied", "Please enter userid/password", "error");
        return false;
    }
    return true;
}

function handleError(xhr) {
    swal("Error", "Problem in server communicaion:" + xhr.statusText, "error");
}

function processResponse(responseText, textStatus, xhr){
    let str=responseText.trim();
    if(str==="error"){
        swal("Access Denied!","please Enter valid userid/password","error");
    }else if(responseText.trim().indexOf("jsessionid")!==-1){
        swal("Success","Login Successfully","success").then((value)=>{
            window.location=responseText.trim();
        });
    }else{
        swal("Access Denied","Some Problem occured:"+responseText,"error");
    }
}

