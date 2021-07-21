var check_password = /^[A-Za-z0-9!@#$%^&*()_]{6,20}$/;

function checknull(txt) {
    if (txt.value.length == 0)
        return true;
    else
        return false;
}

function StringMatch(txt, reg) {
    return reg.test(txt.value);
}

function validform(f) {
    if (checknull(f.fullname)) {
        alert("Full name must be not null");
        f.fullname.focus();
        return;
    }
    if (checknull(f.email)) {
        alert("Email must be not null");
        f.email.focus();
        return;
    }
    if (checknull(f.phone)) {
        alert("Phone must be not null");
        f.phone.focus();
        return;
    }
    if (!isNaN(f.phone)) {
        alert("Phone must be number!");
        f.phone.value = "";
        f.phone.focus();
        return;
    }
    if (checknull(f.password)) {
        alert("Password must be not null");
        f.password.focus();
        return;
    }
    if (!StringMatch(f.password, check_password)) {
        alert("Password is not valid");
        f.password.value = "";
        f.password.focus();
        return;
    }
    alert("Successfully!!!");
}