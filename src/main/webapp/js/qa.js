function checkVotePrivilege(url) {
    var hasPrivileage = false;

    $.ajax({
        url: url,
        method: 'post',
        dataType: 'json',
        async: false,
        success: function (data) {
            //{passed: true, message: "OK"}
            //{passed: false, message: "cannot vote up a question of yours." }
            if (data.passed) {
                hasPrivileage = true;
            } else {
                alert(data.message);
            }
        },
        error: function (data) {
            console.log(data);
        }
    });

    return hasPrivileage;
}

function vote(url) {
    var voteCountSpan = $("span[data-name='voteCountSpan']");

    $.ajax({
        url: url,
        method: "post",
        success: function (data) {
            if(parseInt(data) < 0) {
                voteCountSpan.addClass("text-danger");
            }
            voteCountSpan.text(data);
        },
        error: function (data) {
            console.log(data);
        }
    });
}
$(function () {
    $("a[data-name='voteup']").click(function () {
        var questionId = $(this).attr("value");


        if (!checkVotePrivilege("/qa-system/check/voteup/question/" + questionId)) {
            return;
        }

        vote("/qa-system/question/" + questionId + "/voteup");

    });

    $("a[data-name='votedown']").click(function () {
        var questionId = $(this).attr("value");

        if (!checkVotePrivilege("/qa-system/check/votedown/question/" + questionId)) {
            return;
        }

        vote("/qa-system/question/" + questionId + "/votedown");
    });
});