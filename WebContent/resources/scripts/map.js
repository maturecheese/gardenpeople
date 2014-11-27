//Google Maps JavaScript API v3 licensed under the Creative Commons Attribution 3.0 License
//http://creativecommons.org/licenses/by/3.0/

 




	
function initialize() {
//to initialize a map we create a 'map options' object to contain map initialization variables.
//2 required options for mapOption is center and zoom.
//we want to center on a specific point so we pass coordinates to the LatLng object
  var mapOptions = {
    center: new google.maps.LatLng(51.508742,-0.120850), //these are the coordinates that center on london
    zoom: 13
	    //panControl: false,
    //zoomControl: false,

  };
  
  //this is the map object
  //the map class is the js class that represents a map, objects of this class define a single map on the html page, if i create more than 1 map object it will appear as a separate map
  //we also pass 'mapOptions' so that these options can initialize the maps properties
  var map = new google.maps.Map(document.getElementById('map-canvas'),
    mapOptions);

  var input = /** @type {HTMLInputElement} */(
      document.getElementById('pac-input')); //pac = place auto complete.

	  //test
	var options = {
	//restricts the search to a specific country i.e. UK
	//this restricts the autocomplete results to the uk only.
	componentRestrictions :{'country' : 'uk'}
	};	
	  

  //https://developers.google.com/maps/documentation/javascript/controls
  //The maps displayed through the Google Maps API contain UI elements to allow user interaction with the map.
//  These elements are known as controls and you can include variations of these controls in your Google Maps API application. 
//puts input box at top right
 // map.controls[google.maps.ControlPosition.TOP_RIGHT].push(input);

  // Create the autocomplete object, restricting the search to geographical location types.
  //Autocomplete creates a text input field on your web page, supplies predictions of places in a UI pick list, and returns place details in response to a getPlace() request. 
  //Each entry in the pick list corresponds to a single place (as defined by the Places API).
  var autocomplete = new google.maps.places.Autocomplete(input, options);
  //autocomplete.bindTo('bounds', map);

  //info window doesnt show anything
  var infowindow = new google.maps.InfoWindow();
  
  //The google.maps.Marker constructor takes a single Marker options object literal, specifying the initial properties of the marker
  //The following fields are particularly important and commonly set when constructing a marker:
  //•1. position (required) specifies a LatLng identifying the initial location of the marker.
//2. map (optional) specifies the Map on which to place the marker. If you do not specify the map on construction of the marker, the marker is created but is not attached to (or displayed on) the map. You may add the marker later by calling the marker's setMap() method.
	var marker = new google.maps.Marker({
    map: map,
    anchorPoint: new google.maps.Point(0, -29)
  });
//see if place has changed
 //Add listener to detect autocomplete selection
  google.maps.event.addListener(autocomplete, 'place_changed', function() {

    infowindow.close();
    marker.setVisible(false);
    var place = autocomplete.getPlace(); //getPlace returns placeResult
	document.getElementById('Latitude').value = place.geometry.location.lat(); //extract latitude to form
    document.getElementById('Longitude').value = place.geometry.location.lng(); //extract longitude to form
	
	
    if (!place.geometry) {
      return;
    }

    // If the place has a geometry, then present it on a map.
    if (place.geometry.viewport) {

      map.fitBounds(place.geometry.viewport); //viewport = the rectangular portion of the map displayed on the screen.
    } else {
      map.setCenter(place.geometry.location);
      map.setZoom(17);  // zoom in to location
    }
    marker.setIcon(/** @type {google.maps.Icon} */({
      url: place.icon,
      size: new google.maps.Size(71, 71),
      origin: new google.maps.Point(0, 0),
      anchor: new google.maps.Point(17, 34),
      scaledSize: new google.maps.Size(35, 35)
    }));
    marker.setPosition(place.geometry.location);
    marker.setVisible(true); //so we can see the marker

    var address = ''; //i will display this on the info window
	//address_components: The collection of address components for this Place's location. See the Geocoding service's Address Component Types section for more details.
	// There is also a types[] array in the address_components[], indicating the type of each part of the address.
	
	if (place.address_components) {
      address = [ 
        (place.address_components[0] && place.address_components[0].short_name || ''),
        //(place.address_components[1] && place.address_components[1].short_name || ''),//1 is country
		(place.address_components[5] && place.address_components[5].short_name || ''),//5 is postcode
        (place.address_components[2] && place.address_components[2].short_name || '')
      ].join(' ');
    }
	
	document.getElementById('Address').value = address; //extract address to form
	
//FILL IN THE FORM
		 for (var component in componentForm) {
    document.getElementById(component).value = '';
    document.getElementById(component).disabled = false;
  }

  // Get each component of the address from the place details
  // and fill the corresponding field on the form.
  for (var i = 0; i < place.address_components.length; i++) {
    var addressType = place.address_components[i].types[0];
    if (componentForm[addressType]) {
      var val = place.address_components[i][componentForm[addressType]];
      document.getElementById(addressType).value = val;
    }
  }
	// end of fill form
	var coord = autocomplete.getPlace().geometry.location; //coord is the latitude and longitude of the location so i can store it in DB
	var fullAddress = place.name + address; //might store this in database
	//info window will display address
    infowindow.setContent(address); //didnt show place.name because sometimes its the same as address

	//call the open() method on the InfoWindow, passing it the Map on which to open, and optionally, the Marker with which to anchor it.
    infowindow.open(map, marker);
  });


 
}


//form that will be filled in
var componentForm = {
  street_number: 'short_name',
  route: 'long_name',
  locality: 'long_name',
  administrative_area_level_1: 'short_name', //state
  country: 'long_name',
  postal_code: 'short_name'
  
};	


//load map


