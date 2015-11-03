$(function(){
    $("a[data-name='voteup']").click(function(){
        var questionId = $(this).attr("value");
        var voteCountSpan = $("span[data-name='voteCountSpan']");

        var hasPrivileage = false;
        $.ajax({
            url: "/qa-system/check/voteup/question/" + questionId,
            method: 'post',
            dataType: 'json',
            async: false,
            success: function(data) {
                //{passed: true, message: "OK"}
                //{passed: false, message: "cannot vote up a question of yours." }
                if(data.passed) {
                    hasPrivileage = true;
                } else {
                    alert(data.message);
                }
            },
            error: function(data) {
                console.log(data);
            }
        });

        if(!hasPrivileage) {
            return;
        }

        $.ajax({
            url: "/qa-system/question/" + questionId + "/voteup",
            method: "post",
            success: function(data) {
                voteCountSpan.text(data);
            },
            error: function(data) {
                console.log(data);
            }
        });
    });
});