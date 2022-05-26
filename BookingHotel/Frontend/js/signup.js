async function signup() {
  let fname = document.getElementById("fname").value;
  let lname = document.getElementById("lname").value;
  let uname = document.getElementById("uname").value;
  let idCard = document.getElementById("idCard").value;
  let email = document.getElementById("email").value;
  let pword = document.getElementById("pword").value;
  let address = document.getElementById("address").value;
  let city = document.getElementById("city").value;

  let data = {
    addressCity: city,
    addressCountry: address,
    email: email,
    firstName: fname,
    idCard: idCard,
    lastName: lname,
    password: pword,
    userName: uname,
  };
  // console.log(JSON.stringify(data));
  const LG = await fetch("http://localhost:8686/UTCAuth/signUp", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  })
    .then((res) => res.json())
    .then((dataRes) => {
      if (dataRes.status === 200) {
        alert("Đăng ký thành công");
      }
      window.location.href = "http://127.0.0.1:5500/index.html";
    })
    .catch((error, status) => {
      console.log("Error:", error, status);
      // if (error.c == 401 && error == 500) {
      //   console.log("dsadadad");
      // }
    });
}
