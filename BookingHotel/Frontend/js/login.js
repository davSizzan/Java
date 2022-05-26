async function login() {
  let uname = document.getElementById("uname").value;
  let pword = document.getElementById("pword").value;
  let token;
  let data = {
    password: pword,
    userName: uname,
  };
  // console.log(JSON.stringify(data));
  const LG = await fetch("http://localhost:8686/UTCAuth/signIn", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  })
    .then((res) => res.json())
    .then((dataRes) => {
      localStorage.setItem("USERNAME", uname);
      localStorage.setItem("TOKEN", dataRes.token);
      window.location.href = "http://127.0.0.1:5500/index.html";
    })
    .catch((error, status) => {
      console.log("Error:", error, status);
      // if (error.c == 401 && error == 500) {
      //   console.log("dsadadad");
      // }
    });
}
