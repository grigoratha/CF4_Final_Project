function clearApp() {

    $("#app").html("");
    $("#search-count").html("");
}

function addComponent(componentName, parentName = "#app", flushParent = true) {

    if(flushParent) {

        clearApp();
    }

    $.get(componentName, function(component) {

        $(parentName).append(component);
    });
}

function loadDefaultComp() {

    $("#search-input").val("");

    addComponent("compDefaultView.html");
}

function loadErrorComp(message="", parentName = "#app") {

    clearApp();

    $.get("compErrorView.html", function(component) {

        $(parentName).append(component).append("<span>" + message + "</span>");
    });
}

function loadLoaderComp() {
    addComponent("compLoaderView.html");
}

function loadUserEditViewComp() {
    addComponent("compUserEditView.html");
}

function loadItemAddViewComp() {

    clearApp();

    $.get("compItemAddView.html", function(component) {

        $("#app").append(component);

        $("#btn-update").hide();
    });
}

function loadItemViewComp(json, parentName = "#app") {

    if(json == null) {

        loadErrorComp("The requested data is currently unavailable");
        return;
    }

    if( !Array.isArray(json) ) {
        //This converts a single JSON object
        //to a single element array as this view
        //is expected to handle an array of JSON objects
        //and its logic will fail with a single JSON object
        json = [json];
    }

    itemCount = Object.keys(json).length;

    if(itemCount == 0) {
        alert("Currently no information is available");
        return;
    }

    clearApp();

    if(itemCount == 1) {

        $("#search-count").append(itemCount + " vechicle found");
    }
    else {
        $("#search-count").append(itemCount + " vechicles found");   
    }

    $("#search-count").append("<i class='fa-solid fa-car'></i>");

    $(parentName).append("<div id='itemGridView' class='item-grid-view'></div>");

    console.log("Results: " + itemCount);

    $.get("compItemView.html", function(component) {

        for(i=0; i<itemCount; i++) {

            var item = $(component).clone(); 

            var itemTitle = json[i]['brand'] + " " + json[i]['model'];

            var itemImageURI = "";

            if( (json[i]['imageURI']) == "" ) {
    
                itemImageURI = "http://localhost:8080/img/cars/default.png";
            }
            else {

                itemImageURI = (json[i]['imageURI']);
            }

            //Container
            item.attr("id", "item-n" + json[i]['id']);
            //Brand
            item.find("[name='data-brand']").text(json[i]['brand']);
            //Model
            item.find("[name='data-model']").text(json[i]['model']);
            //Title
            item.find("[name='data-title']").text(itemTitle);
            //ID
            item.find("[name='data-id']").text(json[i]['id']);
            //Image
            item.find("[name='data-img']").attr("src", itemImageURI); 
            //Description
            item.find("[name='data-description']").text(json[i]['description']);
            //Spec 1
            item.find("[name='data-spec-1']").text(json[i]['year']);
            //Spec 2
            item.find("[name='data-spec-2']").text(json[i]['mileage'] + " Khm");
            //Spec 3
            item.find("[name='data-spec-3']").text(json[i]['engineCC'] + " CC");
            //Spec 4
            item.find("[name='data-spec-4']").text(json[i]['engineHP'] + "  HP");
            //Spec 5
            item.find("[name='data-spec-5']").text(json[i]['color']);

            //Edit
            editElement = item.find("[name='data-action-edit']");
            editElement.attr("data-id", json[i]['id']);
            editElement.off("click");
            editElement.on("click", function() {

                //bind is used to retain reference to the initial caller
                checkSession(editItem.bind(this), "admin", "Sorry your user role does not allow editing or deleting items");
            });

            //Delete
            deleteElement = item.find("[name='data-action-delete']");
            deleteElement.attr("data-id", json[i]['id']);
            deleteElement.off("click");
            deleteElement.on("click", function() {

                //bind is used to retain reference to the initial caller
                checkSession(deleteItem.bind(this), "admin", "Sorry your user role does not allow editing or deleting items");
            });

            //Favorite
            isFavorite = false;

            favoritesArray = JSON.parse( getFavoritesCookie() );

            favoriteElement = item.find("[name='data-favorite']");
            favoriteElement.attr("data-id", json[i]['id']);
            favoriteElement.off("click");

            for (let j = 0; j < favoritesArray.length; j++) {

                if (favoritesArray[j] === json[i]['id']) {

                    isFavorite = true;
                    break;
                }
            }

            if(isFavorite) {

                favoriteElement.css("color", "Crimson");
                favoriteElement.attr("data-action", "remove");
                favoriteElement.on('click', function() {

                    //bind is used to retain reference to the initial caller
                    checkSession(deleteFavoriteItem.bind(this), "all", "Sorry your user session is invalid");
                })
            }
            else {

                favoriteElement.on('click', function() {

                    //bind is used to retain reference to the initial caller
                    checkSession(addFavoriteItem.bind(this), "all", "Sorry your user session is invalid");
                })
            }

            console.log("[" + i + "]: " + itemTitle + " (Favorite: " + isFavorite + ")");
            $("#itemGridView").append(item);
        }

        checkSession( hideControls, "user");
    });
}

function hideControls() {

    $(".item-controls").css("display", "none");
}

function editItem() {
    vehicleId = $(this).attr('data-id');

    item =  $("div#item-n" + vehicleId);

    var vehicleData = {
        "id": vehicleId,
        "brand": item.find("[name='data-brand']").text(),
        "model": item.find("[name='data-model']").text(),
        "year": parseInt( item.find("[name='data-spec-1']").text() ),
        "mileage": parseInt( item.find("[name='data-spec-2']").text() ),
        "color": item.find("[name='data-spec-5']").text(),
        "engineCC": parseInt( item.find("[name='data-spec-3']").text() ),
        "engineHP": parseInt( item.find("[name='data-spec-4']").text() ),
        "description": item.find("[name='data-description']").text(),
        "imageURI": item.find("[name='data-img']").attr("src")
    }

    console.log("Vehicle information to edit:");
    console.log(vehicleData);
    
    //Remove path from imageURI string
    vehicleData.imageURI = vehicleData.imageURI.replace("http://localhost:8080/img/cars/", "");


    clearApp();

    $("#app").load("compItemAddView.html", function() {

        $("#app").ready(function () {

            $("#vehicle-id").html(vehicleId);
            $("#vehicle-brand").val(vehicleData.brand).change();
            $("#vehicle-model").val(vehicleData.model);
            $("#vehicle-description").val(vehicleData.description);
            $("#vehicle-image").val(vehicleData.imageURI);
            $("#vehicle-year").val(vehicleData.year);
            $("#vehicle-mileage").val(vehicleData.mileage);
            $("#vehicle-engine-cc").val(vehicleData.engineCC);
            $("#vehicle-engine-hp").val(vehicleData.engineHP);
            $("#vehicle-color").val(vehicleData.color).change();

            updateColorSample();

            $("#btn-save").hide();
            $("#btn-update").show();
        });
        
    });
}

function deleteItem() {
    vehicleId = $(this).attr('data-id');

    if( window.confirm("Are you sure you want to delete the vehicle with ID: " + vehicleId + " ?") ) {

        deleteVehicleData(vehicleId);
    }
}

function addFavoriteItem() {
    favoriteElement = $(this);

    vehicleId = favoriteElement.attr('data-id');

    userId = getUserIdCookie();

    if( confirm("Are you sure you want to add this vehicle to your favorites ?") ) {

        putUserFavorite(userId, vehicleId, favoriteElement);
    }
}

function deleteFavoriteItem() {
    favoriteElement = $(this);

    vehicleId = favoriteElement.attr('data-id');

    userId = getUserIdCookie();

    if( confirm("Are you sure you want to remove this vehicle from your favorites ?") ) {
       
        deleteUserFavorite(userId, vehicleId, favoriteElement);
    }
}