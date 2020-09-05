$(document).ready(function() {
    verifyAdmin()
    listEmps()
})

$(document).keypress((event) => {
    if (event.keyCode == 13) {
        $("#" + currentShow + " > [name='submitBtn']").trigger("click")
    }
})

// 验证身份
function verifyAdmin() {
    if (sessionStorage.getItem("empId") != 'admin' || sessionStorage.getItem("empName") != 'admin') {
        alert("权限不足")
        window.location.href = 'login'
    }
}

// 切换功能标签
let currentShow = "selectEmpDiv"
function showDiv(id) {
    if (currentShow != null) {
        $("#" + currentShow).css("display", "none")
    }
    $("#" + id).css("display", "inline")
    currentShow = id
}

// 退出管理员账号
function logoutAdmin() {
    sessionStorage.clear()
    window.location.href = "login"
}

// 修改员工信息
function updateEmp() {
    if ($("#updateEmpId").val() == "") {
        alert("请输入要修改员工信息的账号")
    } else if ($("#updateEmpName").val() == "") {
        alert("没有需要修改的信息")
    } else if ($("#updateEmpId").val() == "admin") {
        alert("不可修改管理员账号")
    } else {
        $.ajax({
            url: "/admin/update",
            type: "POST",
            data: {
                empId: $("#updateEmpId").val(),
                empName: $("#updateEmpName").val()
            },
            contentType: "application/x-www-form-urlencoded",
            dataType: "json",
            success: (data) => {
                if (!data.isOk) {
                    alert("账号不存在")
                } else {
                    alert("员工信息修改成功")
                }
            },
            error: (error) => {
                alert("发生错误")
                console.log(error)
            }
        })
    }
}


// 新增员工
function insertEmp() {
    if ($("#insertEmpId").val() == "" || $("#insertEmpPwd").val() == "") {
        alert("账号及密码不可为空")
    } else if ($("#insertEmpPwd").val() == "") {
        alert("密码不可为空")
    } else if ($("#insertEmpPwd").val() != $("#insertEmpPwdAgain").val()) {
        alert("确认密码与密码不一致")
    } else if ($("#insertEmpId").val() == "admin") {
        alert("不可添加管理员账号")
    } else {
        $.ajax({
            url: "/admin/insert",
            type: "POST",
            data: {
                empId: $("#insertEmpId").val(),
                empPwd: md5($("#insertEmpPwd").val()),
                empName: $("#insertEmpName").val()
            },
            contentType: "application/x-www-form-urlencoded",
            dataType: "json",
            success: (data) => {
                if (!data.isOk) {
                    alert("账号已存在")
                } else {
                    alert("账号添加成功")
                    $("#insertEmpId").val("")
                    $("#insertEmpPwd").val("")
                    $("#insertEmpPwdAgain").val("")
                    $("#insertEmpName").val("")
                }
            },
            error: (error) => {
                alert("发生错误")
                console.log(error)
            }
        })
    }
}

//删除员工
function deleteEmp() {
    if ($("#deleteEmpId").val() == "" || $("#deleteEmpName").val() == "") {
        alert("请输入被删除员工的账号与姓名")
    } else if ($("#adminPwd").val() == "") {
        alert("请输入管理员密码")
    } else if ($("#deleteEmpId").val() == "admin") {
        alert("不可删除管理员账号")
    } else {
        $.ajax({
            url: "/admin/delete",
            type: "POST",
            data: {
                empId: $("#deleteEmpId").val(),
                empName: $("#deleteEmpName").val(),
                adminPwd: md5($("#adminPwd").val())
            },
            contentType: "application/x-www-form-urlencoded",
            dataType: "json",
            success: (data) => {
                if (!data.isVerified) {
                    alert("管理员密码不正确")
                } else if (!data.isOk) {
                    alert("被删除员工信息不正确")
                } else {
                    alert("删除成功")
                    $("#deleteEmpId").val("")
                    $("#adminPwd").val("")
                    $("#deleteEmpName").val("")
                }
            },
            error: (error) => {
                alert("发生错误")
                console.log(error)
            }
        })
    }
}

// 查询员工
function selectEmp() {
    sessionStorage.setItem("selectEmpId", $("#selectEmpId").val())
    sessionStorage.setItem("selectEmpName", $("#selectEmpName").val())
    window.location.href = window.location.href
}

// 清除查询条件
function clearSelect() {
    $("#selectEmpId").val("")
    $("#selectEmpName").val("")
}

// 显示查找的员工信息列表
function listEmps() {
    $('#selectEmpTable').bootstrapTable({
        ajax: function (request) {
            $.ajax({
                type: 'POST',
                url: '/admin/select',
                contentType: 'application/x-www-form-urlencoded',
                data: {
                    empId: sessionStorage.getItem("selectEmpId"),
                    empName: sessionStorage.getItem("selectEmpName")
                },
                dataType: 'json',
                success: function (data) {
                    request.success({
                        row: data.list,
                    })
                    $('#selectEmpTable').bootstrapTable('load', data.list)
                },
                error: function (error) {
                    alert("发生错误")
                    console.log(error)
                },
            })
        },
        striped: true,
        pageNumber: 1,
        pagination: true,
        sidePagination: 'client',
        pageSize: 10,
        search: true,
        columns: [
            {
                title: '员工账号',
                field: 'id',
            },
            {
                title: '员工姓名',
                field: 'name',
            },
        ],
    })
    $("#selectEmpId").val(sessionStorage.getItem("selectEmpId"))
    $("#selectEmpName").val(sessionStorage.getItem("selectEmpName"))
    sessionStorage.setItem("selectEmpId", "")
    sessionStorage.setItem("selectEmpName", "")
}

// 修改管理员密码
function changeAdminPwd() {
    if ($("#oldAdminPwd").val() == "") {
        alert("请输入原管理员密码")
    } else if ($("#newAdminPwd").val() == "") {
        alert("新密码不可为空")
    } else if ($("#newAdminPwd").val() != $("#newAdminPwdAgain").val()) {
        alert("新密码与密码确认不一致")
    } else {
        $.ajax({
            url: "/admin/changePwd",
            type: "POST",
            data: {
                oldAdminPwd: md5($("#oldAdminPwd").val()),
                newAdminPwd: md5($("#newAdminPwd").val())
            },
            contentType: "application/x-www-form-urlencoded",
            dataType: "json",
            success: (data) => {
                if (!data.isOk) {
                    alert("原管理员密码不正确")
                } else {
                    alert("管理员密码修改成功")
                    $("#oldAdminPwd").val("")
                    $("#newAdminPwd").val("")
                    $("#newAdminPwdAgain").val("")
                }
            },
            error: (error) => {
                alert("发生错误")
                console.log(error)
            }
        })
    }
}