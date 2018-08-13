<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=utf-8"); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<%
   
%>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=acb3de07cd7e06ff26709e0cda4c89c2&libraries=services"></script>
<script type="text/javascript">
   function test(){
      var code=$("input[name=code]").val();
      var out=$("input[name=out]").val();
      var radius =$("input[name=sido]").val();
      var pro=$("input[name='prodcd']:checked").val();
      
      $.ajax({
         url:"http://www.opinet.co.kr/api/aroundAll.do",
         method:"get",
         data:{"out":out,
            "radius":radius,
            "code":code,
            "prodcd":pro,
            "sort":"1",
            "x":"314681.8",
            "y":"544837"
         },
         async:false,
         datatype:"xml",
         success:function(xmlData){
            var rows=$(xmlData).find("OIL");
            alert(rows);
//             if(radius!=""){
//                rows=rows.find("UNI-ID:contains(A0009974)").parent();
//             }
            var table=makeTable(rows);
            $("#test_div").empty().append(table);
         },
         error:function(){
            alert("서버통신실패");
         }
      });
   }
   
   function chk_maker(str){
      var geocoder = new daum.maps.services.Geocoder();

      // 주소로 좌표를 검색합니다
//       for(var i=0; i<str.)
      geocoder.addressSearch('제주특별자치도 제주시 첨단로 242', function(result, status) {

          // 정상적으로 검색이 완료됐으면 
           if (status === daum.maps.services.Status.OK) {

              var coords = new daum.maps.LatLng(result[0].y, result[0].x);

              // 결과값으로 받은 위치를 마커로 표시합니다
              var marker = new daum.maps.Marker({
                  map: map,
                  position: coords
              });

              // 인포윈도우로 장소에 대한 설명을 표시합니다
              var infowindow = new daum.maps.InfoWindow({
                  content: '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>'
              });
              infowindow.open(map, marker);

              // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
              map.setCenter(coords);
          } 
      });    
      
   }
   
   function makeTable(rows){
      var table=$("<table border='1'></table>");
      
      var tr1=$("<tr style='background:red'></tr>");
      for(var k=0; k<rows.eq(0).children().length; k++){
         
         var td1=$("<td></td>").text(rows.eq(0).children().eq(k).prop("tagName"));
         tr1.append(td1);
      }
      table.append(tr1);
      
      
      for(var i=0; i<rows.length; i++){
         var tr=$("<tr></tr>");
         for(var j=0; j< rows.eq(0).children().length; j++){
            var td=$("<td></td>").text(rows.eq(i).children().eq(j).text());
            tr.append(td);
            
         }
         table.append(tr);
      }
      return table;
   }

</script>
</head>
<body>
<div>

   <input type="hidden" value="F330180731" name="code">
   <input type="hidden" value="xml" name="out">
   <input type="text" name="sido">
   <input type="radio" name="prodcd" value="B027">휘발유
   <input type="radio" name="prodcd" value="B034">고급유
   <input type="radio" name="prodcd" value="D047">경유
   <input type="radio" name="prodcd" value="C004">등유
   <input type="radio" name="prodcd" value="K015">부탄
   <button onclick="test()">테스트</button>
   <button onclick="test2()">테스트2</button>
</div>
<div id="test_div">

</div>
<div id="map" style="width:500px;height:400px;"></div>
<script type="text/javascript" src="http://dapi.kakao.com/v2/maps/sdk.js?appkey=acb3de07cd7e06ff26709e0cda4c89c2"></script>
<script>
      var container = document.getElementById('map');
      var options = {
         center: new daum.maps.LatLng(37.609811,126.9767129231),
         level: 3
      };

      var map = new daum.maps.Map(container, options);
      
      var marker = new daum.maps.Marker({ 
          // 지도 중심좌표에 마커를 생성합니다 
          position: map.getCenter() 
      }); 
      // 지도에 마커를 표시합니다
      marker.setMap(map);
      
      
      
</script>
 



</body>
</html>