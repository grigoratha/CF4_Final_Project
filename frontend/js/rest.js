//Car REST APIs
function fetchVehicleAll() {

    $.ajax({
        url: "http://localhost:8080/api/car/all",
        type: 'GET',
        success: function(json, status, xhr) {
            console.log("http://localhost:8080/api/car/all: " + xhr.status + " HTTP");
            
            loadItemViewComp(json);
        },
        error: function(json, status, xhr) {
            console.log("http://localhost:8080/api/car/all: " + json.status + " HTTP");

            if(json.status == 404) {
                alert("Sorry no vehicle information found");
            }

            if(json.status == 500) {
                alert("Sorry there was an error with the service. Please try later");
            }
        }
    });
}

function fetchVehicleById(vehicleId) {

    $.ajax({
        url: "http://localhost:8080/api/car/find/id/" + vehicleId,
        type: 'GET',
        success: function(json, status, xhr) {
            console.log("http://localhost:8080/api/car/find/id/" + vehicleId + ": " + xhr.status + " HTTP");
            
            loadItemViewComp(json);

        },
        error: function(json, status, xhr) {
            console.log("http://localhost:8080/api/car/find/id/" + vehicleId + " :" + json.status + " HTTP");

            if(json.status == 404) {
                alert("Sorry no vehicle information found with the specified ID");
            }

            if(json.status == 500) {
                alert("Sorry there was an error with the service. Please try later");
            }
        }
    });
} 

function fetchVehicleByIds() {

    favoritesData = getFavoritesCookie();

    console.log(favoritesData);

    $.ajax({
        url: "http://localhost:8080/api/car/find/list",
        type: 'POST',
        data: favoritesData,
        contentType: 'application/json',
        success: function(json, status, xhr) {
            console.log("http://localhost:8080/api/car/find/list: " + xhr.status + " HTTP");
            
            loadItemViewComp(json);

        },
        error: function(json, status, xhr) {
            console.log("http://localhost:8080/api/car/find/list :" + json.status + " HTTP");

            if(json.status == 404) {
                alert("Sorry no vehicle information found with the specified IDs");
            }

            if(json.status == 500) {
                alert("Sorry there was an error with the service. Please try later");
            }
        }
    });
}

function fetchVehicleByName(vehicleName) {

    $.ajax({
        url: "http://localhost:8080/api/car/find/name/" + vehicleName,
        type: 'GET',
        success: function(json, status, xhr) {
            console.log("http://localhost:8080/api/car/find/name/" + vehicleName + " :" + xhr.status + " HTTP");
            loadItemViewComp(json);
        },
        error: function(json, status, xhr) {
            console.log("http://localhost:8080/api/car/find/name/" + vehicleName + " :" + json.status + " HTTP");

            if(json.status == 404) {
                alert("Sorry no vehicle information found with the specified name");
            }

            if(json.status == 500) {
                alert("Sorry there was an error with the service. Please try later");
            }
        }
    });
}

function postVehicleData(vehicleData) {

    $.ajax({
        url: "http://localhost:8080/api/car/add",
        type: 'POST',
        data: vehicleData,
        contentType: 'application/json',
        success: function (json, status, xhr) { 
            console.log("http://localhost:8080/api/car/add: " + xhr.status + " HTTP");
            alert("Vehicle information successfully added.");
            uiResetForm();
        },
        error: function (json, status, xhr) { 
            console.log("http://localhost:8080/api/car/add: " + json.status + " HTTP");

            if(json.status == 406) {
                alert("Sorry the specified vehicle already exists");
            }

            if(json.status == 500) {
                alert("Sorry there was an error with the service. Please try later");
            }
        }
    });
}

function putVehicleData(vehicleId, vehicleData) {
    
    $.ajax({
        url: "http://localhost:8080/api/car/update/" + vehicleId,
        type: 'PUT',
        data: vehicleData,
        contentType: 'application/json',
        success: function (json, status, xhr) { 
            console.log("http://localhost:8080/api/car/update/" + vehicleId + ": " + xhr.status + " HTTP");

            alert("The vehicle information successfully updated");
        },
        error: function (json, status, xhr) { 
            console.log("http://localhost:8080/api/car/update/" + vehicleId + ": " + json.status + " HTTP");

            if(json.status == 404) {
                alert("Sorry no vehicle information found with the specified ID to be edited");
            }

            if(json.status == 500) {
                alert("Sorry there was an error with the service. Please try later");
            }
        }
    });
}

function deleteVehicleData(vehicleId) {

    $.ajax({
        url: "http://localhost:8080/api/car/delete/" + vehicleId,
        type: 'DELETE',
        success: function (json, status, xhr) { 
             console.log("http://localhost:8080/api/car/delete: " + xhr.status + " HTTP");
             
             alert("Vehicle with ID: " + vehicleId + " successfully deleted");
             
            //This hides the element so we do not have to refresh the view
             //Due to the grid display property the items will reposition properly
             $("div#item-n" + vehicleId).hide();
        },
        error: function (json, status, xhr) { 
            console.log("http://localhost:8080/api/car/delete: " + json.status + " HTTP");

            if(json.status == 404) {
                alert("Sorry no vehicle information found with the specified ID to be deleted");
            }

            if(json.status == 500) {
                alert("Sorry there was an error with the service. Please try later");
            }
        }
    });
}

//Image Upload Rest APIs
function uploadImage(formData) {
    
    $.ajax({
        url: 'http://localhost:8080/api/upload',
        type: 'POST',
        data: formData,
        contentType: false,
        processData: false,
        success: function (data) {
            console.log("http://localhost:8080/api/car/update/: " + data.status + " HTTP");
            
            alert("Image successfully uploaded");
        },
        error: function (error) {
            console.log("http://localhost:8080/api/car/update/: " + error.status + " HTTP");
            
            alert("Sorry there was an error with the service. Please try later");
        }
    });
}

//User REST APIs
function postUserData(userData) {

    $.ajax({
        url: "http://localhost:8080/api/user/add",
        type: 'POST',
        data: userData,
        contentType: 'application/json',
        success: function (json, status, xhr) { 
            console.log("http://localhost:8080/api/user/add: " + xhr.status + " HTTP");
            alert("Thank you for registering, please proceed to login.");

            window.location.href = "http://localhost:3000";
        },
        error: function (json, status, xhr) { 
            console.log("http://localhost:8080/api/user/add: " + json.status + " HTTP");

            if(json.status == 406) {
                alert("Sorry the specified user already exists");
            }

            if(json.status == 500) {
                alert("Sorry there was an error with the service. Please try later");
            }
        }
    });
}

function putUserData(userId, userData) {
    
    $.ajax({
        url: "http://localhost:8080/api/user/update/" + userId,
        type: 'PUT',
        data: userData,
        contentType: 'application/json',
        success: function (json, status, xhr) { 
            console.log("http://localhost:8080/api/user/update/" + userId + ": " + xhr.status + " HTTP");

            alert("The user information successfully updated");
        },
        error: function (json, status, xhr) { 
            console.log("http://localhost:8080/api/user/update/" + userId + ": " + json.status + " HTTP");

            if(json.status == 404) {
                alert("Sorry no user information found with the specified ID to be edited");
            }

            if(json.status == 500) {
                alert("Sorry there was an error with the service. Please try later");
            }
        }
    });
}

function deleteUserData(userId) {

    $.ajax({
        url: "http://localhost:8080/api/user/delete/" + userId,
        type: 'DELETE',
        success: function (json, status, xhr) { 
             console.log("http://localhost:8080/api/user/delete: " + xhr.status + " HTTP");
             
             alert("User with ID: " + userId + " successfully deleted");
             
            //This hides the element so we do not have to refresh the view
             //Due to the grid display property the items will reposition properly
             userTerminate(userId);
        },
        error: function (json, status, xhr) { 
            console.log("http://localhost:8080/api/user/delete: " + json.status + " HTTP");

            if(json.status == 404) {
                alert("Sorry no user information found with the specified ID to be deleted");
            }

            if(json.status == 500) {
                alert("Sorry there was an error with the service. Please try later");
            }
        }
    });
}

//User Favorites REST APIs
function fetchUserFavorites(userId) {

    $.ajax({
        url: "http://localhost:8080/api/favorites/find/id/" + userId,
        type: 'GET',
        success: function(json, status, xhr) {
            console.log("http://localhost:8080/api/favorites/find/id/" + userId + ": " + xhr.status + " HTTP");
            
            console.log("User favorites: " + json.itemList);
            
            setFavoritesCookie(json.itemList);

        },
        error: function(json, status, xhr) {
            console.log("http://localhost:8080/api/favorites/find/id/" + userId + " :" + json.status + " HTTP");

            if(json.status == 404) {
                alert("Sorry no favorites information found with the specified ID");
            }

            if(json.status == 500) {
                alert("Sorry there was an error with the service. Please try later");
            }
        }
    });
} 

function putUserFavorite(userId, vehicleId, favoriteElement) {
    
    $.ajax({
        url: "http://localhost:8080/api/favorites/add/" + userId + "/" + vehicleId,
        type: 'GET',
        success: function (json, status, xhr) { 
            console.log("http://localhost:8080/api/favorites/add/" + userId + "/" + vehicleId + ": " + xhr.status + " HTTP");

            addToFavoritesCookie(vehicleId);

            favoriteElement.css("color", "crimson");

            //This attribute is only used for inspecting purposes
            //and no logic is based on its value
            favoriteElement.attr("data-action", "remove");

            //We must also change the click event to the appropriate action
            favoriteElement.off("click");
            favoriteElement.on("click", function() {
                checkSession(deleteFavoriteItem.bind(this), "all", "Sorry your user session is invalid");
            })

            alert("Vehicle added to favorites");
        },
        error: function (json, status, xhr) { 
            console.log("http://localhost:8080/api/favorites/add/" + userId + "/" + vehicleId + ": " + json.status + " HTTP");

            if(json.status == 404) {
                alert("Sorry no user information found with the specified ID, can not add to favorites");
            }

            if(json.status == 500) {
                alert("Sorry there was an error with the service. Please try later");
            }
        }
    });
}

function deleteUserFavorite(userId, vehicleId, favoriteElement) {

    $.ajax({
        url: "http://localhost:8080/api/favorites/delete/" + userId + "/" + vehicleId,
        type: 'DELETE',
        success: function (json, status, xhr) { 
            console.log("http://localhost:8080/api/favorites/delete/" + userId + "/" + vehicleId + ": " + xhr.status + " HTTP");
             
            removeFromFavoritesCookie(vehicleId);
            
            favoriteElement.css("color", "#464646");

            //This attribute is only used for inspecting purposes
            //and no logic is based on its value
            favoriteElement.attr("data-action", "add");

            //We must also change the click event to the appropriate action
            favoriteElement.off("click");
            favoriteElement.on("click", function() {
                checkSession(addFavoriteItem.bind(this), "all", "Sorry your user session is invalid");
            })

            alert("Vehicle removed from favorites");
        },
        error: function (json, status, xhr) { 
            console.log("http://localhost:8080/api/favorites/delete/" + userId + "/" + vehicleId + ": " + json.status + " HTTP");

            if(json.status == 404) {
                alert("Sorry no user information found with the specified ID, can not remove from favorites");
            }

            if(json.status == 500) {
                alert("Sorry there was an error with the service. Please try later");
            }
        }
    });
}

//User Authentication REST APIs
function userAuthenticate(username, password) {

    $.ajax({
        url: 'http://localhost:8080/api/auth/',
        type: 'POST',
        data: JSON.stringify({
            "username":username,
            "password":password
        }),
        contentType: 'application/json',
        success: function (data, status, xhr) {
            console.log("http://localhost:8080/api/auth/: " + xhr.status + " HTTP");

            if(data == "json error") {
                alert("Sorry there was an error with the service");
            }

            if(data == "user not found") {
                alert("The user specified does not exist.");
                return;
            }

            if(data == "authentication failed") {
                alert("Authentication failed.");
                return;
            }

            console.log("User authentication successfull: " + data);

            json = JSON.parse(data);

            setUserIdCookie(json.id);
            setUsernameCookie(username);
            setSessionCookie(json.token);
            setRoleCookie(json.role);

            //We fetch user favorites 
            fetchUserFavorites(json.id);

            uiReflectSession(username, json.role);
        },
        error: function (xhr, status, error) {
            console.log("http://localhost:8080/api/auth/: " + xhr.status + " HTTP");
            
            alert("Sorry there was an error with the service. Please try later");
        }
    });
}

function userTerminate(userId) {

    $.ajax({
        url: "http://localhost:8080/api/session/delete/" + userId,
        type: 'DELETE',
        success: function (json, status, xhr) { 
             console.log("http://localhost:8080/api/session/delete: " + xhr.status + " HTTP");

             deleteUserIdCookie();
             deleteUsernameCookie();
             deleteSessionCookie();
             deleteRoleCookie();
             deleteFavoritesCookie();

             loadDefaultComp();
             showLoginForm();
              
        },
        error: function (json, status, xhr) { 
            console.log("http://localhost:8080/api/session/delete: " + json.status + " HTTP");

            if(json.status == 404) {
                //This should never happen but if the user ID no longer exists
                //and there is an active session for this user we will redirect
                //them to login, no need to show an error
                console.log("User ID does not exists for the active session");
            }

            if(json.status == 500) {
                alert("Sorry there was an error with the service. Please try later");
            }

            //If an error occurs the session will be retained in the database.
            //We redirect the user to the login form to either login 
            //as another user or refresh the current session
            loadDefaultComp();
            showLoginForm();
        }
    });
}

function fetchSession(sessionToken, callback, role, message) {

    $.ajax({
        url: "http://localhost:8080/api/session/find/token/" + sessionToken,
        type: 'GET',
        success: function(json, status, xhr) {
            console.log("http://localhost:8080/api/session/find/token/" + sessionToken + " :" + xhr.status + " HTTP");

            console.log("User role: [" + json.role + "] invoked action: [" + callback.name + "] which is allowed for role: [" + role + "]");

            if( role == "all" || role == json.role) {

                //The logic is to check against the database if
                //the session is valid and based on user roles allow
                //or not the callback action to excute
                console.log("Authorized action allowed");

                if( callback.name == "uiReflectSession") {
                    
                    //This is the only callback function that needs parameters
                    callback(json.username, json.role);
                }
                else {
                    callback();
                }
            }    
            else {

                //This should never happen as the UI elements are shown/hidden 
                //according to user role. Also its value is checked against the database
                //and not the cookie set at the login proccess.
                console.log("Unauthorized action stopped");

                if(message != "") {

                    alert(message);
                }
                
            }        
        },
        error: function(json, status, xhr) {
            console.log("http://localhost:8080/api/session/find/token/" + sessionToken + " :" + json.status + " HTTP");

            if(json.status == 404) {

                console.log("Session: " + sessionToken + " not found");
                showLoginForm();
            }

            if(json.status == 500) {

                alert("Sorry there was an error with the service. Please try later");
            }
        }
    });

}

