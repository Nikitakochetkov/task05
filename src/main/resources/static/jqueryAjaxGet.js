$(document).on("click","#blockBtn",function() {
    console.warn("nik")
    var userId = $(this).parent().find('input').val();
    var workingObject = $(this);

    $.ajax({
        type : "POST",
        url : window.location + "/block/" + userId,
        success: function(resultMsg){
            $("#resultMsgDiv").html("<p style='background-color:#67597E; color:white; padding:20px 20px 20px 20px'>" +
                "User with Id=" + userId + " is blocked successfully!"  +
                "</p>");

            workingObject.closest("tr").remove();

            // re-css for table
            $( "#customerTable tbody tr:odd" ).addClass("info");
            $( "#customerTable tbody tr:even" ).addClass("success");
        },
        error : function(e) {
            alert("ERROR: ", e);
            console.log("ERROR: ", e);
        }
    });
});


$(document).on("click","#deleteBtn",function() {
    console.warn("nik")
    var userId = $(this).parent().find('input').val();
    var workingObject = $(this);

    $.ajax({
        type : "DELETE",
        url : window.location + "/delete/" + userId,
        success: function(resultMsg){
            $("#resultMsgDiv").html("<p style='background-color:#67597E; color:white; padding:20px 20px 20px 20px'>" +
                "User with Id=" + userId + " is delete successfully!"  +
                "</p>");

            workingObject.closest("tr").remove();

            // re-css for table
            $( "#customerTable tbody tr:odd" ).addClass("info");
            $( "#customerTable tbody tr:even" ).addClass("success");
        },
        error : function(e) {
            alert("ERROR: ", e);
            console.log("ERROR: ", e);
        }
    });
});

$(document).on("click","#unblockBtn",function() {
    console.warn("nik")
    var userId = $(this).parent().find('input').val();
    var workingObject = $(this);

    $.ajax({
        type : "POST",
        url : window.location + "/unblock/" + userId,
        success: function(resultMsg){
            $("#resultMsgDiv").html("<p style='background-color:#67597E; color:white; padding:20px 20px 20px 20px'>" +
                "User with Id=" + userId + " is unblocked successfully!"  +
                "</p>");

            workingObject.closest("tr").remove();

            // re-css for table
            $( "#customerTable tbody tr:odd" ).addClass("info");
            $( "#customerTable tbody tr:even" ).addClass("success");
        },
        error : function(e) {
            alert("ERROR: ", e);
            console.log("ERROR: ", e);
        }
    });
});
