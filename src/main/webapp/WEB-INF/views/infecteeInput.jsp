<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:include page="common/default.jsp"/>
<body>
<br>
<br>
<div>
    위경도 검색 : <input class="form-group" id="addr" name="query" placeholder="위경도를 검색할 주소를 입력해주세요." style="width: 500px"/>
    <button onclick="selectXY()">조회</button>
    <div id="searchResult" style="display: none">
        위도 : <text id = "lat">df</text> | 경도 : <text id = "lon">fff</text>
    </div>
</div>

<br>
<br>
<br>
<div class="row">
    <div class="col-lg-12">
        <div class="ibox ">
            <div class="ibox-title">
                <h5>감염자 데이터 추가</h5>
            </div>
            <div class="ibox-content">
                <div class="table-responsive">
                    <table class="table table-bordered table-hover">
                        <col width="20%">
                        <col width="*">
                        <tbody>
                            <tr>
                                <td>
                                    123
                                </td>
                                <td>
                                    456
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    123
                                </td>
                                <td>
                                    456
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    123
                                </td>
                                <td>
                                    456
                                </td>
                            </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
<script>
    function selectXY() {
        var query = $("#addr").val();

        if (query === null || query === '') {
            alert("위경도를 검색할 주소를 입력해주세요.");
            return;
        }

        var data = {
            query: query
        };

        $.ajax({
            url: '/infectee/search/location',
            data: data,
            type: 'POST',
            async: false,
            error: function (error) {
                toastr.error('Error!');
            },
            success: function (data) {
                console.log(data);
                $("#searchResult").show();
                $("#lat").html(data.x);
                $("#lon").html(data.y);
            }
        });
    }
</script>