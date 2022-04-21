var actionForm= $("form");

$(".removeBtn").click(function(){

    actionForm.attr("action","/board/remove").attr("method","post").submit();
})

$(".listBtn").click(function(){

    var page =$("input[name='page']");
    var type =$("input[name='type']");
    var keyword =$("input[name='keyword']");

    actionForm.empty();

    actionForm.append(page).append(type).append(keyword);

    actionForm.attr("action","/board/list").attr("method","get").submit();
})


$(".modifyBtn").click(function(){

                if(!confirm("수정하시겠습니까?")){
                    return;
                }else{
                actionForm.attr("action","/board/modify").attr("method","post").submit();
                }
            });

