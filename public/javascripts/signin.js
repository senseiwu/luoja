
function createHtml(data) {
    var html='';
      $.each(data, function (i, item) {
        html += '<tr><td>' + item.name + '</td><td>' + item.when + '</td><td>' + item.where + '</td></tr>';
      })
      return html
}

function httpCall(urljson, tag, createHtml) {
  console.log("http call: " + urljson)
  $.ajax(
    {
      type: "GET",
      url: urljson,
      data: "{}",
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      cache: false,
      success: function (data) {
        console.log("OK " + data)
        $(tag).append(createHtml(data))
      },
      error: function (msg) {
        alert(msg.responseText);
      }
     }
  )
}

function redirect()
{
  console.log("Redirect..")
  var url = "/register"
//  window.location.href = url
}

