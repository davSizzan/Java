const roomsQty = async () => {
  const res = await fetch("http://localhost:8686/UTCDemo/room");
  return await res.json();
};

window.onload = LoadData = async () => {
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

  const getListAddress = await fetch("http://localhost:8686/UTCDemo/address")
    .then((res) => res.json())
    .then((data) => {
      let list = Object.values(
        data.reduce((c, e) => {
          if (!c[e.city]) c[e.city] = e;
          return c;
        }, {})
      );
      return list;
    });
  getListAddress.map((item, index) => {
    let cityNameSlug;
    if (item.city !== "Đà Nẵng") {
      cityNameSlug = item.city.normalize("NFD").replace(/[\u0300-\u036f]/g, "");
    } else {
      cityNameSlug = "Da Nang";
    }
    if (index < 8) {
      $("#listAddress").append(
        `<div class='col-3 text-end address-item${index}'  style="margin: 10px 0"></div>`
      );
      $(`.address-item${index}`).append(
        `<a class="location wow zoomInUp" href="/listHotel.html?${cityNameSlug}" data-wow-delay="0.1s">${item.city}</a>
        <img class="img-fluid rounded w-100 wow zoomIn" data-wow-delay="0.1s" src="img/about-${index}.jpg"  style="height: 90%;">`
      );
    }
  });

  const getListHotel = await fetch("http://localhost:8686/UTCDemo/hotel")
    .then((res) => res.json())
    .then((data) => data);
  getListHotel.map((item, index) => {
    if (index < 12) {
      let hotelNameSlug = item.name
        .normalize("NFD")
        .replace(/[\u0300-\u036f]/g, "");
      $("#listHotel").append(
        `<div class="col-lg-4 col-md-6 shadow wow fadeInUp hotel-item${index}" data-wow-delay="0.1s"></div>`
      );
      $(`.hotel-item${index}`).append(`<div class="position-relative">
      <img class="img-fluid" src="img/room-${
        Math.floor(Math.random() * 10) + 1
      }.jpg" alt="" style="height: 250px; width: 100%;">
    </div>`);
      $(`.hotel-item${index}`).append(
        `<div class="p-4 mt-2 hotel-item-child${index}"></div>`
      );
      $(`.hotel-item-child${index}`).append(
        `<div class="d-flex justify-content-between mb-3 hotel-item-child-title${index}"></div>`
      );
      $(`.hotel-item-child-title${index}`).append(
        `<h5 class="mb-0">${item.name}</h5>`
      );
      $(`.hotel-item-child-title${index}`).append(
        `<div class="ps-2 star-qty${index}"><small class="fa fa-star text-primary"></small>
        <small class="fa fa-star text-primary"></small>
        <small class="fa fa-star text-primary"></small>
        <small class="fa fa-star text-primary"></small>
        <small class="fa fa-star text-primary"></small></div>`
      );
      $(`.hotel-item-child${index}`).append(
        `<p class="text-body mb-3 hotel-item-child-text">${item.description}</p>`
      );
      $(`.hotel-item-child${index}`).append(
        `<div class="d-flex justify-content-between">
        <a class="btn btn-sm btn-primary rounded py-2 px-4" href="/detail.html?${hotelNameSlug}">View Detail</a>
      </div>`
      );
    }
  });
};
