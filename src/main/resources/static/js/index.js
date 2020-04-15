function analysisExcel() {
    $("#btn-div").attr("disabled","disabled");
    alert("开始解析啦！");
    $.ajax({
        url: "/qianqian/analysisExcel",
        data: {
            filePath: $("#filePath").val(),
            type: "1"
        },
        type: "post",
        dataType: "json",
        success: function (result) {
            console.log(result);
            if(result.success == true) {
                alert("解析成功！");
            } else {
                alert("解析失败！");
            }
        }
    })
}