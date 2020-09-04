//清空登录信息
function clearLogin() {
    $("#empId").val('')
    $("#empPassword").val('')
}

//登录
function login() {
    $.ajax({
        url: "/login",
        type: "POST",
        data: {
            emp: new Emp($("#empId").val(), "", $("#empPassword").val())
        },
        contentType: "application/xx-www-form-urlencoded",
        dataType: "json",
        success: (emp) => {
            if (emp == null) {
                alert("密码错误")
            } else {
                sessionStorage.setItem("empId", emp.getEmpId())
                sessionStorage.setItem("empName", emp.getEmpName())
            }
        },
        error: (error) => {
            alert("发生错误：" + error)
        }
    })

}