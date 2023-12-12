function showAll() {

    fetchVehicleAll();
}

function showFavorites() {

    fetchVehicleByIds();
}

function search() {

    searchInput = getById("search-input");
    searchInputValue = searchInput.value;

    if( isInputEmpty(searchInput) ) {
        
        alert("Search criteria has not been supplied");
        return;
    }

    var regex=/^[0-9]+$/;

    if( searchInputValue.match(regex) ) {

        fetchVehicleById(searchInputValue);
    }
    else {

        if(searchInputValue.length < 4) {
            
            alert("Please provide a vehicle name with at least 4 characters");
            return;
        }

        fetchVehicleByName(searchInputValue);
    }
}

function showSearchInfo() {

    alert("Search functionality supported critera:\n --Search with name (matches either Brand or Model)\n --Search with vehicle ID");
}

function showLoginForm() {
    $("#login").show();
}

function hideLoginForm() {
    $("#login").hide();
}

function showAddForm() { 

    loadItemAddViewComp();
}

function showUserForm() {
    loadUserEditViewComp();
}

function uiReflectSession(username, role) {

    //document.getElementById("login").style.display = "none";
    $("#login").fadeOut();

    $("#current-user").text(username);
    $("#current-role").text(role);

    if(role == "user") {
        $("#btn-add").hide();
    }
    else {
        $("#btn-add").show();
    }
}

function documentReload() {

    checkSession( uiReflectSession, "all");
}

function login() {
    
    username = $("#username").val().trim();
    password = $("#password").val().trim();

    if(username == "") {
        alert("Please provide a username");
        return;
    }

    if(password == "") {
        alert("Please provide a password");
        return;
    }

    userAuthenticate(username, password);

    $("#username").val("");
    $("#password").val("");
}

function checkSession(callback, role = "all", message = "") {

    if( !cookieExists("token") ) {

        console.log("No session saved for current user, login is needed");

        $("#login").show();
        return false;
    }

    fetchSession( getSessionCookie(), callback, role, message);
    return true;
}

function logout() {
    userId = getUserIdCookie();

    userTerminate(userId);
}


