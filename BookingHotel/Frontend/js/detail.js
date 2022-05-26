let roomName = location.search.split("?")[1].replace(/%20/g, " ");

window.onload = OnLoad = async () => {
  var idCard;
  var uname = localStorage.getItem("USERNAME");
  var token = localStorage.getItem("TOKEN");
  const getDetailRoom = await fetch(
    `http://localhost:8686/UTCDemo/room/pageHotelName?hotelName=${roomName}`
  )
    .then((res) => res.json())
    .then((data) => data.content);
  $(`.location`).append(roomName);

  $(`.form-select`).append(() => {
    var list = [];
    getDetailRoom.forEach((item) => {
      if (item.status === "DRUM") {
        list += `<option value="${item.number}">Room ${item.number}</option>`;
      }
    });
    return list;
  });

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

  document.getElementById("form").onsubmit = async function onSubmit(form) {
    form.preventDefault();
    let select = document.querySelector(".form-select").value;
    let checkin = document.getElementById("checkin").value;
    let checkout = document.getElementById("checkout").value;

    if (select === "0") {
      alert("Hãy chọn số phòng");
    } else {
      if (
        Date.parse(checkin) > Date.parse(checkout) ||
        checkin === "" ||
        checkout === ""
      ) {
        alert("Lỗi chọn ngày");
      } else {
        var getRoom = await fetch(
          `http://localhost:8686/UTCDemo/room/getRoomByNumberAndHotelName?hotelName=${roomName}&number=${select}`
        )
          .then((res) => res.json())
          .then((data) => data);
        if (token !== "null") {
          let obj = {
            checkIn: checkin,
            checkOut: checkout,
            hotelName: roomName,
          };
          let booking = await fetch(
            `http://localhost:8686/UTCDemo/booking?idCard=${idCard}`,
            {
              method: "POST",
              headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer=${token}`,
              },
              body: JSON.stringify(obj),
            }
          ).then((res) => res);

          let objRoomBook = {
            bookingGuestsIdCard: idCard,
            roomNumber: getRoom.number,
            hotelName: roomName,
          };
          let roomBook = await fetch(`http://localhost:8686/UTCDemo/roomBook`, {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer=${token}`,
            },
            body: JSON.stringify(objRoomBook),
          }).then((res) => res.ok);
          if (roomBook === true) {
            alert("Đặt phòng thành công");
          }
        } else {
          alert("Bạn cần đăng nhập");
        }
      }
    }
  };
};

// insert vao booking POST
// insert room book
// select room
