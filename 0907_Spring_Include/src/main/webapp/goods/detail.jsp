<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
let u=0;
$(function(){
  $('.updates').hide();
  $('.replys').hide();
  //댓글수정
  $('.up').click(function(){
	$('.updates').hide();
	$('.up').text("수정");
    let no = $(this).attr("data-no");
    if(u==0){
      $('#u'+no).show();
      $('#up'+no).text("취소");
      u=1;
    }else{
   	  $('#u'+no).hide();
      $('#up'+no).text("수정");
      u=0;
    }
  })
  //대댓글
  $('.re').on("click",function(){
	  $('.replys').hide();
	  $('.re').text("대댓글");
	  let no = $(this).attr("data-no");
	  if(u==0){
	      $('#r'+no).show();
	      $('#re'+no).text("취소");
	      u=1;
	  }else{
	   	  $('#r'+no).hide();
	      $('#re'+no).text("대댓글");
	      u=0;
	  }
  })
})
</script>
</head>
<body>
  <div class="container">
    <div class="row">
     <table class="table">
       <tr>
         <td width=30% class="text-center" rowspan="4">
           <img src="${vo.goods_poster }" style="width: 100%">
         </td>
         <td width=70%><h3>${vo.goods_name }</h3>
           <sub style="color:gray">${vo.goods_sub }</sub>
         </td>
       </tr>
       <tr>
         <td width=70%><span style="color:orange">${vo.goods_discount }%</span>&nbsp;${vo.goods_price }</td>
       </tr>
       <tr>
         <td width=70% style="color:green">첫구매할인가 &nbsp;${vo.goods_first_price }</td>
       </tr>
       <tr>
         <td width=70%>배송:${vo.goods_delivery }</td>
       </tr>
       <tr>
         <td colspan="2" class="text-right">
          <input type=button value="목록" class="btn btn-sm btn-primary"
            onclick="javascript:history.back()">
         </td>
       </tr>
     </table>
    </div>
    <!-- 댓글 출력 -->
    <div style="height:20px"></div>
    <hr>
    <div class="col-sm-8">
      <table class="table">
        <tr>
          <td>
            <c:forEach var="rvo" items="${rList }">
              <table class="table">
                <tr>
                  <td class="text-left">
                    <!-- 대댓글 표시 -->
                    <c:if test="${rvo.group_tab>0 }">
                      <c:forEach var="i" begin="1" end="${rvo.group_tab }">
                        &nbsp;&nbsp;
                      </c:forEach>
                      <img src="../goods/re.png" style="width:30px;height:30px;">
                    </c:if>
                    <span style="color:orange">◎${rvo.name }</span>_${rvo.id }&nbsp;(${rvo.dbday })
                  </td>
                  <td class="text-right">
                    <c:if test="${sessionScope.id!=null }">
                      <c:if test="${sessionScope.id==rvo.id }">
                        <span class="btn btn-xs btn-success up" id="up${rvo.no }" data-no="${rvo.no }" >수정</span> 
                        <a href="../reply/reply_delete.do?no=${rvo.no }&bno=${rvo.bno}&type=${rvo.type }" class="btn btn-xs btn-danger">삭제</a> 
                      </c:if>
                      <span class="btn btn-xs btn-primary re" id="re${rvo.no }" data-no="${rvo.no }" >대댓글</span>
                    </c:if>
                  </td>
                </tr>
                <tr>
                  <td colspan="2">
                    <c:if test="${rvo.group_tab>0 }">
                      <c:forEach var="i" begin="1" end="${rvo.group_tab }">
                        &nbsp;&nbsp;
                      </c:forEach>
                    </c:if>
                    <c:set var="k" value="${30*rvo.group_tab }"/>
					<pre style="white-space:pre-wrap; border:none; background-color:white; margin-left:${k}px">${rvo.msg }</pre>
                  </td>
                </tr>
              </table>
              <!-- 댓글 수정 영역 -->
              <table class="table updates" style="display:none" id="u${rvo.no }">
              <tr>
                <td>
                  <form method="post" action="../reply/reply_update.do">
                    <input type="hidden" name="no" value="${rvo.no }">
                    <input type="hidden" name="bno" value="${vo.no }" >
                    <input type="hidden" name="type" value="${typeno }" >
                    <textarea rows="6" cols="85" name="msg" style="float:left;">${rvo.msg }</textarea>
                    <input type="submit" class="btn-success" value="댓글 수정" style="height:126px; float:left">
                  </form>
                </td>
              </tr>
            </table>
            <!-- 대댓글 영역 -->
              <table class="table replys" style="display:none" id="r${rvo.no }">
              <tr>
                <td>
                  <form method="post" action="../reply/reply_reply_insert.do">
                    <input type="hidden" name="pno" value="${rvo.no }"> <!-- 원글번호 -->
                    <input type="hidden" name="bno" value="${vo.no }" >
                    <input type="hidden" name="type" value="${typeno }" >
                    <textarea rows="6" cols="85" name="msg" style="float:left;" placeholder="덧글을 입력하세요"></textarea>
                    <input type="submit" class="btn-primary" value="대댓글" style="height:126px; float:left" >
                  </form>
                </td>
              </tr>
            </table>
            </c:forEach>
          </td>
        </tr>
      </table>
      <c:if test="${sessionScope.id!=null }">
        <table class="table">
          <tr>
            <td>
              <form method="post" action="../reply/reply_insert.do">
                <input type="hidden" name="bno" value="${vo.no }" >
                <input type="hidden" name="type" value="${typeno }" >
                <textarea rows="6" cols="87" name="msg" style="float:left;" placeholder="덧글을 입력하세요"></textarea>
                <input type="submit" id="replyBtn" class="btn-danger" value="댓글 작성" style="height:126px; float:left">
              </form>
            </td>
          </tr>
        </table>
      </c:if>
    </div>
    <div class="col-sm-4">
      <img src="">
    </div>
  </div>
</body>
</html>

