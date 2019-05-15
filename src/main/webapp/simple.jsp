<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Table拖拽测试</title>
   <%-- <link href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">--%>
    <style>
        table {
            *border-collapse: collapse; /* IE7 and lower */
            border-spacing: 0;
        }
        th,td{border:1px solid #ddd;text-align: center;
         width:200px;}
        th{background-color:#eee; }
        .bordered {
            border: solid #ccc 1px;
            -moz-border-radius: 6px;
            -webkit-border-radius: 6px;
            border-radius: 6px;
            -webkit-box-shadow: 0 1px 1px #ccc;
            -moz-box-shadow: 0 1px 1px #ccc;
            box-shadow: 0 1px 1px #ccc;
        }
    </style>
    <script src="/js/jquery.min.js"></script>
    <script type="text/javascript">
        $(function(){
            var d1=new dragTable();
        })

        function dragTable() {
            this.disX = 0; // 移动的距离:鼠标移动的位置-初始位置
            this.lanX = 0; // 拖动到的位置
            this.clickEv = null;
            var that = this;
            $(document).on('mousedown','table th',function(e){
                that.clickEv = this;
                that.mousedown(this,e);
            });
            $(document).on('mouseup','table th',function(e){
                that.mouseup(this,e);
            })
        }
        dragTable.prototype.mousedown = function(that_, e){
            that_.style.cursor="move";
            var initX = e.pageX;
            var that = this;
            $(document).on('mousemove', function (ev) {
                that.mousemove(that, initX, ev);
            });
        }

        dragTable.prototype.mousemove=function(that_, initX, ev){
            this.disX = ev.pageX - initX;
            this.lanX = ev.pageX;
        }
        dragTable.prototype.mouseup=function(that_, ev){
            that_.style.cursor="point";
            $(document).unbind('mousemove');
            this.resetTable();
        }

        dragTable.prototype.resetTable = function(){
            this.getCurrentTh('up');
            this.lanX = 0;
            this.disX = 0; // 重置disX
        }


        dragTable.prototype.getCurrentTh = function(type){
            var that = this;
            //获取鼠标移动到的单元格
            $( 'table th').each(function (index,item){
                var l = $(item).offset().left;
                var r = $(item).offset().left + $(item).width();

                if(that.lanX > l && that.lanX < r){
                    if(that.disX>4) { // 右
                        console.log("right");
                        type=='up'? that.setThTd(index,'after') :'';
                    }
                    if(that.disX<-4){ // 左边
                        console.log("left");
                        type=='up'? that.setThTd(index,'before') :'';
                    }
                }else{

                }
            })
        }

        dragTable.prototype.setThTd = function(newsindex,type){
            var oldIndex = $(this.clickEv).index();
            var $th = $('table th').eq(oldIndex);
            $( 'table th').eq(newsindex)[type]($th.clone());
            $th.remove();
            $('table tbody tr').each(function (index, tdelement) {
                var $td = $(tdelement).find('td').eq(oldIndex);
                $(tdelement).find('td').eq(newsindex)[type]($td.clone());
                $td.remove();
            });
        }
    </script>
</head>
<body>
<table class="bordered" id="tableId">
    <thead>
    <th>A</th><th>B</th><th>C</th>
    </thead>
    <tbody>
    <tr><td>10</td><td>20</td><td>30</td></tr>
    <tr><td>11</td><td>21</td><td>31</td></tr>
    <tr><td>12</td><td>22</td><td>32</td></tr>
    <tr><td>13</td><td>23</td><td>33</td></tr>
    </tbody>
</table>
<br>
</body>
</html>
