function reload(obj, datatype) {
    if (datatype == "treegrid") {
        $('#' + obj).treegrid('reload');
    } else {
        $('#' + obj).datagrid('reload');
    }

}

var dialogParent = "";
var dialogOwn = "";

/**
 *
 * @param title 标题
 * @param url新增(修改)跳转(请求的页面)
 * @param type (0新增1修改)
 * @param openId 页面弹出div的id
 * @param submitUrl 提交url
 * @param width 弹出框页面宽度
 * @param reloadId 成功后要刷新的列表id
 */
function oprateUser(title, url, type, openId, submitUrl, width, reloadId, datatype) {
    var rows = "";
    if (type == 1) { //edit

        if (datatype == "treegrid") {
                rows = $('#' + reloadId).treegrid('getSelections')
            } else {
                rows = $('#' + reloadId).datagrid('getSelections')
            }
            if (rows.length != 1) {
                $.messager.alert('消息', '<br/>请选择一行数据!', 'info');
                return;
            } else {
                url = url + "?id=" + rows[0].id;
        }
    }
    if (datatype == "treegrid" && type != "1") {
        rows = $('#' + reloadId).treegrid('getSelections')
        if (rows != "" && rows.length > 0) {
            url = url + "?id=" + rows[0].id;
        }

    }
    //解决关闭不了的问题
    dialogParent = $("#" + openId).parent();
    dialogOwn = $("#" + openId).clone();
    $('#' + openId).dialog({
        href: url,
        modal: true,
        title: title,
        top: '15%',
        left: '30%',
        width: '50%',
        resizable: true,
        cache: false,
        buttons: [{
            text: '确定',
            iconCls: 'icon-ok',
            handler: function () {
                //dialogOwn.appendTo(dialogParent);
                dataSubmit(submitUrl, openId, reloadId, datatype);
            }
        }, {
            text: '取消',
            iconCls: 'icon-cancel',
            handler: function () {
                dialogOwn.appendTo(dialogParent);
                $('#' + openId).dialog("destroy").remove();
            }
        }],
        onClose: function () {
            dialogOwn.appendTo(dialogParent);
            $(this).dialog('destroy');
        },
        onLoad: function () {

        }
    });
}
/**
 * 提交
 * @param url 提交的url
 * @param openId 要打开的divID
 * @param reloadId 要刷新的列表id
 */
function dataSubmit(url, openId, reloadId, datatype) {
    $.messager.confirm('确认', '确定要操作该数据?', function (r) {
        if (r) {
            $('#dataform').form('submit', {
                url: url,
                dataType: 'json',
                
                ajax: 'true',
                onSubmit: function () {
                    var validator = $(this).form('validate');
                    if (validator) {
                        var win = $.messager.progress({
                            title: 'Please waiting',
                            msg: '正在提交数据...'
                        });
                        return true;
                    }
                    return false;
                },
                success: function (data) {

                    close_process();
                    var data = eval('(' + data + ')');
                    if (data.result == "true") {
                        dialogOwn.appendTo(dialogParent);
                        $('#' + openId).dialog("destroy").remove();      // close the dialog
                        $.messager.show({title: 'Success', msg: "<br/>" + data.message});
                        reload(reloadId, datatype)   // reload the  data
                    } else {
                        $.messager.alert('Error', "<br/>" + data.message, 'Error');
                    }
                }

            });
        }
    });
}

function close_process() {
    $.messager.progress('close');
}

/**
 * 删除
 * @param dataId
 * @param url
 */
function del(dataId, url, datatype) {
    var rows = "";
    if (datatype == "treegrid") {
        rows = $('#' + dataId).treegrid('getSelections');
    } else {
        rows = $('#' + dataId).datagrid('getSelections');
    }
    if (rows.length != 1) {
        $.messager.alert('消息', '<br/>请选择一行数据!', 'info');
        return;
    }

    if (rows.length > 0) {
        var ids = '';
        for (var i = 0; i < rows.length; i++) {
            ids += 'ids=' + rows[i].id + '&';
        }
        ids = ids.substring(0, ids.length - 1);
        url = url + '?' + ids;
        $.messager.confirm('Confirm', '确定要删除选择的数据吗?', function (r) {
            if (r) {
                var win = $.messager.progress({
                    title: 'Please waiting',
                    msg: '正在提交请求...'
                });
                $.get(url, function (data) {
                    close_process();
                    var data = eval('(' + data + ')');
                    if (data.result == "true") {
                        $.messager.show({title: 'Success', msg: data.message});
                        reload(dataId, datatype);

                    } else if (data.result == "false") {
                        $.messager.alert('Error', "<br/>" + data.message, 'Error');
                    } else {
                        $.messager.alert('Error', "<br/>" + data, 'Error');
                    }
                });

            }
        });
    } else {
        $.messager.alert('消息', '<br/>请选择要删除的数据!', 'info');
    }
}


/**
 *
 * @param title 标题
 * @param url请求的页面
 * @param openId 页面弹出div的id
 * @param width 弹出框页面宽度
 */
function showDetail(title, url, reloadId, openId, width) {
    dialogParent = $("#" + openId).parent();
    dialogOwn = $("#" + openId).clone();
    var rows = $('#' + reloadId).datagrid('getSelections')
    if (rows.length != 1) {
        $.messager.alert('消息', '<br/>请选择一行数据!', 'info');
        return;
    } else {
        url = url + "?id=" + rows[0].id;
    }
    $('#' + openId).dialog({
        href: url,
        modal: true,
        title: title,
        top: '15%',
        left: '30%',
        width: '50%',
        resizable: true,
        cache: false,
        buttons: [{
            text: '关闭',
            iconCls: 'icon-cancel',
            handler: function () {
                dialogOwn.appendTo(dialogParent);
                $('#' + openId).dialog("destroy").remove();
            }
        }],
        onClose: function () {
            dialogOwn.appendTo(dialogParent);
            $(this).dialog('destroy');
        },
        onLoad: function () {

        }
    });
}

/**
 * 格式化时间 2015-06-30 14:04:00
 * @param val
 * @returns {string}
 */
function formatterDate(val) {
    if (val != null) {
        var myDate = new Date(val);
        var year = myDate.getFullYear();
        var month = ("0" + (myDate.getMonth() + 1)).slice(-2);
        var day = ("0" + myDate.getDate()).slice(-2);
        var h = ("0" + myDate.getHours()).slice(-2);
        var m = ("0" + myDate.getMinutes()).slice(-2);
        var s = ("0" + myDate.getSeconds()).slice(-2);
        var mi = ("00" + myDate.getMilliseconds()).slice(-3);
        return year + "-" + month + "-" + day + " " + h + ":" + m + ":" + s;
    }
}

/**
 * 格式化时间 2015-06-30 14:04:00
 * @param val
 * @returns {string}
 */
function formatterDateTime(val) {
    if (val != null) {
        var myDate = new Date(val);
        var year = myDate.getFullYear();
        var month = ("0" + (myDate.getMonth() + 1)).slice(-2);
        var day = ("0" + myDate.getDate()).slice(-2);
        var h = ("0" + myDate.getHours()).slice(-2);
        var m = ("0" + myDate.getMinutes()).slice(-2);
        var s = ("0" + myDate.getSeconds()).slice(-2);
        var mi = ("00" + myDate.getMilliseconds()).slice(-3);
        return year + "-" + month + "-" + day;
    }
}
