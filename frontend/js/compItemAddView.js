function uiImageUpload() {
    const sizeLimit = 10000000;

    imageData = $("#image-upload").prop('files')[0];
    imageSize = imageData.size;
    imageName = imageData.name;

    if(imageSize > sizeLimit) {
        alert("Images with size above 10MB are not currently supported");
        return;
    }

    $("#vehicle-image").val( imageName );
    
    var formData = new FormData();

    formData.append("file", imageData);

    uploadImage(formData);
}

function showHint(element) {
    var hint = "Sorry, no hint available";

    var attrName = $(element).attr("name");
    
    if(attrName == "vehicle-model-hint" || attrName == "vehicle-brand-hint") {
        hint = "This field is required\n";
    }
    else if(attrName == "vehicle-year-hint") {
        hint = "This field is required\n\n" +
        "On January 29 1886, Carl Benz applied for a patent for his vehicle powered by a gas engine.\n\n" +
        "The Lamborghini Lanzador concept has been unveiled in the US at 2023 and planned for future production.\n\n" +
        "(Min: 1886, Max: 2023)";
    }
    else if(attrName == "vehicle-mileage-hint") {
        hint = "On car history there has never been a car with negative mileage. \n\n" +
        "The highest mileage on record is Irv Gordon's 1966 Volvo P1800S, with 4828032 Khm \n\n" +
        "(Min: 0, Max: 4828032)";
    }
    else if(attrName == "vehicle-engine-cc-hint") {
        hint = "This field is required\n\n" +
        "With an engine capacity of 50cc the Peel P50 has the world record of production car with the smallest engine.\n\n" +
        "The biggest engine ever put in a production car is the Bugatti Veyron Super Sport, which features an 8.0-liter W16 engine.\n\n" +
        "(Min: 50, Max: 8000)";
    }
    else if(attrName == "vehicle-engine-hp-hint") {
        hint = "Renault Twizy 45 is an electric vehicle with a power output of 5,0 BHP.\n\n" +
        "Hennessey Venom F5 engine is confirmed to have a power output of 1.817,0 BHP.\n\n" +
        "(Min: 5, Max: 1817)";
    }

    alert(hint);
}

function updateColorSample() {

    $("#vehicle-color-sample").css("background", $("#vehicle-color").val());
}

function uiResetForm() {

    $("#vehicle-brand").val("Audi");
    $("#vehicle-model").val("");
    $("#vehicle-description").val("");
    $("#vehicle-image").val("");
    $("#vehicle-year").val("Audi");
    $("#vehicle-mileage").val("Audi");
    $("#vehicle-engine-cc").val("Audi");
    $("#vehicle-engine-hp").val("Audi");
    $("#vehicle-color").val("");
}

function validateBrand(brand = null) {
    if(brand == null) {
        brand = $("#vehicle-brand").val();
    }

    if(brand == "") {
        return false;
    }

    return true;
}

function validateModel(model = null) {
    if(model == null) {
        model = $("#vehicle-model").val();
    }
   
    modelTrimmed = model.trim()

    if(model == "" || modelTrimmed == "") {
        return false;
    }

    return true;
}

function validateYear(year = null) {
    if(year == null) {
        year = $("#vehicle-year").val();
    }

    if(year == "" ) {

        return false;
    }

    currentYear = new Date().getFullYear();

    if(year < 1886 || year > currentYear) {

        return false;
    }

    return true;
}

function validateMileage (mileage = null) {
    if(mileage == null) {
        mileage = $("#vehicle-mileage").val();
    }

    if(mileage == "") {
        return true;
    }

    if(mileage < 0 || mileage > 4828032) {
        
        return false;
    }

    return true;
}

function validateEngineCC (engineCC = null) {
    if(engineCC == null) {
        engineCC = $("#vehicle-engine-cc").val();
    }

    if(engineCC < 50 || engineCC > 8000) {

        return false;
    }

    return true;
}

function validateEngineHP (engineHP = null) {
    if(engineHP == null) {
        engineHP = $("#vehicle-engine-hp").val();
    }

    if(engineHP == "") {
        return true;
    }

    if(engineHP < 5 || engineHP > 1817) {

        return false;
    }

    return true;
}

function uiValidate(validationResult, selector, message = "") {

    if(validationResult) {

        $(selector).css("border-bottom", "1px Solid Gray");
        return true;
    }

    $(selector).css("border-bottom", "1px Solid Crimson");

    $("#info-message").append(message);

    return false;
}

function ValidateAll() {
    var validationResult = 0;

    $("#info-message").html("");

    validationResult += uiValidate( validateBrand(), "#vehicle-brand", "<div><i class='fa-solid fa-lightbulb'></i>Please provide valid vehicle brand information.</div>");
    validationResult += uiValidate( validateModel(), "#vehicle-model", "<div><i class='fa-solid fa-lightbulb'></i>Please provide valid vehicle model information.</div>");
    validationResult += uiValidate( validateYear(), "#vehicle-year", "<div><i class='fa-solid fa-lightbulb'></i>Please provide valid vehicle year information.</div>");
    validationResult += uiValidate( validateMileage(), "#vehicle-mileage", "<div><i class='fa-solid fa-lightbulb'></i>Please provide valid vehicle mileage information.</div>");
    validationResult += uiValidate( validateEngineCC(), "#vehicle-engine-cc", "<div><i class='fa-solid fa-lightbulb'></i>Please provide valid vehicle engine capacity information.</div>");
    validationResult += uiValidate( validateEngineHP(), "#vehicle-engine-hp", "<div><i class='fa-solid fa-lightbulb'></i>Please provide valid vehicle horsepower information.</div>");

    if(validationResult != 6) {
        $("#info-container").css("visibility", "visible");
        console.log("Add vehicle validation: failed");
        return false;
    }

    $("#info-container").css("visibility", "hidden");
    console.log("Vehicle validation: success");

    return true;
}

function createVehicleData() {

    var vehicleData = {
        "brand": $("#vehicle-brand").val(),
        "model": $("#vehicle-model").val(),
        "year": parseInt( $("#vehicle-year").val() ),
        "mileage": parseInt($("#vehicle-mileage").val() ),
        "color": $("#vehicle-color").val(),
        "engineCC": parseInt( $("#vehicle-engine-cc").val() ),
        "engineHP": parseInt( $("#vehicle-engine-hp").val() ),
        "description": $("#vehicle-description").val(),
        
        //We need to construct the absolute path that will be stored in the database 
        "imageURI": "http://localhost:8080/img/cars/" + $("#vehicle-image").val()
    }

    return JSON.stringify(vehicleData);
}

function addVehicleData() {

    if( ValidateAll() ) {

        vehicleData = createVehicleData();
        postVehicleData(vehicleData);
    }
}

function updateVehicleData() {

    if( ValidateAll() ) {

        vehicleId = parseInt( $("#vehicle-id").html() );
        vehicleData = createVehicleData();
        putVehicleData(vehicleId, vehicleData);
    }
}