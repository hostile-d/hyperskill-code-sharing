<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <style>
        .hidden {
            display: none;
        }
    </style>
</head>
<body>
    <div class="container">
        <form id="form">
            <div class="form-group">
                <label for="code_snippet">Code snippet</label>
                <textarea id="code_snippet" placeholder="Code snippet" class="form-control mb-3"></textarea>
            </div>
            <div class="form-group">
                <label for="code_snippet">Time restriction</label>
                <input id="time_restriction" placeholder="Time restriction" type="text"/>
            </div>
            <div class="form-group">
                <label for="code_snippet">Views times amount restriction</label>
                <input id="views_restriction" placeholder="Views times amount restriction" type="text"/>
            </div>
            <button id="send_snippet" type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
    <div class="container">
        <p id="result" class="hidden">Code snippet <span id="id"></span> was saved.</p>
    </div>
    <script>
        function send(event) {
            fetch('/api/code/new', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    "code": document.getElementById("code_snippet").value,
                    "time": parseInt(document.getElementById("time_restriction").value) || 0,
                    "views": parseInt(document.getElementById("views_restriction").value) || 0,
                }),
            })
            .then((response) => response.json())
            .then((data) => {
                document.getElementById("result").classList.remove("hidden");
                document.getElementById("id").innerText = data.id;
            })
            .catch((error) => {
                document.getElementById("result").classList.add("hidden");
            });
        }

        const form = document.getElementById("form");
        form.addEventListener("submit", (event) => {
            event.preventDefault();
            send();
        });
    </script>
</body>
</html>