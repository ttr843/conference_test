<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Admin panel</title>
</head>
<body>
<div>
    <#if users??>
        <#list users as user>
            <div>
                <h4>${user.getFirstName()}</h4>
                <h4>${user.getLastName()}</h4>
                <h4>${user.getEmail()}</h4>
                <form method="post" action="/admin/delete/${user.getId()}">
                    <button type="submit">delete</button>
                </form>
                <form method="post" action="/admin/role/${user.getId()}">
                    <button>change role</button>
                </form>
            </div>
        </#list>
    </#if>
</div>
</body>
</html>