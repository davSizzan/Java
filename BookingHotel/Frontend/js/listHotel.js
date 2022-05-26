let cityNameSlug = location.search.split("?")[1].replace(/%20/g, " ");

window.onload = OnLoad = async () => {
  var idCard;
  var uname = localStorage.getItem("USERNAME");
  var token = localStorage.getItem("TOKEN");

  if (token !== "null") {
    $("#logOutBtn").removeClass("hide");
    let getIdCard = await fetch(
      `http://localhost:8686/UTCDemo/guests/guestsByUserName?userName=${uname}`,
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer=${token}`,
        },
      }
    )
      .then((res) => res.json())
      .then((data) => data);
    idCard = await getIdCard.idCard;

    // get list rooms booked
    let getListRoomBooked = await fetch(
      `http://localhost:8686/UTCDemo/roomBook/byIdCard?idCard=${idCard}`,
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer=${token}`,
        },
      }
    )
      .then((res) => res.json())
      .then((data) => data);

    getListRoomBooked.forEach((item) => {
      $("#bodyTable").append(`
      <tr>
      <th>${item.roomHotelName}</th>
      <td>${item.bookingCheckIn}</td>
      <td>${item.bookingCheckOut}</td>
      <td>${item.roomNumber}</td>
            </tr>
      `);
    });
  } else {
    $("#logInBtn").removeClass("hide");
  }

  const getListHotel = await fetch(
    `http://localhost:8686/UTCDemo/hotel/getByCity?city=${cityNameSlug}`
  )
    .then((res) => res.json())
    .then((data) => data.content);
  $(`#location`).append(cityNameSlug);
  getListHotel.map((item, index) => {
    let hotelNameSlug = item.name
      .normalize("NFD")
      .replace(/[\u0300-\u036f]/g, "");
    $(`#listHotel`).append(`
    <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
                <div class="room-item shadow rounded overflow-hidden">
                  <div class="position-relative">
                    <img class="img-fluid" src="img/room-${
                      Math.floor(Math.random() * 10) + 1
                    }.jpg" alt="">
                  </div>
                  <div class="p-4 mt-2">
                    <div class="d-flex justify-content-between mb-3">
                      <h5 class="mb-0">${item.name}</h5>
                      <div class="ps-2">
                        <small class="fa fa-star text-primary"></small>
                        <small class="fa fa-star text-primary"></small>
                        <small class="fa fa-star text-primary"></small>
                        <small class="fa fa-star text-primary"></small>
                        <small class="fa fa-star text-primary"></small>
                      </div>
                    </div>
                    <div class="d-flex mb-3">
                      <small class="border-end me-3 pe-3"><i class="fa fa-bed text-primary me-2"></i>3 Bed</small>
                      <small class="border-end me-3 pe-3"><i class="fa fa-bath text-primary me-2"></i>2 Bath</small>
                      <small><i class="fa fa-wifi text-primary me-2"></i>Wifi</small>
                    </div>
                    <p class="text-body mb-3">${item.description}</p>
                    <div class="d-flex justify-content-between">
                      <a class="btn btn-sm btn-primary rounded py-2 px-4" href="/detail.html?${hotelNameSlug}">View Detail</a>
                    </div>
                  </div>
                </div>
              </div>
    `);
  });
};
