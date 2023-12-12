function userUpdateValidate() {


    if( !isInputEmpty( getById("new-password") ) ) {
        
        if( !validatePassword( getById("new-password").value ) ) {
            alert(
                "Please provide as password with the following criteria\n \
                --At least 8 characters long\n \
                --Contains 1 Uppercase character\n \
                --Contains 1 Special character\n \
                --Contains 1 number \
            ");
            return false;
        }
    
        if( getById("new-password").value != getById("new-repassword").value ) {
            alert("The password provided does not match");
            return false;
        }

    }

    if( !isInputEmpty( getById("new-email") ) ) {

        if( !validateEmail( getById("new-email").value ) ) {
            alert("Please provide a valid e-mail");
            return false;
        }
    }

    return true;
}

function updateUser() {

    if( userUpdateValidate() ) {

        var newPassword = null;
        var newEmail = null;

        if( !isInputEmpty( getById("new-password") ) ) {

            newPassword = getById("new-password").value;
        }

        if( !isInputEmpty( getById("new-email") ) ) {

            newEmail = getById("new-email").value;
        }

        if(newPassword == null && newEmail == null) {
            alert("Please provide new username and/or new e-mail to make the corresponding changes");
            return;
        }

        userId = getUserIdCookie();

        var userData = {
            "id": userId,
            "username": null,
            "password": newPassword,
            "email": newEmail,
            "role": null
        }

        console.log(userData);

        putUserData( userId, JSON.stringify(userData) );
    }
}

function deleteUser() {

    if( confirm("Warning! This action can not be undone\n Are you sure you want to permantly delete the account ?") ) {
        
        deleteUserData( getUserIdCookie() );
    }
}