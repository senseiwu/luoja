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
