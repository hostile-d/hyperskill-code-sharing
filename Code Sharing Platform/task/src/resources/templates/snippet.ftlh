<html lang="en">
<head>
    <title>Code</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="stylesheet" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
</head>
<body>
    <div class="container">
        <span id="load_date" class="badge bg-secondary mb-3 mt-3">${snippet.date}</span>
        <#if snippet.viewsRestriction>
          <p><span id="views_restriction">${snippet.views}</span> more views allowed</p>
        </#if>
        <#if snippet.timeRestriction>
          <p>The code will be available for <span id="time_restriction">${snippet.time}</span> seconds</p>
        </#if>
        <pre id="code_snippet" class="shadow p-3 mb-5 bg-body rounded"><code>${snippet.code}</code></pre>
    </div>
<script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
<script>hljs.initHighlightingOnLoad();</script>
</body>
</html>