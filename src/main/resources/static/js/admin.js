// 切换功能标签
let currentShow = "selectEmpDiv"
function showDiv(id) {
    if (currentShow != null) {
        $("#" + currentShow).css("display", "none")
    }
    $("#" + id).css("display", "inline")
    currentShow = id
}

// 修改员工信息
function updateEmp() {
    if ($("#updateEmpId").val() == "") {
        alert("请输入要修改员工信息的账号")
    } else if ($("#updateEmpName").val() == "") {
        alert("没有需要修改的信息")
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
    } else if ($("#insertEmpPwd").val() != $("#insertEmpPwdAgain").val()) {
        alert("确认密码与密码不一致")
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

// 显示员工信息列表
$(document).ready(function () {
    $('#selectEmpTable').bootstrapTable({
        ajax: function (request) {
            $.ajax({
                type: 'GET',
                url: '/admin/select',
                contentType: 'application/x-www-form-urlencoded',
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
})