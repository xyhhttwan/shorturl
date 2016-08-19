$list = $('#fileList'),
// 优化retina, 在retina下这个值是2
    ratio = window.devicePixelRatio || 1,

// 缩略图大小
    thumbnailWidth = 100 * ratio,
    thumbnailHeight = 100 * ratio;

var uploader = WebUploader.create({
    // 选完文件后，是否自动上传。
    auto: true,

    // swf文件路径
    swf: '../../js/upload/Uploader.swf',

    // 文件接收服务端。
    server: '../../../common/upload/uploadImage?pt=news',

    // 选择文件的按钮。可选。
    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
    pick: '#filePicker',

    // 只允许选择图片文件。
    accept: {
        title: 'Images',
        extensions: 'gif,jpg,jpeg,bmp,png',
        mimeTypes: 'image/*'
    },
    fileNumLimit: 2
});
uploader.on('beforeFileQueued', function (file) {

    var fileSize = 2 * 1024 * 1024;
    if (file.size > fileSize) {

        layer.alert("请上传小于2M的图片", 8);
        return false;
    }
    return true;

});
//当有文件添加进来的时候
uploader.on('fileQueued', function (file) {
//     var $li = $(
//             '<div id="' + file.id + '" class="file-item thumbnail">' +
//             '<img style="width: 100px;height: 100px">' +
//             '<div class="info">' + file.name + '</div>' +
//             '<a class="cp_img_jian">删除</a></div>'
//         ),
//         $img = $li.find('img');
// //        $list为容器jQuery实例
//     $list.append($li);
//     uploader.makeThumb(file, function (error, src) {
//         if (error) {
//             $img.replaceWith('<span>不能预览</span>');
//             return;
//         }
//         $img.attr('src', src);
//     }, thumbnailWidth, thumbnailHeight);

    $("#uploader-item").hide();
});
// 文件上传过程中创建进度条实时显示。
uploader.on('uploadProgress', function (file, percentage) {
    var $li = $('#' + file.id),
        $percent = $li.find('.progress span');

    // 避免重复创建
    if (!$percent.length) {
        $percent = $('<p class="progress"><span></span></p>')
            .appendTo($li)
            .find('span');
    }

    $percent.css('width', percentage * 100 + '%');
});

// 文件上传成功，给item添加成功class, 用样式标记上传成功。
uploader.on('uploadSuccess', function (file, result) {
    $('#' + file.id).addClass('upload-state-done');
    if (result != null && result != '') {
        $("#tem_pic").attr('src',BASE_URL+result);
        $("#hh").attr("href",BASE_URL+result);
        $("#pic_div").show();
        $("#uploader-item").show();
        $("#process").hide();
        $("#pic").val(result);
    }

});

// 文件上传失败，显示上传出错。
uploader.on('uploadError', function (file) {
    var $li = $('#' + file.id),
        $error = $li.find('div.error');

    // 避免重复创建
    if (!$error.length) {
        $error = $('<div class="error"></div>').appendTo($li);
    }
    $("#uploader-item").show();
    $error.text('上传失败');
});

// 完成上传完了，成功或者失败，先删除进度条。
uploader.on('uploadComplete', function (file) {
    $("#uploader-item").show();
    $('#' + file.id).find('.progress').remove();
});

//显示删除按钮
$(".cp_img").on("mouseover", function () {
    $(this).children(".cp_img_jian").css('display', 'block');

});
//隐藏删除按钮
$(".cp_img").on("mouseout", function () {
    $(this).children(".cp_img_jian").css('display', 'none');

});
//执行删除方法
$list.on("click", ".cp_img_jian", function () {
    var id = $(this).parent().attr("id");
    uploader.removeFile(uploader.getFile(id, true));
    $(this).parent().remove();
});