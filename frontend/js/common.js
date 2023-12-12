
function getById(elementId) {
    return document.getElementById(elementId);
}

function getByClassName(className) {
    return document.getElementsByClassName(className);
}

function isInputEmpty(inputElem) {

    if(inputElem == null || inputElem.value == "") {

        return true;
    }

    return false;
}

function writeSpan(content) {
    return "<span>" + content + "</span>";
}

function writeDiv(content) {
    return "<div>" + content + "</div>";
}

function addOptions(selectName, contentName) {

    $.get(contentName, function(content) {

        $(selectName).append(content);
    });
}

function setUserIdCookie(userId) {
    document.cookie = "id=" + userId + "; path=/; SameSite=Strict";
}

function setSessionCookie(token) {
    document.cookie = "token=" + token + "; path=/; SameSite=Strict";
}

function setUsernameCookie(username) {
    document.cookie = "username=" + username + "; path=/; SameSite=Strict";
}

function setRoleCookie(role) {
    document.cookie = "role=" + role + "; path=/; SameSite=Strict";
}

function setFavoritesCookie(favorites) {
    document.cookie = "favorites=" + favorites + "; path=/; SameSite=Strict";
}

function addToFavoritesCookie(itemId) {
    favoritesArray = JSON.parse( getFavoritesCookie() );

    const index = favoritesArray.indexOf(itemId);

    if(index == -1) {
        favoritesArray.push( parseInt(itemId) );
        setFavoritesCookie( JSON.stringify(favoritesArray) );
        return true;
    }

    return false;
}

function removeFromFavoritesCookie(itemId) {
    favoritesArray = JSON.parse( getFavoritesCookie() );

    const index = favoritesArray.indexOf( parseInt(itemId) );

    if(index > -1) {
        //Remove from array
        favoritesArray.splice(index, 1);
        setFavoritesCookie( JSON.stringify(favoritesArray) );
        return true;
    }

    return false;
}
 
function getCookie(cookieName) {
    var cookies = document.cookie.split(';');

    for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i].trim();

        if (cookie.startsWith(cookieName + '=')) {

            return cookie.substring(cookieName.length + 1);
        }
    }

    return null;
}

function getUserIdCookie() {
    return getCookie("id");
}

function getSessionCookie() {
    return getCookie("token");
}

function getUsernameCookie() {
    return getCookie("username");
}

function getRoleCookie() {
    return getCookie("role");
}

function getFavoritesCookie() {
    return getCookie("favorites");
}

function deleteCookie(cookieName) {
    document.cookie = cookieName + "=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
}

function deleteUserIdCookie() {
    return deleteCookie("id");
}

function deleteSessionCookie() {
    return deleteCookie("token");
}

function deleteUsernameCookie() {
    return deleteCookie("username");
}

function deleteRoleCookie() {
    return deleteCookie("role");
}

function deleteFavoritesCookie() {
    return deleteCookie("favorites");
}

function cookieExists(cookieName) {

    if( getCookie(cookieName) == null ) {

        return false;
    }

    return true;
}

const validateEmail = (email) => {
    return email.match(
      /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    );
};

const validatePassword = (password) => {
    return password.match(
        /^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(.{8,})$/
    );
};
