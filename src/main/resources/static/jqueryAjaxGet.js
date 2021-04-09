$(document).on("click","#blockButton",function() {
    var elements = document.getElementsByClassName("chkCheckBoxId");
    for (var i = 0; i < elements.length; i++) {
        var checked = elements.item(i).checked;
        if(checked) {
            $.ajax({
                type : "POST",
                url :  "/block/" + elements.item(i).value,
                success: function(resultMsg){
                    $("#resultMsgDiv").html("<p style='background-color:#67597E; color:white; padding:20px 20px 20px 20px'>" +
                        "User with Id=" + userId + " is blocked successfully!"  + "</p>");

                    // re-css for table
                    $( "#customerTable tbody tr:odd" ).addClass("info");
                    $( "#customerTable tbody tr:even" ).addClass("success");
                },
                error : function(e) {
                    alert("ERROR: ", e);
                    console.log("ERROR: ", e);
                }
            });
        }
    }
})


$(document).on("click","#unblockButton",function() {
    var elements = document.getElementsByClassName("chkCheckBoxId");
    for (var i = 0; i < elements.length; i++) {
        var checked = elements.item(i).checked;
        var id = elements.item(i).value;
        if(checked) {
            $.ajax({
                type : "POST",
                url :  "/unblock/" + elements.item(i).value,
                success: function(resultMsg){
                    $("#resultMsgDiv").html("<p style='background-color:#67597E; color:white; padding:20px 20px 20px 20px'>" +
                        "User with Id=" + userId + " is unblocked successfully!"  + "</p>");

                    // re-css for table
                    $( "#customerTable tbody tr:odd" ).addClass("info");
                    $( "#customerTable tbody tr:even" ).addClass("success");
                },
                error : function(e) {
                    alert("ERROR: ", e);
                    console.log("ERROR: ", e);
                }
            });
        }
    }
})


$(document).on("click","#deleteButton",function() {
    var elements = document.getElementsByClassName("chkCheckBoxId");
    for (var i = 0; i < elements.length; i++) {
        var checked = elements.item(i).checked;
        if(checked) {
            $.ajax({
                type : "DELETE",
                url :  "/delete/" + elements.item(i).value,
                success: function(resultMsg){
                    $("#resultMsgDiv").html("<p style='background-color:#67597E; color:white; padding:20px 20px 20px 20px'>" +
                        "User with Id=" + userId + " is delete successfully!"  + "</p>");

                    // re-css for table
                    $( "#customerTable tbody tr:odd" ).addClass("info");
                    $( "#customerTable tbody tr:even" ).addClass("success");
                },
                error : function(e) {
                    alert("ERROR: ", e);
                    console.log("ERROR: ", e);
                }
            });
        }
    }
})

