<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="logo_box width_full_size text_center">
	<a href="${context}/common/container.jsp"><img src="${img}/common/index_logo.png"></a>
</div>
<jsp:include page="gnb.jsp"/>
<style>
.pat_detail {text-align: center; margin:0 auto;}
.pat_detail tr td{border: 1px solid #bbbbbb}
</style>
	<div class="wtac">
            <table class="pat_detail" style="width: 560px">
                  <tr>
                        <th style="width:80px">순서</th>
                        <th style="width:80px">진료일</th>
                        <th style="width:80px">진료NO</th>
                        <th style="width:80px">담당의사</th>
                        <th style="width:80px">직책</th>
                        <th style="width:80px">진료과목</th>
                        <th style="width:80px">병명</th>
                        <th style="width:80px">처방내역</th>
                  </tr>
                  <tr>
                        <td>for문 돌릴곳</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                  </tr>
            </table>
      </div>
</body>
</html>