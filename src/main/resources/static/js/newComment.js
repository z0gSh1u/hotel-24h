/// <reference path="./jquery-3.3.1.min.js"/>

;(function () {
  $('#orderId').val(getQueryVariable('orderId') || '')
  $('#submitBtn').on('click', function () {
    console.log({
      orderId: $('#orderId').val(),
      comment: $('#commentContent').val(),
    })
    $.ajax({
      url: '/comment/add',
      method: 'POST',
      data: {
        orderId: $('#orderId').val(),
        comment: $('#commentContent').val(),
      },
      contentType: "application/x-www-form-urlencoded",
      dataType: "json",
      success: (data) => {
        console.log(data)
      },
      error: (error) => {
        console.log(error)
      },
    })
  })
})()
