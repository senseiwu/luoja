// $(document).ready(function(){
//   mapboxgl.accessToken = 'pk.eyJ1Ijoia296bG93c3QiLCJhIjoiY2lzeGVlNTFnMDAyeTJ0cGR2N3czaXN3ciJ9.fmbuFc5B24xKGiiLJ7mrpA';
//   var map = new mapboxgl.Map({
//     container: 'map',
//     style: 'mapbox://styles/mapbox/streets-v9'
//   });
// })

function vomit(input) {
  console.log("vomit!");
  if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function (e) {
      $('#blah').attr('src', e.target.result);
    }
    reader.readAsDataURL(input.files[0]);
  }
}

function updatePicture(input, id) {
  console.log("update picture " + input + ", " + id);
  if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function (e) {
      $("#"+id).attr('src', e.target.result);
    }
    reader.readAsDataURL(input.files[0]);
  }
}

function triggerInputAction(id) {
  console.log("trigger input action " + id)
  $('#'+id).click();
}

function showDiv(checkbox, id) {
  if (checkbox.checked) {
    $("#"+id).show
  } else {
    $("#"+id).hide
  }
}
