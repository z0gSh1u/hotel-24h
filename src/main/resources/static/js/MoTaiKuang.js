function processNewRoom() {
  const roomId = $('#roomNoInput').val()

  $.ajax()
}

$('#newRoomModal').on('shown.bs.modal', function (event) {
  console.log(event)
  var modal = $(this)

  const roomTypes = ['ROMA', 'ROMB']
  roomTypes.forEach((v) => {
    console.log(modal.find('#roomTypeSelect'))
    modal.find('#roomTypeSelect').append(`<option value="${v}">${v}</option>`)
  })
})
