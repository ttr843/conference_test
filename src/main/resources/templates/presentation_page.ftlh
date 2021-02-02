<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Presentation</title>
</head>
<body>
<div>
    <#if presentations??>
        <#list presentations as pres>
            <div>
                <h4>${pres.getId()}</h4>
                <h4>${pres.getTitle()}</h4>
                <form method="post" action="/presentation/delete/${pres.getId()}">
                    <button type="submit">delete</button>
                </form>
            </div>
        </#list>
    </#if>
</div>
<h1>Create presentation</h1>
<div>
    <form action="/presentation" method="post">
        <input name="title" placeholder="write title">
        <input type="submit" value="publish">
    </form>
</div>
<h1>Add your presentation to schedule</h1>
<form action="/addSchedule" method="post">
    <input name="presentationId" placeholder="write presentationId">
    <input name="roomNumber" placeholder="write room number">
    <input name="startTime" placeholder="write start time">
    <input name="endTime" placeholder="write end time">
    <input type="submit" value="add presentation">
</form>
</body>
</html>