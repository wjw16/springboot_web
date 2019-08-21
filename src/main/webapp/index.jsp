<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    ${ctx};
    <meta charset="UTF-8">
    <title>Table拖拽测试</title>
   <%-- <link href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">--%>
    <style>

        body {
            width: 600px;
            margin: 40px auto;
            font-family: 'trebuchet MS', 'Lucida sans', Arial;
            font-size: 14px;
            color: #444;
        }

        table {
            *border-collapse: collapse; /* IE7 and lower */
            border-spacing: 0;
            width: 100%;
        }

        .bordered {
            border: solid #ccc 1px;
            -moz-border-radius: 6px;
            -webkit-border-radius: 6px;
            border-radius: 6px;
            -webkit-box-shadow: 0 1px 1px #ccc;
            -moz-box-shadow: 0 1px 1px #ccc;
            box-shadow: 0 1px 1px #ccc;
        }

        #tableId  tr:hover {
            background: #fbf8e9;
            -o-transition: all 0.1s ease-in-out;
            -webkit-transition: all 0.1s ease-in-out;
            -moz-transition: all 0.1s ease-in-out;
            -ms-transition: all 0.1s ease-in-out;
            transition: all 0.1s ease-in-out;
        }

        .bordered td, .bordered th {
            border-left: 1px solid #ccc;
            border-top: 1px solid #ccc;
            padding: 10px;
            text-align: left;
        }

        .bordered th {
            background-color: #dce9f9;
            background-image: -webkit-gradient(linear, left top, left bottom, from(#ebf3fc), to(#dce9f9));
            background-image: -webkit-linear-gradient(top, #ebf3fc, #dce9f9);
            background-image:    -moz-linear-gradient(top, #ebf3fc, #dce9f9);
            background-image:     -ms-linear-gradient(top, #ebf3fc, #dce9f9);
            background-image:      -o-linear-gradient(top, #ebf3fc, #dce9f9);
            background-image:         linear-gradient(top, #ebf3fc, #dce9f9);
            -webkit-box-shadow: 0 1px 0 rgba(255,255,255,.8) inset;
            -moz-box-shadow:0 1px 0 rgba(255,255,255,.8) inset;
            box-shadow: 0 1px 0 rgba(255,255,255,.8) inset;
            border-top: none;
            text-shadow: 0 1px 0 rgba(255,255,255,.5);
        }

        .bordered td:first-child, .bordered th:first-child {
            border-left: none;
        }

        .bordered th:first-child {
            -moz-border-radius: 6px 0 0 0;
            -webkit-border-radius: 6px 0 0 0;
            border-radius: 6px 0 0 0;
        }

        .bordered th:last-child {
            -moz-border-radius: 0 6px 0 0;
            -webkit-border-radius: 0 6px 0 0;
            border-radius: 0 6px 0 0;
        }

        .bordered th:only-child{
            -moz-border-radius: 6px 6px 0 0;
            -webkit-border-radius: 6px 6px 0 0;
            border-radius: 6px 6px 0 0;
        }

        .bordered tr:last-child td:first-child {
            -moz-border-radius: 0 0 0 6px;
            -webkit-border-radius: 0 0 0 6px;
            border-radius: 0 0 0 6px;
        }

        .bordered tr:last-child td:last-child {
            -moz-border-radius: 0 0 6px 0;
            -webkit-border-radius: 0 0 6px 0;
            border-radius: 0 0 6px 0;
        }

    </style>
    <script src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/jquery.tablednd.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#tableId").tableDnD({
                    onDragClass:'red',
                    //回调函数
                    onDrop:function(table,row){
                        console.log('AAA');
                    }
                }
            );
        });
    </script>
</head>
<body>
<h2>Drag the table——Neven.Jr Production</h2>
<table class="bordered" id="tableId">
<%--    <thead>

    <tr>
        <th>#</th>
        <th>IMDB Top 10 Movies</th>
        <th>Year</th>
        <th>Action</th>
    </tr>
    </thead>--%>
    <tbody>
    <tr>
        <td>1</td>
        <td>The Shawshank Redemption</td>

        <td>1994</td>
        <td>
            <a href="javascript:void(0);"><i class="icon-bookmark"></i>Mark</a>  
            <a href="javascript:void(0);"><i class="icon-edit">Detail</i></a>
        </td>
    </tr>
    <tr>
        <td>2</td>
        <td>The Godfather</td>
        <td>1972</td>
        <td>
            <a href="javascript:void(0);"><i class="icon-bookmark"></i>Mark</a>  
            <a href="javascript:void(0);"><i class="icon-edit">Detail</i></a>
        </td>
    </tr>
    <tr>

        <td>3</td>
        <td>The Godfather: Part II</td>
        <td>1974</td>
        <td>
            <a href="javascript:void(0);"><i class="icon-bookmark"></i>Mark</a>  
            <a href="javascript:void(0);"><i class="icon-edit">Detail</i></a>
        </td>
    </tr>
    <tr>
        <td>4</td>
        <td>The Good, the Bad and the Ugly</td>
        <td>1966</td>
        <td>
            <a href="javascript:void(0);"><i class="icon-bookmark"></i>Mark</a>  
            <a href="javascript:void(0);"><i class="icon-edit">Detail</i></a>
        </td>
    </tr>
    <tr>
        <td>5</td>
        <td>Pulp Fiction</td>
        <td>1994</td>
        <td>
            <a href="javascript:void(0);"><i class="icon-bookmark"></i>Mark</a>  
            <a href="javascript:void(0);"><i class="icon-edit">Detail</i></a>
        </td>
    </tr>
    <tr>
        <td>6</td>
        <td>12 Angry Men</td>

        <td>1957</td>
        <td>
            <a href="javascript:void(0);"><i class="icon-bookmark"></i>Mark</a>  
            <a href="javascript:void(0);"><i class="icon-edit">Detail</i></a>
        </td>
    </tr>
    <tr>
        <td>7</td>
        <td>Schindler's List</td>
        <td>1993</td>
        <td>
            <a href="javascript:void(0);"><i class="icon-bookmark"></i>Mark</a>  
            <a href="javascript:void(0);"><i class="icon-edit">Detail</i></a>
        </td>
    </tr>
    <tr>

        <td>8</td>
        <td>One Flew Over the Cuckoo's Nest</td>
        <td>1975</td>
        <td>
            <a href="javascript:void(0);"><i class="icon-bookmark"></i>Mark</a>  
            <a href="javascript:void(0);"><i class="icon-edit">Detail</i></a>
        </td>
    </tr>
    <tr>
        <td>9</td>
        <td>The Dark Knight</td>

        <td>2008</td>
        <td>
            <a href="javascript:void(0);">Mark</a>  
            <a href="javascript:void(0);"><i class="icon-edit">Detail</i></a>
        </td>
    </tr>
    <tr class=" nodrag">
        <td>10</td>
        <td>The Lord of the Rings: The Return of the King</td>
        <td>2003</td>
        <td>
            <a href="javascript:void(0);"><i class="icon-bookmark"></i>Mark</a>  
            <a href="javascript:void(0);"><i class="icon-edit">Detail</i></a>
        </td>
    </tr>
    </tbody>
</table>
<br>
</body>
</html>
