$(document).ready(function() {
    sessionStorage.clear()
})

$(document).keypress((event) => {
    if (event.keyCode == 13) {
        login()
    }
})

// 清空登录信息
function clearLogin() {
    $("#empId").val('')
    $("#empPassword").val('')
}

// 登录
function login() {
    if ($("#empId").val() == "" || $("#empPassword").val() == "") {
        alert("账号及密码不可为空")
    } else {
        $.ajax({
            url: "/login",
            type: "POST",
            data: {
                empId: $("#empId").val(),
                empPassword: md5($("#empPassword").val())
            },
            contentType: "application/x-www-form-urlencoded",
            dataType: "json",
            success: (data) => {
                if (data.empId == "") {
                    alert("密码错误")
                } else {
                    sessionStorage.setItem("empId", data.empId)
                    sessionStorage.setItem("empName", data.empName)
                    if (sessionStorage.getItem("empId") == 'admin') {
                        window.location.href = "/adminPage"
                    } else {
                        // alert("登录成功！欢迎，" + sessionStorage.getItem("empName") + "!")
                        window.location.href = "/indexPage"
                    }
                }
            },
            error: (error) => {
                alert("发生错误")
                console.log(error)
            }
        })
    }
}
