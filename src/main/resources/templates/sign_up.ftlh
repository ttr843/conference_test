<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<h1>Регистрация</h1>
<div>
    <form action="/signUp" method="post">
        <input name="firstName" placeholder="Имя">
        <input name="lastName" placeholder="Фамилия">
        <input name="email" placeholder="Email">
        <input type="password" name="password" placeholder="Пароль">
        <input type="submit" value="РЕГИСТРАЦИЯ">
    </form>
</div>
<div>
    <#if errors??>
        <#list errors as error>
            <h2>${error.getDefaultMessage()}</h2>
        </#list>
    </#if>
</div>
</body>
</html>
