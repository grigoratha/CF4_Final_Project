function registerValidate() {

    if(isInputEmpty( getById("username") ) ) {
        alert("Please provide a username");
        return false;
    }
    
    if(isInputEmpty( getById("email") ) ) {
        alert("Please provide a email");
        return;
    }

    if( !validateEmail( getById("email").value ) ) {
        alert("Please provide a valid e-mail");
        return false;
    }

    if(isInputEmpty( getById("password") ) ) {
        alert("Please provide a password");
        return false;
    }

    if( !validatePassword( getById("password").value ) ) {
        alert(
            "Please provide as password with the following criteria\n \
            --At least 8 characters long\n \
            --Contains 1 Uppercase character\n \
            --Contains 1 Special character\n \
            --Contains 1 number \
        ");
        return false;
    }

    if(isInputEmpty( getById("repassword") ) ) {
        alert("Please confirm the password");
        return false;
    }

    if( getById("password").value != getById("repassword").value ) {
        alert("The password provided does not match");
        return false;
    }

    return true;
}

function registerUser() {

    if( registerValidate() ) {

        var userData = {
            "username": getById("username").value,
            "password": getById("password").value,
            "email": getById("email").value,
            "role": getById("role").value
        }

        console.log(userData);

        postUserData( JSON.stringify(userData) );
    }
}