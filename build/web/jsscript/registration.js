/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
let username, password, cpassword, city, address, adhar, email, mobile;

function addUser() {
    username = $("#username").val();   //username field se value utha ke username variable laarha h
    password = $("#password").val();
    cpassword = $("#cpassword").val();
    city = $("#city").val();
    address = $("#address").val();
    adhar = $("#adhar").val();
    email = $("#email").val();
    mobile = $("#mobile").val();
    gender =$("input[type='radio'][name='gender']:checked").val();

//to see koi variable blank to nahi hai 
    if (validateUser() === true) {
        if (password !== cpassword) {
            swal("Error", "Password do not match", "error");
            return;
        } else {
            if (checkEmail() === false)
                return;
            //we have created an object to send data (key:value) //servlet me jis naam se get kiya hai wahi use karne h 
            let data = {
                username: username,
                password: password,
                city: city,
                address: address,
                userid: adhar,
                email: email,
                mobile: mobile,
                gender: gender
            };
            //xhr is for handling error
            console.log(data);
            let xhr = $.post("Registrationcontrollerservlet", data, processResponse); //lecture 3 1:20:00
            //xhr.error(handleError);
            
            xhr.fail(handleError);
//            $.ajax({
//                type: "GET",
//                url: "Registrationcontrollerservlet",
//                data: data,
//                success: function (data) {
//                    let str = data.trim();
//                    if (str === "success") {
//                        swal("Success", "Registration done successfylly! you can now login", "success");
//                        setTimeout(redirectUser, 3000); //is function ko thodi der se chalana hai taki user upar msg ko padh paye
//                    } else if (str === "uap")
//                        swal("Error", "Sorry! the userid is already present", "error");
//                    else
//                        swal("Error", "Registration failed", "error"); //humare server end pr koi dikkat hai
//                },
//                error: function (e) {
//                    swal("Problem in server communicaion:", e, "error");
//                }
//            });
            
        }
    }
}

function processResponse(responseText, textStatus, xhr) {
    let str = responseText.trim();
    if (str === "success") {
        swal("Success", "Registration done successfylly! you can now login", "success");
        setTimeout(redirectUser, 3000); //is function ko thodi der se chalana hai taki user upar msg ko padh paye
    } else if (str === "uap")
        swal("Error", "Sorry! the userid is already present", "error");
    else
        swal("Error", "Registration failed", "error"); //humare server end pr koi dikkat hai 
}

function handleError(xhr) {
    swal("Error", "Problem in server communicaion:" + xhr.statusText, "error");
}

function validateUser() {
    if (username === "" || password === "" || cpassword === "" || city === "" || address === "" || adhar === "" || email === "" || mobile === "") {
        swal("Error", "All fields are mandatory", "error");
        return false;
    }
    return true;
}

function checkEmail() {
    let attheratepos = email.indexOf("@");
    let dotpos = email.indexOf(".");
    if (attheratepos < 1 || dotpos < attheratepos + 2 || dotpos + 2 >= email.length) {
        swal("Error!", "Plsease enter a valid email", "error");
        return false;
    }
    return true;
}


function redirectUser() {
    window.location = "login.html"; //is page pr redirect ho jayege agar error aai 
}


