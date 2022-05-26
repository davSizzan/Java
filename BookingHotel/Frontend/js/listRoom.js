let roomNameSlug = location.search.split("?")[1].replace(/%20/g, " ");

console.log("list room ", roomNameSlug);

window.onload = OnLoad = async () => {
  const getRoom = await fetch(
    `http://localhost:8686/UTCDemo/room/pageHotelName?hotelName=${roomNameSlug}`
  )
    .then((res) => res.json())
    .then((data) => data.content);
  console.log(getRoom);
};
