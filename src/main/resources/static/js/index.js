$(function () {

    //'추가' 버튼 클릭시
    //요청한 커스텀 확장자가 db에 저장되고
    //리스트에 추가됨
    $('#submitBtn').click(function () {
        var data = {
            name: $("#custom-extension-input").val()
        }
        $.ajax({
            cache: false,
            url: '/extension/custom/save',
            type: 'POST',
            data: data,
            success: function (fragment) {
                    $('#custom-list').replaceWith(fragment);
                    $("#custom-extension-input").val("");
                    $("#exist-alert-span").text("");
            },
            error: function (error) {
                $("#exist-alert-span").text("등록불가");
                $("#custom-extension-input").val("");
            }
        });

        //checkbox 상태 변경 시
        //상태가 db저장
        $('input[type=checkbox]').change(function () {

            var data = {
                id: $(this).val(),
                isChecked: $(this).is(":checked")
            };

            $.ajax({
                cache: false,
                url: '/extension/fix/update',
                type: 'POST',
                data: data,
            })
                .done(function (fragment) {
                    $('#fix-list').replaceWith(fragment);
                    $("#exist-alert-span").text("");
                });
        });
    });
});
