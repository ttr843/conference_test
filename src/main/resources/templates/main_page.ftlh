<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Main</title>
</head>
<body>
    <div>
        <#if map??>
            <#list map as key,value>
                <h2>Room number ${key}</h2>
                <#list value as val>
                   <div>
                       <h3>Название: ${val.getPresentation().getTitle()}</h3>
                       <h3>Начало: ${val.getStartTime().toString()}</h3>
                       <h3>Конец: ${val.getEndTime().toString()}</h3>
                   </div>
                </#list>
            </#list>
        </#if>
    </div>
</body>
</html>
