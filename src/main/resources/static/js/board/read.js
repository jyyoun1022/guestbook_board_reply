

$(document).ready(function(){

    var bno = [[${dto.bno}]];

    //댓글이 추가될 영역
var listGroup = $(".replyList");

//날짜 처리를 위한 함수
function formatTime(str){

    var date = new Date(str);

    return date.getFullYear() + '/' +
    (date.getMonth()+1) + '/' + 
    date.getDate() + ' ' +
    date.getHours() + ':' +
    date.getMinutes();

}

//특정한 게시글의 댓글을 처리하는 함수
function loadJSONData(){
    $.getJSON('/replies/board/'+bno, function(arr){

        console.log(arr);
        var str = " ";

        $(".replyCount").html("Reply Count - "+arr.length);

        $.each(arr, function(idx,reply){
            console.log(reply);
            console.log(idx);

            str += '<div class="card-body" data-rno="'+reply.rno+'"><b>' +reply.rno + '</b>';
            str += '<h5 class="card-title">' +reply.text + '</h5>';
            str += '<h6 class="card-subtitle mb-2 text-muted">'+reply.replier +'</h6>'   ;
            str += '<p class="card-text">' + formatTime(reply.regDate)+'</p>';
            str += '</div>';
        })

        listGroup.html(str);
    });
}   

//댓글리스트 
$(".replyCount").click(function(){

    loadJSONData();
})
})
