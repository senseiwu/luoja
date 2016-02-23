
$(document).ready(function(){
  jQuery.support.cors = true

  $("#sres").hide()

  $("#sin").click(function(e) {
    $("#sres").show()
    $("#myTabs a:last").tab('show')
  })

  $("#sin").focusout(function() {
    if($(this).val() == "") {
      $("#myTabs a:first").tab('show')
      $("#sres").hide()
    }
  })

  $('#myTabs a').click(function (e) {
    e.preventDefault()
    $(this).tab('show')
  })
})


function createHTMLEventTable(data) {
  var html = '<table class="table table-condensed table-striped volumes" id="upcoming">'
  html += '<tbody>'
  $.each(data, function (i, item) {
    html += '<tr><td>' + item.name + '</td><td>' + item.when + '</td><td>' + item.where + '</td></tr>';
  })
  html += '</tbody><table>'
  return html
}

function httpCall(urljson, tag, createHtml) {
  $.ajax(
    {
      type: "GET",
      url: urljson,
      data: "{}",
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      cache: false,
      success: function (data) {
        if(data.length == 0) $(tag).append('<h>No data</h>')
        else $(tag).append(createHtml(data))
      },
      error: function (msg) {
        alert(msg.responseText);
      }
     }
  )
}
