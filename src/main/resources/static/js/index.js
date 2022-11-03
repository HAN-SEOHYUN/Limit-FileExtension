$(function () {
    //'추가' 버튼 클릭시
    //요청한 커스텀 확장자가 db에 저장되고
    //리스트에 추가됨
    $('#submitBtn').click(function () {
        var data = {
            name: $("#custom-extension-input").val()
        }

        if (!check_text(data.name)) {
            alert("올바른 확장자를 입력하세요");
            refresh();
            return false;
        }

        $.ajax({
            cache: false,
            url: '/extension/custom/save',
            type: 'POST',
            data: data,
            success: function (fragment) {
                $('#custom-list').replaceWith(fragment);
                refresh();
            },
            error: function (error) {
                $("#exist-alert-span").text("등록된 확장자입니다");
            }
        });
    });

    //checkbox 상태 변경 시
    //checkbox 클릭 여부 정보를 db에 저장
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
                refresh();
            });
    });

    //입력된 글자가
    //한글, 공백, 특수문자를 포함하면 false 리턴
    function check_text(extension) {
        const regex = /[^?a-zA-Z0-9/]/;
        return !(extension.length > 20 || regex.test(extension));
    }

    //입력란 & 오류메세지 초기화
    function refresh() {
        $("#custom-extension-input").val("");
        $("#custom-extension-input").focus();
        $("#exist-alert-span").text("");
    }
});
