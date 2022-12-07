<html>
<head>
    <title>Latest</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <h1>Latest</h1>
        <#list snippets as snippet>
            <span id="load_date" class="badge bg-secondary mb-3">${snippet.date}</span>
            <pre id="code_snippet" class="shadow p-3 mb-5 bg-body rounded">${snippet.code}</pre>
        </#list>
    </div>
</body>
</html>