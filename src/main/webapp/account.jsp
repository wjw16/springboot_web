<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="utf-8" lang="utf-8">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

  <title>div change width by drag</title>

  <script src="/js/jquery.min.js"></script>


</head>

<body>
<label>账号：</label><input type="text" id="account" lay-verify="account"/>
</body>


<script type="text/javascript">
  $(function(){
     $("[ lay-verify='account']").on('input propertychange', function()
     {

         var dataValue=$(this).val().replace(/\s/g, '').replace(/[^\d]/g, '').replace(/(\d{4})(?=\d)/g, '$1 ');
           $(this).val(dataValue);
      })
  });
</script>
</html>