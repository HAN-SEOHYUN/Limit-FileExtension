$(function () {

    //커스텀 데이터 추가
    $('#submitBtn').click(function () {
        var data = {
            name: $("#custom-extension-input").val()
        }

        $.ajax({
            cache: false,
            url: '/extension/custom/save',
            type: 'POST',
            data: data,
        })
            .done(function (fragment) {
                $('#custom-list').replaceWith(fragment);
                $("#custom-extension-input").val("");
            });
    });

    //고정 확장자 선택
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
            });
    });
});